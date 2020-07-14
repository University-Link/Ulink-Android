package com.example.ulink.NoticeRecycler

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ulink.R
import com.example.ulink.ScheduleRecycler.ScheduleItemData
import com.example.ulink.ScheduleRecycler.nowDay
import com.example.ulink.ScheduleRecycler.nowMonth
import com.example.ulink.ScheduleRecycler.nowYear
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

        date.text = dateIndex[1] + " / " + dateIndex[2]
        title.text = scheduleData.content
        time.text = scheduleData.startTime + " ~ " + scheduleData.endTime
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