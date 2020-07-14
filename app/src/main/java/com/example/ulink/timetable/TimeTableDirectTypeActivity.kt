package com.example.ulink.timetable

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import android.widget.TimePicker.OnTimeChangedListener
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.ulink.R
import com.example.ulink.TimeTableDirectRecycler.TimeTableDirectAdapter
import com.example.ulink.TimeTableDirectRecycler.TimeTableDirectData
import com.example.ulink.showToast
import kotlinx.android.synthetic.main.activity_direct_type_time_table.*
import kotlinx.android.synthetic.main.toolbar_direct_time_table.*

//타임피커 커스
const val DEFAULT_INTERVAL = 15
const val MINUTES_MIN = 0
const val MINUTES_MAX = 60

class TimeTableDirectTypeActivity : AppCompatActivity(), onClickListener {

    lateinit var time: String
    var check: Boolean = false

    lateinit var day: String
    lateinit var start: String
    lateinit var end: String

    lateinit var TimeTableDirectAdapter: TimeTableDirectAdapter
    val datas: MutableList<TimeTableDirectData> = mutableListOf<TimeTableDirectData>()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_direct_type_time_table)

        time_picker.setTimeInterval() // 시간 간격을 15분 단위로 설정

        TimeTableDirectAdapter = TimeTableDirectAdapter(this, this)
        rv_time_add.adapter = TimeTableDirectAdapter
        lateinit var title: String

        var textCheck = "#ffffff"
        var textUncheck = "#727272"


        btn_check.setOnClickListener() {
            if (et_title.text.toString() == "") showToast("제목을 설정해주세요.")
        }
    }
}