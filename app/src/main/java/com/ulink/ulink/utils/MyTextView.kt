package com.ulink.ulink.utils

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView


class MyTextView @JvmOverloads constructor(context : Context, attrs: AttributeSet? = null, defStyleAttr : Int = 0) : androidx.appcompat.widget.AppCompatTextView(context, attrs, defStyleAttr) {

    override fun setText(text: CharSequence, type: BufferType) {
        super.setText(text.toString().replace(" ", "\u00A0"), type)
    }

}