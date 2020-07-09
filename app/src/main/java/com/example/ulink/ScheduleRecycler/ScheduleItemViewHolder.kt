package com.example.ulink.ScheduleRecycler

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.ulink.R

class ScheduleItemViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    val category : TextView = itemView.findViewById(R.id.tv_schedule_category)
    val className : TextView = itemView.findViewById(R.id.tv_schedule_class)
    val content : TextView = itemView.findViewById(R.id.tv_schedule_content)
    val time : TextView = itemView.findViewById(R.id.tv_schedule_time)

    val background : ConstraintLayout = itemView.findViewById(R.id.layout_schedule_background)

    fun bind(scheduleItemData : ScheduleItemData){
        category.text = scheduleItemData.category
        className.text = scheduleItemData.classname+" "
        content.text = scheduleItemData.content
        time.text = scheduleItemData.startTime
        categoryBackground(scheduleItemData.category, category)
        todayBackground(scheduleItemData.date, background)
    }
}