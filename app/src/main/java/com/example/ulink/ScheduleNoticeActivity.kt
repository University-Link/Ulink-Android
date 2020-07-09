package com.example.ulink

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.toolbar_schedulenotice.*

class ScheduleNoticeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class_notice)

        btn_back.setOnClickListener() {
            finish()
        }
    }
}