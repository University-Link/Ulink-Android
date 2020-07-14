package com.example.ulink.repository


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SubjectDetail(
    val cartIdx : Int,
    val userIdx : Int,
    val subjectIdx : Int,
    val semester : String,
    val subjectCode : String,
    val name : String,
    val nameAtomic : String,
    val professor : String,
    val school : String,
    val college : String,
    val major : String,
    val grade : Int,
    val credit : Int,
    val people : Int,
    val course : String
) : Parcelable