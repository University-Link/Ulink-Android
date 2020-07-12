package com.example.ulink

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class chatdata(
    val uid : String,
    val userName : String
)