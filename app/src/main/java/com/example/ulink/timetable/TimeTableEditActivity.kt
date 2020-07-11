package com.example.ulink.timetable

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.ulink.R
import com.example.ulink.repository.Subject
import com.example.ulink.repository.TimeTable
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_time_table_edit.*

class TimeTableEditActivity : AppCompatActivity() {

    //    Timetable 다보여주고 마지막에 항상 하나 더 보여주는거 자동
    val timeTableList: MutableList<TimeTable> = arrayListOf()

    val mAdapter = TimeTableAddAdapter(this)


//    TODO Edit된 timetable 어떡할건지와 어떻게 edit할건지?
//    TimeTableFilterSearchFragment랑 TimeTableCandidatorFragment의 onclick을 얘가 받아서
//    timeTableList의 currentitem에 draw해줘야함

//    TODO
//     클릭했을때 깜빡이는거 해결

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

    fun formatToFloat(time: String): Float {
        val timesplit = time.split(":")
        return timesplit[0].toFloat() + (timesplit[1].toFloat() - timesplit[1].toFloat() % 15) / 60
    }

    fun checkIsOver(subject: Subject, timeTable: TimeTable): Boolean {

        var check = false
        for (s in timeTable.subjectList!!) {
            if (subject.day == s.day) {
                check = !(formatToFloat(subject.endtime) <= formatToFloat(s.starttime) || formatToFloat(subject.starttime) >= formatToFloat(s.endtime))
                if (check) return check
            }
        }
        return check
    }


    fun addToTable(subject: Subject) {
        val position = vp_timetableadd.currentItem

//        TODO 여기서 DB에 저장도 해야함
        val timeTable = mAdapter.timeTableList[position]
        if (!checkIsOver(subject, timeTable)) {
            timeTable.subjectList.add(subject)
            mAdapter.replaceAtList(position, timeTable)
            mAdapter.reDrawFragment(position)
            vp_timetableadd.setCurrentItem(position, false)
            Toast.makeText(this, "시간표에 등록 되었습니다", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "시간표에 등록 실패", Toast.LENGTH_SHORT).show()
        }

        Log.d("tag","addToTable = ${timeTable.toString()}")
    }

    fun addToSampleTable(subject: Subject) {
        val position = vp_timetableadd.currentItem

        val timeTable = mAdapter.timeTableSampleList[position]

//        samplelist의 timetable.subjectlist를 초기화 시킬까?
//        아니면 samplelist에서 subject의 isSample로 그거만 그릴까? 후자가 나은듯

        timeTable.subjectList.add(subject)
        mAdapter.replaceAtSampleList(position, timeTable)
        mAdapter.reDrawFragment(position)
        vp_timetableadd.setCurrentItem(position, false)

        Log.d("tag","addTosampleTable = ${timeTable.toString()}")
        Log.d("tag","addTosampleTable = ${mAdapter.timeTableList[position].toString()}")

    }


    fun rollBack(){
        mAdapter.timeTableSampleList[vp_timetableadd.currentItem] = mAdapter.timeTableList[vp_timetableadd.currentItem]
        mAdapter.reDrawFragment(vp_timetableadd.currentItem)
        vp_timetableadd.setCurrentItem(vp_timetableadd.currentItem, false)
        Log.d("tag","rollbacked")

    }


    fun showDialog() {

        val builder = AlertDialog.Builder(this)
        val layout = LayoutInflater.from(this).inflate(R.layout.dialog_timetable_name, null)
        builder.setView(layout)
        val dialog = builder.create()

        val et = layout.findViewById<EditText>(R.id.et_name)

        layout.findViewById<Button>(R.id.btn_ok).setOnClickListener {
            val timeTable = TimeTable(1, "2020-2", et.text.toString(), false, "09:00", "18:00")

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
        vp_timetableadd.setCurrentItem(mAdapter.timeTableList.size - 1, false)
    }


    fun dptopx(dp: Int): Float {
        val metrics = resources.displayMetrics
        return dp * ((metrics.densityDpi.toFloat()) / DisplayMetrics.DENSITY_DEFAULT)
    }

    fun setTimeTableAdd() {

//        TODO TimeTableFragment에서 받아와서 여기서 사용!
        mAdapter.setList(timeTableList)

        mAdapter.timeTableAddListener = object : TimeTableAddListener {
            override fun onAdded(timeTable: TimeTable) {
                moveToLastItem()
            }
        }
        vp_timetableadd.adapter = mAdapter

        vp_timetableadd.setPageTransformer(MarginPageTransformer(dptopx(30).toInt()))

        TabLayoutMediator(tl_indicator, vp_timetableadd) { v, p ->
            Unit
        }.attach()
    }


    fun setClassSearch() {
        val mEditorAdapter = TimeTableEditorAdapter(this)
        vp_timetableeditor.adapter = mEditorAdapter
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
            dialog.dismiss()

        }
        layout.findViewById<Button>(R.id.btn_grade3).setOnClickListener {
            dialog.dismiss()

        }
        layout.findViewById<Button>(R.id.btn_grade4).setOnClickListener {
            dialog.dismiss()

        }
        layout.findViewById<Button>(R.id.btn_grade5).setOnClickListener {
            dialog.dismiss()
        }

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialog.show()
    }

}