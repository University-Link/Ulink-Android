package com.example.ulink.repository

data class RequestAddPersonalPlan(
        var name : String,
        var startTime : String,
        var endTime : String,
        var day : String,
        var content : String,
        var color : Int,
        var scheduleIdx : Int
)