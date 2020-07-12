package com.example.ulink.NoticeRecycler

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ulink.R
import com.example.ulink.ScheduleRecycler.ScheduleItemData

class ScheduleNoticeViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    var date : TextView = itemView.findViewById(R.id.rv_item_notice_date)
    var title : TextView = itemView.findViewById(R.id.rv_item_notice_title)
    var time : TextView = itemView.findViewById(R.id.rv_item_notice_time)

    fun bind(scheduleData : ScheduleItemData){
        var dateIndex = scheduleData.date.split("-")

        date.text = dateIndex[1] + "/" +dateIndex[2]
        title.text = scheduleData.content

        if(scheduleData.startTime == "" && scheduleData.endTime == "") {
            time.text = "시간정보없음"
        }
        else
            time.text = scheduleData.startTime + " ~ " + scheduleData.endTime

    }
 }