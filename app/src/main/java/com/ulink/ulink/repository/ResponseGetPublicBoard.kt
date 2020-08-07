package com.ulink.ulink.repository

import com.ulink.ulink.Ulink.BoardUlinkData

data class ResponseGetPublicBoard(
        val status : Int,
        val success : Boolean,
        val message : String,
        val data : Data
){
    data class Data(
            val board : List<BoardUlinkData>,
            val nextPage : Int
    )
}