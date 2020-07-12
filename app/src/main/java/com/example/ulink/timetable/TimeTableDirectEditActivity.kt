package com.example.ulink.timetable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.widget.NestedScrollView
import com.example.ulink.R
import com.example.ulink.repository.Subject
import com.example.ulink.repository.TimeTable
import kotlinx.android.synthetic.main.activity_time_table_direct_edit.*
import java.util.ArrayList


class TimeTableDirectEditActivity : AppCompatActivity() {

    lateinit var timeTableDrawerDrag : TimeTableDrawerDrag

    @ExperimentalStdlibApi
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_table_direct_edit)

        val timeTable = intent.getParcelableExtra<TimeTable>("timeTable")

        timeTableDrawerDrag = TimeTableDrawerDrag(this, LayoutInflater.from(this), timeTable)

        timeTableDrawerDrag.draw(findViewById<FrameLayout>(R.id.layout_timetable))
        findViewById<NestedScrollView>(R.id.layout_timetable_scrollable).isVerticalScrollBarEnabled = false


        Handler().postDelayed({
            setDragView()
        }, 100)


        findViewById<Button>(R.id.btn_cancel).setOnClickListener {
            finish()
        }

        findViewById<Button>(R.id.btn_retry).setOnClickListener {
            rollBack()
        }

        findViewById<TextView>(R.id.btn_ok).setOnClickListener {
            val intent = Intent()
            intent.putExtra("timeTable", timeTableDrawerDrag.getTable())
            setResult(200, intent)
            finish()
        }
    }


    @ExperimentalStdlibApi
    fun rollBack(){
        timeTableDrawerDrag.rollBack()
    }

    fun setDragView(){
        timeTableDrawerDrag.setDragView((findViewById<LinearLayout>(R.id.timetable_root)))
    }


}