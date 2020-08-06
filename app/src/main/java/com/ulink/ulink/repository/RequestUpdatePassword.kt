package com.ulink.ulink.repository

data class RequestUpdatePassword(
        val password : String,
        val newPassword : String
)