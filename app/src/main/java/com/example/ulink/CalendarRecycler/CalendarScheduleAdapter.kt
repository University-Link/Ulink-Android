package com.example.ulink.ScheduleRecycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ulink.CalendarRecycler.CalendarDayAdapter
import com.example.ulink.CalendarRecycler.CalendarScheduleViewHolder
import com.example.ulink.R

class CalendarScheduleAdapter() : RecyclerView.Adapter<CalendarScheduleViewHolder>(){
    var datas = mutableListOf<ScheduleItemData>()

    lateinit var dayClickListener : CalendarDayAdapter.DayClickListener

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