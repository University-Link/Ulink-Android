package com.ulink.ulink.repository

data class RequestAddPersonalPlan(
        var scheduleList : List<Schedule>
){
    data class Schedule(
            var name : String,
            var startTime : String,
            var endTime : String,
            var day : Int,
            var content : String,
            var color : Int,
            var scheduleIdx : Int
    )
}