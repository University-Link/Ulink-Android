package com.example.ulink.NoticeRecycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ulink.R
import com.example.ulink.ScheduleRecycler.ScheduleItemData

class ScheduleNoticeAdapter(private val context : Context) : RecyclerView.Adapter<ScheduleNoticeViewHolder>(){
    var datas = mutableListOf<ScheduleItemData>()

    private lateinit var scheduleItemClickListener : ScheduleNoticeClickListener

    interface ScheduleNoticeClickListener {
        fun onClick(view: View, position:Int)
    }

    fun setScheduleItemClickListener(scheduleItemClickListener : ScheduleNoticeClickListener){
        this.scheduleItemClickListener = scheduleItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleNoticeViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_notice, parent,false)
        return ScheduleNoticeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: ScheduleNoticeViewHolder, position: Int) {
        holder.bind(datas[position])
        holder.itemView.setOnClickListener{scheduleItemClickListener.onClick(it,position)}
    }
}