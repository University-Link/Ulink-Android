package com.example.ulink.repository

data class ResponseAddPersonalPlan(
        val status : Int,
        val success : Boolean,
        val message : String,
        val data : Data
){
    data class Data(
            val idx : Int
    )
}