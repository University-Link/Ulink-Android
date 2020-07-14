package com.example.ulink.repository

data class RequestCalendar(
    val jwt : String,
    val start : String,
    val end : String
)