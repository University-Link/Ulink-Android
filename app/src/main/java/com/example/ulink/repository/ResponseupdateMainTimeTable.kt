package com.example.ulink.repository

data class ResponseupdateMainTimeTable (
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : updatedMainTable
)

data class updatedMainTable(
    val idx : String
)