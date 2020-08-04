package com.ulink.ulink.register

import android.graphics.Color
import android.text.InputFilter
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.ulink.ulink.R
import kotlinx.android.synthetic.main.fragment_register.*
import java.util.regex.Pattern

fun EditText.setFilters (regex : String){
    this.filters += InputFilter { source, start, end, dest, dstart, dend ->
        val ps: Pattern =
            Pattern.compile(regex)
        if (!ps.matcher(source).matches()) {
            ""
        } else null
    }
}

fun EditText.filterCheck(regex : String) : Boolean {
    val strCheck = this.text.toString()
    val result = Pattern.matches(regex, strCheck)

    return result || this.text.toString() == ""
}

fun EditText.warningCheck(regex : String, warning : TextView, warning2 : TextView) : Boolean {
    if(this.filterCheck(regex))
    {
        warning.setTextColor(Color.parseColor("#989898"))
        warning2.setTextColor(Color.parseColor("#989898"))
        return true
    }
    else
    {
        warning.setTextColor(Color.parseColor("#fd4165"))
        warning2.setTextColor(Color.parseColor("#fd4165"))
        return false
    }
}

fun EditText.warningPasswordCheck(regex : String, warning : TextView) : Boolean {
    if(this.filterCheck(regex))
    {
        warning.setTextColor(Color.parseColor("#989898"))
        return true
    }
    else
    {
        warning.setTextColor(Color.parseColor("#fd4165"))
        return false
    }
}

fun EditText.passwordNextCheck(min : Int) : Boolean {
    var regex = "^[a-zA-Z0-9!@#\$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?~`]+$"
    var result = this.filterCheck(regex) && this.text.toString().length>=min
    return result
}

fun passwordCheck(et_password : EditText, et_password_check : EditText, tv_password_different : TextView){
    if(et_password.text.toString() != et_password_check.text.toString())
        tv_password_different.visibility= View.VISIBLE
    else
        tv_password_different.visibility= View.GONE
}