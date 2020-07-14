package com.example.ulink.timetable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ExpandableListView
import android.widget.ImageView
import com.example.ulink.R
import kotlinx.android.synthetic.main.activity_time_class_filter.*

class TimeTableClassFilterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_class_filter)

        val majorList = arrayListOf<String>("바이오시스템의과학부","필수교양", "선택교양","타전공","일반교양")

        val bioList = arrayListOf<String>("분자생물학", "유전학", "생화학")
        val pilsuList = arrayListOf<String>("필수교양1", "영어토론과발표", "영어 읽기와 쓰기")

        val dataList: HashMap<String, List<String>> = hashMapOf(
                majorList[0] to bioList, majorList[1] to pilsuList
        )

        val adapter = TimeTableExpandableAdapter(this)
        adapter.majorNameList = majorList
        adapter.majorDataList = dataList

        ev_major.setAdapter(adapter)


        ev_major.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
            v.findViewById<ImageView>(R.id.iv_circle).setBackgroundResource(R.drawable.io_main_majorsetting_btn_round_selected)
            return@setOnChildClickListener true
        }



    }


}