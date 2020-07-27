package com.ulink.ulink

import android.view.View
import android.widget.Button
import android.widget.EditText

fun Button.textResetButton(ed : EditText) {
    this.visibility = View.INVISIBLE
    this.setOnClickListener(){
        if (ed.text.toString() != null) ed.setText("")
    }

    ed.textChangedListener{
        if(ed.text.isNullOrBlank()) this.visibility = View.INVISIBLE
        else this.visibility = View.VISIBLE
    }
}