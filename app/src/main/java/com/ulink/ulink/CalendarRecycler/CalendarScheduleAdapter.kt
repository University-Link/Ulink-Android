package com.ulink.ulink.ScheduleRecycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ulink.ulink.CalendarRecycler.CalendarScheduleViewHolder
import com.ulink.ulink.R

class CalendarScheduleAdapter() : RecyclerView.Adapter<CalendarScheduleViewHolder>(){
    var datas = mutableListOf<ScheduleItemData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarScheduleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_small_schedule, parent,false)
        return CalendarScheduleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: CalendarScheduleViewHolder, position: Int) {
        holder.bind(datas[position])
    }
}