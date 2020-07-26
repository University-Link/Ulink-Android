package com.example.ulink.ScheduleRecycler

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ulink.CalendarRecycler.endDay
import com.example.ulink.R
import java.util.*

var cal: Calendar = Calendar.getInstance()
var nowDay = cal.get(Calendar.DATE)
var nowYear = cal.get(Calendar.YEAR)
var nowMonth = cal.get(Calendar.MONTH)+1

fun nowDateCheck(date : Long) : String {
    var date = date*-1
    var tempDate = (date.toInt()+1)%7
    var nowDate = ""

    when (tempDate) {
        1 -> nowDate = "일"
        2 -> nowDate = "월"
        3 -> nowDate = "화"
        4 -> nowDate = "수"
        5 -> nowDate = "목"
        6 -> nowDate = "금"
        0 -> nowDate = "토"
    }

    return nowDate
}

fun categoryBackground(category : String, tv_category : TextView) {
    when (category) {
        "시험" -> tv_category.setBackgroundResource(R.drawable.chatting_schedule_img_label_test)
        "수업" -> tv_category.setBackgroundResource(R.drawable.chatting_schedule_img_label_class)
        "과제" -> tv_category.setBackgroundResource(R.drawable.chatting_schedule_img_label_hw)
    }
}

fun todayBackground(todayCheck : String, background : ConstraintLayout) {

    var todayCheckMonth : String = nowMonth.toString()
    var todayCheckDay : String = nowDay.toString()
    if(nowMonth<10) todayCheckMonth = "0"+todayCheckMonth
    if(nowDay<10) todayCheckDay = "0"+todayCheckDay

    var checkDate = nowYear.toString()+"-"+todayCheckMonth+"-"+todayCheckDay

    if(checkDate == todayCheck) background.setBackgroundResource(R.drawable.chatting_schedule_img_day)
    else background.setBackgroundResource(R.drawable.chatting_schedule_img_bg)

}

fun zeroCheck(check : String) : String {
    var zeroCheck = check
    if(zeroCheck.length==2 && zeroCheck.toInt()<10) zeroCheck = zeroCheck.replace("0", "")
    return zeroCheck
}