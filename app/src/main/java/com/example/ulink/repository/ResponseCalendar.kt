package com.example.ulink.repository
data class ResponseCalendar(
        val status : Int,
        val success : Boolean,
        val message : String,
        val data : List<SomeData>
)
data class SomeData(
        val date : String,
        val notice : List<Notice>
)
data class Notice(
        val name : String,
        val color : Int,
        val noticeIdx : Int,
        val category : String,
        val startTime : String,
        val endTime : String,
        val title : String
)