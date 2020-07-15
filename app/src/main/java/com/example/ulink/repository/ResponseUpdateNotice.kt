package com.example.ulink.repository

data class ResponseUpdateNotice (
    val status: Int,
    val success: Boolean,
    val message : String,
    val data: UpdateNotice
)

data class UpdateNotice(
    val idx : String
)