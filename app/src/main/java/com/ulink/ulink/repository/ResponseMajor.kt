package com.ulink.ulink.repository

class ResponseMajor (
    val status: Int,
    val success: Boolean,
    val message : String,
    val data : List<Major>
)

data class Major(
    val majorIdx : Int,
    val name : String
)