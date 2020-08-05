package com.ulink.ulink.register

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ulink.ulink.R
import com.ulink.ulink.utils.DialogBuilder
import kotlinx.android.synthetic.main.fragment_agree.*
import kotlinx.android.synthetic.main.fragment_agree.btn_back
import kotlinx.android.synthetic.main.fragment_agree.btn_next

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AgreeFragment : Fragment() {
    private var majorIdx: String = ""
    private var studentNumber: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            majorIdx = it.getString(ARG_PARAM1).toString()
            studentNumber = it.getString(ARG_PARAM2).toString()
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
                (activity as RegisterActivity?)!!.replaceFragment(AuthenticationFragment.newInstance(majorIdx, studentNumber, btn_advertise_agree.isChecked.toString(), btn_referral_agree.isChecked.toString()))
            else{
                DialogBuilder().apply {
                    build(view.context)
                    setContent(getString(R.string.agree_popup))
                    setClickListener {
                        dismiss()
                    }
                    show()
                }
            }
        }

        btn_back.setOnClickListener {
            (activity as RegisterActivity?)!!.finishFragment(this)
        }

        btn_all_agree.setOnClickListener{
            if(!btn_policy_agree.isChecked or !btn_information_agree.isChecked or !btn_advertise_agree.isChecked or !btn_referral_agree.isChecked){
                btn_policy_agree.isChecked=true
                btn_information_agree.isChecked=true
                btn_advertise_agree.isChecked=true
                btn_referral_agree.isChecked=true
                btnAgreeSelector(btn_information_agree, btn_policy_agree, btn_next)
            }
            else{
                btn_policy_agree.isChecked=false
                btn_information_agree.isChecked=false
                btn_advertise_agree.isChecked=false
                btn_referral_agree.isChecked=false
                btnAgreeSelector(btn_information_agree, btn_policy_agree, btn_next)
            }
        }

        btn_policy_agree.setOnClickListener {
            Log.d("check", majorIdx+" "+studentNumber)
            btnAgreeSelector(btn_information_agree, btn_policy_agree, btn_next)
            allAgree(btn_policy_agree, btn_information_agree, btn_advertise_agree, btn_referral_agree, btn_all_agree)
        }
        btn_information_agree.setOnClickListener{
            btnAgreeSelector(btn_information_agree, btn_policy_agree, btn_next)
            allAgree(btn_policy_agree, btn_information_agree, btn_advertise_agree, btn_referral_agree, btn_all_agree)
        }

        btn_advertise_agree.setOnClickListener {
            allAgree(btn_policy_agree, btn_information_agree, btn_advertise_agree, btn_referral_agree, btn_all_agree)
        }

        btn_referral_agree.setOnClickListener{
            allAgree(btn_policy_agree, btn_information_agree, btn_advertise_agree, btn_referral_agree, btn_all_agree)
        }

        btn_policy_more.setOnClickListener{
            val intent = Intent(view.context, ServiceAgreeActivity::class.java)
            startActivity(intent)
        }

        btn_information_more.setOnClickListener{
            val intent = Intent(view.context, CollectAgreeActivity::class.java)
            startActivity(intent)
        }

        btn_advertise_more.setOnClickListener{
            val intent = Intent(view.context, ServiceAgreeActivity::class.java)
            startActivity(intent)
        }

        btn_referral_more.setOnClickListener{
            val intent = Intent(view.context, ServiceAgreeActivity::class.java)
            startActivity(intent)
        }
    }

        companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AgreeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}