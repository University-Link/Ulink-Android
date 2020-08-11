package com.ulink.ulink.Ulink.ulinknotice

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ulink.ulink.R
import com.ulink.ulink.repository.DataRepository
import com.ulink.ulink.repository.RetrofitService
import com.ulink.ulink.utils.DialogBuilder
import kotlinx.android.synthetic.main.activity_notice_write.*
import kotlinx.android.synthetic.main.activity_notice_write.tv_category
import kotlinx.android.synthetic.main.activity_notice_write.tv_toolbar_title
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class NoticeWriteActivity : AppCompatActivity() {

    var cal = Calendar.getInstance()
    private var datePickerYear = ""
    private var datePickerMonth = ""
    private var datePickerDay =  ""

    var category = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice_write)

        val categoryList = resources.getStringArray(R.array.category)
        val timeList = resources.getStringArray(R.array.time)

        val subjectName = intent.getStringExtra("subjectName")
        val subjectIdx = intent.getStringExtra("subjectIdx")
        val noticeIdx = intent.getIntExtra("noticeIdx", 0)
        val noticeDetail = intent.getParcelableExtra<RequestNoticeAdd>("noticeDetail")

        if(subjectName!="")
            tv_toolbar_title.text = subjectName+" 공지"

        if(noticeDetail!=null) {
            category = intentNoticeAdd(
                noticeDetail,
                tv_category,
                tv_write_date,
                tv_start_time,
                tv_end_time,
                et_notice_title,
                et_notice_content
            )

            val strIntentDate = noticeDetail.date.split("-")
            datePickerYear = strIntentDate[0].trim()
            datePickerMonth = strIntentDate[1].trim()
            datePickerDay = strIntentDate[2].trim()
        }
        else
            tv_write_date.text = SimpleDateFormat("yyyy년 M월 d일").format(System.currentTimeMillis())

        btn_back.setOnClickListener{
            finish()
        }

        layout_select_category.setOnClickListener {
            val builder = android.app.AlertDialog.Builder(this)
            val layout = LayoutInflater.from(this).inflate(R.layout.custom_picker, null)

            val categoryPicker = layout.findViewById<NumberPicker>(R.id.picker)
            categoryPicker.displayedValues = categoryList
            categoryPicker.minValue=0
            categoryPicker.maxValue=categoryList.size-1
            categoryPicker.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS

            if(noticeDetail!=null){
                for(i in categoryList.indices){
                    if(categoryList[i]==noticeDetail.category)
                        categoryPicker.value=i
                }
            }
            else {
                for (i in categoryList.indices) {
                    if (categoryList[i] == "수업")
                        categoryPicker.value = i
                }
            }

            builder.setView(layout)

            var dialog = builder.create()

            layout.findViewById<TextView>(R.id.tv_picker_cancel).setOnClickListener {
                dialog.dismiss()
            }

            layout.findViewById<TextView>(R.id.tv_picker_confirm).setOnClickListener {
                category = categoryList[categoryPicker.value]
                tv_category.text = categoryList[categoryPicker.value]
                dialog.dismiss()
            }

            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()

            var width = resources.getDimensionPixelSize(R.dimen.picker_width)
            var height = resources.getDimensionPixelSize(R.dimen.picker_height)
            dialog.window?.setLayout(width, height)
        }

        val dateSetListener = DatePickerDialog.OnDateSetListener{view, year, monthOfYear, dayOfMonth ->

            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val format = "yyyy년 M월 d일"
            val sdf = SimpleDateFormat(format, Locale.US)
            tv_write_date.text = sdf.format(cal.time)
            tv_write_date.setTextColor(Color.parseColor("#363636"))
            val strDate = sdf.format(cal.time).split("년", "월", "일")
            datePickerYear = strDate[0].trim()
            datePickerMonth = strDate[1].trim()
            datePickerDay = strDate[2].trim()
        }

        tv_write_date.setOnClickListener {
            DatePickerDialog(this, dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        tv_start_time.setOnClickListener {
            val builder = android.app.AlertDialog.Builder(this)
            val layout = LayoutInflater.from(this).inflate(R.layout.custom_picker, null)

            layout.findViewById<TextView>(R.id.tv_picker_title).text = "시작시간"
            val timePicker = layout.findViewById<NumberPicker>(R.id.picker)
            timePicker.displayedValues = timeList
            timePicker.minValue=0
            timePicker.maxValue=timeList.size-1
            timePicker.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS

            if(noticeDetail!=null){
                for(i in timeList.indices){
                    if(timeList[i]==noticeDetail.startTime)
                        timePicker.value=i
                    if(noticeDetail.startTime=="-1"){
                        timePicker.value=0
                        break;
                    }
                }
            }

            builder.setView(layout)

            var dialog = builder.create()

            layout.findViewById<TextView>(R.id.tv_picker_cancel).setOnClickListener {
                dialog.dismiss()
            }

            layout.findViewById<TextView>(R.id.tv_picker_confirm).setOnClickListener {
                tv_start_time.text = timeList[timePicker.value]
                dialog.dismiss()
            }

            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()

            var width = resources.getDimensionPixelSize(R.dimen.picker_width)
            var height = resources.getDimensionPixelSize(R.dimen.picker_height)
            dialog.window?.setLayout(width, height)
        }

        tv_end_time.setOnClickListener {
            val builder = android.app.AlertDialog.Builder(this)
            val layout = LayoutInflater.from(this).inflate(R.layout.custom_picker, null)

            layout.findViewById<TextView>(R.id.tv_picker_title).text = "종료시간"
            val timePicker = layout.findViewById<NumberPicker>(R.id.picker)
            timePicker.displayedValues = timeList
            timePicker.minValue=0
            timePicker.maxValue=timeList.size-1
            timePicker.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS


            if(noticeDetail!=null){
                for(i in timeList.indices){
                    if(timeList[i]==noticeDetail.endTime)
                        timePicker.value=i
                    if(noticeDetail.endTime=="-1") {
                        timePicker.value=0
                        break;
                    }
                }
            }

            builder.setView(layout)

            var dialog = builder.create()

            layout.findViewById<TextView>(R.id.tv_picker_cancel).setOnClickListener {
                dialog.dismiss()
            }

            layout.findViewById<TextView>(R.id.tv_picker_confirm).setOnClickListener {
                tv_end_time.text = timeList[timePicker.value]
                dialog.dismiss()
            }

            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()

            var width = resources.getDimensionPixelSize(R.dimen.picker_width)
            var height = resources.getDimensionPixelSize(R.dimen.picker_height)
            dialog.window?.setLayout(width, height)
        }

        btn_confirm.setOnClickListener {
            if(category == ""){
                DialogBuilder().apply {
                    build(this@NoticeWriteActivity)
                    setContent(getString(R.string.category_empty))
                    setClickListener {
                        dismiss()
                    }
                    show()
                }
            }
            else if(datePickerYear=="" || datePickerMonth=="" || datePickerDay==""){
                DialogBuilder().apply {
                    build(this@NoticeWriteActivity)
                    setContent(getString(R.string.date_empty))
                    setClickListener {
                        dismiss()
                    }
                    show()
                }
            }
            else if(tv_start_time.text.toString()=="시작시간" || tv_end_time.text.toString()=="종료시간"){
                DialogBuilder().apply {
                    build(this@NoticeWriteActivity)
                    setContent(getString(R.string.time_empty))
                    setClickListener {
                        dismiss()
                    }
                    show()
                }
            }
            else if(tv_start_time.text.toString()!="시간정보없음" && tv_end_time.text.toString()!="시간정보없음" && tv_start_time.text.toString().split(":")[0].toInt() > tv_end_time.text.toString().split(":")[0].toInt()){
                DialogBuilder().apply {
                    build(this@NoticeWriteActivity)
                    setContent(getString(R.string.time_fail))
                    setClickListener {
                        dismiss()
                    }
                    show()
                }
            }
            else if(et_notice_title.text.toString()=="") {
                DialogBuilder().apply {
                    build(this@NoticeWriteActivity)
                    setContent(getString(R.string.title_empty))
                    setClickListener {
                        dismiss()
                    }
                    show()
                }
            }
            else if(et_notice_content.text.toString()=="") {
                DialogBuilder().apply {
                    build(this@NoticeWriteActivity)
                    setContent(getString(R.string.content_empty))
                    setClickListener {
                        dismiss()
                    }
                    show()
                }
            }
            else{
                var startTime = tv_start_time.text.toString()
                var endTime = tv_end_time.text.toString()
                if(startTime == "시간정보없음") startTime = "-1"
                if(endTime == "시간정보없음") endTime = "-1"

                val body = RequestNoticeAdd(category = category, date = datePickerYear+"-"+datePickerMonth+"-"+datePickerDay,
                    startTime = startTime, endTime = endTime,
                    title = et_notice_title.text.toString(), content = et_notice_content.text.toString())

                if(intent.getStringExtra("mode")=="add") {
                    RetrofitService.service.addNotice(DataRepository.token, subjectIdx, body).enqueue(object : Callback<ResponseNotice> {
                        override fun onFailure(call: Call<ResponseNotice>, t: Throwable) {
                        }

                        override fun onResponse(
                            call: Call<ResponseNotice>,
                            response: Response<ResponseNotice>
                        ) {
                            response.body()?.let {
                                if (it.status == 201) {
                                    DialogBuilder().apply {
                                        build(this@NoticeWriteActivity)
                                        setContent(getString(R.string.notice_add_success))
                                        setClickListener {
                                            dismiss()
                                            finish()
                                        }
                                        show()
                                    }
                                }
                            }
                        }
                    })
                }
                else if(intent.getStringExtra("mode")=="update") {
                    RetrofitService.service.updateNotice(DataRepository.token, noticeIdx, body).enqueue(object : Callback<ResponseNotice> {
                        override fun onFailure(call: Call<ResponseNotice>, t: Throwable) {
                        }

                        override fun onResponse(
                            call: Call<ResponseNotice>,
                            response: Response<ResponseNotice>
                        ) {
                            response.body()?.let {
                                if (it.status == 201) {
                                    DialogBuilder().apply {
                                        build(this@NoticeWriteActivity)
                                        setContent(getString(R.string.notice_update_success))
                                        setClickListener {
                                            dismiss()
                                            finish()
                                        }
                                        show()
                                    }
                                }
                            }
                        }
                    })
                }
            }
        }
    }
}