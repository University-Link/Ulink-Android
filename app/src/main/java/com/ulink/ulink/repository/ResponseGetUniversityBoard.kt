package com.ulink.ulink.repository

import com.ulink.ulink.Ulink.BoardData

data class ResponseGetUniversityBoard(
        val status : Int,
        val success : Boolean,
        val message : String,
        val data : Data
){
    data class Data(
            val board : List<BoardData>,
            val nextPage : Int
    )
}