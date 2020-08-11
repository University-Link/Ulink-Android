package com.ulink.ulink.CalendarRecycler

import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ulink.ulink.R
import com.ulink.ulink.ScheduleRecycler.CalendarScheduleAdapter
import com.ulink.ulink.ScheduleRecycler.ScheduleItemData
import com.ulink.ulink.ScheduleRecycler.zeroCheck

class CalendarViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    fun bind(dayData : CalendarDayData, datas : List<ScheduleItemData>){

        calendarDayColorCheck(dayData, itemView)
        calendarAlpha(dayData, itemView)

        val rvSmallSchedule = itemView.findViewById<RecyclerView>(R.id.rv_small_schedule)

        val adapter = CalendarScheduleAdapter()

        var month = ""
        if(dayData.month<10) month = "0"+dayData.month.toString()
        else month = dayData.month.toString()

        var day = ""
        if(dayData.day.length==1) day = "0"+dayData.day
        else day = dayData.day

        var currentposdate = dayData.year.toString()+"-"+month+"-"+day

        for (i in datas){
            if (currentposdate == i.date && dayData.check){
                adapter.datas.add(i)
            }
            rvSmallSchedule.adapter = adapter
            adapter.notifyDataSetChanged()
        }
    }
}