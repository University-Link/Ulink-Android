package com.example.ulink.CalendarRecycler

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ulink.R

class CalendarViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    val day : TextView = itemView.findViewById(R.id.day)

    fun bind(dayData : CalendarDayData){
        day.text = dayData.day

        var sundayColor: String = "#5F5DE9"
        var monthColor : String = "#000000"
        var otherColor : String = "#888888"

        if(dayData.check)
            day.setTextColor(Color.parseColor(monthColor))
        else
            day.setTextColor(Color.parseColor(otherColor))
        if(dayData.date % 7 == 0 && dayData.check)
            day.setTextColor(Color.parseColor(sundayColor))

    }
}
