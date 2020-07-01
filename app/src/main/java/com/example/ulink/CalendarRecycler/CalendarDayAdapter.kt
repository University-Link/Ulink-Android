package com.example.ulink.CalendarRecycler

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ulink.R

class CalendarDayAdapter(private val context : Context) : RecyclerView.Adapter<CalendarViewHolder>(){
    var datas = mutableListOf<CalendarDayData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.calendar_item,parent,false)
        return CalendarViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        holder.bind(datas[position])
    }
}