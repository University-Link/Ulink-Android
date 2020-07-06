package com.example.ulink.repository

import androidx.room.*

@Dao
interface NotificationDao {

    @Query("SELECT * FROM Notification")
    fun getAll() : List<Notification>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(noti : Notification)

    @Delete
    fun delete(noti: Notification)


}