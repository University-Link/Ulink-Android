package com.example.ulink.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.ulink.*
import com.example.ulink.ScheduleRecycler.ScheduleItemData
import com.example.ulink.NoticeRecycler.ScheduleNoticeAdapter
import com.example.ulink.NoticeRecycler.emptyCheck
import com.example.ulink.repository.DataRepository
import com.example.ulink.repository.ResponseGetClassNotice
import com.example.ulink.repository.RetrofitService
import kotlinx.android.synthetic.main.activity_notice.*
import kotlinx.android.synthetic.main.toolbar_notice.*
import kotlinx.android.synthetic.main.toolbar_notice.btn_back
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        var idx = intent.getStringExtra("idx")
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
                finish()
            }
        })

        taskNoticeAdapter.setScheduleItemClickListener(object: ScheduleNoticeAdapter.ScheduleNoticeClickListener{
            override fun onClick(view: View, position:Int){
                val intent = Intent(view.context, ScheduleNoticeActivity::class.java)
                intent.putExtra("scheduleItemData", taskNoticeAdapter.datas[position])
                startActivity(intent)
                finish()
            }
        })

        classNoticeAdapter.setScheduleItemClickListener(object: ScheduleNoticeAdapter.ScheduleNoticeClickListener{
            override fun onClick(view: View, position:Int){
                val intent = Intent(view.context, ScheduleNoticeActivity::class.java)
                intent.putExtra("scheduleItemData", classNoticeAdapter.datas[position])
                startActivity(intent)
                finish()
            }
        })

        btn_back.setOnClickListener {
            finish()
        }

        btn_calendar.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("flag", true)
            startActivity(intent)
            finish()
        }
        btn_plus.setOnClickListener {
            val intent = Intent(this, NoticeAddActivity::class.java)
            intent.putExtra("idx", idx)
            intent.putExtra("class", className)
            intent.putExtra("addcheck", "add")
            startActivity(intent)
            finish()
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

        RetrofitService.service.getClassNotice(DataRepository.token, idx).enqueue(object : Callback<ResponseGetClassNotice> {
            override fun onFailure(call: Call<ResponseGetClassNotice>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<ResponseGetClassNotice>,
                response: Response<ResponseGetClassNotice>
            ) {
                response.body()?.let {
                    if (it.status == 200) {
                        Log.d("rjq", idx)
                        Log.d("rjq", it.toString())
                        if (it.data.assignment.isNotEmpty()) {
                            var size = it.data.assignment.size
                            for (i in 0 until size) {
                                taskData.apply {
                                    add(
                                        ScheduleItemData(
                                            idx = it.data.assignment[i].noticeIdx,
                                            date = it.data.assignment[i].date,
                                            category = "과제",
                                            classname = className,
                                            content = it.data.assignment[i].title,
                                            startTime = it.data.assignment[i].startTime,
                                            endTime = it.data.assignment[i].endTime,
                                            memo = ""
                                        )
                                    )
                                }
                                taskNoticeAdapter.notifyDataSetChanged()
                                emptyCheck(taskData, tv_task_notice_empty, rv_task_notice, tv_task_notice_more)
                            }
                        }
                        if (it.data.exam.isNotEmpty()) {
                            var size = it.data.exam.size
                            for (i in 0 until size) {
                                testData.apply {
                                    add(
                                        ScheduleItemData(
                                            idx = it.data.exam[i].noticeIdx,
                                            date = it.data.exam[i].date,
                                            category = "시험",
                                            classname = className,
                                            content = it.data.exam[i].title,
                                            startTime = it.data.exam[i].startTime,
                                            endTime = it.data.exam[i].endTime,
                                            memo = ""
                                        )
                                    )
                                }
                                testNoticeAdapter.notifyDataSetChanged()
                                emptyCheck(testData, tv_test_notice_empty, rv_test_notice, tv_test_notice_more)
                            }
                        }
                        if (it.data.lecture.isNotEmpty()) {
                            var size = it.data.lecture.size
                            for (i in 0 until size) {
                                classData.apply {
                                    add(
                                        ScheduleItemData(
                                            idx = it.data.lecture[i].noticeIdx,
                                            date = it.data.lecture[i].date,
                                            category = "수업",
                                            classname = className,
                                            content = it.data.lecture[i].title,
                                            startTime = it.data.lecture[i].startTime,
                                            endTime = it.data.lecture[i].endTime,
                                            memo = ""
                                        )
                                    )
                                }
                            }
                            classNoticeAdapter.notifyDataSetChanged()
                            emptyCheck(classData, tv_class_notice_empty, rv_class_notice, tv_class_notice_more)
                        }
                    }
                } ?: Log.d("tag", response.message())
            }
        })

      emptyCheck(taskData, tv_task_notice_empty, rv_task_notice, tv_task_notice_more)
      emptyCheck(testData, tv_test_notice_empty, rv_test_notice, tv_test_notice_more)
      emptyCheck(classData, tv_class_notice_empty, rv_class_notice, tv_class_notice_more)

    }

    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
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
    }*/
}