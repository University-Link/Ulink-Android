package com.example.ulink

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.ulink.ClassNoticeRecycler.ClassNoticeAdapter
import com.example.ulink.ClassNoticeRecycler.ClassNoticeData
import com.example.ulink.ExperimentNoticeRecycler.ExperimentNoticeAdapter
import com.example.ulink.ExperimentNoticeRecycler.ExperimentNoticeData
import com.example.ulink.TestNoticeRecycler.TestNoticeAdapter
import com.example.ulink.TestNoticeRecycler.TestNoticeData
import com.example.ulink.TaskNoticeRecycler.TaskNoticeAdapter
import com.example.ulink.TaskNoticeRecycler.TaskNoticeData
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_chatting.*
import kotlinx.android.synthetic.main.activity_notice.*
import kotlinx.android.synthetic.main.toolbar_classnotice.*

class NoticeActivity : AppCompatActivity(){
    lateinit var TestNoticeAdapter : TestNoticeAdapter
    lateinit var TaskNoticeAdapter : TaskNoticeAdapter
    lateinit var ClassNoticeAdapter : ClassNoticeAdapter
    lateinit var ExperimentNoticeAdapter : ExperimentNoticeAdapter
    val datas : MutableList<TestNoticeData> = mutableListOf<TestNoticeData>()
    val datas1: MutableList<TaskNoticeData> = mutableListOf<TaskNoticeData>()
    val datas2: MutableList<ClassNoticeData> = mutableListOf<ClassNoticeData>()
    val datas3: MutableList<ExperimentNoticeData> = mutableListOf<ExperimentNoticeData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice)

        TestNoticeAdapter = TestNoticeAdapter(this)
        TaskNoticeAdapter = TaskNoticeAdapter(this)
        ClassNoticeAdapter = ClassNoticeAdapter(this)
        ExperimentNoticeAdapter = ExperimentNoticeAdapter(this)

        rv_test_notice.adapter = TestNoticeAdapter
        rv_task_notice.adapter = TaskNoticeAdapter
        rv_class_notice.adapter = ClassNoticeAdapter

        btn_home.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        loadDatas()
        loadDatas1()
        loadDatas2()

        if(datas.isEmpty()){
            Log.d("datas상태",datas.isEmpty().toString())
            test_notice_nothing.visibility = View.VISIBLE
            rv_test_notice.visibility=View.INVISIBLE
        }else{
            test_notice_nothing.visibility = View.INVISIBLE
            rv_test_notice.visibility=View.VISIBLE
        }

        if(datas1.isEmpty()){
            Log.d("datas1상태",datas1.isEmpty().toString())
            task_notice_nothing.visibility = View.VISIBLE
            rv_task_notice.visibility=View.INVISIBLE
        }else{
            task_notice_nothing.visibility = View.INVISIBLE
            rv_task_notice.visibility=View.VISIBLE
        }
        if(datas2.isEmpty()){
            Log.d("datas2상태",datas2.isEmpty().toString())
            class_notice_nothing.visibility = View.VISIBLE
            rv_class_notice.visibility=View.INVISIBLE
        }else{
            class_notice_nothing.visibility = View.INVISIBLE
            rv_class_notice.visibility=View.VISIBLE
        }

    }

    private fun loadDatas() {
        datas.apply {
            add(
                TestNoticeData(
                    StartDate = 5,
                    EndDate = 13,
                    NoticeName = "중간고사",
                    StartTime = "13:00",
                    EndTime = "14:00"


                )
            )
            add(
                TestNoticeData(
                    StartDate = 5,
                    EndDate = 13,
                    NoticeName = "중간고사",
                    StartTime = "13:00",
                    EndTime = "14:00"


                )
            )
            add(
                TestNoticeData(
                    StartDate = 5,
                    EndDate = 13,
                    NoticeName = "중간고사",
                    StartTime = "13:00",
                    EndTime = "14:00"


                )
            )
            add(
                TestNoticeData(
                    StartDate = 5,
                    EndDate = 13,
                    NoticeName = "중간고사",
                    StartTime = "13:00",
                    EndTime = "14:00"

                )
            )
            add(
                TestNoticeData(
                    StartDate = 5,
                    EndDate = 13,
                    NoticeName = "중간고사",
                    StartTime = "13:00",
                    EndTime = "14:00"

                )
            )
        }
        TestNoticeAdapter.datas = datas
        TestNoticeAdapter.notifyDataSetChanged()
    }
    private fun loadDatas1() {
        datas1.apply {
            add(
                TaskNoticeData(
                    StartDate = 5,
                    EndDate = 13,
                    TaskName = "연습문제 풀기",
                    Task = "범위 143쪽 10문제"

                    )
            )
            add(
                TaskNoticeData(
                    StartDate = 5,
                    EndDate = 13,
                    TaskName = "연습문제 풀기",
                    Task = "범위 143쪽 10문제"

                )
            )
            add(
                TaskNoticeData(
                    StartDate = 5,
                    EndDate = 13,
                    TaskName = "연습문제 풀기",
                    Task = "범위 143쪽 10문제"

                )
            )
            add(
                TaskNoticeData(
                    StartDate = 5,
                    EndDate = 13,
                    TaskName = "연습문제 풀기",
                    Task = "범위 143쪽 10문제"

                )
            )
        }
        TaskNoticeAdapter.datas = datas1
        TaskNoticeAdapter.notifyDataSetChanged()
    }
    private fun loadDatas2() {
        datas2.apply {
//            add(
//                ClassNoticeData(
//                    StartDate = 5,
//                    EndDate = 4,
//                    ClassName = "휴강",
//                    Class="교수님의 개인사정으로 휴강합니다."
//                )
//            )

        }
        ClassNoticeAdapter.datas = datas2
        ClassNoticeAdapter.notifyDataSetChanged()
    }


}