package com.example.ulink.repository

data class ResponseLogin(
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : LoginData?
)

data class LoginData(
    val uid : String,
    val accessToken : String
)