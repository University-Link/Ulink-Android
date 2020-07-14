package com.example.ulink.CalendarRecycler

import android.graphics.Color
import android.view.View
import android.widget.TextView
import com.example.ulink.R
import java.util.*

var endDay = arrayOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

var cal: Calendar = Calendar.getInstance()
var now_day = cal.get(Calendar.DATE)
var now_month = cal.get(Calendar.MONTH)+1
var now_year = cal.get(Calendar.YEAR)

fun calendarTodayCheck(i : Int, data : CalendarData) : Boolean { //today
    return (i == now_day && data.month == now_month && data.year == now_year)
}

fun calendarPreviousIndexCheck(data : CalendarData, index : Int) : Int { //prev_month start day
    if(data.month==1) return endDay[11] - index + 1
    else return endDay[data.month-2] - index + 1
}

fun calendarLeapYearCheck(data: CalendarData) : Int {
    if ((data.year%4==0 && data.year%100!=0 || data.year%400==0)) return 29
    else return 28
}

fun calendarDayColorCheck(dayData: CalendarDayData, itemView : View) {
    val day : TextView = itemView.findViewById(R.id.day)

    day.text = dayData.day

    val sundayColor = "#674FEE"
    val monthColor = "#363636"
    //var otherColor = "#888888"
    val todayColor = "#ffffff"

    if(dayData.check) day.setTextColor(Color.parseColor(monthColor)) // now_month
    //else day.setTextColor(Color.parseColor(otherColor)) // prev, next month
    if(dayData.date % 7 == 0 && dayData.check) day.setTextColor(Color.parseColor(sundayColor)) //sunday
    if(dayData.today)
    {
        day.setBackgroundResource(R.drawable.calendar_img_bg_today)
        day.setTextColor(Color.parseColor(todayColor))
    }
}

fun calendarAlpha(dayData: CalendarDayData, itemView : View){
    val day : TextView = itemView.findViewById(R.id.day)
    val schedule1 : TextView = itemView.findViewById(R.id.schedule1)
    val schedule2 : TextView = itemView.findViewById(R.id.schedule2)
    val schedule3 : TextView = itemView.findViewById(R.id.schedule3)
    val schedule4 : TextView = itemView.findViewById(R.id.schedule4)
    val schedule5 : TextView = itemView.findViewById(R.id.schedule5)

    if(!dayData.check){
        day.alpha=0.3f
        schedule1.alpha=0.3f
        schedule2.alpha=0.3f
        schedule3.alpha=0.3f
        schedule4.alpha=0.3f
        schedule5.alpha=0.3f
    } // prev, next month
}

fun calendarDataInit(tv_month : TextView) : CalendarData {
    tv_month.setText(now_year.toString()+"년 "+now_month.toString()+"월")
    return CalendarData(now_year, now_month)
}

fun calendarPrevMonth(tv_month : TextView, data_month : Int, data_year : Int) : CalendarData {
    var month = data_month - 1
    var year = data_year

    if(month == 0){
        month = 12
        year -= 1
    }

    tv_month.setText(year.toString()+"년 "+month.toString()+"월")
    return CalendarData(year, month)
}

fun calendarNextMonth(tv_month : TextView, data_month : Int, data_year : Int) : CalendarData {
    var month = data_month + 1
    var year = data_year

    if (month == 13){
        month = 1
        year += 1
    }

    tv_month.setText(year.toString()+"년 "+month.toString()+"월")
    return CalendarData(year, month)
}

fun getDay(year : Int, month : Int) : Int{
    val preyear = year-1
    var daysum = preyear*365 + preyear/4 - preyear/100 + preyear/400

    if (month>=3 && (year%4==0 && year%100!=0 || year%400 ==0)) daysum += 1

    val premonth = month - 1

    for (i in 0 until premonth) daysum += endDay[i]
    daysum += 1

    return daysum % 7
}

fun firstIndex (data_year : Int, data_month : Int) : Int
{
    val year : Int = data_year
    val month : Int = data_month
    val index = getDay(year, month);
    return index;
}

fun popupDateCheck(position : Int) : String {

    var tempDate = position % 7

    var nowDate : String = ""

    when (tempDate) {
        0 -> nowDate = "일"
        1 -> nowDate = "월"
        2 -> nowDate = "화"
        3 -> nowDate = "수"
        4 -> nowDate = "목"
        5 -> nowDate = "금"
        6 -> nowDate = "토"
    }

    return nowDate
}

fun popupDayCheck(position : Int, index : Int, last_empty : Int, prev_empty_index : Int) : Int {
    var popupDay = 0

    if(position < index)
        popupDay = prev_empty_index + position

    else if (position in index until last_empty)
        popupDay = (position - index) + 1

    else if (position >= last_empty)
        popupDay = (position - last_empty + 1)

    return popupDay
}

fun popupMonthCheck(position : Int, index : Int, last_empty : Int, data_month : Int) : Int {
    var popupMonth = data_month

    if(position < index){
        if(popupMonth != 1)
            popupMonth -= 1
        else
            popupMonth = 12
    }

    else if (position >= last_empty) {
        if(popupMonth <12)
            popupMonth +=1
        else
            popupMonth = 1
    }

    return popupMonth
}

fun popupYearCheck(data_year : Int, data_month : Int, position : Int, index : Int, last_empty : Int) : Int{
    var popupYear = data_year

    if(data_month == 1 && position < index) popupYear-=1

    if(data_month == 12 && position >= last_empty) popupYear +=1

    return popupYear
}