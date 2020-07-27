package com.ulink.ulink.utils

import com.ulink.ulink.ScheduleRecycler.ScheduleItemData
import com.ulink.ulink.repository.TimeTable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> deepCopy(any: T) :  T{
    val Json = Gson().toJson(any)
    return Gson().fromJson(Json, T::class.java)
}

fun deepCopyRetrofit (tableList : List<TimeTable>) : MutableList<TimeTable> {
    val type = object : TypeToken<List<TimeTable>>(){}.type
    val Json = Gson().toJson(tableList)
    return Gson().fromJson(Json, type)
}

fun deepCopySchedule (tableList : List<ScheduleItemData>) : MutableList<ScheduleItemData> {
    val type = object : TypeToken<List<ScheduleItemData>>(){}.type
    val Json = Gson().toJson(tableList)
    return Gson().fromJson(Json, type)
}