package com.ulink.ulink.Ulink.UlinkBoardRecycler


// TODO 대학 데이터 추가하기
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