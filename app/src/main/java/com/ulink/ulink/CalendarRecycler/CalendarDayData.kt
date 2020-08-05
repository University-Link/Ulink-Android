package com.ulink.ulink.CalendarRecycler

class CalendarDayData (
    val year : Int,
    val month : Int,
    val day : String, // day
    val check : Boolean, // now_month
    val date : Int, // sunday_check & firstLine_check
    val today : Boolean // today_check
)