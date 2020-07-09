package com.example.ulink.TestNoticeRecycler

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TestNoticeData (
    val StartDate :Int,
    val EndDate : Int,
    val TestName : String,
    val StartTime : String,
    val EndTime : String

) : Parcelable