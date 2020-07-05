package com.example.ulink.CalendarRecycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CalendarViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    fun bind(dayData : CalendarDayData){
        CalendarDayColorCheck(dayData, itemView)
        CalendarAlpha(dayData, itemView)
    }
}
