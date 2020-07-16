package com.example.ulink.ScheduleRecycler

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ulink.CalendarRecycler.CalendarDayData
import com.example.ulink.CalendarRecycler.CalendarScheduleViewHolder
import com.example.ulink.CalendarRecycler.CalendarViewHolder
import com.example.ulink.NoticeRecycler.ddaySchedule
import com.example.ulink.R
import com.example.ulink.repository.CalendarNoticeData

class CalendarScheduleAdapter(private val context : Context) : RecyclerView.Adapter<CalendarScheduleViewHolder>(){
    var datas = mutableListOf<ScheduleItemData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarScheduleViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_small_schedule, parent,false)
        return CalendarScheduleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: CalendarScheduleViewHolder, position: Int) {
        holder.bind(datas[position])
    }
}