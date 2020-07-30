package com.ulink.ulink.CalendarRecycler

import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ulink.ulink.R
import com.ulink.ulink.ScheduleRecycler.ScheduleItemData
import com.ulink.ulink.scheduleColorSelector

class CalendarScheduleViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    val schedule : TextView = itemView.findViewById(R.id.tv_schedule)

    fun bind(data : ScheduleItemData) {
        schedule.text = data.classname
        Log.d("tag",data.toString())
        itemView.setBackgroundResource(scheduleColorSelector(data.color))
    }
}