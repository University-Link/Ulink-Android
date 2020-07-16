package com.example.ulink

data class ResponseNoticeData(
    val date : String,
    val name : String,
    val color : Int,
    val noticeIdx : Int,
    val category : String,
    val startTime : String,
    val endTime : String,
    val title : String,
    var check : Boolean = true,
    val day : String = "", // day
    val dayIndex : Int = 0, // sunday_check & firstLine_check
    val today : Boolean = false // today_check
)