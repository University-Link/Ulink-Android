package com.ulink.ulink.ScheduleRecycler

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ScheduleItemData (
    var idx : Int,
    var date : String,
    var category : String,
    var classname : String,
    var content : String,
    var startTime : String,
    var endTime : String,
    var memo : String,
    var color : Int = 0,
    var day : String = "",
    var dayindex : String = "",
    var dday : Long = 0L
) : Parcelable