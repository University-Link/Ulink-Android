package com.example.ulink.CalendarRecycler

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ulink.R
import com.example.ulink.ScheduleRecycler.ScheduleItemData

class CalendarScheduleViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    val schedule : TextView = itemView.findViewById(R.id.tv_schedule)

    fun bind(data : ScheduleItemData) {
        schedule.text = data.classname
        schedule.setTextColor(getColors(data.color))
    }

    fun getColors(type: Int): Int {
        return when (type) {
            0 -> R.drawable.bg_round_border_subject_color_1
            1 -> R.drawable.bg_round_border_subject_color_2
            2 -> R.drawable.bg_round_border_subject_color_3
            3 -> R.drawable.bg_round_border_subject_color_4
            4 -> R.drawable.bg_round_border_subject_color_5
            5 -> R.drawable.bg_round_border_subject_color_6
            6 -> R.drawable.bg_round_border_subject_color_7
            7 -> R.drawable.bg_round_border_subject_color_8
            8 -> R.drawable.bg_round_border_subject_color_9
            9 -> R.drawable.bg_round_border_subject_color_10
            else -> R.drawable.bg_round_border_subject
        }
    }
}