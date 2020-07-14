package com.example.ulink.timetable

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.ulink.R
import com.example.ulink.repository.Subject
import com.example.ulink.repository.TimeTable
import com.example.ulink.utils.deepCopy
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_time_table_edit.*


const val REQUEST_DIRECT_EDIT_ACTIVITY = 999
const val REQUEST_DIRECT_TYPE_ACTIVITY = 888

class TimeTableEditActivity : AppCompatActivity() {

    //    Timetable 다보여주고 마지막에 항상 하나 더 보여주는거 자동
    val timeTableList: MutableList<TimeTable> = arrayListOf()

    val mAdapter = TimeTableAddAdapter(this)


//    TODO Edit된 timetable 어떡할건지와 어떻게 edit할건지?
//    TimeTableFilterSearchFragment랑 TimeTableCandidatorFragment의 onclick을 얘가 받아서
//    timeTableList의 currentitem에 draw해줘야함
//    TODO
//     클릭했을때 깜빡이는거 해결

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_table_edit)

        intent.getParcelableArrayListExtra<TimeTable>("timeTableList")?.let {
            timeTableList.addAll(it)
        }

        Handler().postDelayed({

// TODO sharedpreference 사용해서 영구 저장
            showCheckGrade()
        }, 400)

        setTimeTableAdd()
        setClassSearch()

        btn_direct.setOnClickListener {
            showAddDialog()
        }
        btn_tableplus.setOnClickListener {
            showAddTableDialog()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_DIRECT_EDIT_ACTIVITY){
            if (resultCode == 200){
                val timeTableAdded = data?.getParcelableExtra<TimeTable>("timeTable")
                if (timeTableAdded != null) {
                    timeTableList[vp_timetableadd.currentItem] = deepCopy(timeTableAdded)
                    mAdapter.replaceAtList(vp_timetableadd.currentItem, deepCopy(timeTableAdded))
                    mAdapter.replaceAtSampleList(vp_timetableadd.currentItem, deepCopy(timeTableAdded))
                    mAdapter.reDrawFragment(vp_timetableadd.currentItem)
                    Log.d("tag", "timetable replaced")
                }
            }
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
                check = !(formatToFloat(subject.endTime) <= formatToFloat(s.startTime) || formatToFloat(subject.startTime) >= formatToFloat(s.endTime))
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
            subject.isSample = false
            timeTable.subjectList.add(subject)
            mAdapter.replaceAtList(position, timeTable)
            mAdapter.reDrawFragment(position)
            vp_timetableadd.setCurrentItem(position, false)
            Toast.makeText(this, "시간표에 등록 되었습니다", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "시간표에 등록 실패", Toast.LENGTH_SHORT).show()
        }

    }


    fun addToSampleTable(subject: Subject) {
        val position = vp_timetableadd.currentItem
        var timeTable: TimeTable = mAdapter.timeTableSampleList.get(position)
        timeTable.subjectList.add(subject)
        mAdapter.replaceAtSampleList(position, timeTable)
        mAdapter.reDrawFragment(position)
        mAdapter.scrollToPosition(position, subject)
        vp_timetableadd.setCurrentItem(position, false)

    }

//    TODO 미리보기 저장된거 색깔 왜 그러지 = Sample로 들어가서 그런듯! 추가할때는 sample말고 그냥으로!
//    DRAWER 고치기

    fun rollBack() {
        val position = vp_timetableadd.currentItem
        mAdapter.replaceAtSampleList(position, mAdapter.timeTableList[position])
        mAdapter.reDrawFragment(vp_timetableadd.currentItem)
        mAdapter.scrollToTop(position)
        vp_timetableadd.setCurrentItem(vp_timetableadd.currentItem, false)

    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun showAddTableDialog() {
        val builder = AlertDialog.Builder(this)
        val layout = LayoutInflater.from(this).inflate(R.layout.dialog_timetable_name, null)
        builder.setView(layout)
        val dialog = builder.create()

        val et = layout.findViewById<EditText>(R.id.et_name)

        layout.findViewById<TextView>(R.id.tv_ok).setOnClickListener {

//            TODO 여기서 DB로 저장하고 edit에 넣긴 해야함

            val timeTable = TimeTable(1, "2020-2", et.text.toString(), false, "09:00", "18:00")
//          EditActivity에 넣어줄 필요가 있나? 이걸
//            timeTableList.add(timeTable)

            mAdapter.addToList(deepCopy(timeTable))
            moveToLastItem()
            dialog.dismiss()
        }

        layout.findViewById<TextView>(R.id.tv_cancel).setOnClickListener {
            dialog.dismiss()
        }

        val back = ColorDrawable(Color.TRANSPARENT)
        val inset = InsetDrawable(back, 80)


        dialog.window?.setBackgroundDrawable(inset)

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

        var icon1 : ImageView

        TabLayoutMediator(tl_timetableeditor, vp_timetableeditor, object : TabLayoutMediator.TabConfigurationStrategy {
            override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                val tablayout = LayoutInflater.from(applicationContext).inflate(R.layout.tab_timetableeditor, null)

                icon1 =tablayout.findViewById<ImageView>(R.id.ic_tab)


                when (position) {
                    0 -> {
                        icon1.setBackgroundResource(R.drawable.timetableadd_filterandsearch_btn_filterandsearch)
                        tablayout.findViewById<TextView>(R.id.tv_tab).text = "필터"
                    }
                    1 -> {
                        icon1.setBackgroundResource(R.drawable.timetableadd_ic_filter)
                        tablayout.findViewById<ImageView>(R.id.ic_tab).setBackgroundResource(R.drawable.red_circle_for_alert)
                        tablayout.findViewById<TextView>(R.id.tv_tab).text = "후보"
                        icon1.setBackgroundResource(R.drawable.timetableadd_ic_filter)

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

        val back = ColorDrawable(Color.TRANSPARENT)
        val inset = InsetDrawable(back, 70)


        dialog.window?.setBackgroundDrawable(inset)

        dialog.show()
    }

    fun showAddDialog(){
        val builder = AlertDialog.Builder(this)
        val layout = LayoutInflater.from(this).inflate(R.layout.dialog_direct_add, null)
        builder.setView(layout)

        val dialog = builder.create()

        layout.findViewById<Button>(R.id.btn_drag).setOnClickListener {
            val intent = Intent(this, TimeTableDirectEditActivity::class.java)
            intent.putExtra("timeTable", deepCopy(mAdapter.timeTableList[vp_timetableadd.currentItem]))
            startActivityForResult(intent, REQUEST_DIRECT_EDIT_ACTIVITY)
            dialog.dismiss()
        }

        layout.findViewById<Button>(R.id.btn_type).setOnClickListener {
            //intent.putExtra("timeTable", deepCopy(mAdapter.timeTableList[vp_timetableadd.currentItem]))
            //startActivityForResult(intent, REQUEST_DIRECT_TYPE_ACTIVITY)

            dialog.dismiss()
        }
        layout.findViewById<Button>(R.id.btn_cancel).setOnClickListener {
            dialog.dismiss()
        }

        val back = ColorDrawable(Color.TRANSPARENT)
        val inset = InsetDrawable(back, 80)

        dialog.window?.setBackgroundDrawable(inset)

        dialog.show()
    }
}