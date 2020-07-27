package com.ulink.ulink.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ulink.ulink.NotificationRecyclerAdapter
import com.ulink.ulink.R
import com.ulink.ulink.repository.Notification
import com.ulink.ulink.repository.NotificationDB

class NotificationActivity : AppCompatActivity() {

    var NotificationData : List<Notification>?  = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)


        val adapter = NotificationRecyclerAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.rv_notification)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


//       데이터 받아서 adapter에 넣어주기 전에
//       room하고 비교해서 새로운데이터 추가하고 넣어주기

        val NotificationDB = NotificationDB.getInstance(this)

        val r = Runnable {
            NotificationData = NotificationDB?.NotiDao()?.getAll()
//          나중에는 코루틴으로 묵자.. 서버에서 받아와서

        }
        val thread = Thread(r)
        thread.start()

    }
}