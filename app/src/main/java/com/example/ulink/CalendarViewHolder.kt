package com.example.ulink

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CalendarViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    val day : TextView = itemView.findViewById(R.id.day)

    fun bind(dayData : CalendarDayData){
        day.text = dayData.day
    }
}