package com.ulink.ulink.Ulink

import com.google.gson.annotations.SerializedName


data class BoardUniversityData(
        @SerializedName("boardSchoolIdx")
        val boardUniversityIdx : Int = 0,
        val title : String,
        val initial : String,
        val nickname : String,
        val content : String,
        val likeCount : Int =0,
        val commentCount : Int=0,
        val userIdx : Int = 0,
        val createdAt : String = "",
        val updatedAt : String = "",
        val isLike : Boolean,
        val universityIdx : Int,
        val isMine : Boolean
)

