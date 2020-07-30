package com.ulink.ulink.myActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.ulink.ulink.R
import kotlinx.android.synthetic.main.activity_my_activity.*
import kotlinx.android.synthetic.main.activity_time_table_edit.*

class MyActivityActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_activity)


        val type = intent.getIntExtra("type", 0)
        setViewpager(type)
        setTab()
    }


    fun setTab(){
        TabLayoutMediator(tl_myactivity, vp_myactivity, TabLayoutMediator.TabConfigurationStrategy { tab, position ->
            val tablayout = LayoutInflater.from(applicationContext).inflate(R.layout.tab_myactivity, null)
            var text = tablayout.findViewById<TextView>(R.id.tv_tab)

            when (position) {
                0 -> {
                    text.text = "내가 쓴 글"
                }
                1 -> {
                    text.text = "작성한 댓글"
                }
                2 -> {
                    text.text = "내가 쓴 글"
                }
            }
            tab.customView = tablayout
        }).attach()
    }

    fun setViewpager(type : Int){
        vp_myactivity.adapter = MyActivityAdapter(this)
        vp_myactivity.currentItem = type
    }
}