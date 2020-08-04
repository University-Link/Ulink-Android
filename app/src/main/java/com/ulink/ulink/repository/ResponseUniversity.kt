package com.ulink.ulink.repository

class ResponseUniversity (
    val status: Int,
    val success: Boolean,
    val message : String,
    val data : List<University>
)

data class University(
    val universityIdx : Int,
    val name : String
)