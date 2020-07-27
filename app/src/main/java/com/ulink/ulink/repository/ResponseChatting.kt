package com.ulink.ulink.repository

data class ResponseChatting(
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : ChatData
)

data class ChatData(
    val semester : String,
    val     chat : List<Chat>
)

data class Chat(
    val subjectIdx : Int,
    val name : String,
    val color : Int,
    val total : Int,
    val current : Int
)