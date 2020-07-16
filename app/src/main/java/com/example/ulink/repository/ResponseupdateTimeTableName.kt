package com.example.ulink.repository

data class ResponseupdateTimeTableName(
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : modifiedName
)

data class modifiedName(
    val idx : String,
    val name : String
)