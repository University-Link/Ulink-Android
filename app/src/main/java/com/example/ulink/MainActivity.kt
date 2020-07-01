package com.example.ulink

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nv_timetable -> main_viewPager.currentItem = 0
                R.id.nv_class -> main_viewPager.currentItem = 1
                R.id.nv_ulink -> main_viewPager.currentItem = 2
                R.id.nv_calendar -> main_viewPager.currentItem = 3
                R.id.nv_my -> main_viewPager.currentItem = 4
            }
            true
        }
    }
}