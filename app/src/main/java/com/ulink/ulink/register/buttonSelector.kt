package com.ulink.ulink.register

import android.graphics.Color
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import com.ulink.ulink.R
import kotlinx.android.synthetic.main.fragment_agree.*
import kotlinx.android.synthetic.main.fragment_register.*

fun btnCheckSelector(btn : Button, et : EditText){
    if (et.text.isNotEmpty()) {
        btn.setBackgroundResource(R.drawable.signup_btn_search_activated)
        btn.setTextColor(Color.parseColor("#ffffff"))
    }
    else {
        btn.setBackgroundResource(R.drawable.signup_btn_next_unactivated)
        btn.setTextColor(Color.parseColor("#989898"))
    }
}

fun btnFilterSelector(btn : Button, et : EditText, regex : String, min : Int) : Boolean {
    if (et.filterCheck(regex) && et.text.toString().length>=min) {
        btn.setBackgroundResource(R.drawable.signup_btn_search_activated)
        btn.setTextColor(Color.parseColor("#ffffff"))
        return true
    }
    else {
        btn.setBackgroundResource(R.drawable.signup_btn_next_unactivated)
        btn.setTextColor(Color.parseColor("#989898"))
        return false
    }
}

fun allAgree(btn_policy_agree: CheckBox, btn_information_agree:CheckBox, btn_advertise_agree:CheckBox, btn_referral_agree:CheckBox, btn_all_agree:CheckBox){
    btn_all_agree.isChecked = btn_policy_agree.isChecked && btn_information_agree.isChecked && btn_advertise_agree.isChecked && btn_referral_agree.isChecked
}

fun btnAgreeSelector(btn_information_agree: CheckBox, btn_policy_agree: CheckBox, btn_next: Button){
    if(btn_information_agree.isChecked && btn_policy_agree.isChecked) {
        btn_next.setBackgroundResource(R.drawable.signup_btn_next_activated)
        btn_next.setTextColor(Color.parseColor("#ffffff"))
    }
    else {
        btn_next.setBackgroundResource(R.drawable.signup_btn_next_unactivated)
        btn_next.setTextColor(Color.parseColor("#989898"))
    }
}

fun btnRegisterSelector(idCheck : ImageView, nicknameCheck : ImageView, et_pw : EditText, et_pw_check : EditText, btn_next : Button) : Boolean{
    if(idCheck.visibility==View.VISIBLE && nicknameCheck.visibility==View.VISIBLE &&
        et_pw.passwordNextCheck(8) && et_pw.text.toString() == et_pw_check.text.toString()){
        btn_next.btnNextSelector()
        return true
    }
    else
        return false
}

fun Button.btnNextSelector(){
    this.setBackgroundResource(R.drawable.signup_btn_next_activated)
    this.setTextColor(Color.parseColor("#ffffff"))
}

fun Button.btnNextReset(){
    this.setBackgroundResource(R.drawable.signup_btn_next_unactivated)
    this.setTextColor(Color.parseColor("#989898"))
}

fun checkSelector(btn : Button, img : ImageView, check : Boolean) {
    if (check){
        img.visibility = View.VISIBLE
        btn.visibility = View.INVISIBLE
    }
}