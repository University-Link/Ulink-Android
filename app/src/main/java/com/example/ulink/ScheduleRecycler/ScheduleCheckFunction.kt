package com.example.ulink.ScheduleRecycler

import java.util.*

var cal: Calendar = Calendar.getInstance()
var nowDay = cal.get(Calendar.DATE)
var nowMonth = cal.get(Calendar.MONTH)+1
var nowYear = cal.get(Calendar.YEAR)

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