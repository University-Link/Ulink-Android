package com.ulink.ulink.idPw

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.EditText
import com.ulink.ulink.R
import com.ulink.ulink.register.btnCheckSelector
import com.ulink.ulink.register.btnNextReset
import com.ulink.ulink.register.btnNextSelector
import com.ulink.ulink.register.setFilters
import com.ulink.ulink.repository.RequestPhoneAuthentication
import com.ulink.ulink.repository.ResponsePhoneAuthentication
import com.ulink.ulink.repository.RetrofitService
import com.ulink.ulink.textChangedListener
import com.ulink.ulink.utils.DialogBuilder
import kotlinx.android.synthetic.main.activity_find_id_pw.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FindIdPwActivity : AppCompatActivity() {
    var timer : CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_id_pw)

        var authenticationCode = 0
        var authentication = false

        btn_next.setOnClickListener{
            val intent = Intent(this, FindSuccessActivity::class.java)
            startActivity(intent)
        }

        btn_back.setOnClickListener{
            finish()
        }

        et_find_name.setFilters("^[a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣]+$")

        et_find_name.textChangedListener {
            if(et_find_name.text.toString()!="" && authentication)
                btn_next.btnNextSelector()
            else
                btn_next.btnNextReset()
        }

        et_number.textChangedListener {
            btnCheckSelector(btn_send, et_number)
            if(et_find_name.text.toString()!="" && authentication)
                btn_next.btnNextSelector()
            else
                btn_next.btnNextReset()
        }

        et_authentication_number.textChangedListener {
            btnCheckSelector(btn_authentication_check, et_authentication_number)
            if(et_find_name.text.toString()!="" && authentication)
                btn_next.btnNextSelector()
            else
                btn_next.btnNextReset()
        }

        btn_send.setOnClickListener {
            if (et_number.text.toString() != "" && !authentication && tv_authentication_time.visibility== View.INVISIBLE) {
                val body = RequestPhoneAuthentication(phoneNumber = et_number.text.toString())
                RetrofitService.service.phoneNumberAuthentication(body).
                enqueue(object : Callback<ResponsePhoneAuthentication> {
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
                                editTextFocus(et_number, et_authentication_number)
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
                        build(this@FindIdPwActivity)
                        setContent(getString(R.string.authentication))
                        setClickListener {
                            timer?.cancel()
                            dismiss()
                         }
                        show()
                    }
                    tv_authentication_time.visibility = View.INVISIBLE
                    authentication = true
                }
                else {
                    DialogBuilder().apply {
                        build(this@FindIdPwActivity)
                        setContent(getString(R.string.authentication_fail))
                        setClickListener {
                            dismiss()
                        }
                        show()
                    }
                    authentication = false
                }
            }

            if(et_find_name.text.toString()!="" && authentication)
                btn_next.btnNextSelector()
            else
                btn_next.btnNextReset()
        }

        btn_next.setOnClickListener {
            val intent = Intent(this, FindSuccessActivity::class.java)
            intent.putExtra("name", et_find_name.text.toString())
            intent.putExtra("id", "idididididid")
            startActivity(intent)
        }
    }

    fun startTimer() {

        timer?.cancel()

        timer = object : CountDownTimer(60 * 1000, 1000) {
            override fun onFinish() {
                btnCheckSelector(btn_send, et_number)
                et_authentication_number.setText("")
                editTextFocus(et_authentication_number, et_number)
                tv_authentication_time.visibility = View.INVISIBLE
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

    fun editTextFocus(et : EditText, et2 : EditText){
        et.isFocusable = false
        et.isFocusableInTouchMode = false
        et_number.clearFocus()
        et2.isFocusable = true
        et2.isFocusableInTouchMode = true
        et2.requestFocus()
    }
}