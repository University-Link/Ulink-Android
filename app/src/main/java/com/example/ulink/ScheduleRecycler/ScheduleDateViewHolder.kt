package com.example.ulink.ScheduleRecycler

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ulink.R

class ScheduleDateViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    val scheduleday : TextView = itemView.findViewById(R.id.schedule_day)
    val scheduledate : TextView = itemView.findViewById(R.id.schedule_date)
    val scheduledday : TextView = itemView.findViewById(R.id.schedule_dday)

    fun bind(scheduleDateData : ScheduleDateData){
        scheduleday.text = scheduleDateData.day.toString()+"일"
        scheduledate.text = " (" + scheduleDateData.date + ") "
        scheduledday.text = "D-" + scheduleDateData.dday
    }
}