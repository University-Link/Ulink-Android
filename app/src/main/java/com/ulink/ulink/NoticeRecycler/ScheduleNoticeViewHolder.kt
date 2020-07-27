package com.ulink.ulink.NoticeRecycler

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ulink.ulink.R
import com.ulink.ulink.ScheduleRecycler.ScheduleItemData
import com.ulink.ulink.ScheduleRecycler.zeroCheck

class ScheduleNoticeViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    var date : TextView = itemView.findViewById(R.id.rv_item_notice_date)
    var title : TextView = itemView.findViewById(R.id.rv_item_notice_title)
    var time : TextView = itemView.findViewById(R.id.rv_item_notice_time)

    fun bind(scheduleData : ScheduleItemData){

        var dateIndex = scheduleData.date.split("-")

        date.text = zeroCheck(dateIndex[1]) + "/" + zeroCheck(dateIndex[2])
        title.text = scheduleData.content

        if(scheduleData.startTime == "-1") scheduleData.startTime= ""
        if (scheduleData.endTime == "-1") scheduleData.endTime=""

        if (scheduleData.startTime != "" || scheduleData.endTime != "")
            time.text = scheduleData.startTime + " ~ " + scheduleData.endTime

        if (scheduleData.startTime == "" && scheduleData.endTime == "")
            time.text = "시간정보없음"

    }
 }