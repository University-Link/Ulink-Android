package com.example.ulink.repository

data class ResponseGetSubjectByGrade(
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : List<SubjectListByGrade>
)

data class SubjectListByGrade(
    val subjectIdx : Int,
    val subjectCode : String,
    val name : String,
    val professor : String,
    val credit :Int,
    val course : String,
    val startTime : List<String>,
    val endTime : List<String>,
    val day : List<String>,
    val content : List<String>
)