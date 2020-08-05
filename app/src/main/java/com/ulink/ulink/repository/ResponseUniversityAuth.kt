package com.ulink.ulink.repository

data class ResponseUniversityAuth(
        val status : Int,
        val success : Boolean,
        val message : String,
        val data : Data
){
    data class Data(
            val authNum : String
    )
}