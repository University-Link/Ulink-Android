package com.ulink.ulink.Ulink

data class UlinkBoardData(
    val img_profile : String,
    val nickname : String,
    val time : String,
    val content : String,
    val like : Boolean,
    val comment_count : String,
    val heart_count : String,
    val category : String = "",
    val board_category : String =""
)