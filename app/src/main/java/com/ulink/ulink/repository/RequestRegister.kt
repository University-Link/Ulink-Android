package com.ulink.ulink.repository

data class RequestRegister (
    val id : String,
    val password : String,
    val name : String,
    val nickname : String,
    val majorIdx : Int,
    val gender : String,
    val studentNumber : Int,
    val phoneNumber : String,
    val agreeAd : Int,
    val agreeThird : Int
)

data class ResponseRegister(
    val status : Int,
    val success: Boolean,
    val message : String,
    val data : UserId
)

data class UserId(
    val userId : String,
    val userIdx : Int
)