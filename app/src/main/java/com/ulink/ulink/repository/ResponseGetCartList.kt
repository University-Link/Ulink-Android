package com.ulink.ulink.repository

import com.google.gson.annotations.SerializedName

data class ResponseGetCartList(
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : List<GetCartData>
)

data class GetCartData(
    val subjectIdx : Int,
    val subjectCode : String,
    val name : String,
    val professor : String,
    val credit : Int,
    val course : String,
    var startTime : List<String>,
    var endTime : List<String>,
    var day : List<Int>,
    @SerializedName("content")
    var place : List<String>
)