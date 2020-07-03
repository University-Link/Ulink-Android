package com.example.ulink

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ulink.ClassNoticeRecycler.ClassNoticeAdapter
import com.example.ulink.ClassNoticeRecycler.ClassNoticeData
import com.example.ulink.ClassRecycler.ClassAdapter
import com.example.ulink.ClassRecycler.ClassData
import kotlinx.android.synthetic.main.activity_notice.*

class NoticeActivity : AppCompatActivity(){
    lateinit var ClassNoticeAdapter : ClassNoticeAdapter
    val datas : MutableList<ClassNoticeData> = mutableListOf<ClassNoticeData>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice)

        ClassNoticeAdapter = ClassNoticeAdapter(this)
        rv_class_notice.adapter = ClassNoticeAdapter

        loadDatas()

    }

    private fun loadDatas() {
        datas.apply {
            add(
                ClassNoticeData(
                    StartDate = 5,
                    EndDate = 13,
                    NoticeName = "중간고사",
                    StartTime = "13:00",
                    EndTime = "14:00",
                    StartTask = "1장",
                    EndTask = "7장"

                )
            )
            add(
                ClassNoticeData(
                    StartDate = 5,
                    EndDate = 13,
                    NoticeName = "중간고사",
                    StartTime = "13:00",
                    EndTime = "14:00",
                    StartTask = "1장",
                    EndTask = "7장"

                )
            )
            add(
                ClassNoticeData(
                    StartDate = 5,
                    EndDate = 13,
                    NoticeName = "중간고사",
                    StartTime = "13:00",
                    EndTime = "14:00",
                    StartTask = "1장",
                    EndTask = "7장"

                )
            )
            add(
                ClassNoticeData(
                    StartDate = 5,
                    EndDate = 13,
                    NoticeName = "중간고사",
                    StartTime = "13:00",
                    EndTime = "14:00",
                    StartTask = "1장",
                    EndTask = "7장"

                )
            )
            add(
                ClassNoticeData(
                    StartDate = 5,
                    EndDate = 13,
                    NoticeName = "중간고사",
                    StartTime = "13:00",
                    EndTime = "14:00",
                    StartTask = "1장",
                    EndTask = "7장"

                )
            )
        }
        ClassNoticeAdapter.datas = datas
        ClassNoticeAdapter.notifyDataSetChanged()
    }
}