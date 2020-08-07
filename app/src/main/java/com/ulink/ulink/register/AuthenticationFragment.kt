package com.ulink.ulink.register

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.text.InputFilter
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.ulink.ulink.R
import com.ulink.ulink.repository.RequestPhoneAuthentication
import com.ulink.ulink.repository.ResponsePhoneAuthentication
import com.ulink.ulink.repository.RetrofitService
import com.ulink.ulink.textChangedListener
import com.ulink.ulink.utils.DialogBuilder
import kotlinx.android.synthetic.main.activity_school_certificate.*
import kotlinx.android.synthetic.main.fragment_authentication.*
import kotlinx.android.synthetic.main.fragment_authentication.btn_back
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"
private const val ARG_PARAM4 = "param4"

class AuthenticationFragment : Fragment() {
    private var majorIdx: String = ""
    private var studentNumber: String = ""
    private var agreeAd: String = ""
    private var agreeThird: String = ""
    var timer : CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            majorIdx = it.getString(ARG_PARAM1).toString()
            studentNumber = it.getString(ARG_PARAM2).toString()
            agreeAd = it.getString(ARG_PARAM3).toString()
            agreeThird = it.getString(ARG_PARAM4).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity!!.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        return inflater.inflate(R.layout.fragment_authentication, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var gender = ""

        var authenticationCode = 0
        var authentication = false
        var nextCheck = false

        btn_next.setOnClickListener{
            if(nextCheck)
                (activity as RegisterActivity?)!!.replaceFragment(RegisterFragment.newInstance(majorIdx, studentNumber, agreeAd, agreeThird, et_name.text.toString(), gender, et_number.text.toString()))
        }

        btn_back.setOnClickListener{
            (activity as RegisterActivity?)!!.finishFragment(this)
        }

        et_name.setFilters("^[a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣]+$")

        et_number.textChangedListener {
            btnCheckSelector(btn_send, et_number)
            nextCheck = btn_next.authenticatioBtnNextSelector(et_name, gender, authentication)
        }

        et_authentication_number.textChangedListener {
            btnCheckSelector(btn_authentication_check, et_authentication_number)
            nextCheck = btn_next.authenticatioBtnNextSelector(et_name, gender, authentication)
        }

        layout_missing_code.setOnClickListener{
            DialogBuilder().apply {
                build(view.context)
                setContent(getString(R.string.missing_cord))
                setClickListener {
                    dismiss()
                }
                show()
            }
        }

        btn_send.setOnClickListener {
            if (et_number.text.toString() != "" && !authentication) {
                val body = RequestPhoneAuthentication(phoneNumber = et_number.text.toString())
                RetrofitService.service.phoneNumberAuthentication(body).
                    enqueue(object : Callback<ResponsePhoneAuthentication>{
                        override fun onFailure(call: Call<ResponsePhoneAuthentication>, t: Throwable) {
                        }
                        override fun onResponse(
                            call: Call<ResponsePhoneAuthentication>,
                            response: Response<ResponsePhoneAuthentication>
                        ) {
                            response.body()?.let {
                                if (it.status == 200) {
                                    tv_will_send.visibility = View.VISIBLE
                                    tv_authentication_time.visibility = View.VISIBLE
                                    btn_send.setBackgroundResource(R.drawable.signup_btn_next_unactivated)
                                    btn_send.setTextColor(Color.parseColor("#989898"))
                                    et_authentication_number.requestFocus()
                                    startTimer()
                                    authenticationCode = it.data
                                }
                            }
                        }
                    })
                }
            }

        btn_authentication_check.setOnClickListener {
            if(authenticationCode!=0 && tv_authentication_time.visibility==View.VISIBLE) {
                if (authenticationCode.toString() == et_authentication_number.text.toString()) {
                    DialogBuilder().apply {
                        build(view.context)
                        setContent(getString(R.string.authentication))
                        setClickListener {
                            timer?.cancel()
                            dismiss()
                            nextCheck = btn_next.authenticatioBtnNextSelector(et_name, gender, authentication)
                        }
                        show()
                    }
                    tv_authentication_time.visibility = View.INVISIBLE
                    authentication = true
                }
                else {
                    DialogBuilder().apply {
                        build(view.context)
                        setContent(getString(R.string.authentication_fail))
                        setClickListener {
                            dismiss()
                        }
                        show()
                    }
                    authentication = false
                }
            }
        }

        btn_female.setOnClickListener{
            btn_male.isChecked=false
            btn_gender_nothing.isChecked=false
            gender = "f"
            nextCheck = btn_next.authenticatioBtnNextSelector(et_name, gender, authentication)
        }

        btn_male.setOnClickListener{
            btn_female.isChecked=false
            btn_gender_nothing.isChecked=false
            gender = "m"
            nextCheck = btn_next.authenticatioBtnNextSelector(et_name, gender, authentication)
        }

        btn_gender_nothing.setOnClickListener{
            btn_female.isChecked=false
            btn_male.isChecked=false
            gender = "x"
            nextCheck = btn_next.authenticatioBtnNextSelector(et_name, gender, authentication)
        }

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String, param3: String, param4: String) =
            AuthenticationFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                    putString(ARG_PARAM3, param3)
                    putString(ARG_PARAM4, param4)
            }
        }
    }

    fun startTimer() {

        timer?.cancel()

        timer = object : CountDownTimer(60 * 1000, 1000) {
            override fun onFinish() {
                btnCheckSelector(btn_send, et_number)
            }

            override fun onTick(millisUntilFinished: Long) {
                val count = millisUntilFinished / 1000
                if (count - count / 60 * 60 >= 10) {
                    tv_authentication_time.text = (count / 60).toString() + ":" + (count - count / 60 * 60)
                } else {
                    tv_authentication_time.text = (count / 60).toString() + ":0" + (count - count / 60 * 60)
                }
            }
        }.start()
    }
}