package com.ulink.ulink.repository

data class ResponsePhoneAuthentication (
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : Int
)

data class RequestPhoneAuthentication (
    val phoneNumber : String
)