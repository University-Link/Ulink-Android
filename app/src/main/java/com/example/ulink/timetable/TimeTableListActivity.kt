package com.example.ulink.timetable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ulink.R
import kotlinx.android.synthetic.main.activity_time_table_list.*

class TimeTableListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_table_list)

        val adapter = TimeTableListAdapter()
        rv_timetablelist.adapter = adapter
    }
}