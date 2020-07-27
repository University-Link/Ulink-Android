package com.ulink.ulink.repository

data class ResponseSpecificNotice(
    val status: Int,
    val success: Boolean,
    val message : String,
    val data: SpecificNotice
)
data class SpecificNotice(
    val noticeIdx : Int,
    val category : String,
    val date : String,
    val startTime : String,
    val endTime : String,
    val title : String,
    val content : String
)