package com.ulink.ulink.repository

data class BaseResponse (
    val status : Int,
    val success : Boolean,
    val message : String
)