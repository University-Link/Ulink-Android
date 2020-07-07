package com.example.ulink.timetable

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.ulink.R
import com.example.ulink.repository.TimeTable
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_time_table_edit.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TimeTableEdit : AppCompatActivity() {

    val timeTableList : MutableList<TimeTable> = arrayListOf()
    val mAdapter = TimeTableAddAdapter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_table_edit)

        intent.getParcelableArrayListExtra<TimeTable>("timeTableList")?.let {
            timeTableList.addAll(it)
            Log.d("tag","fasdf")
        }

        Handler().postDelayed({
            showCheckGrade()
        }, 400)

        setTimeTableAdd()
        setClassSearch()

        btn_direct.setOnClickListener {


        }
        btn_tableplus.setOnClickListener {
            mAdapter.toIndex(timeTableList.size)

        }


    }

    fun setTimeTableAdd(){
//        TODO TimeTableFragment에서 받아와서 여기서 사용!
        mAdapter.setList(timeTableList)


        vp_timetableadd.adapter = mAdapter
        TabLayoutMediator(tl_indicator,vp_timetableadd){
            v, p -> Unit
        }.attach()
    }


    fun setClassSearch(){
        vp_timetableeditor.adapter = TimeTableEdiotrAdapter(this)
        TabLayoutMediator(tl_timetableeditor,vp_timetableeditor, object : TabLayoutMediator.TabConfigurationStrategy{
            override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                val tablayout = LayoutInflater.from(applicationContext).inflate(R.layout.tab_timetableeditor, null)

                when(position){
                    0 -> {
                        tablayout.findViewById<ImageView>(R.id.ic_tab).setBackgroundResource(R.drawable.red_circle_for_alert)
                        tablayout.findViewById<TextView>(R.id.tv_tab).text = "필터"
                    }
                    1 -> {
                        tablayout.findViewById<ImageView>(R.id.ic_tab).setBackgroundResource(R.drawable.red_circle_for_alert)
                        tablayout.findViewById<TextView>(R.id.tv_tab).text = "후보"
                    }
                }
                tab.customView = tablayout
            }
        }).attach()


    }

    @SuppressLint("ResourceType")
    fun showCheckGrade(){
        val builder = AlertDialog.Builder(this)
        val layout = LayoutInflater.from(this).inflate(R.layout.dialog_timetable_checkgrade, null)
        builder.setView(layout)

        val dialog = builder.create()



        layout.findViewById<Button>(R.id.btn_grade1).setOnClickListener {
            it.setBackgroundColor(resources.getColor(R.color.black))
            Handler().postDelayed({
                dialog.dismiss()            }, 200)

        }
        layout.findViewById<Button>(R.id.btn_grade2).setOnClickListener {
            it.setBackgroundColor(resources.getColor(Color.parseColor("#674FEE")))
            dialog.dismiss()

        }
        layout.findViewById<Button>(R.id.btn_grade3).setOnClickListener {
            it.setBackgroundColor(resources.getColor(Color.parseColor("#674FEE")))
            dialog.dismiss()

        }
        layout.findViewById<Button>(R.id.btn_grade4).setOnClickListener {
            it.setBackgroundColor(resources.getColor(Color.parseColor("#674FEE")))
            dialog.dismiss()

        }
        layout.findViewById<Button>(R.id.btn_grade5).setOnClickListener {
            it.setBackgroundColor(resources.getColor(Color.parseColor("#674FEE")))
            dialog.dismiss()
        }

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialog.show()
    }

}