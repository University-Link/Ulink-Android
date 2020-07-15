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
import com.example.ulink.repository.*
import com.example.ulink.utils.deepCopy
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_time_table_edit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

var token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4IjoxLCJuYW1lIjoi6rmA67O067CwIiwic2Nob29sIjoi7ZWc7JaR64yA7ZWZ6rWQIiwibWFqb3IiOiLshoztlITtirjsm6jslrQiLCJpYXQiOjE1OTQ3NDgyNTQsImV4cCI6MTU5NjE4ODI1NCwiaXNzIjoiYm9iYWUifQ.dFU9h8EZLqoMekAfRNTfGQkUAbq_CXoQmA5Jl7KsQ70"
const val REQUEST_DIRECT_EDIT_ACTIVITY = 999
const val REQUEST_DIRECT_TYPE_ACTIVITY = 888

class TimeTableEditActivity : AppCompatActivity(),getGradeClickListener {

    //    Timetable 다보여주고 마지막에 항상 하나 더 보여주는거 자동
    val timeTableList: MutableList<TimeTable> = arrayListOf()
    val mAdapter = TimeTableAddAdapter(this)
    val mEditorAdapter = TimeTableEditorAdapter(this)




    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_table_edit)

//      TODO 여기서 불러오기


        intent.getParcelableArrayListExtra<TimeTable>("timeTableList")?.let {
            timeTableList.addAll(it)
        }

        btn_cancel.setOnClickListener {
            finish()
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

    override fun onResume() {
        super.onResume()

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_DIRECT_EDIT_ACTIVITY) {
            if (resultCode == 200) {
                val timeTableAdded = data?.getParcelableExtra<TimeTable>("timeTable")
                if (timeTableAdded != null) {
                    timeTableList[vp_timetableadd.currentItem] = deepCopy(timeTableAdded)
                    mAdapter.replaceAtList(vp_timetableadd.currentItem, deepCopy(timeTableAdded))
                    mAdapter.replaceAtSampleList(vp_timetableadd.currentItem, deepCopy(timeTableAdded))
                    mAdapter.reDrawFragment(vp_timetableadd.currentItem)
                    Log.d("tag", "timetable replaced")
                }
            }
        } else if(requestCode == REQUEST_DIRECT_TYPE_ACTIVITY){
            if (resultCode == 200){
                val subjectList = data?.getParcelableArrayListExtra<Subject>("subjects")
//                TODO 여기 어떻게 등록할지?
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
        if (position == mAdapter.itemCount - 1) {
            return
        }
//        TODO 여기서 DB에 저장도 해야함
        val timeTable = mAdapter.timeTableList[position]

        if (!checkIsOver(subject, timeTable)) {
            subject.isSample = false

            DataRepository.addSchoolPlan(RequestAddSchoolPlan(
                  subject.id.toInt(), subject.color, timeTable.id
            ), onSuccess = {
                timeTable.subjectList.add(subject)
                mAdapter.replaceAtList(position, timeTable)
                mAdapter.reDrawFragment(position)
                vp_timetableadd.setCurrentItem(position, false)
                Toast.makeText(this, "시간표에 등록 되었습니다", Toast.LENGTH_SHORT).show()
            }, onFailure = {

            })
        } else {
            Toast.makeText(this, "시간표에 등록 실패", Toast.LENGTH_SHORT).show()
        }

    }


    fun addToSampleTable(subject: Subject) {
        val position = vp_timetableadd.currentItem
        if (position == mAdapter.itemCount - 1) {
            return
        }
        var timeTable: TimeTable = mAdapter.timeTableSampleList.get(position)
        
        timeTable.subjectList.add(subject)
        mAdapter.replaceAtSampleList(position, timeTable)
        mAdapter.reDrawFragment(position)
        mAdapter.scrollToPosition(position, subject)
        vp_timetableadd.setCurrentItem(position, false)

    }

    fun rollBack() {
        val position = vp_timetableadd.currentItem
        if (position == mAdapter.itemCount - 1) {
            return
        }
        Log.d("tag", vp_timetableadd.childCount.toString())
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

            DataRepository.addTimeTable("2020-2", et.text.toString(),
                    onSuccess = {
                        val timeTable = TimeTable(1, "2020-2", et.text.toString(), 0, "09:00", "18:00")
                        mAdapter.addToList(deepCopy(timeTable))
                        moveToLastItem()
                        Log.d("debug","${it.data.idx} 시간표 생성")
                    },
                    onFailure = {
                        Toast.makeText(this, "오류가 발생하였습니다", Toast.LENGTH_SHORT).show();
                        Log.d("error",it)
                    }
            )
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

        mEditorAdapter.setFragments()
        vp_timetableeditor.adapter = mEditorAdapter

        var icon1: ImageView

        TabLayoutMediator(tl_timetableeditor, vp_timetableeditor, object : TabLayoutMediator.TabConfigurationStrategy {
            override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                val tablayout = LayoutInflater.from(applicationContext).inflate(R.layout.tab_timetableeditor, null)

                icon1 = tablayout.findViewById<ImageView>(R.id.ic_tab)


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
                //1학년 필터
                onClick(1)
                dialog.dismiss()

        }
        layout.findViewById<Button>(R.id.btn_grade2).setOnClickListener {
            //2학년 필터
            onClick(2)
            dialog.dismiss()

        }
        layout.findViewById<Button>(R.id.btn_grade3).setOnClickListener {
            //3학년 필터
            onClick(3)
            dialog.dismiss()

        }
        layout.findViewById<Button>(R.id.btn_grade4).setOnClickListener {
            //4학년 필터
            onClick(4)
            dialog.dismiss()

        }
        layout.findViewById<Button>(R.id.btn_grade5).setOnClickListener {
            //5학년 필터
            onClick(5)
            dialog.dismiss()
        }

        val back = ColorDrawable(Color.TRANSPARENT)
        val inset = InsetDrawable(back, 70)

        dialog.window?.setBackgroundDrawable(inset)

        dialog.show()
    }

    fun showAddDialog() {
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
//            TODO 이거 해제
            val intent = Intent(this, TimeTableDirectTypeActivity::class.java)
            intent.putExtra("addable", true)
            intent.putExtra("timeTable", deepCopy(mAdapter.timeTableList[vp_timetableadd.currentItem]))
            startActivityForResult(intent, REQUEST_DIRECT_TYPE_ACTIVITY)

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

    override fun onClick(position: Int) {
        RetrofitService.service.getSubjectByGrade(token,position).enqueue(object : Callback<ResponseGetSubjectByGrade>{
            override fun onFailure(call: Call<ResponseGetSubjectByGrade>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<ResponseGetSubjectByGrade>,
                response: Response<ResponseGetSubjectByGrade>
            ) {
                response.body()?.let{
                    if(it.status == 200){
                        Log.d("성공",it.toString())
                        val list : MutableList<Subject> = arrayListOf()

                        for (i in it.data){
                            for (s in 0 until i.startTime.size){
                                val subject = Subject(i.subjectIdx,i.name,i.startTime[s],i.endTime[s],i.day[s],i.content[s],0,true,i.credit,i.professor,i.course,true,i.subjectCode)
                                list.add(subject)
                            }
                        }

//                        TODO 여기 나중에 정리하기 setList로
                        (mEditorAdapter.fragmentList[0] as TimeTableFilterSearchFragment).subjectList = list
                        (mEditorAdapter.fragmentList[0] as TimeTableFilterSearchFragment).mAdapter.subjectList = list
                        (mEditorAdapter.fragmentList[0] as TimeTableFilterSearchFragment).mAdapter.notifyDataSetChanged()

                    }else {
                        Log.d("실패", it.toString())
                    }
                }
            }

        })
    }
}
interface getGradeClickListener{
    fun onClick(position : Int)
}