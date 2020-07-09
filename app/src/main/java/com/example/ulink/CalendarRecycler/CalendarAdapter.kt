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

            val rv_calendar = itemView as RecyclerView
            val rvAdapter = CalendarDayAdapter(context)

            //LeapYear
            endDay[1] = calendarLeapYearCheck(data)

            //month
            var index = firstIndex(data.year, data.month)
            var lastindex = endDay[data.month - 1]

            //Log.d("idx","$index")

            //previous_month
            var prev_empty_index = calendarPreviousIndexCheck(data, index)

            //next_month
            var last_empty = index + lastindex
            var last_empty_index = 1

            var popup_last_empty = index + lastindex
            var popup_empty_index = prev_empty_index

            rv_calendar.adapter = rvAdapter

            rvAdapter.datas.apply {
                //previous_month
                var dateindex = 0
                for (i in 0 until index) {
                    add(
                        CalendarDayData(
                            day = prev_empty_index.toString(),
                            check = false,
                            date = dateindex,
                            today = false
                        )
                    )
                    dateindex += 1
                    prev_empty_index += 1
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
                    if (last_empty % 7 != 0) {
                        add(
                            CalendarDayData(
                                day = last_empty_index.toString(),
                                check = false,
                                date = dateindex,
                                today = false
                            )
                        )
                        last_empty_index += 1
                        last_empty += 1
                        dateindex += 1
                    } else break;
                }
                rvAdapter.notifyDataSetChanged()
            }


            rvAdapter.setDayClickListener(object: CalendarDayAdapter.DayClickListener{
                override fun onClick(view:View, position:Int) {

                    val builder = android.app.AlertDialog.Builder(context)
                    val layout = LayoutInflater.from(context).inflate(R.layout.calendar_popup_layout, null)

                    layout.findViewById<TextView>(R.id.tv_calendar_popup_date).text =
                        popupDayCheck(position, index, popup_last_empty, data.month, popup_empty_index) + popupDateCheck(position)

                    val rvPopupAdapter = SchedulePopupAdapter(context)
                    layout.findViewById<RecyclerView>(R.id.rv_popup_schedule_item).adapter = rvPopupAdapter

                    var itemMonth : String = zeroPlus(data.month.toString())
                    var itemday : String = zeroPlus(rvAdapter.datas[position].day)

                    rvPopupAdapter.datas.apply {
                        add(
                            ScheduleItemData(
                                date = data.year.toString()+"-"+itemMonth+"-"+itemday,
                                category = "시험",
                                classname = "유링",
                                content = "유링",
                                startTime = "11:00",
                                endTime = "13:00"
                            )
                        )
                        add(
                            ScheduleItemData(
                                date = data.year.toString()+"-"+itemMonth+"-"+itemday,
                                category = "과제",
                                classname = "안드",
                                content = "유링크",
                                startTime = "11:00",
                                endTime = "13:00"
                            )
                        )
                        add(
                            ScheduleItemData(
                                date = data.year.toString()+"-"+itemMonth+"-"+itemday,
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