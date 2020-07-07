package com.example.ulink.repository

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Subject (
        var id : Long,
        var name : String,
        var starttime : String,
        var endtime : String,
        var day : String,
        var place : String?,
        var color : Int,
        var subject : Boolean
) : Parcelable