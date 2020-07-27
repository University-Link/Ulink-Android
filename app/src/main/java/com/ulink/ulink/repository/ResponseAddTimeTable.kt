package com.ulink.ulink.repository

data class ResponseAddTimeTable (
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : Data
){
    data class Data(val idx : String)
}


