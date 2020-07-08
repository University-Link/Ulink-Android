package com.example.ulink.repository

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "TimeTable")
@Parcelize
data class TimeTable (
    @PrimaryKey
    var id : Long,
    @ColumnInfo(name = "semester")
    var semseter :  String,
    @ColumnInfo(name = "name")
    var name : String,
//    TODO 여기 바꿔야함 DB에 객체 리스트 못넣음
    @ColumnInfo(name = "list")
    var subjectList : MutableList<Subject>?,
    @ColumnInfo(name = "main")
    var isMain : Boolean?,
    @ColumnInfo(name = "startTime")
    var startTime : String,
    @ColumnInfo(name = "endTime")
    var endTime : String
) : Parcelable