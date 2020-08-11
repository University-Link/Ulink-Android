package com.ulink.ulink.Ulink.ulinknotice

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//공지 조회
data class ResponseUlinkNotice(
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : List<UlinkNoticeData>
)
data class UlinkNoticeData (
    val noticeIdx : Int,
    val category : String,
    val title : String,
    val startTime : String,
    val endTime : String,
    val date : String
)

//공지 상세 조회
data class ResponseDetailNotice(
    val status: Int,
    val success: Boolean,
    val message : String,
    val data : DetailNotice
)
data class DetailNotice(
    val noticeIdx : Int,
    val category : String,
    val date : String,
    val startTime : String,
    val endTime : String,
    val title : String,
    val content : String,
    val nickname : String,
    val isMine : Boolean
)

//공지 등록
@Parcelize
data class RequestNoticeAdd(
    val category : String,
    val date : String,
    val startTime : String,
    val endTime : String,
    val title : String,
    val content : String
) : Parcelable

//공지 신고
data class RequestNoticeReport(
    val noticeIdx : Int,
    val reason : Int,
    val content : String
)

//공지 수정 요청
data class RequestNoticeModify(
    val noticeIdx : Int,
    val reasons : List<Int>,
    val contents : List<String>
)

//Response
data class ResponseNotice(
    val status : Int,
    val success : Boolean,
    val message : String
)