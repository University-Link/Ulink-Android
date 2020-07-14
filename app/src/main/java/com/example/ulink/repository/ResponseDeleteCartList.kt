package com.example.ulink.repository

data class ResponseDeleteCartList(
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : List<DeleteCartData>
)

data class DeleteCartData(
    val subjectIdx : Int
)