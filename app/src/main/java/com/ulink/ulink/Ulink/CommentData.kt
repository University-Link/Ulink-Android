package com.ulink.ulink.Ulink

data class CommentData (
    val caommentIdx : Int,
    val content : String,
    val likeCount : Int,
    val createdAt : String,
    val profileImage : String,
    val nickname : String,
    val initial : String,
    val userIdx : Int,
    val isLike : Boolean = false,
    val isMine : Boolean = false,
    val reportCount : Int = 0,
    val boardIdx : Int

)