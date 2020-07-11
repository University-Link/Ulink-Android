package com.example.ulink.repository

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Subject (
        var id : Long,
        var name : String,
        var starttime : String,
        var endtime : String,
        var day : String,
        var place : String?,
        var color : Int,
        var subject : Boolean,
        var credit : Float? = 0f,
        var professor : String = "",
        var course : String = "",
        var isSample : Boolean = false
) : Parcelable