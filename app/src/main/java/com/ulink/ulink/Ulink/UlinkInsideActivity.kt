package com.ulink.ulink.Ulink

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ulink.ulink.R
import kotlinx.android.synthetic.main.activity_ulink_inside_fragment.*

class UlinkInsideActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ulink_inside_fragment)


        val ulinkInsideAdapter =
            UlinkInsideAdapter(supportFragmentManager)
        vp_ulink_inside.adapter = ulinkInsideAdapter
        tablayout_ulink_inside.setupWithViewPager(vp_ulink_inside)

    }
}