package com.example.ulink.repository

data class ResponseAddSchoolPlan(
        val status : Int,
        val success : Boolean,
        val message : String,
        val data : Data
){
    data class Data(
            val idx : String
    )
}