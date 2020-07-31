package com.ulink.ulink.register

import android.text.InputFilter
import android.widget.EditText
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
