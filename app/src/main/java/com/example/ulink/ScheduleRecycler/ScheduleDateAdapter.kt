package com.example.ulink.ScheduleRecycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ulink.R

class ScheduleDateAdapter(private val context : Context) : RecyclerView.Adapter<ScheduleDateViewHolder>(){
    var datas = mutableListOf<ScheduleDateData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleDateViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.schedule_day_item,parent,false)
        return ScheduleDateViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: ScheduleDateViewHolder, position: Int) {
        holder.bind(datas[position])
    }
}