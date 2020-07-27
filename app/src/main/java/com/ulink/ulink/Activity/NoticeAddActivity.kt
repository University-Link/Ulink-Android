package com.ulink.ulink.Activity

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.ulink.ulink.NoticeRecycler.*
import com.ulink.ulink.R
import com.ulink.ulink.ScheduleRecycler.*
import com.ulink.ulink.repository.*
import kotlinx.android.synthetic.main.activity_notice_add.*
import kotlinx.android.synthetic.main.toolbar_notice_add.*
import kotlinx.android.synthetic.main.toolbar_notice_add.btn_back
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice_add)

        var idx = intent.getStringExtra("idx")
        var noticeIdx = intent.getStringExtra("noticeIdx")

        // intent
        var scheduleItemData = intent.getParcelableExtra<ScheduleItemData>("scheduleItemData")
        var intentClassName = intent.getStringExtra("class")

        // date
        tv_date.text = SimpleDateFormat("yyyy년 MM월 dd일").format(System.currentTimeMillis())

        //spinner
        spinner_start = findViewById(R.id.spinner_start)
        spinner_end = findViewById(R.id.spinner_end)
        val adapter = ArrayAdapter.createFromResource(this,
            R.array.time, android.R.layout.simple_spinner_item)
        spinnerInit(spinner_start, adapter)
        spinnerInit(spinner_end, adapter)

        // toolbartitle
        if(intentClassName != null)
            tv_classname.text = intentClassName+" 공지"

        // datainit
        if (scheduleItemData != null) {
            category = intentNoticeAdd(
                scheduleItemData, btn_task_notice, btn_test_notice, btn_class_notice,
                tv_date, tv_classname, et_title, et_memo, spinner_start, spinner_end
            )

            val strData = scheduleItemData.date.split("-")
            datePickerYear = Integer.parseInt(strData[0].trim())
            datePickerMonth = Integer.parseInt(strData[1].trim())
            datePickerDay = Integer.parseInt(strData[2].trim())
        }

        // category
        btn_task_notice.setOnClickListener(){
            btn_task_notice.setBackgroundResource(R.drawable.btn_bg_notice_selected)
            btn_task_notice.setTextColor(Color.parseColor("#ffffff"))
            btn_test_notice.setBackgroundResource(R.drawable.btn_bg_notice)
            btn_test_notice.setTextColor(Color.parseColor("#727272"))
            btn_class_notice.setBackgroundResource(R.drawable.btn_bg_notice)
            btn_class_notice.setTextColor(Color.parseColor("#727272"))
            category="과제"
        }
        btn_test_notice.setOnClickListener(){
            btn_task_notice.setBackgroundResource(R.drawable.btn_bg_notice)
            btn_task_notice.setTextColor(Color.parseColor("#727272"))
            btn_test_notice.setBackgroundResource(R.drawable.btn_bg_notice_selected)
            btn_test_notice.setTextColor(Color.parseColor("#ffffff"))
            btn_class_notice.setBackgroundResource(R.drawable.btn_bg_notice)
            btn_class_notice.setTextColor(Color.parseColor("#727272"))
            category="시험"
        }
        btn_class_notice.setOnClickListener() {
            btn_test_notice.setBackgroundResource(R.drawable.btn_bg_notice)
            btn_test_notice.setTextColor(Color.parseColor("#727272"))
            btn_task_notice.setBackgroundResource(R.drawable.btn_bg_notice)
            btn_task_notice.setTextColor(Color.parseColor("#727272"))
            btn_class_notice.setBackgroundResource(R.drawable.btn_bg_notice_selected)
            btn_class_notice.setTextColor(Color.parseColor("#ffffff"))
            category = "수업"
        }

        val dateSetListener = DatePickerDialog.OnDateSetListener{ view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "yyyy년 MM월 dd일" // mention the format you need
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

        btn_check.setOnClickListener() {

            //정보를 가지고 item생성
            var item = dataReturn(
                datePickerYear,
                datePickerMonth,
                datePickerDay,
                category,
                intentClassName,
                et_title,
                et_memo,
                spinner_start,
                spinner_end,
                noticeIdx
            )

            //item을 가지고 requestBody생성
            var body = RequestRegisterNotice(
                category = item.category,
                date = item.date,
                startTime = item.startTime,
                endTime = item.endTime,
                title = item.content,
                content = item.memo
            )

            //intent
            var check = intent.getStringExtra("addcheck")
            Log.d("check", check.toString())

            //add = registerNotice
            if(check.equals("add")) {
                RetrofitService.service.registerNotice(DataRepository.token, idx, body)
                    .enqueue(object : Callback<ResponseRegisterNotice> {
                        override fun onFailure(call: Call<ResponseRegisterNotice>, t: Throwable) {
                            Log.d("실패", t.message.toString())
                        }
                        override fun onResponse(
                            call: Call<ResponseRegisterNotice>,
                            response: Response<ResponseRegisterNotice>
                        ) {
                            response.body()?.let {
                                if (it.status == 201) {
                                }
                            }
                        }
                    })
                //success -> NoticeActivity
                val intent = Intent(this, NoticeActivity::class.java)
                intent.putExtra("idx", idx)
                intent.putExtra("class", intentClassName)
                startActivity(intent)
                finish()
            }
            //revise = updateNotice
            else if(check.equals("revise")){
                Log.d("check", noticeIdx.toString())
                RetrofitService.service.updateNotice(DataRepository.token, noticeIdx, body)
                    .enqueue(object : Callback<ResponseUpdateNotice> {
                        override fun onFailure(call: Call<ResponseUpdateNotice>, t: Throwable) {
                            Log.d("수정실패", "수정실패")
                        }
                        override fun onResponse(
                            call: Call<ResponseUpdateNotice>,
                            response: Response<ResponseUpdateNotice>
                        ) {
                            response.body()?.let {
                                if (it.status == 204) {

                                }
                            } ?: Log.d("수정실패1", response.message())
                        }
                    })
                //success -> scheduleNoticeActivity
                val intent = Intent(this, ScheduleNoticeActivity::class.java)
                intent.putExtra("scheduleItemData", item)
                intent.putExtra("class", intentClassName)
                startActivity(intent)
                finish()
            }
        }
        btn_back.setOnClickListener {
            finish()
        }
    }
}