package com.ulink.ulink.ChattingRecycler

data class ChattingData(
    val token : String, //사용자 구분 토큰
    val profile :String, //프로필이미지
    val username :String, //사용자 닉네임
    val message:String, //채팅내용
    val count :Int, //읽은 사람 수
    val time:String //채팅보낸시간
)