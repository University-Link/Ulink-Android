package com.ulink.ulink.register

import android.graphics.Color
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import com.ulink.ulink.R
import kotlinx.android.synthetic.main.fragment_agree.*

fun buttonSelector(btn : Button, et : EditText){
    if (et.text.isNotEmpty()) {
        btn.setBackgroundResource(R.drawable.signup_btn_search_activated)
        btn.setTextColor(Color.parseColor("#ffffff"))
    }
    else {
        btn.setBackgroundResource(R.drawable.signup_btn_next_unactivated)
        btn.setTextColor(Color.parseColor("#989898"))
    }
}

fun allAgree(btn_policy_agree: CheckBox, btn_information_agree:CheckBox, btn_advertise_agree:CheckBox, btn_referral_agree:CheckBox, btn_all_agree:CheckBox){
    btn_all_agree.isChecked = btn_policy_agree.isChecked && btn_information_agree.isChecked && btn_advertise_agree.isChecked && btn_referral_agree.isChecked
}

fun btnNextSelector(btn_information_agree: CheckBox, btn_policy_agree: CheckBox, btn_next: Button){
    if(btn_information_agree.isChecked && btn_policy_agree.isChecked) {
        btn_next.setBackgroundResource(R.drawable.signup_btn_next_activated)
        btn_next.setTextColor(Color.parseColor("#ffffff"))
    }
    else {
        btn_next.setBackgroundResource(R.drawable.signup_btn_next_unactivated)
        btn_next.setTextColor(Color.parseColor("#989898"))
    }
}