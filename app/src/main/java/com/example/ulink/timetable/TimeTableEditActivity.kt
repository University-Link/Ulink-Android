package com.example.ulink.timetable

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.ulink.R
import com.example.ulink.repository.TimeTable
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_time_table_edit.*

class TimeTableEditActivity : AppCompatActivity() {

    //    Timetable 다보여주고 마지막에 항상 하나 더 보여주는거 자동
    val timeTableList: MutableList<TimeTable> = arrayListOf()

    val mAdapter = TimeTableAddAdapter(this)


//    TODO Edit된 timetable 어떡할건지와 어떻게 edit할건지?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_table_edit)

        intent.getParcelableArrayListExtra<TimeTable>("timeTableList")?.let {
            timeTableList.addAll(it)
        }

        Handler().postDelayed({
            showCheckGrade()
        }, 400)

        setTimeTableAdd()
        setClassSearch()

        btn_direct.setOnClickListener {

        }
        btn_tableplus.setOnClickListener {
            showDialog()
        }
    }


    fun showDialog() {

        val builder = AlertDialog.Builder(this)
        val layout = LayoutInflater.from(this).inflate(R.layout.dialog_timetable_name, null)
        builder.setView(layout)
        val dialog = builder.create()

        val et = layout.findViewById<EditText>(R.id.et_name)

        layout.findViewById<Button>(R.id.btn_ok).setOnClickListener {
            val timeTable = TimeTable(1, "2020-2", et.text.toString(), null, false, "09:00", "18:00")

//          EditActivity에 넣어줄 필요가 있나? 이걸
//            timeTableList.add(timeTable)
            mAdapter.addToList(timeTable)
            moveToLastItem()
            dialog.dismiss()
        }

        layout.findViewById<Button>(R.id.btn_cancel).setOnClickListener {
            dialog.dismiss()
        }

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialog.show()
    }


    fun moveToLastItem() {
        vp_timetableadd.adapter = mAdapter
        vp_timetableadd.setCurrentItem(mAdapter.timetableList.size - 1, false)
    }

    fun setTimeTableAdd() {

//        TODO TimeTableFragment에서 받아와서 여기서 사용!
        mAdapter.setList(timeTableList)

        mAdapter.timeTableAddListener = object : timeTableAddListener {
            override fun onAdded(timeTable: TimeTable) {
                moveToLastItem()
            }
        }

        vp_timetableadd.adapter = mAdapter

        TabLayoutMediator(tl_indicator, vp_timetableadd) { v, p ->
            Unit
        }.attach()
    }


    fun setClassSearch() {
        vp_timetableeditor.adapter = TimeTableEditorAdapter(this)
        TabLayoutMediator(tl_timetableeditor, vp_timetableeditor, object : TabLayoutMediator.TabConfigurationStrategy {
            override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {

                val tablayout = LayoutInflater.from(applicationContext).inflate(R.layout.tab_timetableeditor, null)

                when (position) {
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
    fun showCheckGrade() {
        val builder = AlertDialog.Builder(this)
        val layout = LayoutInflater.from(this).inflate(R.layout.dialog_timetable_checkgrade, null)
        builder.setView(layout)

        val dialog = builder.create()



        layout.findViewById<Button>(R.id.btn_grade1).setOnClickListener {
            it.setBackgroundColor(resources.getColor(R.color.black))
            Handler().postDelayed({
                dialog.dismiss()
            }, 200)

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