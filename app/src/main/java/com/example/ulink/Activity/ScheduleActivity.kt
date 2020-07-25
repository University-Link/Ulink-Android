package com.example.ulink.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.ulink.CalendarRecycler.tenday
import com.example.ulink.CalendarRecycler.today
import com.example.ulink.NoticeRecycler.ddaySchedule
import com.example.ulink.R
import com.example.ulink.ScheduleRecycler.*
import com.example.ulink.repository.CalendarNoticeData
import com.example.ulink.repository.DataRepository
import com.example.ulink.repository.ResponseCalendar
import com.example.ulink.repository.RetrofitService
import com.example.ulink.utils.deepCopySchedule
import kotlinx.android.synthetic.main.activity_schedule.*
import kotlinx.android.synthetic.main.toolbar_schedule_notice.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ScheduleActivity : AppCompatActivity() {

    lateinit var scheduleDateAdapter: ScheduleDateAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)

        btn_back.setOnClickListener() {
            finish()
        }

        Log.d("day", today())
        Log.d("dayten", tenday())

        var tenDayData: List<CalendarNoticeData> = arrayListOf()
        val bigList: MutableList<MutableList<ScheduleItemData>> = arrayListOf()

        scheduleDateAdapter = ScheduleDateAdapter(this, bigList)
        rv_schedule_date.adapter = scheduleDateAdapter

        // TODO 10일말고 30일
        RetrofitService.service.getAllNotice(DataRepository.token, today(), tenday())
            .enqueue(object : Callback<ResponseCalendar> {
                override fun onFailure(call: Call<ResponseCalendar>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<ResponseCalendar>,
                    response: Response<ResponseCalendar>
                ) {
                    response.body()?.let {
                        if (it.status == 200) {
                            tenDayData = it.data
                            for (i in 0 until it.data.size) {
                                val innerList = mutableListOf<ScheduleItemData>()
                                for (j in 0 until it.data[i].notice.size) {
                                    innerList.add(
                                        ScheduleItemData(
                                            idx = tenDayData[i].notice[j].noticeIdx,
                                            date = tenDayData[i].date,
                                            category = tenDayData[i].notice[j].category,
                                            classname = tenDayData[i].notice[j].name,
                                            content = tenDayData[i].notice[j].title,
                                            startTime = tenDayData[i].notice[j].startTime,
                                            endTime = tenDayData[i].notice[j].endTime,
                                            memo = "",
                                            day = tenDayData[i].date.split("-")[2],
                                            dayindex = nowDateCheck(i),
                                            dday = ddaySchedule(tenDayData[i])
                                        )
                                    )
                                }
                                // TODO deepCopy 빼보기
                                bigList.add(deepCopySchedule(innerList))

                                scheduleDateAdapter.dateDatas = bigList
                                scheduleDateAdapter.notifyDataSetChanged()
                            }
                        }
                    } ?: Log.d("tag4", response.message())
                }
            })
    }
}
