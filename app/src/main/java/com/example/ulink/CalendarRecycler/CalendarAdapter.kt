package com.example.ulink.CalendarRecycler

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
import com.example.ulink.*
import com.example.ulink.Activity.ScheduleNoticeActivity
import com.example.ulink.NoticeRecycler.ddaySchedule
import com.example.ulink.ScheduleRecycler.*
import com.example.ulink.repository.DataRepository
import com.example.ulink.repository.ResponseCalendar
import com.example.ulink.repository.RetrofitService
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

            val rvAdapter = CalendarDayAdapter(context, rootView, datas)

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

            var strFirstDay = strFirstDay(prevEmptyIndex, data)
            var strLastDay = strLastDay(lastEmpty, data)

            Log.d("day", strFirstDay)
            Log.d("day", strLastDay)

            rvCalendar.adapter = rvAdapter


            val schedule: MutableList<ScheduleItemData> = arrayListOf()
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
                                                    dayindex = nowDateCheck(i),
                                                    dday = ddaySchedule(it.data[i])
                                                )
                                            )
                                        }
                                    }
                                }

                                rvAdapter.scheduleDatas.clear()
                                rvAdapter.scheduleDatas.addAll(datass)
                                rvAdapter.notifyDataSetChanged()

                                Log.d("size", datas.toString())
                            }
                        } ?: Log.d("tag4", response.message())
                    }
                })


            rvAdapter.datas.apply {
                //previous_month
                var dateindex = 0
                for (i in 0 until index) {
                    add(
                        CalendarDayData(
                            day = prevEmptyIndex.toString(),
                            check = false,
                            date = dateindex,
                            today = false
                        )
                    )
                    dateindex += 1
                    prevEmptyIndex += 1
                }
                //month
                for (i in 1..lastindex) {
                    if (calendarTodayCheck(i, data))
                        add(
                            CalendarDayData(
                                day = i.toString(),
                                check = true,
                                date = dateindex,
                                today = true
                            )
                        )
                    else
                        add(
                            CalendarDayData(
                                day = i.toString(),
                                check = true,
                                date = dateindex,
                                today = false
                            )
                        )
                    dateindex += 1
                }
                //next_month
                while (true) {
                    if (lastEmpty % 7 != 0) {
                        add(
                            CalendarDayData(
                                day = lastEmptyIndex.toString(),
                                check = false,
                                date = dateindex,
                                today = false
                            )
                        )
                        lastEmptyIndex += 1
                        lastEmpty += 1
                        dateindex += 1
                    } else break;
                }
                rvAdapter.notifyDataSetChanged()

                // TODO 리싸이클러뷰 크기 조정
                rvAdapter.setDayClickListener(
                    object : CalendarDayAdapter.DayClickListener {
                        override fun onClick(view: View, position: Int) {

                            val builder = android.app.AlertDialog.Builder(context)
                            val layout = LayoutInflater.from(context)
                                .inflate(R.layout.calendar_popup_layout, null)

                            //Popup
                            var popupMonth =
                                popupMonthCheck(position, index, popupLastEmpty, data.month)
                            var popupDay =
                                popupDayCheck(position, index, popupLastEmpty, popupEmptyIndex)

                            layout.findViewById<TextView>(R.id.tv_calendar_popup_date).text =
                                popupMonth.toString() + "월 " + popupDay.toString() + "일 " + popupDateCheck(
                                    position
                                )

                            val rvPopupAdapter = SchedulePopupAdapter(context)
                            layout.findViewById<RecyclerView>(R.id.rv_popup_schedule_item).adapter =
                                rvPopupAdapter

                            //ScheduleNotice
                            //var itemMonth : String = zeroPlus(popupMonth.toString())
                            //var itemDay : String = zeroPlus(rvAdapter.datas[position].day)
                            var itemYear: String = popupYearCheck(
                                data.year,
                                data.month,
                                position,
                                index,
                                popupLastEmpty
                            ).toString()

                            var retrofitStr =
                                itemYear + "-" + popupMonth.toString() + "-" + popupDay.toString() // 통신용

                            val popupList: MutableList<ScheduleItemData> = arrayListOf()

                            RetrofitService.service.getAllNotice(DataRepository.token, retrofitStr, retrofitStr)
                                .enqueue(object : Callback<ResponseCalendar> {
                                    override fun onFailure(
                                        call: Call<ResponseCalendar>,
                                        t: Throwable
                                    ) {
                                    }

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
                                                                dayindex = nowDateCheck(i),
                                                                dday = ddaySchedule(it.data[i])
                                                            )
                                                        )
                                                    }
                                                    rvPopupAdapter.datas = popupList
                                                    rvPopupAdapter.notifyDataSetChanged()
                                                }
                                            }
                                        } ?: Log.d("tag4", response.message())
                                    }
                                })

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
                        }
                    })
            }
        }
    }
}