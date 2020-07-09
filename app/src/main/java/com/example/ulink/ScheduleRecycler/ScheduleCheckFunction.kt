package com.example.ulink.ScheduleRecycler

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ulink.R
import java.util.*

var cal: Calendar = Calendar.getInstance()
var nowDay = cal.get(Calendar.DATE)
var nowYear = cal.get(Calendar.YEAR)
var nowMonth = cal.get(Calendar.MONTH)+1

fun nowDay() : Int {
    return nowDay
}

fun nowDateCheck(i : Int) : String {

    var tempDate = (cal.get(Calendar.DAY_OF_WEEK)+i)%7

    var nowDate : String = ""

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

fun ddayCheck(i : Int) : String {
    return if(i==0) "day"
    else i.toString()
}

fun categoryBackground(category : String, tv_category : TextView) {
    if(category=="시험") tv_category.setBackgroundResource(R.drawable.chatting_schedule_img_label_test)
    else if(category=="수업") tv_category.setBackgroundResource(R.drawable.chatting_schedule_img_label_hw)
    else if(category=="과제") tv_category.setBackgroundResource(R.drawable.chatting_schedule_img_label_class)
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

fun zeroPlus(check : String) : String {
    var zeroCheck = check
    if(zeroCheck.length==1) zeroCheck = "0"+check
    return zeroCheck
}