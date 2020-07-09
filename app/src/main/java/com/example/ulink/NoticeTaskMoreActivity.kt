package com.example.ulink

import android.os.Bundle
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.ulink.TaskNoticeRecycler.TaskNoticeAdapter
import com.example.ulink.TaskNoticeRecycler.TaskNoticeData
import kotlinx.android.synthetic.main.activity_notice.*
import kotlinx.android.synthetic.main.activity_schedule.*
import kotlinx.android.synthetic.main.toolbar_notice_class_more.*

class NoticeTaskMoreActivity : AppCompatActivity(){
    lateinit var TaskNoticeAdapter : TaskNoticeAdapter
    val datas1: MutableList<TaskNoticeData> = mutableListOf<TaskNoticeData>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice_task_more)

        btn_back.setOnClickListener{
            finish()
        }

        TaskNoticeAdapter = TaskNoticeAdapter(this)
        rv_task_notice.adapter = TaskNoticeAdapter
        loadDatas1()

    }
    private fun loadDatas1() {
        datas1.apply {
            add(
                TaskNoticeData(
                    StartDate = 5,
                    EndDate = 13,
                    TaskName = "연습문제 풀기",
                    StartTime = "13:00",
                    EndTime = "14:00"
                )
            )
            add(
                TaskNoticeData(
                    StartDate = 5,
                    EndDate = 13,
                    TaskName = "연습문제 풀기",
                    StartTime = "13:00",
                    EndTime = "14:00"
                )
            )
            add(
                TaskNoticeData(
                    StartDate = 5,
                    EndDate = 13,
                    TaskName = "연습문제 풀기",
                    StartTime = "13:00",
                    EndTime = "14:00"
                )
            )
            add(
                TaskNoticeData(
                    StartDate = 5,
                    EndDate = 13,
                    TaskName = "연습문제 풀기",
                    StartTime = "13:00",
                    EndTime = "14:00"
                )
            )


        }
        TaskNoticeAdapter.datas = datas1
        TaskNoticeAdapter.notifyDataSetChanged()
    }
}
