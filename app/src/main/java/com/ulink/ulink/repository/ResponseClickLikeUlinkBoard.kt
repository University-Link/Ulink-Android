package com.ulink.ulink.repository

import com.ulink.ulink.Ulink.BoardData

data class ResponseClickLikeUlinkBoard(
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : List<BoardData>
)