package com.ulink.ulink.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.ulink.ulink.R
import com.ulink.ulink.textChangedListener
import kotlinx.android.synthetic.main.fragment_authentication.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"
private const val ARG_PARAM4 = "param4"
private const val ARG_PARAM5 = "param5"

class AuthenticationFragment : Fragment() {
    private var university: String = ""
    private var major: String = ""
    private var year: String = ""
    private var advertisement: String = ""
    private var referral: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            university = it.getString(ARG_PARAM1).toString()
            major = it.getString(ARG_PARAM2).toString()
            year = it.getString(ARG_PARAM3).toString()
            advertisement = it.getString(ARG_PARAM4).toString()
            referral = it.getString(ARG_PARAM5).toString()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_authentication, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var gender = ""
        btn_next.setOnClickListener{
            (activity as RegisterActivity?)!!.replaceFragment(RegisterFragment.newInstance(university, major, year, advertisement, referral, et_name.text.toString(), gender, et_number.text.toString()))
        }

        btn_back.setOnClickListener{
            (activity as RegisterActivity?)!!.finishFragment(this)
        }

        et_number.textChangedListener {
            buttonSelector(btn_send, et_number)
        }

        et_authentication_number.textChangedListener {
            buttonSelector(btn_authentication_check, et_authentication_number)
        }

        btn_female.setOnClickListener{
            btn_male.isChecked=false
            btn_gender_nothing.isChecked=false
            gender = "female"
        }

        btn_male.setOnClickListener{
            btn_female.isChecked=false
            btn_gender_nothing.isChecked=false
            gender = "male"
        }

        btn_gender_nothing.setOnClickListener{
            btn_female.isChecked=false
            btn_male.isChecked=false
            gender = "nothing"
        }

    }

        companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String, param3: String, param4: String, param5: String) =
            AuthenticationFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                    putString(ARG_PARAM3, param3)
                    putString(ARG_PARAM4, param4)
                    putString(ARG_PARAM5, param5)
                }
            }
    }
}