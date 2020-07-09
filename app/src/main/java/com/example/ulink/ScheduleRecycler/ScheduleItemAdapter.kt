package com.example.ulink.ScheduleRecycler

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ulink.CalendarRecycler.CalendarDayAdapter
import com.example.ulink.CalendarRecycler.popupDateCheck
import com.example.ulink.CalendarRecycler.popupDayCheck
import com.example.ulink.R

class ScheduleItemAdapter(private val context : Context, mutableList:MutableList<ScheduleItemData>) : RecyclerView.Adapter<ScheduleItemViewHolder>() {
    private var scheduleItemDataList = mutableList

    private lateinit var scheduleItemClickListener : ScheduleItemClickListener

    interface ScheduleItemClickListener {
        fun onClick(view: View, position:Int)
    }

    fun setScheduleItemClickListener(scheduleItemClickListener : ScheduleItemClickListener){
        this.scheduleItemClickListener = scheduleItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.schedule_item, parent, false)
        return ScheduleItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return scheduleItemDataList.size
    }

    override fun onBindViewHolder(holder:ScheduleItemViewHolder, position: Int) {
        holder.bind(scheduleItemDataList[position])
        holder.itemView.setOnClickListener{scheduleItemClickListener.onClick(it,position)}
    }

}