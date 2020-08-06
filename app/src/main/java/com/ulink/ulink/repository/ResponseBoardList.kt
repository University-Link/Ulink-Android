package com.ulink.ulink.repository
data class ResponseBoardList(
        val status : Int,
        val success : Boolean,
        val message : String,
        val data : BoardList
)
data class BoardList(
        val semester : String,
        val list : List<BoardSubject>
)
data class BoardSubject(
        val subjectIdx : Int,
        val name : String
)