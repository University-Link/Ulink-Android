package com.ulink.ulink.repository

import com.ulink.ulink.Ulink.BoardData
import com.ulink.ulink.Ulink.BoardDetailData

data class ResponsegetUlinkBoardList (
    val status : Int,
    val succes : Boolean,
    val message : String,
    val data : List<BoardDetailData>
)
