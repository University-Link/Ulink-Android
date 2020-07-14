package com.example.ulink.repository

data class ResponseGetClassNotice(
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : NoticeList
)

data class NoticeList(
    val assignment : List<NoticeData>,
    val exam : List<NoticeData>,
    val lecture : List<NoticeData>
)

data class NoticeData(
    val noticeIdx : Int,
    val title : String,
    val startTime : String,
    val endTime : String,
    val date : String
)