package com.example.ulink

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.ulink.ScheduleRecycler.ScheduleItemData
import com.example.ulink.NoticeRecycler.ScheduleNoticeAdapter
import com.example.ulink.NoticeRecycler.emptyCheck
import com.example.ulink.repository.RetrofitService
import kotlinx.android.synthetic.main.activity_notice.*
import kotlinx.android.synthetic.main.toolbar_notice.*
import kotlinx.android.synthetic.main.toolbar_notice.btn_back

class NoticeActivity : AppCompatActivity(){

    lateinit var testNoticeAdapter : ScheduleNoticeAdapter
    lateinit var taskNoticeAdapter : ScheduleNoticeAdapter
    lateinit var classNoticeAdapter : ScheduleNoticeAdapter

    private val testData = mutableListOf<ScheduleItemData>()
    private val taskData = mutableListOf<ScheduleItemData>()
    private val classData = mutableListOf<ScheduleItemData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice)


        var className = intent.getStringExtra("class")
        if(className!="") tv_classname.text = className+" 공지"

        testNoticeAdapter = ScheduleNoticeAdapter(this)
        taskNoticeAdapter = ScheduleNoticeAdapter(this)
        classNoticeAdapter = ScheduleNoticeAdapter(this)

        testNoticeAdapter.datas = testData
        rv_test_notice.adapter = testNoticeAdapter

        taskNoticeAdapter.datas = taskData
        rv_task_notice.adapter = taskNoticeAdapter

        classNoticeAdapter.datas = classData
        rv_class_notice.adapter = classNoticeAdapter

        testNoticeAdapter.setScheduleItemClickListener(object: ScheduleNoticeAdapter.ScheduleNoticeClickListener{
            override fun onClick(view: View, position:Int){
                val intent = Intent(view.context, ScheduleNoticeActivity::class.java)
                intent.putExtra("scheduleItemData", testNoticeAdapter.datas[position])
                startActivity(intent)
            }
        })

        taskNoticeAdapter.setScheduleItemClickListener(object: ScheduleNoticeAdapter.ScheduleNoticeClickListener{
            override fun onClick(view: View, position:Int){
                val intent = Intent(view.context, ScheduleNoticeActivity::class.java)
                intent.putExtra("scheduleItemData", taskNoticeAdapter.datas[position])
                startActivity(intent)
            }
        })

        classNoticeAdapter.setScheduleItemClickListener(object: ScheduleNoticeAdapter.ScheduleNoticeClickListener{
            override fun onClick(view: View, position:Int){
                val intent = Intent(view.context, ScheduleNoticeActivity::class.java)
                intent.putExtra("scheduleItemData", classNoticeAdapter.datas[position])
                startActivity(intent)
            }
        })

        btn_back.setOnClickListener {
            finish()
        }

        btn_home.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        btn_plus.setOnClickListener {
            val intent = Intent(this, NoticeAddActivity::class.java)
            intent.putExtra("class", className)
            startActivityForResult(intent,100)
        }
        tv_task_notice_more.setOnClickListener {
            val intent = Intent(this, NoticeMoreActivity::class.java)
            intent.putParcelableArrayListExtra("rvData", ArrayList(taskData))
            intent.putExtra("category", "과제")
            startActivity(intent)
        }
        tv_test_notice_more.setOnClickListener {
            val intent = Intent(this, NoticeMoreActivity::class.java)
            intent.putParcelableArrayListExtra("rvData", ArrayList(testData))
            intent.putExtra("category", "시험")
            startActivity(intent)
        }
        tv_class_notice_more.setOnClickListener {
            val intent = Intent(this, NoticeMoreActivity::class.java)
            intent.putParcelableArrayListExtra("rvData", ArrayList(classData))
            intent.putExtra("category", "수업")
            startActivity(intent)
        }

        emptyCheck(taskData, tv_task_notice_empty, rv_task_notice, tv_task_notice_more)
        emptyCheck(testData, tv_test_notice_empty, rv_test_notice, tv_test_notice_more)
        emptyCheck(classData, tv_class_notice_empty, rv_class_notice, tv_class_notice_more)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && data != null) {
            if (resultCode == 200) {
                taskNoticeAdapter.datas.add(data.getParcelableExtra("data"))
                taskNoticeAdapter.notifyDataSetChanged()
            }
            if (resultCode == 300) {
                testNoticeAdapter.datas.add(data.getParcelableExtra("data"))
                testNoticeAdapter.notifyDataSetChanged()
            }
            if (resultCode == 400) {
                classNoticeAdapter.datas.add(data.getParcelableExtra("data"))
                classNoticeAdapter.notifyDataSetChanged()
            }
        }

        // empty again check
        emptyCheck(taskData, tv_task_notice_empty, rv_task_notice, tv_task_notice_more)
        emptyCheck(testData, tv_test_notice_empty, rv_test_notice, tv_test_notice_more)
        emptyCheck(classData, tv_class_notice_empty, rv_class_notice, tv_class_notice_more)
    }
}