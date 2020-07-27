package com.ulink.ulink.repository

data class ResponseGetSubjectByGrade(
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : List<SubjectByGrade>
)

data class SubjectByGrade(
    val subjectIdx : Long,
    val subjectCode : String,
    val name : String,
    val professor : String,
    val credit :Float,
    val course : String,
    val startTime : List<String>,
    val endTime : List<String>,
    val day : List<Int>,
    val content : List<String>
)