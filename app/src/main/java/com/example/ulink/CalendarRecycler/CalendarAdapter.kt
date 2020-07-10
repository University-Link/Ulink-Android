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
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.ulink.*
import com.example.ulink.ScheduleRecycler.*


class CalendarAdapter(private val context : Context, data : CalendarData) : RecyclerView.Adapter<CalendarAdapter.Vholder>(){
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
            val rvAdapter = CalendarDayAdapter(context)

            //LeapYear
            endDay[1] = calendarLeapYearCheck(data)

            //month
            var index = firstIndex(data.year, data.month)
            var lastindex = endDay[data.month - 1]

            //Log.d("idx","$index")

            //previous_month
            var prevEmptyIndex = calendarPreviousIndexCheck(data, index)

            //next_month
            var lastEmpty = index + lastindex
            var lastEmptyIndex = 1

            var popupLastEmpty = index + lastindex
            var popupEmptyIndex = prevEmptyIndex

            rvCalendar.adapter = rvAdapter

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
            }


            rvAdapter.setDayClickListener(object: CalendarDayAdapter.DayClickListener{
                override fun onClick(view:View, position:Int) {

                    val builder = android.app.AlertDialog.Builder(context)
                    val layout = LayoutInflater.from(context).inflate(R.layout.calendar_popup_layout, null)

                    //Popup
                    var popupMonth = popupMonthCheck(position, index, popupLastEmpty, data.month)
                    var popupDay = popupDayCheck(position, index, popupLastEmpty, popupEmptyIndex)

                    layout.findViewById<TextView>(R.id.tv_calendar_popup_date).text =
                        popupMonth.toString()+"월 "+popupDay.toString()+"일 "+popupDateCheck(position)

                    val rvPopupAdapter = SchedulePopupAdapter(context)
                    layout.findViewById<RecyclerView>(R.id.rv_popup_schedule_item).adapter = rvPopupAdapter

                    //ScheduleNotice
                    var itemMonth : String = zeroPlus(popupMonth.toString())
                    var itemDay : String = zeroPlus(rvAdapter.datas[position].day)
                    var itemYear : String = popupYearCheck(data.year, data.month, position, index, popupLastEmpty).toString()

                    rvPopupAdapter.datas.apply {
                        add(
                            ScheduleItemData(
                                date = "$itemYear-$itemMonth-$itemDay",
                                category = "시험",
                                classname = "유링",
                                content = "유링",
                                startTime = "11:00",
                                endTime = "13:00"
                            )
                        )
                        add(
                            ScheduleItemData(
                                date = "$itemYear-$itemMonth-$itemDay",
                                category = "과제",
                                classname = "안드",
                                content = "유링크",
                                startTime = "11:00",
                                endTime = "13:00"
                            )
                        )
                        add(
                            ScheduleItemData(
                                date = "$itemYear-$itemMonth-$itemDay",
                                category = "수업",
                                classname = "바보",
                                content = "멍청이",
                                startTime = "11:00",
                                endTime = "13:00"
                            )
                        )
                        rvPopupAdapter.notifyDataSetChanged()
                    }

                    rvPopupAdapter.setScheduleItemClickListener(object: SchedulePopupAdapter.ScheduleItemClickListener{
                        override fun onClick(view:View, position:Int){
                            val intent = Intent(view.context, ScheduleNoticeActivity::class.java)
                            intent.putExtra("scheduleItemData", rvPopupAdapter.datas[position])
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