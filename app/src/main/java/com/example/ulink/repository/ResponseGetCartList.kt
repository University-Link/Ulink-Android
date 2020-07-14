package com.example.ulink.repository

data class ResponseGetCartList(
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : List<GetCartData>
)

data class GetCartData(
    val cartIdx : Int,
    val userIdx : Int,
    val subjectIdx : Int,
    val semester : String,
    val subjectCode : String,
    val name : String,
    val nameAtomic : String,
    val professor : String,
    val school : String,
    val college : String,
    val major : String,
    val grade : Int,
    val credit : Int,
    val people : Int,
    val course : String
)