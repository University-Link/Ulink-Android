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
import androidx.viewpager2.widget.ViewPager2
import com.example.ulink.R
import com.example.ulink.R.*
import com.example.ulink.repository.*
import com.example.ulink.utils.deepCopy
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_time_table_edit.*
import kotlinx.android.synthetic.main.fragment_time_table.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.HashMap

const val REQUEST_DIRECT_EDIT_ACTIVITY = 999
const val REQUEST_DIRECT_TYPE_ACTIVITY = 888

class TimeTableEditActivity : AppCompatActivity(),getGradeClickListener {

    //    Timetable 다보여주고 마지막에 항상 하나 더 보여주는거 자동
    val timeTableList: MutableList<TimeTable> = arrayListOf()
    val mAdapter = TimeTableAddAdapter(this)
    val mEditorAdapter = TimeTableEditorAdapter(this)

    lateinit var semester : String
    var lastpage = 0


    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent()
        if (vp_timetableadd.currentItem < timeTableList.size){
            intent.putExtra("timeTable", timeTableList[lastpage])
            setResult(200, intent)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_time_table_edit)

//      여기서 set

        btn_cancel.setOnClickListener {
            val intent = Intent()
            if (vp_timetableadd.currentItem < timeTableList.size){
                intent.putExtra("timeTable", timeTableList[lastpage])
                setResult(200, intent)
            }
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

        //timeTableList[0].semester

        if (intent.getParcelableExtra<TimeTable>("timeTable") != null){
            semester = intent.getParcelableExtra<TimeTable>("timeTable")!!.semester
        }

        intent.getParcelableExtra<TimeTable>("timeTable")?.let { timeTable ->
            Log.d("tag",timeTable.toString())
            DataRepository.getTimeTableBySemester(timeTable.semester,
                onSuccess = {
                    for (table in it){
                        timeTableList.add(deepCopy(table))
                        setTimeTableAdd()
                        Log.d("tag", table.toString())
                    }
                },
                onFailure = {

                } )
        }





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
            for (b in 0 until subject.day.size){
                if (s.day.contains(subject.day[b])){
                    check = !(formatToFloat(subject.endTime[b]) <= formatToFloat(s.startTime[0]) || formatToFloat(subject.startTime[b]) >= formatToFloat(s.endTime[0]))
                    if (check) return check
                }
            }
        }
        return check
    }

    fun findNextColor(timeTable: TimeTable): Int {


        Log.d("tag",timeTable.toString())
        return timeTable.subjectList.size
    }

    fun addToTable(subject: Subject) {

        val position = vp_timetableadd.currentItem
        if (position == mAdapter.itemCount - 1) {
            return
        }
        val timeTable = mAdapter.timeTableList[position]

        if (!checkIsOver(subject, timeTable)) {

            subject.isSample = false

            Log.d("tagfindnextcolor", findNextColor(timeTable).toString())

            subject.color = findNextColor(timeTable)

            DataRepository.addSchoolPlan(RequestAddSchoolPlan(
                subject.id.toInt(), subject.color, timeTable.id
            ), onSuccess = {
                timeTable.subjectList.add(subject)
                mAdapter.replaceAtList(position, timeTable)
                mAdapter.reDrawFragment(position)
                vp_timetableadd.setCurrentItem(position, false)
                Toast.makeText(this, "시간표에 등록 되었습니다", Toast.LENGTH_SHORT).show()
            }, onFailure = {
                Toast.makeText(this, "시간표에 등록 실패", Toast.LENGTH_SHORT).show()
                Log.d("tag",it)
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

        Log.d("tag11111", timeTable.toString())

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
        mAdapter.replaceAtSampleList(position, mAdapter.timeTableList[position])
        mAdapter.reDrawFragment(vp_timetableadd.currentItem)
        mAdapter.scrollToTop(position)
        vp_timetableadd.setCurrentItem(vp_timetableadd.currentItem, false)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun showAddTableDialog() {
        val builder = AlertDialog.Builder(this)
        val layout = LayoutInflater.from(this).inflate(layout.dialog_timetable_name, null)
        builder.setView(layout)
        val dialog = builder.create()

        val et = layout.findViewById<EditText>(id.et_name)

        layout.findViewById<TextView>(id.tv_ok).setOnClickListener {
//            TODO 여기서 DB로 저장하고 edit에 넣긴 해야함

            DataRepository.addTimeTable(timeTableList[0].semester, et.text.toString(),
                onSuccess = {
                    val timeTable = TimeTable(it.data.idx.toInt(),timeTableList[0].semester , et.text.toString(), 0, "09:00", "18:00")
                    mAdapter.addToList(deepCopy(timeTable))
                    timeTableList.add(deepCopy(timeTable))
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

        layout.findViewById<TextView>(id.tv_cancel).setOnClickListener {
            dialog.dismiss()
            onBackPressed()
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
            override fun onAdded(name: String) {
                DataRepository.addTimeTable(timeTableList[0].semester, name,
                    onSuccess = {
                        val timeTable = TimeTable(it.data.idx.toInt(),timeTableList[0].semester ,name, 0, "09:00", "18:00")
                        mAdapter.addToList(deepCopy(timeTable))
                        timeTableList.add(deepCopy(timeTable))
                        moveToLastItem()
                        Log.d("debug","${it.data.idx} 시간표 생성")

                    },
                    onFailure = {
                        Toast.makeText(this@TimeTableEditActivity, "오류가 발생하였습니다", Toast.LENGTH_SHORT).show();
                        Log.d("error",it)
                    }
                )
            }
        }
        vp_timetableadd.adapter = mAdapter
        vp_timetableadd.setPageTransformer(MarginPageTransformer(dptopx(30).toInt()))

        TabLayoutMediator(tl_indicator, vp_timetableadd) { v, p ->
            Unit
        }.attach()

        vp_timetableadd.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                lastpage = position
            }
        })

    }


    fun setClassSearch() {

        mEditorAdapter.setFragments()
        vp_timetableeditor.adapter = mEditorAdapter


        TabLayoutMediator(tl_timetableeditor, vp_timetableeditor, object : TabLayoutMediator.TabConfigurationStrategy {
            override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                val tablayout = LayoutInflater.from(applicationContext).inflate(layout.tab_timetableeditor, null)
                var filterIcon = tablayout.findViewById<ImageView>(id.ic_tab)
                var cartIcon = tablayout.findViewById<ImageView>(id.ic_tab)
                var filterText = tablayout.findViewById<TextView>(id.tv_tab)
                var cartText = tablayout.findViewById<TextView>(id.tv_tab)
                when (position) {
                    0 -> {
                        filterIcon.setBackgroundResource(drawable.timetableadd_ic_filter_selected)
                        filterText.text = "필터 및 검색"
                    }
                    1 -> {
                        cartIcon.setBackgroundResource(drawable.timetableadd_ic_cart_selected)
                        cartText.text = "후보"
                    }
                }
                tab.customView = tablayout
            }
        }).attach()

    }



    @SuppressLint("ResourceType")
    fun showCheckGrade() {
        val builder = AlertDialog.Builder(this)
        val layout = LayoutInflater.from(this).inflate(layout.dialog_timetable_checkgrade, null)
        builder.setView(layout)

        val dialog = builder.create()

        layout.findViewById<Button>(id.btn_grade1).setOnClickListener {
            it.setBackgroundColor(resources.getColor(color.black))
            //1학년 필터
            onClick(1)
            dialog.dismiss()

        }
        layout.findViewById<Button>(id.btn_grade2).setOnClickListener {
            //2학년 필터
            onClick(2)
            dialog.dismiss()

        }
        layout.findViewById<Button>(id.btn_grade3).setOnClickListener {
            //3학년 필터
            onClick(3)
            dialog.dismiss()

        }
        layout.findViewById<Button>(id.btn_grade4).setOnClickListener {
            //4학년 필터
            onClick(4)
            dialog.dismiss()

        }
        layout.findViewById<Button>(id.btn_grade5).setOnClickListener {
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
        val layout = LayoutInflater.from(this).inflate(layout.dialog_direct_add, null)
        builder.setView(layout)

        val dialog = builder.create()

        layout.findViewById<Button>(id.btn_drag).setOnClickListener {
            val intent = Intent(this, TimeTableDirectEditActivity::class.java)
            intent.putExtra("timeTable", deepCopy(mAdapter.timeTableList[vp_timetableadd.currentItem]))
            startActivityForResult(intent, REQUEST_DIRECT_EDIT_ACTIVITY)
            dialog.dismiss()
        }

        layout.findViewById<Button>(id.btn_type).setOnClickListener {
//            TODO 이거 해제
            val intent = Intent(this, TimeTableDirectTypeActivity::class.java)
            intent.putExtra("addable", true)
            intent.putExtra("timeTable", deepCopy(mAdapter.timeTableList[vp_timetableadd.currentItem]))
            startActivityForResult(intent, REQUEST_DIRECT_TYPE_ACTIVITY)

            dialog.dismiss()
        }

        layout.findViewById<Button>(id.btn_cancel).setOnClickListener {
            dialog.dismiss()
        }

        val back = ColorDrawable(Color.TRANSPARENT)
        val inset = InsetDrawable(back, 80)

        dialog.window?.setBackgroundDrawable(inset)

        dialog.show()
    }

    override fun onClick(position: Int) {
        RetrofitService.service.getSubjectByGrade(DataRepository.token,position).enqueue(object : Callback<ResponseGetSubjectByGrade>{
            override fun onFailure(call: Call<ResponseGetSubjectByGrade>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<ResponseGetSubjectByGrade>,
                response: Response<ResponseGetSubjectByGrade>
            ) {
                response.body()?.let{
                    if(it.status == 200){
                        val list : MutableList<Subject> = arrayListOf()

                        for (i in it.data){
                            val subject = Subject(i.subjectIdx,i.name,i.startTime,i.endTime,i.day,i.content,0,true,i.credit,i.professor,i.course,true,i.subjectCode,i.subjectIdx.toInt())
                            list.add(subject)
                        }
                        Log.d("tag",list.toString())

//                        TODO 여기 나중에 정리하기 setList로
                        (mEditorAdapter.fragmentList[0] as TimeTableFilterSearchFragment).subjectList = list
                        (mEditorAdapter.fragmentList[0] as TimeTableFilterSearchFragment).mAdapter.subjectList = list
                        (mEditorAdapter.fragmentList[0] as TimeTableFilterSearchFragment).mAdapter.notifyDataSetChanged()
                    }else {

                    }
                }
            }

        })
    }

    fun getSemesterFromActivity() : String = semester
    fun getTimeTableFromActivity() : TimeTable = mAdapter.timeTableList[vp_timetableadd.currentItem]

}
interface getGradeClickListener{
    fun onClick(position : Int)
}