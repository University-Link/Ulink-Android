package com.example.ulink.ScheduleRecycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ulink.R

class ScheduleItemAdapter(private val context : Context, mutableList:MutableList<ScheduleItemData>) : RecyclerView.Adapter<ScheduleItemViewHolder>() {
    var scheduleItemDataList = mutableList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.schedule_item, parent, false)
        return ScheduleItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return scheduleItemDataList.size
    }

    override fun onBindViewHolder(holder:ScheduleItemViewHolder, position: Int) {
        holder.bind(scheduleItemDataList[position])
    }
}