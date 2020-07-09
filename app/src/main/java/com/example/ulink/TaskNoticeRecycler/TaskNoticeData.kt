package com.example.ulink.TaskNoticeRecycler

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TaskNoticeData (
    val StartDate :Int,
    val EndDate : Int,
    val TaskName : String,
    val StartTime : String,
    val EndTime : String
) : Parcelable