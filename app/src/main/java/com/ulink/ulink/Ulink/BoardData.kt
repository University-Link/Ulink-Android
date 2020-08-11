package com.ulink.ulink.Ulink

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BoardData(
    val boardIdx : Int = 0,
    val title : String,
    val initial : String = "",
    val profileImage : String?,
    val nickname : String,
    val content : String,
    val likeCount : Int =0,
    val commentCount : Int=0,
    val universityIdx : Int = 0,
    val subjectIdx : Int = 0,
    val isNotice : Int = 0,
    val userIdx : Int = 0,
    val createdAt : String = "",
    val category : Int = 0,
    var isLike : Boolean,
    val isMine : Boolean,
    val noticeIdx : Int = -1,
    val noticeType : Int = -1
) :Parcelable