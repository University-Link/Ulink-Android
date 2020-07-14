package com.example.ulink.fragment

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ulink.CalendarRecycler.*
import com.example.ulink.R
import com.example.ulink.ScheduleActivity
import com.example.ulink.ScheduleRecycler.nowDay
import kotlinx.android.synthetic.main.fragment_calendar.*

class CalendarFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        RetrofitService.service.getAllNotice(token,"2020-06-01", "2020-07-31").enqueue(object : Callback<ResponseCalendar>{
//            override fun onFailure(call: Call<ResponseCalendar>, t: Throwable) {
//                Log.d("dlwldms", t.localizedMessage)
//            }
//
//            override fun onResponse(
//                call: Call<ResponseCalendar>,
//                response: Response<ResponseCalendar>
//            ) {
//                response.body()?.let{
//                    if(it.status == 200){
//                        Log.d("dlwldms", it.data[0].notice[0].name)
//                    } else {
//                        Log.d("qkrrbgml", "애매")
//                    }
//                } ?: Log.d("tag", "??")
//            }
//        })

        lateinit var dialog : AlertDialog
        btn_today.text = nowDay.toString()

        var nowCalendarData = calendarDataInit(tv_month) // now_month

        calendar_viewPager.setUserInputEnabled(false)
        calendar_viewPager.adapter = CalendarAdapter(view.context, nowCalendarData)

        btn_left_month.setOnClickListener(){
            nowCalendarData = calendarPrevMonth(tv_month, nowCalendarData.month, nowCalendarData.year) // prev_month
            calendar_viewPager.adapter = CalendarAdapter(view.context, nowCalendarData)
        }

        btn_right_month.setOnClickListener() {
            nowCalendarData = calendarNextMonth(tv_month, nowCalendarData.month, nowCalendarData.year) // next_month
            calendar_viewPager.adapter = CalendarAdapter(view.context, nowCalendarData)
        }

        btn_today.setOnClickListener() {
            nowCalendarData = calendarDataInit(tv_month) // today
            calendar_viewPager.adapter = CalendarAdapter(view.context, nowCalendarData)
        }

        btn_schedule.setOnClickListener(){
            val intent = Intent(activity, ScheduleActivity::class.java)
            startActivity(intent)
        }

        btn_question.setOnClickListener(){
            val builder = android.app.AlertDialog.Builder(context)
            val layout = LayoutInflater.from(context).inflate(R.layout.calendar_question_popup_layout, null)

            builder.setView(layout)

            dialog = builder.create()

            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()

            var width = getResources().getDimensionPixelSize(R.dimen.popup_width)
            var height = getResources().getDimensionPixelSize(R.dimen.popup_height)
            dialog.window?.setLayout(width, height)

        }
    }
}