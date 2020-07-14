package com.example.ulink

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.ulink.NoticeRecycler.*
import com.example.ulink.ScheduleRecycler.*
import kotlinx.android.synthetic.main.activity_notice_add.*
import kotlinx.android.synthetic.main.toolbar_notice_add.*
import kotlinx.android.synthetic.main.toolbar_notice_add.btn_back
import java.text.SimpleDateFormat
import java.util.*

class NoticeAddActivity : AppCompatActivity() {
    lateinit var spinner_start: Spinner
    lateinit var spinner_end: Spinner

    var cal = Calendar.getInstance()
    private var datePickerYear = cal.get(Calendar.YEAR)
    private var datePickerMonth = cal.get(Calendar.MONTH)+1
    private var datePickerDay =  cal.get(Calendar.DATE)
    var category = ""
    var resultCode = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice_add)

        // intent
        var scheduleItemData = intent.getParcelableExtra<ScheduleItemData>("scheduleItemData")
        var intentClassName = intent.getStringExtra("class")

        //spinner
        spinner_start = findViewById(R.id.spinner_start)
        spinner_end = findViewById(R.id.spinner_end)
        val adapter = ArrayAdapter.createFromResource(this, R.array.time, android.R.layout.simple_spinner_item)
        spinnerInit(spinner_start, adapter)
        spinnerInit(spinner_end, adapter)

        // toolbartitle
        if(intentClassName != null)
            tv_classname.text = intentClassName+" 공지"

        // datainit
        if (scheduleItemData != null)
              intentNoticeAdd(
                  scheduleItemData, btn_task_notice, btn_test_notice, btn_class_notice,
                  tv_date, tv_classname, et_title, et_memo, spinner_start, spinner_end)

        // category
        rg_category.setOnCheckedChangeListener { rg_category, checkedRadioButtonId ->
            when(rg_category.checkedRadioButtonId){
                R.id.btn_task_notice-> category = "과제"
                R.id.btn_test_notice-> category = "시험"
                R.id.btn_class_notice-> category = "수업"
            }
        }

        // date
        tv_date.text = SimpleDateFormat("yyyy년 M월 dd일").format(System.currentTimeMillis())

        // register
        btn_check.setOnClickListener {
            when {
                //category == "" -> showToast("카테고리를 설정하세요.")
                //et_title.text.toString()=="" -> showToast("제목을 입력하세요.")
                else -> {
                    resultCode = resultCode(category)
                    var item = dataReturn(datePickerYear, datePickerMonth, datePickerDay, category, intentClassName, et_title, et_memo, spinner_start, spinner_end)

                    val intent = Intent(this, NoticeActivity::class.java)
                    intent.putExtra("data", item)
                    setResult(resultCode, intent) //서버랑통신할때수정이랑등록구분
                    finish()
                }
            }
        }

        btn_back.setOnClickListener {
            finish()
        }

        val dateSetListener = DatePickerDialog.OnDateSetListener{ view, year, monthOfYear, dayOfMonth ->
                    cal.set(Calendar.YEAR, year)
                    cal.set(Calendar.MONTH, monthOfYear)
                    cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                    val myFormat = "yyyy년 M월 dd일" // mention the format you need
                    val sdf = SimpleDateFormat(myFormat, Locale.US)
                    tv_date.text = sdf.format(cal.time)
                    val s = sdf.format(cal.time).split("년", "월", "일")
                    datePickerYear = Integer.parseInt(s[0].trim())
                    datePickerMonth = Integer.parseInt(s[1].trim())
                    datePickerDay = Integer.parseInt(s[2].trim())
                }


        tv_date.setOnClickListener {
            DatePickerDialog(this, dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        spinner_start.isFocused
    }
}