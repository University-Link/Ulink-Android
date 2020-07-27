package com.ulink.ulink.ScheduleRecycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ulink.ulink.R

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