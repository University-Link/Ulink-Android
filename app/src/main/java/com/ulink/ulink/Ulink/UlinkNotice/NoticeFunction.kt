package com.ulink.ulink.Ulink.UlinkNotice

import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import com.ulink.ulink.CalendarRecycler.today
import com.ulink.ulink.R
import com.ulink.ulink.ScheduleRecycler.zeroCheck
import java.text.SimpleDateFormat
import java.util.*

fun categoryBackground(category : String, date : String, imgCategory : ImageView) {
    when (category) {
        "수업" -> imgCategory.setBackgroundResource(R.drawable.class_notice_ic_class)
        "시험" -> imgCategory.setBackgroundResource(R.drawable.class_notice_ic_exam)
        else -> imgCategory.setBackgroundResource(R.drawable.class_notice_ic_assign)
    }

    if(!noticeDateCompare(date))
        imgCategory.setBackgroundResource(R.drawable.class_notice_ic_assign_end)
}

fun expiredDate(ulinkNoticeData: UlinkNoticeData, tvDate : TextView, tvTitle : TextView, tvTime : TextView){
    if(!noticeDateCompare(ulinkNoticeData.date)){
        tvDate.setTextColor(Color.parseColor("#c4c2c2"))
        tvTitle.setTextColor(Color.parseColor("#c4c2c2"))
        tvTime.setTextColor(Color.parseColor("#c4c2c2"))
    }
    else{
        tvDate.setTextColor(Color.parseColor("#363636"))
        tvTitle.setTextColor(Color.parseColor("#363636"))
        tvTime.setTextColor(Color.parseColor("#5c5c5c"))
    }

}
fun dateTextView(ulinkNoticeData : UlinkNoticeData, tvDate : TextView){

    val strDate = ulinkNoticeData.date.split("-")
    var month = zeroCheck(strDate[1])
    var day = zeroCheck(strDate[2])

    tvDate.text = "$month/$day"
}

fun zeroCheck(check : String) : String {
    var zeroCheck = check
    if(zeroCheck.length==2 && zeroCheck.toInt()<10) zeroCheck = zeroCheck.replace("0", "")
    return zeroCheck
}

fun timeTextView(ulinkNoticeData : UlinkNoticeData, tvTime : TextView){
    var startTime = ulinkNoticeData.startTime
    var endTime = ulinkNoticeData.endTime

    if(ulinkNoticeData.startTime == "-1") startTime = ""
    if (ulinkNoticeData.endTime == "-1") endTime = ""

    if (ulinkNoticeData.startTime != "-1" || ulinkNoticeData.endTime != "-1")
        tvTime.text = startTime + " ~ " + endTime

    if (ulinkNoticeData.startTime == "-1" && ulinkNoticeData.endTime == "-1")
        tvTime.text = "시간정보없음"

    if (ulinkNoticeData.category=="과제" && ulinkNoticeData.endTime != "") tvTime.text = "~ "+endTime
}

fun timeTextView2(startTime : String, endTime : String, category : String, tvTime : TextView){
    var start = startTime
    var end = endTime

    if(startTime == "-1") start = ""
    if (endTime == "-1") end = ""

    if (startTime != "-1" || endTime != "-1")
        tvTime.text = startTime + " ~ " + endTime

    if (startTime == "-1" && endTime == "-1")
        tvTime.text = "시간정보없음"

    if (category=="과제" && endTime != "") tvTime.text = "~ "+endTime
}

fun noticeDateCompare(date: String) : Boolean{
    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    var strTodayDate = today()
    var objTodayDate = simpleDateFormat.parse(strTodayDate)
    var formattedTodayDate = simpleDateFormat.format(objTodayDate)

    var objDate = simpleDateFormat.parse(date)
    var formattedDate = simpleDateFormat.format(objDate)

    return formattedTodayDate <= formattedDate
}

fun noticeDetailDate(date : String) : String{
    var splitDate = date.split("-")

    if(splitDate[1].toInt()<10) splitDate[1].replace("0", "")
    if(splitDate[2].toInt()<10) splitDate[2].replace("0", "")

    return splitDate[0]+"년 "+splitDate[1]+"월 "+splitDate[2]+"일"
}