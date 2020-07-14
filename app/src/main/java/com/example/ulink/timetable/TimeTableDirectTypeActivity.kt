package com.example.ulink.timetable

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import android.widget.TimePicker.OnTimeChangedListener
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.ulink.R
import com.example.ulink.TimeTableDirectRecycler.TimeTableDirectAdapter
import com.example.ulink.TimeTableDirectRecycler.TimeTableDirectData
import kotlinx.android.synthetic.main.activity_direct_type_time_table.*
import kotlinx.android.synthetic.main.toolbar_direct_time_table.*

//타임피커 커스
const val DEFAULT_INTERVAL = 15
const val MINUTES_MIN = 0
const val MINUTES_MAX = 60

class TimeTableDirectTypeActivity : AppCompatActivity(), onClickListener {

    lateinit var time: String
    var check: Boolean = false

    lateinit var day: String
    lateinit var start: String
    lateinit var end: String

    lateinit var TimeTableDirectAdapter: TimeTableDirectAdapter
    val datas: MutableList<TimeTableDirectData> = mutableListOf<TimeTableDirectData>()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_direct_type_time_table)

        time_picker.setTimeInterval() // 시간 간격을 15분 단위로 설정

        TimeTableDirectAdapter = TimeTableDirectAdapter(this, this)
        rv_time_add.adapter = TimeTableDirectAdapter
        lateinit var title: String

        var textCheck = "#ffffff"
        var textUncheck = "#727272"


        btn_check.setOnClickListener() {
            if (et_title.text.toString() == "") directAddPageDialog()
            else {
                // TODO 확인누르면 어디로?
            }
        }
        et_title.setOnFocusChangeListener(object : View.OnFocusChangeListener {
            override fun onFocusChange(v: View?, hasFocus: Boolean) {
                when (et_title.text.toString()) {
                    "동아리", "알바", "스터디", "학생회", "과외", "학원", "근로", "봉사" -> {
                        btn_club.setBackgroundResource(R.drawable.textfield_btn_box_bg)
                        btn_part_time.setBackgroundResource(R.drawable.textfield_btn_box_bg)
                        btn_study.setBackgroundResource(R.drawable.textfield_btn_box_bg)
                        btn_student_council.setBackgroundResource(R.drawable.textfield_btn_box_bg)
                        btn_lesson.setBackgroundResource(R.drawable.textfield_btn_box_bg)
                        btn_academy.setBackgroundResource(R.drawable.textfield_btn_box_bg)
                        btn_work.setBackgroundResource(R.drawable.textfield_btn_box_bg)
                        btn_service.setBackgroundResource(R.drawable.textfield_btn_box_bg)

                        btn_club.setTextColor(Color.parseColor(textUncheck))
                        btn_part_time.setTextColor(Color.parseColor(textUncheck))
                        btn_study.setTextColor(Color.parseColor(textUncheck))
                        btn_student_council.setTextColor(Color.parseColor(textUncheck))
                        btn_lesson.setTextColor(Color.parseColor(textUncheck))
                        btn_academy.setTextColor(Color.parseColor(textUncheck))
                        btn_work.setTextColor(Color.parseColor(textUncheck))
                        btn_service.setTextColor(Color.parseColor(textUncheck))
                        et_title.setText("")
                    }
                }
            }
        })

        btn_club.setOnClickListener() {

            btn_club.setBackgroundResource(R.drawable.textfield_btn_box_bg_selected)
            btn_part_time.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_study.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_student_council.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_lesson.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_academy.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_work.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_service.setBackgroundResource(R.drawable.textfield_btn_box_bg)

            btn_club.setTextColor(Color.parseColor(textCheck))
            btn_part_time.setTextColor(Color.parseColor(textUncheck))
            btn_study.setTextColor(Color.parseColor(textUncheck))
            btn_student_council.setTextColor(Color.parseColor(textUncheck))
            btn_lesson.setTextColor(Color.parseColor(textUncheck))
            btn_academy.setTextColor(Color.parseColor(textUncheck))
            btn_work.setTextColor(Color.parseColor(textUncheck))
            btn_service.setTextColor(Color.parseColor(textUncheck))

            et_title.setText(btn_club.text)
            et_title.clearFocus()
        }
        btn_part_time.setOnClickListener() {

            btn_club.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_part_time.setBackgroundResource(R.drawable.textfield_btn_box_bg_selected)
            btn_study.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_student_council.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_lesson.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_academy.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_work.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_service.setBackgroundResource(R.drawable.textfield_btn_box_bg)

            btn_club.setTextColor(Color.parseColor(textUncheck))
            btn_part_time.setTextColor(Color.parseColor(textCheck))
            btn_study.setTextColor(Color.parseColor(textUncheck))
            btn_student_council.setTextColor(Color.parseColor(textUncheck))
            btn_lesson.setTextColor(Color.parseColor(textUncheck))
            btn_academy.setTextColor(Color.parseColor(textUncheck))
            btn_work.setTextColor(Color.parseColor(textUncheck))
            btn_service.setTextColor(Color.parseColor(textUncheck))

            et_title.text = Editable.Factory.getInstance().newEditable(btn_part_time.text)
            et_title.clearFocus()
        }
        btn_study.setOnClickListener() {

            btn_club.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_part_time.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_study.setBackgroundResource(R.drawable.textfield_btn_box_bg_selected)
            btn_student_council.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_lesson.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_academy.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_work.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_service.setBackgroundResource(R.drawable.textfield_btn_box_bg)

            btn_club.setTextColor(Color.parseColor(textUncheck))
            btn_part_time.setTextColor(Color.parseColor(textUncheck))
            btn_study.setTextColor(Color.parseColor(textCheck))
            btn_student_council.setTextColor(Color.parseColor(textUncheck))
            btn_lesson.setTextColor(Color.parseColor(textUncheck))
            btn_academy.setTextColor(Color.parseColor(textUncheck))
            btn_work.setTextColor(Color.parseColor(textUncheck))
            btn_service.setTextColor(Color.parseColor(textUncheck))

            et_title.text = Editable.Factory.getInstance().newEditable(btn_study.text)
            et_title.clearFocus()
        }

        btn_student_council.setOnClickListener() {

            btn_club.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_part_time.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_study.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_student_council.setBackgroundResource(R.drawable.textfield_btn_box_bg_selected)
            btn_lesson.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_academy.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_work.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_service.setBackgroundResource(R.drawable.textfield_btn_box_bg)

            btn_club.setTextColor(Color.parseColor(textUncheck))
            btn_part_time.setTextColor(Color.parseColor(textUncheck))
            btn_study.setTextColor(Color.parseColor(textUncheck))
            btn_student_council.setTextColor(Color.parseColor(textCheck))
            btn_lesson.setTextColor(Color.parseColor(textUncheck))
            btn_academy.setTextColor(Color.parseColor(textUncheck))
            btn_work.setTextColor(Color.parseColor(textUncheck))
            btn_service.setTextColor(Color.parseColor(textUncheck))

            et_title.text = Editable.Factory.getInstance().newEditable(btn_student_council.text)
            et_title.clearFocus()
        }
        btn_lesson.setOnClickListener() {

            btn_club.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_part_time.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_study.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_student_council.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_lesson.setBackgroundResource(R.drawable.textfield_btn_box_bg_selected)
            btn_academy.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_work.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_service.setBackgroundResource(R.drawable.textfield_btn_box_bg)

            btn_club.setTextColor(Color.parseColor(textUncheck))
            btn_part_time.setTextColor(Color.parseColor(textUncheck))
            btn_study.setTextColor(Color.parseColor(textUncheck))
            btn_student_council.setTextColor(Color.parseColor(textUncheck))
            btn_lesson.setTextColor(Color.parseColor(textCheck))
            btn_academy.setTextColor(Color.parseColor(textUncheck))
            btn_work.setTextColor(Color.parseColor(textUncheck))
            btn_service.setTextColor(Color.parseColor(textUncheck))

            et_title.text = Editable.Factory.getInstance().newEditable(btn_lesson.text)
            et_title.clearFocus()
        }
        btn_academy.setOnClickListener() {

            btn_club.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_part_time.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_study.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_student_council.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_lesson.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_academy.setBackgroundResource(R.drawable.textfield_btn_box_bg_selected)
            btn_work.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_service.setBackgroundResource(R.drawable.textfield_btn_box_bg)


            btn_club.setTextColor(Color.parseColor(textUncheck))
            btn_part_time.setTextColor(Color.parseColor(textUncheck))
            btn_study.setTextColor(Color.parseColor(textUncheck))
            btn_student_council.setTextColor(Color.parseColor(textUncheck))
            btn_lesson.setTextColor(Color.parseColor(textUncheck))
            btn_academy.setTextColor(Color.parseColor(textCheck))
            btn_work.setTextColor(Color.parseColor(textUncheck))
            btn_service.setTextColor(Color.parseColor(textUncheck))

            et_title.text = Editable.Factory.getInstance().newEditable(btn_academy.text)
            et_title.clearFocus()
        }
        btn_work.setOnClickListener() {

            btn_club.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_part_time.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_study.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_student_council.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_lesson.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_academy.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_work.setBackgroundResource(R.drawable.textfield_btn_box_bg_selected)
            btn_service.setBackgroundResource(R.drawable.textfield_btn_box_bg)

            btn_club.setTextColor(Color.parseColor(textUncheck))
            btn_part_time.setTextColor(Color.parseColor(textUncheck))
            btn_study.setTextColor(Color.parseColor(textUncheck))
            btn_student_council.setTextColor(Color.parseColor(textUncheck))
            btn_lesson.setTextColor(Color.parseColor(textUncheck))
            btn_academy.setTextColor(Color.parseColor(textUncheck))
            btn_work.setTextColor(Color.parseColor(textCheck))
            btn_service.setTextColor(Color.parseColor(textUncheck))

            et_title.text = Editable.Factory.getInstance().newEditable(btn_work.text)
            et_title.clearFocus()
        }
        btn_service.setOnClickListener() {

            btn_club.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_part_time.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_study.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_student_council.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_lesson.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_academy.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_work.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_service.setBackgroundResource(R.drawable.textfield_btn_box_bg_selected)

            btn_club.setTextColor(Color.parseColor(textUncheck))
            btn_part_time.setTextColor(Color.parseColor(textUncheck))
            btn_study.setTextColor(Color.parseColor(textUncheck))
            btn_student_council.setTextColor(Color.parseColor(textUncheck))
            btn_lesson.setTextColor(Color.parseColor(textUncheck))
            btn_academy.setTextColor(Color.parseColor(textUncheck))
            btn_work.setTextColor(Color.parseColor(textUncheck))
            btn_service.setTextColor(Color.parseColor(textCheck))

            et_title.text = Editable.Factory.getInstance().newEditable(btn_service.text)
            et_title.clearFocus()
        }







        btn_timetable_add.setOnClickListener {
            check = false
            datas.apply {
                add(
                    TimeTableDirectData(
                        day = 0,
                        start_time = "오전 12 : 00",
                        end_time = "오후 3 : 00"
                    )
                )
            }
            TimeTableDirectAdapter.datas = datas
            TimeTableDirectAdapter.notifyDataSetChanged()
        }

        //시간표 전부 추가 후 확인
        btn_check.setOnClickListener() {
 //           if (et_title.text.toString() == "") showToast("제목을 설정해주세요.")
            finish()
        }

        //타임피커 시간 설정


        //타임피커 내부의 확인
        btn_ok.setOnClickListener {
            time_picker.visibility = View.INVISIBLE
            btn_ok.visibility = View.INVISIBLE
            check = true

        }

    } //oncreate 끝
    fun directAddPageDialog(){
        val builder = android.app.AlertDialog.Builder(this)
        val layout = LayoutInflater.from(this).inflate(R.layout.dialog_my_page_layout, null)

        builder.setView(layout)

        var dialog = builder.create()

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        var width = getResources().getDimensionPixelSize(R.dimen.my_popup_width)
        var height = getResources().getDimensionPixelSize(R.dimen.my_popup_height)
        dialog.window?.setLayout(width, height)

        layout.findViewById<TextView>(R.id.tv_my_dialog).text="제목을 설정해주세요."
        layout.findViewById<TextView>(R.id.tv_check).setOnClickListener {
            dialog.dismiss()
        }
    }

    //타임피커 15분단위로 바꾸기
    @SuppressLint("PrivateApi")
    fun TimePicker.setTimeInterval(
        timeInterval: Int = DEFAULT_INTERVAL
    ) {
        try {
            val classForId = Class.forName("com.android.internal.R\$id")
            val fieldId = classForId.getField("minute").getInt(null)

            (this.findViewById(fieldId) as NumberPicker).apply {
                minValue = MINUTES_MIN
                maxValue = MINUTES_MAX / timeInterval - 1
                displayedValues = getDisplayedValue(timeInterval)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getDisplayedValue(
        timeInterval: Int = DEFAULT_INTERVAL
    ): Array<String> {
        val minutesArray = ArrayList<String>()
        for (i in 0 until MINUTES_MAX step timeInterval) {
            minutesArray.add(i.toString())
        }

        return minutesArray.toArray(arrayOf(""))
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun TimePicker.getDisplayedMinute(
        timeInterval: Int = DEFAULT_INTERVAL
    ): Int = minute * timeInterval


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onClick(position: Int, check: Boolean) {
        time_picker.visibility = View.VISIBLE
        btn_ok.visibility = View.VISIBLE
        time_picker.setOnTimeChangedListener(OnTimeChangedListener { timePicker, hour, min ->
            if (hour < 12) {
                time = "오전"
            } else {
                time = "오후"
            }
            if (!check) {

                start = "${time}${hour}:${time_picker.getDisplayedMinute()}"
                TimeTableDirectAdapter.datas[position].start_time =
                    String.format("${time}" + "%02d:%02d", hour, time_picker.getDisplayedMinute())
                TimeTableDirectAdapter.notifyDataSetChanged()
            } else {
                end = "${time}${hour}:${time_picker.getDisplayedMinute()}"
                TimeTableDirectAdapter.datas[position].end_time =
                    String.format("${time}" + "%02d:%02d", hour, time_picker.getDisplayedMinute())

                TimeTableDirectAdapter.notifyDataSetChanged()
            }
        })

    }
}

interface onClickListener{
    fun onClick(position : Int,check:Boolean

    )
}
