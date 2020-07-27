package com.ulink.ulink.NoticeRecycler

import android.graphics.Color
import android.text.Editable
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.ulink.ulink.R
import com.ulink.ulink.ScheduleRecycler.ScheduleItemData
import com.ulink.ulink.ScheduleRecycler.nowDay
import com.ulink.ulink.ScheduleRecycler.nowMonth
import com.ulink.ulink.ScheduleRecycler.nowYear
import com.ulink.ulink.repository.CalendarNoticeData
import java.text.SimpleDateFormat

fun intentNoticeAdd(scheduleItemData : ScheduleItemData,
                    btn_task : Button, btn_test : Button, btn_class : Button,
                    tv_date : TextView, tv_classname : TextView,
                    et_title : EditText, et_memo : EditText,
                    spinner_start : Spinner, spinner_end : Spinner) : String {

    lateinit var category : String
    lateinit var intentStartTime : List<String>
    lateinit var intentEndTime : List<String>
    var timeStartIndex : Int = 0
    var timeEndIndex : Int = 0

    if(scheduleItemData.startTime!="") {
        intentStartTime = scheduleItemData.startTime.split(":")
        timeStartIndex = intentStartTime[0].toInt() + 1
    }
    if(scheduleItemData.endTime!="") {
        intentEndTime = scheduleItemData.endTime.split(":")
        timeEndIndex = intentEndTime[0].toInt() + 1
    }

    spinner_start.setSelection(timeStartIndex)
    spinner_end.setSelection(timeEndIndex)

    val intentDate= scheduleItemData.date.split("-")
    tv_date.text = intentDate[0]+"년 " + intentDate[1]+"월 " + intentDate[2]+"일"
    tv_classname.text = scheduleItemData.classname+" 공지"
    et_title.text = Editable.Factory.getInstance().newEditable(scheduleItemData.content)
    et_memo.text = Editable.Factory.getInstance().newEditable(scheduleItemData.memo)

    var whiteColor = "#ffffff"

    when (scheduleItemData.category) {
        "과제" -> {
            btn_task.setBackgroundResource(R.drawable.btn_bg_notice_selected)
            btn_task.setTextColor(Color.parseColor(whiteColor))
            category = "과제"
        }
        "시험" -> {
            btn_test.setBackgroundResource(R.drawable.btn_bg_notice_selected)
            btn_test.setTextColor(Color.parseColor(whiteColor))
            category = "시험"
        }
        "수업" -> {
            btn_class.setBackgroundResource(R.drawable.btn_bg_notice_selected)
            btn_class.setTextColor(Color.parseColor(whiteColor))
            category = "수업"
        }
    }

    return category
}

fun spinnerInit(spinner : Spinner, adapter : ArrayAdapter<CharSequence>){

    adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

    spinner.adapter = adapter
    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {
        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long){
        }
    }
}

fun dataReturn(year : Int, month : Int, day : Int, category : String, classname : String, et_title : EditText, et_memo : EditText,
                spinner_start: Spinner, spinner_end: Spinner) : ScheduleItemData {

    var startTime : String
    var endTime : String

    if(spinner_start.selectedItem.toString()=="시간정보없음") startTime = "-1"
    else startTime = spinner_start.selectedItem.toString()
    if(spinner_end.selectedItem.toString()=="시간정보없음") endTime = "-1"
    else endTime = spinner_end.selectedItem.toString()

    var item =
        ScheduleItemData(
            idx = 0,
            date = "$year-$month-$day",
            category = category,
            classname = classname,
            content = et_title.text.toString(), //제목
            startTime = startTime,
            endTime = endTime,
            memo = et_memo.text.toString()
        )

    return item
}

fun emptyCheck(datas : MutableList<ScheduleItemData>, notice_nothing : TextView, rv_notice : RecyclerView, notice_more : TextView){

    if(datas.isEmpty()){
        notice_nothing.visibility = View.VISIBLE
        rv_notice.visibility=View.INVISIBLE
        notice_more.visibility = View.INVISIBLE
    }else{
        notice_nothing.visibility = View.INVISIBLE
        rv_notice.visibility=View.VISIBLE
        notice_more.visibility = View.VISIBLE
    }
}

fun ddayBackground(category : String, dday : TextView) {
    when (category) {
        "수업" -> dday.setBackgroundResource(R.drawable.chatting_notice_label_bg_class)
        "과제" -> dday.setBackgroundResource(R.drawable.chatting_notice_label_bg_hw)
        "시험" -> dday.setBackgroundResource(R.drawable.chatting_notice_label_bg_test)
    }
}

fun ddayCheck(scheduleItemData : ScheduleItemData) : Long{

    var now = "$nowYear-$nowMonth-$nowDay"
    var nowDate = SimpleDateFormat("yyyy-MM-dd").parse(now)
    var scheduleDate = SimpleDateFormat("yyyy-MM-dd").parse(scheduleItemData.date)

    var dayRemainder = nowDate.time - scheduleDate.time
    dayRemainder /= (24 * 60 * 60 * 1000)

    return dayRemainder
}

fun ddaySchedule(calendarNoticeData : CalendarNoticeData) : Long{

    var now = "$nowYear-$nowMonth-$nowDay"
    var nowDate = SimpleDateFormat("yyyy-MM-dd").parse(now)
    var scheduleDate = SimpleDateFormat("yyyy-MM-dd").parse(calendarNoticeData.date)

    var dayRemainder = nowDate.time - scheduleDate.time
    dayRemainder /= (24 * 60 * 60 * 1000)

    return dayRemainder

}