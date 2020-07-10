package com.example.ulink.ScheduleRecycler

import android.text.Editable
import android.view.View
import android.widget.*
import com.example.ulink.R
import kotlinx.android.synthetic.main.activity_notice_add.*
import kotlinx.android.synthetic.main.toolbar_notice_add.*

fun intentNoticeAdd(scheduleItemData : ScheduleItemData, tv_date : TextView, tv_classname : TextView, et_title : EditText, et_memo : EditText, spinner_start : Spinner, spinner_end : Spinner) {
    var intentStartTime = scheduleItemData.startTime.split(":")
    var intentEndTime = scheduleItemData.endTime.split(":")

    var timeStartIndex = intentStartTime[0].toInt()
    var timeEndIndex = intentEndTime[0].toInt()

    spinner_start.setSelection(timeStartIndex)
    spinner_end.setSelection(timeEndIndex)

    val intentDate= scheduleItemData.date.split("-")
    tv_date.text = intentDate[0]+"년 " + intentDate[1]+"월 " + intentDate[2]+"일"
    tv_classname.text = scheduleItemData.classname+" 공지"
    et_title.text = Editable.Factory.getInstance().newEditable(scheduleItemData.content)
    et_memo.text = Editable.Factory.getInstance().newEditable(scheduleItemData.content)

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