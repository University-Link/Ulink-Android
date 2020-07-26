package com.example.ulink.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ulink.R
import com.example.ulink.ScheduleRecycler.ScheduleItemData
import com.example.ulink.ScheduleRecycler.zeroCheck
import com.example.ulink.repository.DataRepository
import com.example.ulink.repository.ResponseSpecificNotice
import com.example.ulink.repository.RetrofitService
import kotlinx.android.synthetic.main.activity_class_notice.*
import kotlinx.android.synthetic.main.toolbar_schedule_notice.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ScheduleNoticeActivity : AppCompatActivity() {
    lateinit var item : ScheduleItemData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class_notice)


        //과목,시험,수업공지에서 넘어온 데이터
        var scheduleItemData = intent.getParcelableExtra<ScheduleItemData>("scheduleItemData")
        var idx = scheduleItemData.idx

        tv_schedule_notice_toolbar.text = scheduleItemData.category+"공지" //툴바타이틀

        /*if(scheduleItemData.startTime == "-1") scheduleItemData.startTime= "" //시간
        if (scheduleItemData.endTime == "-1") scheduleItemData.endTime=""

        if (scheduleItemData.startTime != "" || scheduleItemData.endTime != "")
            tv_schedule_notice_time_content.text = scheduleItemData.startTime + " ~ " + scheduleItemData.endTime

        if (scheduleItemData.startTime == "" && scheduleItemData.endTime == "")
            tv_schedule_notice_time_content.text = "시간정보없음"

        var scheduleDate = scheduleItemData.date.split("-")
        tv_schedule_notice_date_content.text = scheduleDate[0]+"년 "+zeroCheck(scheduleDate[1])+"월 "+zeroCheck(scheduleDate[2])+"일" //날짜

        tv_schedule_notice_title.text = scheduleItemData.content //제목

        tv_schedule_notice_memo_content.text = scheduleItemData.memo //메모*/

        //공지 상세 조회
        RetrofitService.service.getSpecificNotice(DataRepository.token, idx.toString())
            .enqueue(object : Callback<ResponseSpecificNotice> {
                override fun onFailure(call: Call<ResponseSpecificNotice>, t: Throwable) {
                    Log.d("실패", t.message.toString())
                }

                override fun onResponse(
                    call: Call<ResponseSpecificNotice>,
                    response: Response<ResponseSpecificNotice>
                ) {
                    response.body()?.let {
                        if (it.status == 200) {
                            item = ScheduleItemData(
                                idx = it.data.noticeIdx,
                                category = it.data.category,
                                date = it.data.date,
                                classname = scheduleItemData.classname,
                                content = it.data.title,
                                startTime = it.data.startTime,
                                endTime = it.data.endTime,
                                memo = it.data.content
                            )
                            if(item.startTime == "-1") item.startTime= "" //시간
                            if (item.endTime == "-1") item.endTime=""

                            if (item.startTime != "" || item.endTime != "")
                                tv_schedule_notice_time_content.text = item.startTime + " ~ " + item.endTime

                            if (item.startTime == "" && item.endTime == "")
                                tv_schedule_notice_time_content.text = "시간정보없음"

                            var date = item.date.split("-")
                            tv_schedule_notice_date_content.text = date[0]+"년 "+zeroCheck(date[1])+"월 "+zeroCheck(date[2])+"일" //날짜
                            tv_schedule_notice_title.text = item.content //제목
                            tv_schedule_notice_memo_content.text = item.memo //메모*/
                        }
                    }
                }
            })

        btn_back.setOnClickListener() {
            finish()
        }
        //수정뷰로 넘어감
        btn_edit.setOnClickListener() {
            val intent = Intent(this, NoticeAddActivity::class.java)
            intent.putExtra("scheduleItemData", item)
            intent.putExtra("addcheck", "revise")
            intent.putExtra("class", item.classname)
            intent.putExtra("noticeIdx", scheduleItemData.idx.toString())
            startActivity(intent)
        }

        btn_delete.setOnClickListener(){
            // TODO 삭제 통신
            DataRepository.deleteNoticeWithIdx(scheduleItemData.idx.toString(),
            onSuccess = {
                Toast.makeText(this,"삭제되었습니다",Toast.LENGTH_SHORT).show()
                finish()
            },
            onFailure = {

            })

        }
    }
}

