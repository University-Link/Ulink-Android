package com.example.ulink.repository

data class ResponseLogin(
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : Login?
)

data class Login(
    val accessToken : String
)