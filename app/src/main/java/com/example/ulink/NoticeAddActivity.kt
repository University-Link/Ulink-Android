package com.example.ulink

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.ulink.ClassNoticeRecycler.ClassNoticeData
import com.example.ulink.TaskNoticeRecycler.TaskNoticeAdapter
import com.example.ulink.TaskNoticeRecycler.TaskNoticeData
import com.example.ulink.TestNoticeRecycler.TestNoticeData
import kotlinx.android.synthetic.main.activity_notice_add.*
import kotlinx.android.synthetic.main.toolbar_notice_add.*
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

        tv_date.text = SimpleDateFormat("yyyy년 M월 d일").format(System.currentTimeMillis())


        btn_task_notice.setOnClickListener {

            if(task_check){
                btn_task_notice.setBackgroundColor(Color.parseColor("#674FEE"))
                btn_task_notice.setTextColor(Color.parseColor("#ffffff"))
                c1 = true
                task_check = false
                Log.d("c1",c1.toString())
                Log.d("c2",c2.toString())
                Log.d("c3",c3.toString())
            }else{
                btn_task_notice.setBackgroundColor(Color.parseColor("#b4b4b4"))
                btn_task_notice.setTextColor(Color.parseColor("#000000"))
                task_check = true
            }

        }
        btn_test_notice.setOnClickListener {

            if(test_check){
                btn_test_notice.setBackgroundColor(Color.parseColor("#674FEE"))
                btn_test_notice.setTextColor(Color.parseColor("#ffffff"))
                c2 = true
                test_check = false
                Log.d("c1",c1.toString())
                Log.d("c2",c2.toString())
                Log.d("c3",c3.toString())
            }else{
                btn_test_notice.setBackgroundColor(Color.parseColor("#b4b4b4"))
                btn_test_notice.setTextColor(Color.parseColor("#000000"))
                test_check = true
            }
        }
        btn_class_notice.setOnClickListener {

            if(class_check){
                btn_class_notice.setBackgroundColor(Color.parseColor("#674FEE"))
                btn_class_notice.setTextColor(Color.parseColor("#ffffff"))
                class_check = false
                c3 = true
                Log.d("c1",c1.toString())
                Log.d("c2",c2.toString())
                Log.d("c3",c3.toString())
            }else{
                btn_class_notice.setBackgroundColor(Color.parseColor("#b4b4b4"))
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

                val myFormat = "yyyy년 M월 d일" // mention the format you need
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                tv_date.text = sdf.format(cal.time)
                val s = sdf.format(cal.time).split("년","월","일")
                month = Integer.parseInt(s[1].trim())
                day = Integer.parseInt(s[2].trim())

            }


        tv_date.setOnClickListener {
            DatePickerDialog(
                this, dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()

        }

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.time,
            android.R.layout.simple_spinner_item
        )

        val adapter2 = ArrayAdapter.createFromResource(
            this,
            R.array.time,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        spinner_start.adapter = adapter
        spinner_start.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                start_time = parent?.getItemAtPosition(position).toString()

            }

        }

        spinner_end.adapter = adapter2
        spinner_end.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                end_time = parent?.getItemAtPosition(position).toString()

            }

        }
    }
}




