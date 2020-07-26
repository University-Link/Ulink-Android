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

    fun bind(data : MutableList<ScheduleItemData>){
        scheduleday.text = data[0].date.split("-")[1]+"월 "+data[0].day+"일"
        scheduledate.text = " (" + data[0].dayindex + ") "

        if(data[0].dday.toInt() != 0)
            scheduledday.text = "D" + data[0].dday
        else
            scheduledday.text = "D-day"
    }
}