package com.example.ulink

import android.content.Intent
import android.graphics.Insets.add
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.ulink.ClassNoticeRecycler.ClassNoticeAdapter
import com.example.ulink.ClassNoticeRecycler.ClassNoticeData
import com.example.ulink.ClassRecycler.ClassAdapter
import com.example.ulink.ExperimentNoticeRecycler.ExperimentNoticeAdapter
import com.example.ulink.ExperimentNoticeRecycler.ExperimentNoticeData
import com.example.ulink.TestNoticeRecycler.TestNoticeAdapter
import com.example.ulink.TestNoticeRecycler.TestNoticeData
import com.example.ulink.TaskNoticeRecycler.TaskNoticeAdapter
import com.example.ulink.TaskNoticeRecycler.TaskNoticeData
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_chatting.*
import kotlinx.android.synthetic.main.activity_notice.*
import kotlinx.android.synthetic.main.item_class_notice.*
import kotlinx.android.synthetic.main.toolbar_notice.*
import kotlinx.android.synthetic.main.toolbar_notice.btn_back
import kotlinx.android.synthetic.main.toolbar_notice_add.*

class NoticeActivity : AppCompatActivity(){
    lateinit var TestNoticeAdapter : TestNoticeAdapter
    lateinit var TaskNoticeAdapter : TaskNoticeAdapter
    lateinit var ClassNoticeAdapter : ClassNoticeAdapter

    lateinit var rv_test_notice :RecyclerView
    lateinit var rv_task_notice :RecyclerView
    lateinit var rv_class_notice :RecyclerView

    val datas : MutableList<TestNoticeData> = mutableListOf<TestNoticeData>()
    val datas1: MutableList<TaskNoticeData> = mutableListOf<TaskNoticeData>()
    val datas2: MutableList<ClassNoticeData> = mutableListOf<ClassNoticeData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice)

        rv_test_notice = findViewById(R.id.rv_test_notice)
        rv_task_notice = findViewById(R.id.rv_task_notice)
        rv_class_notice = findViewById(R.id.rv_class_notice)

        TestNoticeAdapter = TestNoticeAdapter(this)
        TaskNoticeAdapter = TaskNoticeAdapter(this)
        ClassNoticeAdapter = ClassNoticeAdapter(this)

        rv_test_notice.adapter = TestNoticeAdapter
        rv_task_notice.adapter = TaskNoticeAdapter
        rv_class_notice.adapter = ClassNoticeAdapter

        btn_back.setOnClickListener {
            finish()
        }

        btn_home.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        btn_plus.setOnClickListener {
            val intent = Intent(this, NoticeAddActivity::class.java)
            startActivityForResult(intent,100)
        }

        tv_task_more.setOnClickListener {
            val intent = Intent(this, NoticeTaskMoreActivity::class.java)
            startActivity(intent)
        }
        tv_task_more1.setOnClickListener {
            val intent = Intent(this, NoticeTaskMoreActivity::class.java)
            startActivity(intent)
        }

        tv_test_more.setOnClickListener {
            val intent = Intent(this, NoticeTestMoreActivity::class.java)
            startActivity(intent)
        }
        tv_class_more.setOnClickListener {
            val intent = Intent(this, NoticeClassMoreActivity::class.java)
            startActivity(intent)
        }
        tv_class_more1.setOnClickListener {
            val intent = Intent(this, NoticeClassMoreActivity::class.java)
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
                    TestName = "중간고사",
                    StartTime = "13:00",
                    EndTime = "14:00"


                )
            )
            add(
                TestNoticeData(
                    StartDate = 5,
                    EndDate = 13,
                    TestName = "중간고사",
                    StartTime = "13:00",
                    EndTime = "14:00"


                )
            )
            add(
                TestNoticeData(
                    StartDate = 5,
                    EndDate = 13,
                    TestName = "중간고사",
                    StartTime = "13:00",
                    EndTime = "14:00"


                )
            )
            add(
                TestNoticeData(
                    StartDate = 5,
                    EndDate = 13,
                    TestName = "중간고사",
                    StartTime = "13:00",
                    EndTime = "14:00"

                )
            )
            add(
                TestNoticeData(
                    StartDate = 5,
                    EndDate = 13,
                    TestName = "중간고사",
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
                    StartTime = "13:00",
                    EndTime = "14:00"
                    )
            )
            add(
                TaskNoticeData(
                    StartDate = 5,
                    EndDate = 13,
                    TaskName = "연습문제 풀기",
                    StartTime = "13:00",
                    EndTime = "14:00"
                )
            )
            add(
                TaskNoticeData(
                    StartDate = 5,
                    EndDate = 13,
                    TaskName = "연습문제 풀기",
                    StartTime = "13:00",
                    EndTime = "14:00"
                )
            )
            add(
                TaskNoticeData(
                    StartDate = 5,
                    EndDate = 13,
                    TaskName = "연습문제 풀기",
                    StartTime = "13:00",
                    EndTime = "14:00"
                )
            )
        }
        TaskNoticeAdapter.datas = datas1
        TaskNoticeAdapter.notifyDataSetChanged()
    }
    private fun loadDatas2() {
        datas2.apply {
            add(
                ClassNoticeData(
                    StartDate = 5,
                    EndDate = 4,
                    ClassName = "휴강",
                    StartTime= "시간정보없음",
                    EndTime = ""
                )
            )
            add(
                ClassNoticeData(
                    StartDate = 6,
                    EndDate = 10,
                    ClassName = "수업시간변경",
                    StartTime= "16:00",
                    EndTime = "17:00"
                )
            )

        }
        ClassNoticeAdapter.datas = datas2
        ClassNoticeAdapter.notifyDataSetChanged()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100) {
            if (resultCode == 200) {
                if (data != null) {
                    datas1.add(data.getParcelableExtra("task_data"))
                    TaskNoticeAdapter.notifyDataSetChanged()

                }
            }
            if (data != null) {

                if (resultCode == 300) {
                    datas.add(data.getParcelableExtra("test_data"))
                    TestNoticeAdapter.notifyDataSetChanged()
                }
            }
            if (data != null) {

                if (resultCode == 400) {
                    datas2.add(data.getParcelableExtra("class_data"))
                    ClassNoticeAdapter.notifyDataSetChanged()
                }
            }
        }
    }
}