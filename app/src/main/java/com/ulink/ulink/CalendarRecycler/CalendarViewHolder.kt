package com.ulink.ulink.CalendarRecycler

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ulink.ulink.R
import com.ulink.ulink.ScheduleRecycler.CalendarScheduleAdapter
import com.ulink.ulink.ScheduleRecycler.ScheduleItemData

class CalendarViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    fun bind(dayData : CalendarDayData, rootView : View, datas : List<ScheduleItemData>){

        calendarDayColorCheck(dayData, itemView)
        calendarAlpha(dayData, itemView)

        val rvSmallSchedule = itemView.findViewById<RecyclerView>(R.id.rv_small_schedule)
        val adapter = CalendarScheduleAdapter()

        val day : TextView = itemView.findViewById(R.id.day)
        var yearMonth = rootView.findViewById<TextView>(R.id.tv_month)
        var year = yearMonth.text.toString().split("년")[0]
        var month = yearMonth.text.toString().split("년")[1].split(" ")[1].split("월")[0]
        var currentposdate = ""

        if(month.length == 1){
            currentposdate = year + "-"+ "0"+month + "-"+day.text.toString()
        } else{
            currentposdate = year + "-"+ month + "-"+day.text.toString()
        }

        for (i in datas){
            if (currentposdate == i.date){
                adapter.datas.add(i)
            }
            rvSmallSchedule.adapter = adapter
            adapter.notifyDataSetChanged()
        }

//        TODO 전달 다음달은 index로 나중에 해결하기 currentposition과 비교해ㅓㅅ!

    }
}