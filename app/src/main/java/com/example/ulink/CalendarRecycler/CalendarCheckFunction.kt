package com.example.ulink.CalendarRecycler

import android.graphics.Color
import android.view.View
import android.widget.TextView
import com.example.myapplication.CalendarData
import com.example.ulink.R
import java.util.*

var endDay = arrayOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

var cal: Calendar = Calendar.getInstance()
var now_day = cal.get(Calendar.DATE)
var now_month = cal.get(Calendar.MONTH)+1
var now_year = cal.get(Calendar.YEAR)

fun CalendarTodayCheck(i : Int, data : CalendarData) : Boolean { //today
    return (i == now_day && data.month == now_month && data.year == now_year)
}

fun CalendarPreviousIndexCheck(data : CalendarData, index : Int) : Int { //prev_month start day
    if(data.month==1) return endDay[11] - index + 1
    else return endDay[data.month-2] - index + 1
}

fun CalendarLeapYearCheck(data: CalendarData) : Int {
    if ((data.year%4==0 && data.year%100!=0 || data.year%400==0)) return 29
    else return 28
}

fun CalendarDayColorCheck(dayData: CalendarDayData, itemView : View) {
    val day : TextView = itemView.findViewById(R.id.day)
    day.text = dayData.day

    var sundayColor = "#5F5DE9"
    var monthColor = "#000000"
    var otherColor = "#888888"
    var todayColor = "#ffffff"

    if(dayData.check) day.setTextColor(Color.parseColor(monthColor)) // now_month
    else day.setTextColor(Color.parseColor(otherColor)) // prev, next month
    if(dayData.date % 7 == 0 && dayData.check) day.setTextColor(Color.parseColor(sundayColor)) //sunday
    if(dayData.today)
    {
        day.setBackgroundResource(R.drawable.img_btn_class)
        day.setTextColor(Color.parseColor(todayColor))
    }
}

fun CalendarDataInit(tv_month : TextView) : CalendarData {
    tv_month.setText(now_month.toString()+"월")
    return CalendarData(now_year, now_month)
}

fun CalendarPrevMonth(tv_month : TextView, data_month : Int, data_year : Int) : CalendarData {
    var month = data_month - 1
    var year = data_year

    if(month == 0){
        month = 12
        year -= 1
    }

    tv_month.setText(month.toString()+"월")
    return CalendarData(year, month)
}

fun CalendarNextMonth(tv_month : TextView, data_month : Int, data_year : Int) : CalendarData {
    var month = data_month + 1
    var year = data_year

    if (month == 13){
        month = 1
        year += 1
    }

    tv_month.setText(month.toString()+"월")
    return CalendarData(year, month)
}