package com.example.ulink.timetable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ulink.R
import com.example.ulink.repository.Subject
import com.example.ulink.showToast
import kotlinx.android.synthetic.main.activity_direct_type_time_table.*
import kotlinx.android.synthetic.main.toolbar_direct_time_table.*

class TimeTableDirectTypeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_direct_type_time_table)
//        val subjectList = intent.getParcelableArrayListExtra<Subject>("subjects")



        btn_check.setOnClickListener() {
            if (et_title.text.toString() == "") showToast("제목을 설정해주세요.")
        }
    }
}