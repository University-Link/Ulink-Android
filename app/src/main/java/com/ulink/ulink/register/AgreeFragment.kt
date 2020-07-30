package com.ulink.ulink.register

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ulink.ulink.R
import kotlinx.android.synthetic.main.fragment_agree.*
import kotlinx.android.synthetic.main.fragment_agree.btn_back
import kotlinx.android.synthetic.main.fragment_agree.btn_next
import kotlinx.android.synthetic.main.fragment_major.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"

class AgreeFragment : Fragment() {
    private var university: String = ""
    private var major: String = ""
    private var year: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            university = it.getString(ARG_PARAM1).toString()
            major = it.getString(ARG_PARAM2).toString()
            year = it.getString(ARG_PARAM3).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_agree, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        btn_next.setOnClickListener{
            if(btn_policy_agree.isChecked && btn_information_agree.isChecked)
                (activity as RegisterActivity?)!!.replaceFragment(AuthenticationFragment.newInstance(university, major, year, btn_advertise_agree.isChecked.toString(), btn_referral_agree.isChecked.toString()))
        }

        btn_back.setOnClickListener() {
            (activity as RegisterActivity?)!!.finishFragment(this)
        }

        btn_all_agree.setOnClickListener{
            if(!btn_policy_agree.isChecked or !btn_information_agree.isChecked or !btn_advertise_agree.isChecked or !btn_referral_agree.isChecked){
                btn_policy_agree.isChecked=true
                btn_information_agree.isChecked=true
                btn_advertise_agree.isChecked=true
                btn_referral_agree.isChecked=true
            }
            else{
                btn_policy_agree.isChecked=false
                btn_information_agree.isChecked=false
                btn_advertise_agree.isChecked=false
                btn_referral_agree.isChecked=false
            }
        }

        btn_policy_agree.setOnClickListener {
            btnNextSelector(btn_information_agree, btn_policy_agree, btn_next)
            allAgree(btn_policy_agree, btn_information_agree, btn_advertise_agree, btn_referral_agree, btn_all_agree)
        }
        btn_information_agree.setOnClickListener{
            btnNextSelector(btn_information_agree, btn_policy_agree, btn_next)
            allAgree(btn_policy_agree, btn_information_agree, btn_advertise_agree, btn_referral_agree, btn_all_agree)
        }

        btn_advertise_agree.setOnClickListener {
            allAgree(btn_policy_agree, btn_information_agree, btn_advertise_agree, btn_referral_agree, btn_all_agree)
        }

        btn_referral_agree.setOnClickListener{
            allAgree(btn_policy_agree, btn_information_agree, btn_advertise_agree, btn_referral_agree, btn_all_agree)
        }
    }

        companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String, param3: String) =
            AgreeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                    putString(ARG_PARAM2, param3)
                }
            }
    }
}