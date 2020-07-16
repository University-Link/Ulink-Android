package com.example.ulink.repository

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable


data class ResponsegetSubjectWithWord (
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : List<SearchedData>
)

@Parcelize
data class SearchedData(
    val subjectIdx: Long,
    val name: String,
    val professor: String,
    val credit: Float,
    val course: String,
    val startTime: List<String>,
    val endTime: List<String>,
    val day: List<Int>,
    val content: List<String>
) : Parcelable