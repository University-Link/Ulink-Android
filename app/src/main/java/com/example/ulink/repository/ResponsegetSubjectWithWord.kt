package com.example.ulink.repository

data class ResponsegetSubjectWithWord (
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : List<SearchedData>
)

data class SearchedData(
    val subjectIdx : Int,
    val name : String,
    val professor : String,
    val credit : Float,
    val course : String,
    val startTime : List<String>,
    val endTime : List<String>,
    val day : List<Int>,
    val content : List<String>
)