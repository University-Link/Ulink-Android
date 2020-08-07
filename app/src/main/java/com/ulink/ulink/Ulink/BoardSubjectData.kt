package com.ulink.ulink.Ulink

data class BoardSubjectData(
    val boardSubjectIdx : Int,
    val title : String,
    val initial : String,
    val nickname : String,
    val profileImage : String?,
    val content : String,
    val likeCount : Int =0 ,
    val commentCount : Int=0,
    val userIdx : Int = 0,
    val createdAt : String = "",
    val isLike : Boolean,
    val isNotice : Int,
    val subjectIdx : Int,
    val isMine : Boolean,
    val noticeIdx : Int,
    val category : Int
)

