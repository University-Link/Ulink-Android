package com.ulink.ulink.repository

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Subject (
        @SerializedName("idx")
        var id : Long = 0,
        var name : String,
        var startTime : List<String>,
        var endTime : List<String>,
        var day : List<Int>,
        @SerializedName("content")
        var place : List<String>,
        var color : Int,
        var subject : Boolean,
        var credit : Float? = 0f,
        var professor : String = "",
        var course : String = "",
        var isSample : Boolean = false,
        var number : String = "",
        var subjectIdx : Int = 0
) : Parcelable {
        constructor() : this(0,"", listOf(),listOf(),listOf(),listOf(),1,false,0f,"","",false,"",0)
}