package com.example.ulink.NoticeRecycler

import android.graphics.Color
import android.text.Editable
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.ulink.R
import com.example.ulink.ScheduleRecycler.ScheduleItemData

fun intentNoticeAdd(scheduleItemData : ScheduleItemData,
                    btn_task : Button, btn_test : Button, btn_class : Button,
                    tv_date : TextView, tv_classname : TextView,
                    et_title : EditText, et_memo : EditText,
                    spinner_start : Spinner, spinner_end : Spinner) {

    var intentStartTime = scheduleItemData.startTime.split(":")
    var intentEndTime = scheduleItemData.endTime.split(":")

    var timeStartIndex = intentStartTime[0].toInt()+1
    var timeEndIndex = intentEndTime[0].toInt()+1

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
        }
        "시험" -> {
            btn_test.setBackgroundResource(R.drawable.btn_bg_notice_selected)
            btn_test.setTextColor(Color.parseColor(whiteColor))
        }
        "수업" -> {
            btn_class.setBackgroundResource(R.drawable.btn_bg_notice_selected)
            btn_class.setTextColor(Color.parseColor(whiteColor))
        }
    }

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

    if(spinner_start.selectedItem.toString()=="시간정보없음") startTime = ""
    else startTime = spinner_start.selectedItem.toString()
    if(spinner_end.selectedItem.toString()=="시간정보없음") endTime = ""
    else endTime = spinner_end.selectedItem.toString()

    var item =
        ScheduleItemData(
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

fun resultCode(category : String) : Int{
    var code = 0

    when (category) {
        "과제" -> code = 200
        "시험" -> code = 300
        "수업" -> code = 400
    }

    return code
}

fun ddayBackground(category : String, dday : TextView) {
    when (category) {
        "수업" -> dday.setBackgroundResource(R.drawable.chatting_notice_label_bg_class)
        "과제" -> dday.setBackgroundResource(R.drawable.chatting_notice_label_bg_hw)
        "시험" -> dday.setBackgroundResource(R.drawable.chatting_notice_label_bg_test)
    }

}