package com.ulink.ulink.repository

data class ResponsegetSubjectWithKeyWord (
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : List<String>?
)