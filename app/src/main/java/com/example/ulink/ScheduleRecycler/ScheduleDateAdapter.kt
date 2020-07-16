package com.example.ulink.ScheduleRecycler

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ulink.CalendarRecycler.CalendarDayData
import com.example.ulink.NoticeRecycler.ddaySchedule
import com.example.ulink.R
import com.example.ulink.repository.CalendarNoticeData

class ScheduleDateAdapter(private val context : Context, tenDayData: MutableList<MutableList<ScheduleItemData>>) : RecyclerView.Adapter<ScheduleDateViewHolder>(){
    var dateDatas = tenDayData

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleDateViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.schedule_day_item,parent,false)
        return ScheduleDateViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dateDatas.size
    }

    override fun onBindViewHolder(holder: ScheduleDateViewHolder, position: Int) {
        holder.bind(dateDatas[position])

        holder.recyclerView.adapter = ScheduleItemAdapter(context, dateDatas[position])
    }
}