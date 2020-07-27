package com.ulink.ulink.repository

data class ResponseSubject(
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : List<SubjectList>
)

data class SubjectList(
    val name : String,
    val data : List<SubjectInfo>
)
data class SubjectInfo(
    val subjectIdx : Int,
    val subjectCode : String,
    val name : String,
    val professor : String,
    val credit :Int,
    val course : String,
    val content : List<String>,
    val day : List<String>,
    val dateTime : List<String>
)