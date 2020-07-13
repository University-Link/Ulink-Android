package com.example.ulink

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager.widget.ViewPager
import com.example.ulink.repository.ResponseMainTimeTable
import com.example.ulink.repository.RetrofitService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4Ijo1LCJuYW1lIjoi6rmA67O067CwIiwic2Nob29sIjoi6rCA7LKc64yA7ZWZ6rWQIiwibWFqb3IiOm51bGwsImlhdCI6MTU5NDY2Mzc0MSwiZXhwIjoxNTk0NzUwMTQxLCJpc3MiOiJib2JhZSJ9.y5Vawy70KroWOrZ9QJxCrrZUVEqy5sQ8v-uWEpAlYoo"

        Log.d("tag", "requested")
        RetrofitService.service.getMainTimeTable(token).enqueue(object : Callback<ResponseMainTimeTable>{
            override fun onFailure(call: Call<ResponseMainTimeTable>, t: Throwable) {
                Log.d("tag", "failed")
                Log.d("tag", t.localizedMessage)
            }

            override fun onResponse(call: Call<ResponseMainTimeTable>, response: Response<ResponseMainTimeTable>) {
                response.body()?.let {
                    if (it.status == 200){
                        Log.d("tag",it.data.timeTable.startTime)
                    } else {
                        Log.d("tag","애매")
                    }
                }?: Log.d("tag", "??")
            }
        })



        main_viewPager.adapter = MainPagerAdapter(supportFragmentManager)
        main_viewPager.offscreenPageLimit = 2
        main_viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }
            override fun onPageSelected(position: Int) {
                bottomNavigationView.menu.getItem(position).isChecked = true
            }
        })

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nv_timetable -> main_viewPager.currentItem = 0
                R.id.nv_class -> main_viewPager.currentItem = 1
                R.id.nv_calendar -> main_viewPager.currentItem = 2
                R.id.nv_my -> main_viewPager.currentItem = 4
            }
            true
        }
    }

}