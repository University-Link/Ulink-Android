package com.ulink.ulink.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ulink.ulink.R
import kotlinx.android.synthetic.main.activity_third_agree.*

class ThirdAgreeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third_agree)

        btn_back.setOnClickListener{
            finish()
        }
    }
}