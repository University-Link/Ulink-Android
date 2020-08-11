package com.ulink.ulink.CalendarRecycler

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ulink.ulink.*
import com.ulink.ulink.Activity.ScheduleNoticeActivity
import com.ulink.ulink.NoticeRecycler.ddaySchedule
import com.ulink.ulink.ScheduleRecycler.*
import com.ulink.ulink.repository.DataRepository
import com.ulink.ulink.repository.ResponseCalendar
import com.ulink.ulink.repository.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CalendarAdapter(private val context : Context, data : CalendarData, val rootView : View) : RecyclerView.Adapter<CalendarAdapter.Vholder>() {
    var data: CalendarData = data
    var endDay = arrayOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vholder {

        val view = LayoutInflater.from(context).inflate(R.layout.calendar_layout, parent, false)
        return Vholder(view)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: Vholder, position: Int) {
        holder.bind(data)
    }

    inner class Vholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(data: CalendarData) {

            val rvCalendar = itemView as RecyclerView

            var datas = mutableListOf<ScheduleItemData>()

            val rvAdapter = CalendarDayAdapter(context, datas)

            //LeapYear
            endDay[1] = calendarLeapYearCheck(data)

            //month
            var index = firstIndex(data.year, data.month)
            var lastindex = endDay[data.month - 1]

            //previous_month
            var prevEmptyIndex = calendarPreviousIndexCheck(data, index)

            //next_month
            var lastEmpty = index + lastindex
            var lastEmptyIndex = 1

            var popupLastEmpty = index + lastindex
            var popupEmptyIndex = prevEmptyIndex

            //request
            var strFirstDay = strFirstDay(prevEmptyIndex, data)
            var strLastDay = strLastDay(lastEmpty, data)

            rvCalendar.adapter = rvAdapter

            var datass = mutableListOf<ScheduleItemData>()

            RetrofitService.service.getAllNotice(DataRepository.token, strFirstDay, strLastDay)
                .enqueue(object : Callback<ResponseCalendar> {
                    override fun onFailure(call: Call<ResponseCalendar>, t: Throwable) {
                    }

                    override fun onResponse(
                        call: Call<ResponseCalendar>,
                        response: Response<ResponseCalendar>
                    ) {
                        response.body()?.let {
                            if (it.status == 200) {
                                for (i in 0 until it.data.size) {
                                    for (j in 0 until it.data[i].notice.size) {
                                        datass.apply {
                                            add(
                                                ScheduleItemData(
                                                    idx = it.data[i].notice[j].noticeIdx,
                                                    date = it.data[i].date,
                                                    category = it.data[i].notice[j].category,
                                                    classname = it.data[i].notice[j].name,
                                                    content = it.data[i].notice[j].title,
                                                    startTime = it.data[i].notice[j].startTime,
                                                    endTime = it.data[i].notice[j].endTime,
                                                    memo = "",
                                                    color = it.data[i].notice[j].color,
                                                    day = it.data[i].date.split("-")[2],
                                                    dayindex = "",
                                                    dday = ddaySchedule(it.data[i])
                                                )
                                            )
                                        }
                                    }
                                }

                                rvAdapter.scheduleDatas.clear()
                                rvAdapter.scheduleDatas.addAll(datass)
                                rvAdapter.notifyDataSetChanged()
                            }
                        }
                    }
                })

            rvAdapter.datas.apply {
                //previous_month
                var dateindex = 0
                for (i in 0 until index) { // index(1일) 그 전까지 prev_month
                    if(data.month==1) {
                        add(
                            CalendarDayData(
                                year = data.year-1,
                                month = 12,
                                day = prevEmptyIndex.toString(),
                                check = false, // !now_month
                                date = dateindex, // if(dateindex % 7 == 0) then Sunday
                                today = false
                            )
                        )
                        dateindex += 1
                        prevEmptyIndex += 1
                    }
                    else {
                        add(
                            CalendarDayData(
                                year = data.year,
                                month = data.month-1,
                                day = prevEmptyIndex.toString(),
                                check = false, // !now_month
                                date = dateindex, // if(dateindex % 7 == 0) then Sunday
                                today = false
                            )
                        )
                        dateindex += 1
                        prevEmptyIndex += 1
                    }
                }
                //month
                for (i in 1..lastindex) { // 1일부터 endDay까지
                    if (calendarTodayCheck(i, data))
                        add(
                            CalendarDayData(
                                year = data.year,
                                month = data.month,
                                day = i.toString(),
                                check = true, // for setting now_month
                                date = dateindex,
                                today = true // for setting today background
                            )
                        )
                    else
                        add(
                            CalendarDayData(
                                year = data.year,
                                month = data.month,
                                day = i.toString(),
                                check = true,
                                date = dateindex,
                                today = false
                            )
                        )
                    dateindex += 1
                }
                //next_month
                if(data.month==12) {
                    while (true) {
                        if (lastEmpty % 7 != 0) {
                            add(
                                CalendarDayData(
                                    year = data.year+1,
                                    month = 1,
                                    day = lastEmptyIndex.toString(),
                                    check = false, // !now_month
                                    date = dateindex,
                                    today = false
                                )
                            )
                            lastEmptyIndex += 1
                            lastEmpty += 1
                            dateindex += 1
                        } else break;
                    }
                }
                else{
                    while (true) {
                        if (lastEmpty % 7 != 0) {
                            add(
                                CalendarDayData(
                                    year = data.year,
                                    month = data.month + 1,
                                    day = lastEmptyIndex.toString(),
                                    check = false, // !now_month
                                    date = dateindex,
                                    today = false
                                )
                            )
                            lastEmptyIndex += 1
                            lastEmpty += 1
                            dateindex += 1
                        } else break;
                    }
                }
                rvAdapter.notifyDataSetChanged()

                rvAdapter.setDayClickListener(
                    object : CalendarDayAdapter.DayClickListener {
                        override fun onClick(view: View, position: Int) {

                            val builder = android.app.AlertDialog.Builder(context)
                            val layout = LayoutInflater.from(context)
                                .inflate(R.layout.calendar_popup_layout, null)

                            //popup tv setting O월 O일
                            var popupYear = rvAdapter.datas[position].year.toString()
                            var popupMonth = rvAdapter.datas[position].month.toString()
                            var popupDay = rvAdapter.datas[position].day

                            layout.findViewById<TextView>(R.id.tv_calendar_popup_date).text =
                                popupMonth + "월 " + popupDay + "일 " + popupDateCheck(position)

                            val rvPopupAdapter = SchedulePopupAdapter(context)
                            var rv_popup = layout.findViewById<RecyclerView>(R.id.rv_popup_schedule_item)
                            rv_popup.adapter = rvPopupAdapter

                            //oooo-oo-oo
                            var retrofitStr =
                                popupYear + "-" + popupMonth + "-" + popupDay// request

                            val popupList: MutableList<ScheduleItemData> = arrayListOf()

                            //startDay = endDay = retrofitStr(현재 위치에서의 날짜)
                            RetrofitService.service.getAllNotice(DataRepository.token, retrofitStr, retrofitStr)
                                .enqueue(object : Callback<ResponseCalendar> {
                                    override fun onFailure(
                                        call: Call<ResponseCalendar>,
                                        t: Throwable
                                    ) {}
                                    override fun onResponse(
                                        call: Call<ResponseCalendar>,
                                        response: Response<ResponseCalendar>
                                    ) {
                                        response.body()?.let {
                                            if (it.status == 200) {
                                                for (i in 0 until it.data.size) {
                                                    for (j in 0 until it.data[i].notice.size) {
                                                        popupList.add(
                                                            ScheduleItemData(
                                                                idx = it.data[i].notice[j].noticeIdx,
                                                                date = it.data[i].date,
                                                                category = it.data[i].notice[j].category,
                                                                classname = it.data[i].notice[j].name,
                                                                content = it.data[i].notice[j].title,
                                                                startTime = it.data[i].notice[j].startTime,
                                                                endTime = it.data[i].notice[j].endTime,
                                                                memo = "",
                                                                day = it.data[i].date.split("-")[2],
                                                                dayindex = "",
                                                                dday = ddaySchedule(it.data[i])
                                                            )
                                                        )
                                                    }
                                                    rvPopupAdapter.datas = popupList
                                                    rvPopupAdapter.notifyDataSetChanged()

                                                    // 일정이 없을 경우 '일정이 없습니다.'
                                                    var tv_schedule_empty = layout.findViewById<TextView>(R.id.tv_schedule_empty)

                                                    if(rvPopupAdapter.datas.isEmpty()){
                                                        rv_popup.visibility = View.GONE
                                                        tv_schedule_empty.visibility=View.VISIBLE
                                                    }else{
                                                        rv_popup.visibility = View.VISIBLE
                                                        tv_schedule_empty.visibility=View.GONE
                                                    }
                                                }
                                            }
                                        } ?: Log.d("tag4", response.message())
                                    }
                                })

                            //공지 클릭 시 상세 공지 뷰로 이동
                            rvPopupAdapter.setScheduleItemClickListener(object :
                                SchedulePopupAdapter.ScheduleItemClickListener {
                                override fun onClick(view: View, position: Int) {
                                    val intent =
                                        Intent(view.context, ScheduleNoticeActivity::class.java)
                                    intent.putExtra(
                                        "scheduleItemData",
                                        rvPopupAdapter.datas[position]
                                    )
                                    view.context.startActivity(intent)
                                }
                            })

                            builder.setView(layout)

                            val dialog = builder.create()

                            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                            dialog.show()

                            val width = view.resources.getDimensionPixelSize(R.dimen.calendar_popup_width)
                            val height = view.resources.getDimensionPixelSize(R.dimen.calendar_popup_height)
                            dialog.window?.setLayout(width, height)
                        }
                    })
                }
            }
        }
    }