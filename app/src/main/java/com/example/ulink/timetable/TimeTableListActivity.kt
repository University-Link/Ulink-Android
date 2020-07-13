package com.example.ulink.timetable

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ulink.R
import com.example.ulink.repository.TimeTable
import kotlinx.android.synthetic.main.activity_time_table_list.*

class TimeTableListActivity : AppCompatActivity(), TimeTableOnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_table_list)

        val arrangedList: MutableList<MutableList<TimeTable>> = arrayListOf()

//     서버에서 데이터 받아오기
        //          TODO
//           intent로 받아올 필요있나? 걍 서버에서 계속 받아오자
//           clean architecture이용 local도 파야겠네
//            시간표 작성시 -> local, remote저장, cache invalidate'

        val list: MutableList<TimeTable> = arrayListOf()
        val tt = TimeTable(1, "2020-1", "시간표이름1", true, "09:00", "16:00")
        val tt2 = TimeTable(1, "2020-1", "시간표이름2", false, "09:00", "16:00")
        val tt3 = TimeTable(1, "2020-1", "시간표이름3", false, "09:00", "16:00")
        list.apply {
            add(tt)
            add(tt2)
            add(tt3)
        }

        val list2: MutableList<TimeTable> = arrayListOf()
        val tt4 = TimeTable(1, "2020-2", "시간표이름4", false, "09:00", "16:00")
        val tt5 = TimeTable(1, "2020-2", "시간표이름5", false, "09:00", "16:00")
        val tt6 = TimeTable(1, "2020-2", "시간표이름6", true, "09:00", "16:00")
        list2.apply {
            add(tt4)
            add(tt5)
            add(tt6)
        }


        arrangedList.add(list)
        arrangedList.add(list2)
//        TODO 여기 deepcopy조심

        val adapter = TimeTableListAdapter(arrangedList, this)
        rv_timetablelist.adapter = adapter

        val itemDecoration = TimeTableListDecoration(this)
        rv_timetablelist.addItemDecoration(itemDecoration)


    }

    override fun onClicked(timeTable: TimeTable) {
        val intent = Intent()
        intent.putExtra("timeTable", timeTable)
        setResult(200, intent)
        finish()
    }
}


interface TimeTableOnClickListener {
    fun onClicked(timeTable: TimeTable)
}