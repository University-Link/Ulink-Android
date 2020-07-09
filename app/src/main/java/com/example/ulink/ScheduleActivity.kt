package com.example.ulink

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import com.example.ulink.ScheduleRecycler.*
import kotlinx.android.synthetic.main.activity_class_notice.*
import kotlinx.android.synthetic.main.activity_schedule.*
import kotlinx.android.synthetic.main.schedule_day_item.*
import kotlinx.android.synthetic.main.schedule_item.*
import kotlinx.android.synthetic.main.toolbar_schedulenotice.*
import java.util.*


class ScheduleActivity : AppCompatActivity() {

    lateinit var scheduleDateAdapter: ScheduleDateAdapter
    val dateDatas = mutableListOf<ScheduleDateData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)

        scheduleDateAdapter = ScheduleDateAdapter(this)
        rv_schedule_date.adapter = scheduleDateAdapter

        btn_back.setOnClickListener() {
            finish()
        }

        loadDateDatas()
    }

    private fun loadDateDatas() {

        dateDatas.apply {
            for (i in 0 until 10) {
                add(
                    ScheduleDateData(
                        day = nowDay()+i,
                        date = nowDateCheck(i),
                        dday = ddayCheck(i)
                    )
                )
            }
        }
        scheduleDateAdapter.dateDatas = dateDatas
        scheduleDateAdapter.notifyDataSetChanged()
    }
}