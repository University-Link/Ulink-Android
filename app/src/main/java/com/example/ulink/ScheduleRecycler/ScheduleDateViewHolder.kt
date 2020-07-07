package com.example.ulink.ScheduleRecycler

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.ulink.R

class ScheduleDateViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    val scheduleday : TextView = itemView.findViewById(R.id.tv_schedule_day)
    val scheduledate : TextView = itemView.findViewById(R.id.tv_schedule_date)
    val scheduledday : TextView = itemView.findViewById(R.id.tv_schedule_dday)

    val recyclerView = itemView.findViewById<RecyclerView>(R.id.rv_schedule_item)

    fun bind(scheduleDateData : ScheduleDateData){
        scheduleday.text = scheduleDateData.day.toString()+"일"
        scheduledate.text = " (" + scheduleDateData.date + ") "
        scheduledday.text = "D-" + scheduleDateData.dday
    }
}