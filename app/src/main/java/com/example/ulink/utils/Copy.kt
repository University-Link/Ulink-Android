package com.example.ulink.utils

import com.google.gson.Gson

inline fun <reified T> deepCopy(any: T) :  T{
    val Json = Gson().toJson(any)
    return Gson().fromJson(Json, T::class.java)
}