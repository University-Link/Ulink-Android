package com.example.ulink.ClassNoticeRecycler

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ClassNoticeData (
    val StartDate : Int,
    val EndDate : Int,
    val ClassName : String,
    val StartTime : String,
    val EndTime: String
) : Parcelable