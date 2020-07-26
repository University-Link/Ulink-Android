package com.example.ulink.register

import android.graphics.Color
import android.widget.Button
import android.widget.EditText
import com.example.ulink.R

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
