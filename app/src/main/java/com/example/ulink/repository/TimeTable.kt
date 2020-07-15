package com.example.ulink.repository

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "TimeTable")
@Parcelize
data class TimeTable(
        @PrimaryKey
        @SerializedName("scheduleIdx")
        var id: Int,
        @ColumnInfo(name = "semester")
        @SerializedName("semester")
        var semester: String = "",
        @ColumnInfo(name = "name")
        var name: String,
        @ColumnInfo(name = "main")
        var isMain: Int,
        @ColumnInfo(name = "startTime")
        var startTime: String = "09:00",
        @ColumnInfo(name = "endTime")
        var endTime: String = "18:00",
//    TODO 여기 바꿔야함 DB에 객체 리스트 못넣음
        @ColumnInfo(name = "list")
        var subjectList: MutableList<Subject> = arrayListOf()
) : Parcelable {
        constructor() : this(0,"","",0,"09:00","18:00", arrayListOf())
}