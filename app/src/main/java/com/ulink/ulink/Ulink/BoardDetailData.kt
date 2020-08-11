package com.ulink.ulink.Ulink

data class BoardDetailData(
    val boardIdx : Int = 0,
    val title : String,
    val initial : String = "",
    val profileImage : String?,
    val nickname : String,
    val content : String,
    val likeCount : Int =0,
    val commentCount : Int=0,
    val userIdx : Int = 0,
    val createdAt : String = "",
    val category : Int = 0,
    var isLike : Boolean,
    val isMine : Boolean,
    val comment : List<CommentData>
)