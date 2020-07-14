package com.example.ulink

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.ulink.ScheduleRecycler.ScheduleItemData
import kotlinx.android.synthetic.main.activity_class_notice.*
import kotlinx.android.synthetic.main.calendar_item.*
import kotlinx.android.synthetic.main.toolbar_schedule_notice.*

class ScheduleNoticeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class_notice)

        var scheduleItemData = intent.getParcelableExtra<ScheduleItemData>("scheduleItemData")
        tv_schedule_notice_toolbar.text = scheduleItemData.category+"공지" //툴바타이틀

        if(scheduleItemData.startTime!="" && scheduleItemData.endTime!="") //시간
            tv_schedule_notice_time_content.text = scheduleItemData.startTime + " ~ " + scheduleItemData.endTime
        else
            tv_schedule_notice_time_content.text = "시간정보없음"

        var scheduleDate = scheduleItemData.date.split("-")
        tv_schedule_notice_date_content.text = scheduleDate[0]+"년 "+scheduleDate[1]+"월 "+scheduleDate[2]+"일" //날짜

        tv_schedule_notice_title.text = scheduleItemData.content //제목

        tv_schedule_notice_memo_content.text = scheduleItemData.memo //메모

        btn_back.setOnClickListener() {
            finish()
        }

        btn_edit.setOnClickListener() {
            val intent = Intent(this, NoticeAddActivity::class.java)
            intent.putExtra("scheduleItemData", scheduleItemData)
            startActivityForResult(intent,100)
        }

        btn_delete.setOnClickListener(){
            // TODO 삭제 통신
        }
    }
}

