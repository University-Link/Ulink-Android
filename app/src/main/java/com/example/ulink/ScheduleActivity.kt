package com.example.ulink

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ulink.ScheduleRecycler.*
import kotlinx.android.synthetic.main.activity_schedule.*
import java.util.*


class ScheduleActivity : AppCompatActivity() {

    lateinit var scheduleDateAdapter: ScheduleDateAdapter
    val dateDatas = mutableListOf<ScheduleDateData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)

        scheduleDateAdapter = ScheduleDateAdapter(this)
        rv_schedule_date.adapter = scheduleDateAdapter
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
        scheduleDateAdapter.datas = dateDatas
        scheduleDateAdapter.notifyDataSetChanged()
    }
}