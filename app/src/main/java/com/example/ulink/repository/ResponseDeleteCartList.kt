package com.example.ulink.repository

data class ResponseDeleteCartList(
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : DeleteCartData
)

data class DeleteCartData(
    val subjectIdx : String
)