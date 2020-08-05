package com.ulink.ulink.Activity

import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import com.ulink.ulink.R
import com.ulink.ulink.adapter.FAQExpandableAdapter
import kotlinx.android.synthetic.main.activity_f_a_q.*

class FAQActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_f_a_q)


        val mAdapter = FAQExpandableAdapter(this)
        ev_faq.setAdapter(mAdapter)
        val size = Point()
        windowManager.defaultDisplay.getSize(size)
        val width = size.x
        ev_faq.setIndicatorBoundsRelative(width-dptopx(50).toInt() ,width)

    }

    fun dptopx(dp: Int): Float {
        val metrics = resources.displayMetrics
        return dp * ((metrics.densityDpi.toFloat()) / DisplayMetrics.DENSITY_DEFAULT)
    }

}