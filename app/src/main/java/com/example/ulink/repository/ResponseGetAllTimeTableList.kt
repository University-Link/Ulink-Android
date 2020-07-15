package com.example.ulink.repository

data class ResponseGetAllTimeTableList (
        val status : Int,
        val success : Boolean,
        val message : String,
        val data : List<TimeTables>
) {
    data class TimeTables(
            val semester: String,
            val timeTableList : List<TimeTable>
    )
}