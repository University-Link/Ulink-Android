package com.example.ulink.repository

data class ResponseChangeColor(
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : idx
)

data class idx(
    val idx : String
)