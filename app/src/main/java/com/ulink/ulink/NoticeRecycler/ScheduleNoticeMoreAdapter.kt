package com.ulink.ulink.NoticeRecycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ulink.ulink.R
import com.ulink.ulink.ScheduleRecycler.ScheduleItemData

class ScheduleNoticeMoreAdapter(private val context : Context) : RecyclerView.Adapter<ScheduleNoticeMoreViewHolder>(){
    var datas = mutableListOf<ScheduleItemData>()

    private lateinit var scheduleItemClickListener : ScheduleNoticeClickListener

    interface ScheduleNoticeClickListener {
        fun onClick(view: View, position:Int)
    }

    fun setScheduleItemClickListener(scheduleItemClickListener : ScheduleNoticeClickListener){
        this.scheduleItemClickListener = scheduleItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleNoticeMoreViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_notice_more, parent,false)
        return ScheduleNoticeMoreViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: ScheduleNoticeMoreViewHolder, position: Int) {
        holder.bind(datas[position])
        holder.itemView.setOnClickListener{scheduleItemClickListener.onClick(it,position)}

    }
}