package com.ulink.ulink.CalendarRecycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ulink.ulink.R
import com.ulink.ulink.ScheduleRecycler.ScheduleItemData

class CalendarDayAdapter(private val context : Context, val rootView : View, val scheduleDatas : MutableList<ScheduleItemData>) : RecyclerView.Adapter<CalendarViewHolder>() {
    var datas = mutableListOf<CalendarDayData>()

    private lateinit var dayClickListener : DayClickListener

    interface DayClickListener {
        fun onClick(view: View, position:Int)
    }

    fun setDayClickListener(dayClickListener : DayClickListener){
        this.dayClickListener = dayClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.calendar_item,parent,false)
        return CalendarViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        holder.bind(datas[position],rootView, scheduleDatas)
        holder.itemView.setOnClickListener{ dayClickListener.onClick(it,position)}
        
    }
}