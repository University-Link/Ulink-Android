package com.ulink.ulink.repository

data class RequestRegisterNotice (
    val category : String,
    val date : String,
    val startTime : String,
    val endTime : String,
    val title : String,
    val content : String
)