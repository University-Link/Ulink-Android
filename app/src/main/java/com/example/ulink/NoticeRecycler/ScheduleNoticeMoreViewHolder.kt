package com.example.ulink.NoticeRecycler

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ulink.R
import com.example.ulink.ScheduleRecycler.ScheduleItemData
import com.example.ulink.ScheduleRecycler.categoryBackground
import kotlinx.android.synthetic.main.item_notice_more.*

class ScheduleNoticeMoreViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    var date : TextView = itemView.findViewById(R.id.rv_notice_more_date)
    var title : TextView = itemView.findViewById(R.id.rv_notice_more_title)
    var time : TextView = itemView.findViewById(R.id.rv_notice_more_time)
    var dday : TextView = itemView.findViewById(R.id.rv_notice_more_dday)


    fun bind(scheduleData : ScheduleItemData){

        ddayBackground(scheduleData.category, dday)

        var dateIndex = scheduleData.date.split("-")

        date.text = dateIndex[1] + " / " +dateIndex[2]
        title.text = scheduleData.content
        time.text = scheduleData.startTime + " ~ " + scheduleData.endTime
        dday.text = "D-day"
    }
 }