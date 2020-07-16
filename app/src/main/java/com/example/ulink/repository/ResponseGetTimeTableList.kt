package com.example.ulink.repository

data class ResponseGetTimeTableList (
        val status : Int,
        val success : Boolean,
        val message : String,
        val data : List<TimeTable>
)