package com.ulink.ulink.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Notification::class], version = 1)
abstract class NotificationDB : RoomDatabase() {
    abstract fun NotiDao()  : NotificationDao

    companion object{

        private var Instance : NotificationDB? = null

        fun getInstance(context : Context) : NotificationDB?{
            if(Instance== null){
                synchronized(NotificationDB::class){
                    Instance = Room.databaseBuilder(context.applicationContext, NotificationDB::class.java ,"Notification.db").fallbackToDestructiveMigration().build()
                }
            }
            return Instance
        }

        fun destroyInstance(){
            Instance = null
        }

    }

}