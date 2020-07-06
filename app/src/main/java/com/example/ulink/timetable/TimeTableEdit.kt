package com.example.ulink.timetable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.example.ulink.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_time_table_edit.*

class TimeTableEdit : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_table_edit)


        val timetableDrawer = TimeTableDrawer(applicationContext, LayoutInflater.from(applicationContext))
        timetableDrawer.draw(findViewById<FrameLayout>(R.id.layout_timetable))

        setClassSearch()



    }




    fun setClassSearch(){
        vp_timetableeditor.adapter = TimeTableEdiotrAdapter(this)
        TabLayoutMediator(tl_timetableeditor,vp_timetableeditor, object : TabLayoutMediator.TabConfigurationStrategy{
            override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                val tablayout = LayoutInflater.from(applicationContext).inflate(R.layout.tab_timetableeditor, null)

                when(position){
                    0 -> {
                        tablayout.findViewById<ImageView>(R.id.ic_tab).setBackgroundResource(R.drawable.red_circle_for_alert)
                        tablayout.findViewById<TextView>(R.id.tv_tab).text = "후보"
                    }
                    1 -> {
                        tablayout.findViewById<ImageView>(R.id.ic_tab).setBackgroundResource(R.drawable.red_circle_for_alert)
                        tablayout.findViewById<TextView>(R.id.tv_tab).text = "필터"
                    }
                }
                tab.customView = tablayout
            }
        }).attach()


    }

}