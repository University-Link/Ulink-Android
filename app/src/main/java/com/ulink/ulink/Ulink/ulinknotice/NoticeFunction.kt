package com.ulink.ulink.Ulink.ulinknotice

import android.graphics.Color
import android.widget.*
import com.ulink.ulink.CalendarRecycler.today
import com.ulink.ulink.R
import java.text.SimpleDateFormat
import java.util.*

fun categoryBackground(category : String, date : String, imgCategory : ImageView) {
    when (category) {
        "수업" -> imgCategory.setBackgroundResource(R.drawable.class_notice_ic_class)
        "시험" -> imgCategory.setBackgroundResource(R.drawable.class_notice_ic_exam)
        else -> imgCategory.setBackgroundResource(R.drawable.class_notice_ic_assign)
    }

    if(!noticeDateCompare(date))
        imgCategory.setBackgroundResource(R.drawable.class_notice_ic_assign_end)
}

fun expiredDate(ulinkNoticeData: UlinkNoticeData, tvDate : TextView, tvTitle : TextView, tvTime : TextView){
    if(!noticeDateCompare(ulinkNoticeData.date)){
        tvDate.setTextColor(Color.parseColor("#c4c2c2"))
        tvTitle.setTextColor(Color.parseColor("#c4c2c2"))
        tvTime.setTextColor(Color.parseColor("#c4c2c2"))
    }
    else{
        tvDate.setTextColor(Color.parseColor("#363636"))
        tvTitle.setTextColor(Color.parseColor("#363636"))
        tvTime.setTextColor(Color.parseColor("#5c5c5c"))
    }

}
fun dateTextView(ulinkNoticeData : UlinkNoticeData, tvDate : TextView){

    val strDate = ulinkNoticeData.date.split("-")
    var month = zeroCheck(strDate[1])
    var day = zeroCheck(strDate[2])

    tvDate.text = "$month/$day"
}

fun zeroCheck(check : String) : String {
    var zeroCheck = check
    if(zeroCheck.length==2 && zeroCheck.toInt()<10) zeroCheck = zeroCheck.replace("0", "")
    return zeroCheck
}

fun timeTextView(ulinkNoticeData : UlinkNoticeData, tvTime : TextView){
    var startTime = ulinkNoticeData.startTime
    var endTime = ulinkNoticeData.endTime

    if(ulinkNoticeData.startTime == "-1") startTime = ""
    if (ulinkNoticeData.endTime == "-1") endTime = ""

    if (ulinkNoticeData.startTime != "-1" || ulinkNoticeData.endTime != "-1")
        tvTime.text = startTime + " ~ " + endTime

    if (ulinkNoticeData.startTime == "-1" && ulinkNoticeData.endTime == "-1")
        tvTime.text = "시간정보없음"

    if (ulinkNoticeData.category=="과제" && ulinkNoticeData.endTime != "") tvTime.text = "~ "+endTime
}

fun timeTextView2(startTime : String, endTime : String, category : String, tvTime : TextView){
    var start = startTime
    var end = endTime

    if(startTime == "-1") start = ""
    if (endTime == "-1") end = ""

    if (startTime != "-1" || endTime != "-1")
        tvTime.text = start + " ~ " + end

    if (startTime == "-1" && endTime == "-1")
        tvTime.text = "시간정보없음"

    if (category=="과제" && endTime != "") tvTime.text = "~ "+end
}

fun noticeDateCompare(date: String) : Boolean{
    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    var strTodayDate = today()
    var objTodayDate = simpleDateFormat.parse(strTodayDate)
    var formattedTodayDate = simpleDateFormat.format(objTodayDate)

    var objDate = simpleDateFormat.parse(date)
    var formattedDate = simpleDateFormat.format(objDate)

    return formattedTodayDate <= formattedDate
}

fun noticeDetailDate(date : String) : String{
    var splitDate = date.split("-")

    return splitDate[0]+"년 "+zeroCheck(splitDate[1])+"월 "+zeroCheck(splitDate[2])+"일"
}

fun editTextFocus(btn: CheckBox, et: EditText){
    if (btn.isChecked) {
        et.isFocusable = true
        et.isFocusableInTouchMode = true
        et.requestFocus()
    }
    else{
        et.isFocusable = false
        et.isFocusableInTouchMode = false
        et.clearFocus()
    }
}

fun intentNoticeAdd(item : RequestNoticeAdd,
                    tv_category : TextView, tv_date : TextView,
                    tv_startTime : TextView, tv_endTime : TextView,
                    et_title : EditText, et_content : EditText) : String {
    var category = item.category
    tv_category.text = category

    val intentDate= item.date.split("-")
    tv_date.text = intentDate[0]+"년 " + zeroCheck(intentDate[1])+"월 " + zeroCheck(intentDate[2])+"일"
    tv_date.setTextColor(Color.parseColor("#363636"))

    if(item.startTime != "-1")
        tv_startTime.text = item.startTime
    else
        tv_startTime.text = "시간정보없음"

    if(item.endTime != "-1")
        tv_endTime.text = item.endTime
    else
        tv_endTime.text = "시간정보없음"

    et_title.setText(item.title)
    et_content.setText(item.content)
    return category
}