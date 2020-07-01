package com.example.ulink.CalendarRecycler

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ulink.R

class CalendarViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    val day : TextView = itemView.findViewById(R.id.day)

    fun bind(dayData : CalendarDayData){
        day.text = dayData.day
    }
}