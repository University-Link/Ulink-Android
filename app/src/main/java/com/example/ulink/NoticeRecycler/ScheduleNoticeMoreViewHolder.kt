package com.example.ulink.NoticeRecycler

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ulink.R
import com.example.ulink.ScheduleRecycler.*
import kotlinx.android.synthetic.main.activity_notice_add.*
import java.text.SimpleDateFormat


class ScheduleNoticeMoreViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    var date : TextView = itemView.findViewById(R.id.rv_notice_more_date)
    var title : TextView = itemView.findViewById(R.id.rv_notice_more_title)
    var time : TextView = itemView.findViewById(R.id.rv_notice_more_time)
    var dday : TextView = itemView.findViewById(R.id.rv_notice_more_dday)

    fun bind(scheduleData : ScheduleItemData) {

        // TODO 달바뀌는부분디데이계산
        ddayBackground(scheduleData.category, dday)

        var dayRemainder = ddayCheck(scheduleData)

        var dateIndex = scheduleData.date.split("-")

        date.text = zeroCheck(dateIndex[1]) + "/" + zeroCheck(dateIndex[2])
        title.text = scheduleData.content

        if(scheduleData.startTime == "-1") scheduleData.startTime= ""
        if (scheduleData.endTime == "-1") scheduleData.endTime=""

        if (scheduleData.startTime != "" || scheduleData.endTime != "")
            time.text = scheduleData.startTime + " ~ " + scheduleData.endTime

        if (scheduleData.startTime == "" && scheduleData.endTime == "")
            time.text = "시간정보없음"

        if (scheduleData.category=="과제" && scheduleData.endTime != "") time.text = scheduleData.endTime

        if(dayRemainder!=0.toLong()) {
            if(dayRemainder > 0.toLong()) {
                dday.text = "완료"
            }
            else {
                dayRemainder *= -1
                dday.text = "D-" + dayRemainder.toString()
            }
        }
        else
            dday.text = "D-day"
    }
 }