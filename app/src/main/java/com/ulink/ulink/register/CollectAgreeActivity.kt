package com.ulink.ulink.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ulink.ulink.R
import kotlinx.android.synthetic.main.activity_collect_agree.*

class CollectAgreeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collect_agree)

        val prev = intent.getStringExtra("prevView")

        if(prev=="myPage") {
            layout_communicate_rule.visibility = View.GONE
            tv_title.text = "개인정보 처리방침"
        }

        btn_back.setOnClickListener{
            finish()
        }
    }
}