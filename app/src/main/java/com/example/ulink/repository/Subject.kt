package com.example.ulink.repository

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Subject (

        @SerializedName("idx")
        var id : Long = 0,
        var name : String,
        var startTime : String,
        var endTime : String,
        var day : Int, 
        @SerializedName("content")
        var place : String?,
        var color : Int,
        var subject : Boolean,
        var credit : Float? = 0f,
        var professor : String = "",
        var course : String = "",
        var isSample : Boolean = false,
        var number : String = ""
) : Parcelable {
        constructor() : this(0,"","","",0,"",1,false,0f,"","",false,"")
}