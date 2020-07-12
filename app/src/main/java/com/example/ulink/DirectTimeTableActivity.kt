package com.example.ulink

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_direct_time_table.*
import kotlinx.android.synthetic.main.toolbar_direct_time_table.*

class DirectTimeTableActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_direct_time_table)

        btn_check.setOnClickListener() {
            if (et_title.text.toString() == "") showToast("제목을 설정해주세요.")
        }
    }
}