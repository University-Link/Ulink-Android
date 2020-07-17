package com.example.ulink.repository

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable


data class ResponsegetSubjectWithKeyWord (
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : List<String>
)