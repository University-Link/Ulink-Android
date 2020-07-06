package com.example.ulink.repository

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notification")
class Notification (
        @PrimaryKey(autoGenerate = true)
        var id : Long?,
        @ColumnInfo(name = "title")
        var title : String,
        @ColumnInfo(name = "content")
        var content : String,
        @ColumnInfo(name = "time")
        var time : String,
        @ColumnInfo(name = "color")
        var color : String,
        @ColumnInfo(name = "read")
        var read : Boolean
)