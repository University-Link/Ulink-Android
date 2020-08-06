package com.ulink.ulink.Ulink

data class BoardSubjectData(
    val boardSubjectIdx : Int = 0,
    val title : String,
    val initial : String,
    val nickname : String,
    val content : String,
    val likeCount : Int =0 ,
    val commentCount : Int=0,
    val userIdx : Int = 0,
    val createdAt : String = "",
    val updatedAt : String = "",
    val isLike : Boolean,
    val isNotice : Int,
    val subjectIdx : Int
)

