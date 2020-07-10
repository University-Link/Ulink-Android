package com.example.ulink

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.ulink.ClassNoticeRecycler.ClassNoticeData
import com.example.ulink.ScheduleRecycler.ScheduleItemData
import com.example.ulink.ScheduleRecycler.intentNoticeAdd
import com.example.ulink.ScheduleRecycler.spinnerInit
import com.example.ulink.TaskNoticeRecycler.TaskNoticeAdapter
import com.example.ulink.TaskNoticeRecycler.TaskNoticeData
import com.example.ulink.TestNoticeRecycler.TestNoticeData
import kotlinx.android.synthetic.main.activity_class_notice.*
import kotlinx.android.synthetic.main.activity_notice_add.*
import kotlinx.android.synthetic.main.toolbar_notice_add.*
import kotlinx.android.synthetic.main.toolbar_notice_add.btn_back
import kotlinx.android.synthetic.main.toolbar_schedulenotice.*
import java.text.SimpleDateFormat
import java.util.*

class NoticeAddActivity : AppCompatActivity() {
    lateinit var spinner_start: Spinner
    lateinit var spinner_end: Spinner

    lateinit var start_time: String
    lateinit var end_time: String

    private var month=0
    private var day =0

    var task_check: Boolean = false
    var test_check: Boolean = false
    var class_check: Boolean = false

    var c1:Boolean = false
    var c2:Boolean = false
    var c3:Boolean = false

    lateinit var task_item :TaskNoticeData
    lateinit var test_item :TestNoticeData
    lateinit var class_item :ClassNoticeData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice_add)

        spinner_start = findViewById(R.id.spinner_start)
        spinner_end = findViewById(R.id.spinner_end)

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.time,
            android.R.layout.simple_spinner_item
        )

        spinnerInit(spinner_start, adapter)
        spinnerInit(spinner_end, adapter)

        tv_date.text = SimpleDateFormat("yyyy년 MM월 dd일").format(System.currentTimeMillis())

        var scheduleItemData = intent.getParcelableExtra<ScheduleItemData>("scheduleItemData")
        if(scheduleItemData!=null)
            intentNoticeAdd(scheduleItemData, tv_date, tv_classname, et_title, et_memo, spinner_start, spinner_end )

        btn_task_notice.setOnClickListener {
            if(task_check){
                btn_task_notice.setBackgroundResource(R.drawable.btn_bg_notice_selected)
                btn_task_notice.setTextColor(Color.parseColor("#ffffff"))
                c1 = true
                task_check = false
                Log.d("c1",c1.toString())
                Log.d("c2",c2.toString())
                Log.d("c3",c3.toString())
            }else{
                btn_task_notice.setBackgroundResource(R.drawable.btn_bg_notice)
                btn_task_notice.setTextColor(Color.parseColor("#000000"))
                task_check = true
            }
        }

        btn_test_notice.setOnClickListener {
            if(test_check){
                btn_test_notice.setBackgroundResource(R.drawable.btn_bg_notice_selected)
                btn_test_notice.setTextColor(Color.parseColor("#ffffff"))
                c2 = true
                test_check = false
                Log.d("c1",c1.toString())
                Log.d("c2",c2.toString())
                Log.d("c3",c3.toString())
            }else{
                btn_test_notice.setBackgroundResource(R.drawable.btn_bg_notice)
                btn_test_notice.setTextColor(Color.parseColor("#000000"))
                test_check = true
            }
        }

        btn_class_notice.setOnClickListener {
            if(class_check){
                btn_class_notice.setBackgroundResource(R.drawable.btn_bg_notice_selected)
                btn_class_notice.setTextColor(Color.parseColor("#ffffff"))
                class_check = false
                c3 = true
                Log.d("c1",c1.toString())
                Log.d("c2",c2.toString())
                Log.d("c3",c3.toString())
            }else{
                btn_class_notice.setBackgroundResource(R.drawable.btn_bg_notice)
                btn_class_notice.setTextColor(Color.parseColor("#000000"))
                class_check = true
            }
        }


        //설정한값 리사이클러뷰로 들어가게
        btn_check.setOnClickListener {
            if(c2){
                test_item =  TestNoticeData(
                    StartDate = month,
                    EndDate = day,
                    TestName = tv_title.text.toString(),
                    StartTime = start_time,
                    EndTime = end_time
                )
                val intent = Intent(this,NoticeActivity::class.java)
                intent.putExtra("test_data",test_item)
                setResult(300,intent)

            }else if(c1){
                task_item = TaskNoticeData(
                    StartDate = month,
                    EndDate = day,
                    TaskName = tv_title.text.toString(),
                    StartTime = start_time,
                    EndTime = end_time
                )
                val intent = Intent(this,NoticeActivity::class.java)
                intent.putExtra("task_data",task_item)
                setResult(200,intent)


            }else if(c3){
                class_item =
                    ClassNoticeData(
                        StartDate = month,
                        EndDate = day,
                        ClassName = tv_title.text.toString(),
                        StartTime = start_time,
                        EndTime = end_time
                    )
                val intent = Intent(this,NoticeActivity::class.java)
                intent.putExtra("class_data",class_item)
                setResult(400,intent)
            }
            finish()
        }

        btn_back.setOnClickListener {
            finish()
        }

        var cal = Calendar.getInstance()

        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "yyyy년 MM월 dd일" // mention the format you need
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                tv_date.text = sdf.format(cal.time)
                val s = sdf.format(cal.time).split("년","월","일")
                month = Integer.parseInt(s[1].trim())
                day = Integer.parseInt(s[2].trim()) }


        tv_date.setOnClickListener {
            DatePickerDialog(
                this, dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }
}




