package com.ulink.ulink.ScheduleRecycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ulink.ulink.R

class SchedulePopupAdapter (private val context : Context) : RecyclerView.Adapter<ScheduleItemViewHolder>(){
    var datas = mutableListOf<ScheduleItemData>()

    private lateinit var scheduleItemClickListener : ScheduleItemClickListener

    interface ScheduleItemClickListener {
        fun onClick(view: View, position:Int)
    }

    fun setScheduleItemClickListener(scheduleItemClickListener : ScheduleItemClickListener){
        this.scheduleItemClickListener = scheduleItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.schedule_item,parent,false)
        return ScheduleItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder:ScheduleItemViewHolder, position: Int) {
        holder.bind(datas[position])
        holder.itemView.setOnClickListener{scheduleItemClickListener.onClick(it,position)}
    }
}