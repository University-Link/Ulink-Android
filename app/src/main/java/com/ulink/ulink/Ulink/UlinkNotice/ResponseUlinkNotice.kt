package com.ulink.ulink.Ulink.UlinkNotice

data class ResponseUlinkNotice(
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : List<UlinkNoticeData>
)
data class UlinkNoticeData (
    val noticeIdx : Int,
    val category : String,
    val title : String,
    val startTime : String,
    val endTime : String,
    val date : String
)

data class ResponseDetailNotice(
    val status: Int,
    val success: Boolean,
    val message : String,
    val data : DetailNotice
)
data class DetailNotice(
    val noticeIdx : Int,
    val category : String,
    val date : String,
    val startTime : String,
    val endTime : String,
    val title : String,
    val content : String
)