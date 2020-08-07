package com.ulink.ulink.repository

import com.ulink.ulink.Ulink.BoardUlinkData
import com.ulink.ulink.Ulink.BoardUniversityData

data class ResponseGetUniversityBoard(
        val status : Int,
        val success : Boolean,
        val message : String,
        val data : Data
){
    data class Data(
            val board : List<BoardUniversityData>,
            val nextPage : Int
    )
}