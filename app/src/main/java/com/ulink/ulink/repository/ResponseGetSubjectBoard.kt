package com.ulink.ulink.repository

import com.ulink.ulink.Ulink.BoardSubjectData
import com.ulink.ulink.Ulink.BoardUlinkData

data class ResponseGetSubjectBoard(
        val status : Int,
        val success : Boolean,
        val message : String,
        val data : Data
){
    data class Data(
            val board : List<BoardSubjectData>,
            val nextPage : Int
    )
}