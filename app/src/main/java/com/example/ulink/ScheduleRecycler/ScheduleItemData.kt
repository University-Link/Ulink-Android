package com.example.ulink.ScheduleRecycler

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ScheduleItemData (
    var date : String,
    var category : String,
    var classname : String,
    var content : String,
    var startTime : String,
    var endTime : String
) : Parcelable