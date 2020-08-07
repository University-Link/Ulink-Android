package com.ulink.ulink.repository

data class ResponseNicknameCheck (
    val status : Int,
    val success : Boolean,
    val message : String
)

data class RequestNicknameCheck (
    val nickname : String,
    val majorIdx : Int
)
