package com.ulink.ulink.register

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import com.ulink.ulink.R
import com.ulink.ulink.repository.*
import com.ulink.ulink.textChangedListener
import com.ulink.ulink.utils.DialogBuilder
import kotlinx.android.synthetic.main.activity_change_password.*
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.btn_back
import kotlinx.android.synthetic.main.fragment_register.et_id
import kotlinx.android.synthetic.main.fragment_register.et_password
import kotlinx.android.synthetic.main.fragment_register.et_password_check
import kotlinx.android.synthetic.main.fragment_register.tv_password_warning
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"
private const val ARG_PARAM4 = "param4"
private const val ARG_PARAM5 = "param5"
private const val ARG_PARAM6 = "param6"
private const val ARG_PARAM7 = "param7"

class RegisterFragment : Fragment() {
    private var majorIdx: String = ""
    private var studentNumber: String = ""
    private var adAgree: String = ""
    private var thirdAgree: String = ""
    private var name: String = ""
    private var gender: String = ""
    private var phoneNumber: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            majorIdx = it.getString(ARG_PARAM1).toString()
            studentNumber = it.getString(ARG_PARAM2).toString()
            adAgree = it.getString(ARG_PARAM3).toString()
            thirdAgree = it.getString(ARG_PARAM4).toString()
            name = it.getString(ARG_PARAM5).toString()
            gender = it.getString(ARG_PARAM6).toString()
            phoneNumber = it.getString(ARG_PARAM7).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity!!.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        return inflater.inflate(R.layout.fragment_register, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var idCheck = false
        var idWarningCheck = false
        var nicknameCheck = false
        var nicknameWarningCheck = false
        var pwWarningCheck = false
        var nextCheck = false
        var agreeAd = 0
        var agreeThird = 0

        btn_next.setOnClickListener {
            if(nextCheck) {
                if (adAgree.equals("true")) agreeAd = 1
                else agreeAd = 0
                if (thirdAgree.equals("true")) agreeThird = 1
                else agreeThird = 0

                var requestRegister =
                    RequestRegister(
                        id = et_id.text.toString(),
                        password = et_password.text.toString(),
                        name = name,
                        nickname = et_nickname.text.toString(),
                        majorIdx = majorIdx.toInt(),
                        gender = gender,
                        studentNumber = studentNumber.toInt(),
                        phoneNumber = phoneNumber,
                        agreeAd = agreeAd,
                        agreeThird = agreeThird
                    )
                RetrofitService.service.register(requestRegister).
                enqueue(object : Callback<ResponseRegister> {
                    override fun onFailure(call: Call<ResponseRegister>, t: Throwable) {
                    }

                    override fun onResponse(
                        call: Call<ResponseRegister>,
                        response: Response<ResponseRegister>
                    ) {
                        response.body()?.let {
                            if (it.status == 201) {
                                (activity as RegisterActivity?)!!.replaceFragment(FinishFragment.newInstance(et_id.text.toString(), et_password.text.toString()))
                            }
                        }
                    }
                })
            }
        }

        btn_back.setOnClickListener {
            (activity as RegisterActivity?)!!.finishFragment(this)
        }

        //et_id.setFilters("^[a-zA-Z0-9]+$")
        //et_password.setFilters("^[a-zA-Z0-9!@#\$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?~`]+$")
        //et_password_check.setFilters("^[a-zA-Z0-9!@#\$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?~`]+$")
        //et_nickname.setFilters("^[a-zA-Z0-9가-힣]+$")

        et_id.textChangedListener {
            et_id.warningCheck("^[a-zA-Z0-9]+$", id_warning1, id_warning2)
            idWarningCheck = btnFilterSelector(btn_id_same_check, et_id, "^[a-zA-Z0-9]+$", 8)
        }

        et_nickname.textChangedListener {
            et_nickname.warningCheck("^[a-zA-Z0-9가-힣]+$", nickname_warning1, nickname_warning2)
            nicknameWarningCheck = btnFilterSelector(btn_nickname_same_check, et_nickname, "^[a-zA-Z0-9가-힣]+$", 2)
            nextCheck = btnRegisterSelector(img_id_success, img_nickname_success, et_password, et_password_check, btn_next)
        }

        et_password.textChangedListener {
            pwWarningCheck = et_password.warningPasswordCheck("^[a-zA-Z0-9!@#\$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?~`]+$", tv_password_warning)
            passwordCheck(et_password, et_password_check, tv_password_different)
            nextCheck = btnRegisterSelector(img_id_success, img_nickname_success, et_password, et_password_check, btn_next)
        }

        et_password_check.textChangedListener {
            passwordCheck(et_password, et_password_check, tv_password_different)
            nextCheck = btnRegisterSelector(img_id_success, img_nickname_success, et_password, et_password_check, btn_next)
        }

        et_id.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL && idCheck) {
                btn_next.setBackgroundResource(R.drawable.signup_btn_next_unactivated)
                btn_next.setTextColor(Color.parseColor("#989898"))
                img_id_success.visibility=View.INVISIBLE
                btn_id_same_check.visibility=View.VISIBLE
                idWarningCheck = btnFilterSelector(btn_id_same_check, et_id, "^[a-zA-Z0-9]+$", 8)
                idCheck = false
                nextCheck = false
            }
            false
        }

        et_nickname.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL && nicknameCheck) {
                btn_next.setBackgroundResource(R.drawable.signup_btn_next_unactivated)
                btn_next.setTextColor(Color.parseColor("#989898"))
                img_nickname_success.visibility=View.INVISIBLE
                btn_nickname_same_check.visibility=View.VISIBLE
                nicknameWarningCheck = btnFilterSelector(btn_nickname_same_check, et_nickname, "^[a-zA-Z0-9가-힣]+$", 2)
                nicknameCheck = false
                nextCheck = false
            }
            false
        }

        et_password.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL) {
                btn_next.setBackgroundResource(R.drawable.signup_btn_next_unactivated)
                btn_next.setTextColor(Color.parseColor("#989898"))
                pwWarningCheck = et_password.warningPasswordCheck("^[a-zA-Z0-9!@#\$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?~`]+$", tv_password_warning)
                nextCheck = btnRegisterSelector(img_id_success, img_nickname_success, et_password, et_password_check, btn_next)
            }
            false
        }

        et_password_check.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL) {
                btn_next.setBackgroundResource(R.drawable.signup_btn_next_unactivated)
                btn_next.setTextColor(Color.parseColor("#989898"))
                pwWarningCheck = et_password.warningPasswordCheck("^[a-zA-Z0-9!@#\$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?~`]+$", tv_password_warning)
                nextCheck = btnRegisterSelector(img_id_success, img_nickname_success, et_password, et_password_check, btn_next)
            }
            false
        }

        btn_id_same_check.setOnClickListener {
            if(idWarningCheck) {
                RetrofitService.service.getIdSameCheck(et_id.text.toString()).
                enqueue(object : Callback<ResponseIdCheck> {
                    override fun onFailure(call: Call<ResponseIdCheck>, t: Throwable) {
                    }

                    override fun onResponse(
                        call: Call<ResponseIdCheck>,
                        response: Response<ResponseIdCheck>
                    ) {
                        response.body()?.let {
                            if (it.status == 200) {
                                idCheck = true
                                checkSelector(btn_id_same_check, img_id_success, idCheck)
                                DialogBuilder().apply {
                                    build(view.context)
                                    setContent(getString(R.string.id))
                                    setClickListener {
                                        dismiss()
                                    }
                                    show()
                                }
                                nextCheck = btnRegisterSelector(img_id_success, img_nickname_success, et_password, et_password_check, btn_next)
                            }
                        } ?: let {
                            idCheck = false
                            checkSelector(btn_id_same_check, img_id_success, idCheck)
                            DialogBuilder().apply {
                                build(view.context)
                                setContent(getString(R.string.id_fail))
                                setClickListener {
                                    dismiss()
                                }
                                show()
                            }
                        }
                        nextCheck = btnRegisterSelector(img_id_success, img_nickname_success, et_password, et_password_check, btn_next)
                    }
                })
            }
        }

        btn_nickname_same_check.setOnClickListener {
            if(nicknameWarningCheck) {
                var requestNicknameCheck = RequestNicknameCheck(nickname=et_nickname.text.toString(), majorIdx = majorIdx.toInt())
                RetrofitService.service.getNicknameSameCheck(requestNicknameCheck).
                enqueue(object : Callback<ResponseNicknameCheck> {
                    override fun onFailure(call: Call<ResponseNicknameCheck>, t: Throwable) {
                    }

                    override fun onResponse(
                        call: Call<ResponseNicknameCheck>,
                        response: Response<ResponseNicknameCheck>
                    ) {
                        response.body()?.let {
                            if (it.status == 200) {
                                nicknameCheck = true
                                checkSelector(btn_nickname_same_check, img_nickname_success, nicknameCheck)
                                DialogBuilder().apply {
                                    build(view.context)
                                    setContent(getString(R.string.nickname))
                                    setClickListener {
                                        dismiss()
                                    }
                                    show()
                                }
                            nextCheck = btnRegisterSelector(img_id_success, img_nickname_success, et_password, et_password_check, btn_next)
                            }
                        } ?: let {
                            nicknameCheck = false
                            checkSelector(btn_nickname_same_check, img_nickname_success, nicknameCheck)
                            DialogBuilder().apply {
                                build(view.context)
                                setContent(getString(R.string.nickname_fail))
                                setClickListener {
                                    dismiss()
                                }
                                show()
                            }
                        }
                        nextCheck = btnRegisterSelector(img_id_success, img_nickname_success, et_password, et_password_check, btn_next)
                    }
                })
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String, param3: String, param4: String, param5: String, param6: String, param7: String) =
            RegisterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                    putString(ARG_PARAM3, param3)
                    putString(ARG_PARAM4, param4)
                    putString(ARG_PARAM5, param5)
                    putString(ARG_PARAM6, param6)
                    putString(ARG_PARAM7, param7)
                }
            }
    }
}

