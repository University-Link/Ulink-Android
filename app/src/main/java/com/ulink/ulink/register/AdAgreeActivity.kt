package com.ulink.ulink.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ulink.ulink.R
import kotlinx.android.synthetic.main.activity_ad_agree.*

class AdAgreeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ad_agree)

        btn_back.setOnClickListener{
            finish()
        }
    }
}