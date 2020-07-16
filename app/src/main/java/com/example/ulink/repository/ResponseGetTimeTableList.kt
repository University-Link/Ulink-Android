package com.example.ulink.repository

data class ResponseGetTimeTableList(
        val status: Int,
        val success: Boolean,
        val message: String,
        val data: List<Data>
){
    data class Data(
            val timeTable: TimeTable,
            val subjects: Subjects,
            val minTime : String,
            val maxTime : String
    )
}

