package com.example.ulink.CalendarRecycler

import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ulink.R
import com.example.ulink.ScheduleRecycler.ScheduleItemData
import com.example.ulink.scheduleColorSelector

class CalendarScheduleViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    val schedule : TextView = itemView.findViewById(R.id.tv_schedule)

    fun bind(data : ScheduleItemData) {
        schedule.text = data.classname
        Log.d("tag",data.toString())
        itemView.setBackgroundResource(scheduleColorSelector(data.color))

    }
}