package com.ulink.ulink.utils

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.LinearLayout

class TouchInterceptLinearLayout(context: Context,attributeSet: AttributeSet) : LinearLayout(context, attributeSet) {

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return true
    }
}