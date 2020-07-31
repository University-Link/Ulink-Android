package com.ulink.ulink.register

import android.os.Bundle
import android.text.InputFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ulink.ulink.R
import com.ulink.ulink.textChangedListener
import kotlinx.android.synthetic.main.fragment_register.*
import java.util.regex.Pattern


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"
private const val ARG_PARAM4 = "param4"
private const val ARG_PARAM5 = "param5"
private const val ARG_PARAM6 = "param6"
private const val ARG_PARAM7 = "param7"
private const val ARG_PARAM8 = "param8"

class RegisterFragment : Fragment() {
    private var university: String = ""
    private var major: String = ""
    private var year: String = ""
    private var advertisement: String = ""
    private var referral: String = ""
    private var name: String = ""
    private var gender: String = ""
    private var phoneNumber: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            university = it.getString(ARG_PARAM1).toString()
            major = it.getString(ARG_PARAM2).toString()
            year = it.getString(ARG_PARAM3).toString()
            advertisement = it.getString(ARG_PARAM4).toString()
            referral = it.getString(ARG_PARAM5).toString()
            name = it.getString(ARG_PARAM6).toString()
            gender = it.getString(ARG_PARAM7).toString()
            phoneNumber = it.getString(ARG_PARAM8).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_next.setOnClickListener(){
            (activity as RegisterActivity?)!!.replaceFragment(FinishFragment.newInstance(et_id.text.toString(), et_password.text.toString()))
        }

        btn_back.setOnClickListener() {
            (activity as RegisterActivity?)!!.finishFragment(this)
        }

        et_id.setFilters("^[a-zA-Z0-9]+$")
        et_password.setFilters("^[a-zA-Z0-9!@#\$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?~`]+$")
        et_password_check.setFilters("^[a-zA-Z0-9!@#\$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?~`]+$")
        et_nickname.setFilters("^[a-zA-Z0-9ㄱ-ㅎㅏ-ㅣ가-힣]+$")

        et_id.textChangedListener {
            if(et_id.text.toString().length>=8)
                buttonSelector(btn_id_same_check, et_id)
            else
                buttonSelector(btn_id_same_check, et_id)
        }

        et_nickname.textChangedListener {
            if(et_nickname.text.toString().length>=2)
                buttonSelector(btn_nickname_same_check, et_nickname)
            else
                buttonSelector(btn_nickname_same_check, et_nickname)
        }

        btn_id_same_check.setOnClickListener{
            //TODO 서버 통신(중복확인 성공) 후에 적용시키기(글자 수 설정)
            checkSelector(btn_id_same_check, et_id, img_id_success)
        }

        btn_nickname_same_check.setOnClickListener{
            checkSelector(btn_nickname_same_check, et_nickname, img_nickname_success)
        }

        et_password_check.textChangedListener {
            if(et_password.text.toString() != et_password_check.text.toString())
                tv_password_different.visibility=View.VISIBLE
            else
                tv_password_different.visibility=View.GONE
        }


    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String, param3: String, param4: String, param5: String, param6: String, param7: String, param8: String) =
            RegisterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                    putString(ARG_PARAM3, param3)
                    putString(ARG_PARAM4, param4)
                    putString(ARG_PARAM5, param5)
                    putString(ARG_PARAM6, param6)
                    putString(ARG_PARAM7, param7)
                    putString(ARG_PARAM8, param8)
                }
            }
    }
}

