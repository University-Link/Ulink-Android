package com.example.ulink.ScheduleRecycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ulink.R

class ScheduleDateAdapter(private val context : Context) : RecyclerView.Adapter<ScheduleDateViewHolder>(){
    var dateDatas = mutableListOf<ScheduleDateData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleDateViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.schedule_day_item,parent,false)
        return ScheduleDateViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dateDatas.size
    }

    override fun onBindViewHolder(holder: ScheduleDateViewHolder, position: Int) {
        holder.bind(dateDatas[position])

        val scheduleItemDataList : MutableList<ScheduleItemData> = arrayListOf()
        var nowDay = nowDay()

            scheduleItemDataList.add(
                ScheduleItemData(
                    category = "시험",
                    classname = "유링크",
                    content = "앱잼",
                    time = "11:00"
                )
            )

            scheduleItemDataList.add(
                ScheduleItemData(
                    category = "과제",
                    classname = "유링크",
                    content = "캘린더뷰",
                    time = "11:00"
                )
            )
            scheduleItemDataList.add(
                ScheduleItemData(
                    category = "수업",
                    classname = "유링크",
                    content = "안드개발",
                    time = "11:00"
                )
            )
        holder.recyclerView.adapter = ScheduleItemAdapter(context, scheduleItemDataList)
    }
}