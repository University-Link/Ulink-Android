package com.ulink.ulink.repository

data class RequestBoardReport(
    val boardIdx : Int,
    val reason : Int,
    val content : String
)

data class ResponseBoardReport(
    val status : Int,
    val success : Boolean,
    val message : String
)