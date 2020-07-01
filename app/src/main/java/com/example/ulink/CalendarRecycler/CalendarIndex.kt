package com.example.ulink.CalendarRecycler


val endDates = mutableListOf<Int>(31,28,31,30,31,30,31,31,30,31,30,31)

fun getDay(year : Int, month : Int) : Int{
    val preyear = year-1
    var daysum = preyear*365 + preyear/4 - preyear/100 + preyear/400

    if (month>=3 && (year%4==0 && year%100!=0 || year%400 ==0)) daysum += 1

    var premonth = month - 1

    for (i in 0 until premonth) daysum += endDates[i]
    daysum += 1

    return daysum % 7
}

fun first_Index (year : Int, month : Int) : Int
{
    var year : Int = year
    var month : Int = month
    var index = getDay(year, month);
    return index;
}