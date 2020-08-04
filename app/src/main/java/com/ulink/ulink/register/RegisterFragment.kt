package com.ulink.ulink.register

import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.ulink.ulink.R
import com.ulink.ulink.textChangedListener
import kotlinx.android.synthetic.main.fragment_register.*

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
    private var agreeAd: String = ""
    private var agreeThird: String = ""
    private var name: String = ""
    private var gender: String = ""
    private var phoneNumber: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            majorIdx = it.getString(ARG_PARAM1).toString()
            studentNumber = it.getString(ARG_PARAM2).toString()
            agreeAd = it.getString(ARG_PARAM3).toString()
            agreeThird = it.getString(ARG_PARAM4).toString()
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
        var nicknameCheck = false
        var nextCheck = false

        btn_next.setOnClickListener {
            (activity as RegisterActivity?)!!.replaceFragment(FinishFragment.newInstance(et_id.text.toString(), et_password.text.toString()))
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
            btnFilterSelector(btn_id_same_check, et_id, "^[a-zA-Z0-9]+$", 8)
        }

        et_nickname.textChangedListener {
            et_nickname.warningCheck("^[a-zA-Z0-9가-힣]+$", nickname_warning1, nickname_warning2)
            btnFilterSelector(btn_nickname_same_check, et_nickname, "^[a-zA-Z0-9가-힣]+$", 2)
        }

        et_password.textChangedListener {
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
                btnFilterSelector(btn_id_same_check, et_id, "^[a-zA-Z0-9]+$", 8)
            }
            false
        }

        et_nickname.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL && nicknameCheck) {
                btn_next.setBackgroundResource(R.drawable.signup_btn_next_unactivated)
                btn_next.setTextColor(Color.parseColor("#989898"))
                img_nickname_success.visibility=View.INVISIBLE
                btn_nickname_same_check.visibility=View.VISIBLE
                btnFilterSelector(btn_nickname_same_check, et_nickname, "^[a-zA-Z0-9가-힣]+$", 2)
            }
            false
        }

        et_password.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL) {
                btn_next.setBackgroundResource(R.drawable.signup_btn_next_unactivated)
                btn_next.setTextColor(Color.parseColor("#989898"))
                nextCheck = false
            }
            false
        }

        et_password_check.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL) {
                btn_next.setBackgroundResource(R.drawable.signup_btn_next_unactivated)
                btn_next.setTextColor(Color.parseColor("#989898"))
                nextCheck = false
            }
            false
        }

        btn_id_same_check.setOnClickListener {
            //TODO 서버 통신(중복확인 성공) 후에 적용시키기
            checkSelector(btn_id_same_check, img_id_success)
            nextCheck = btnRegisterSelector(img_id_success, img_nickname_success, et_password, et_password_check, btn_next)
        }

        btn_nickname_same_check.setOnClickListener {
            checkSelector(btn_nickname_same_check, img_nickname_success)
            nextCheck = btnRegisterSelector(img_id_success, img_nickname_success, et_password, et_password_check, btn_next)
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

