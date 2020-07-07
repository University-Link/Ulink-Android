package com.example.ulink.fragment

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ulink.CalendarRecycler.*
import com.example.ulink.ChattingActivity
import com.example.ulink.ClassRecycler.ClassAdapter
import com.example.ulink.R
import com.example.ulink.ScheduleActivity
import kotlinx.android.synthetic.main.calendar_popup_layout.*
import kotlinx.android.synthetic.main.fragment_calendar.*

class CalendarFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_today.text = nowDay().toString()

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
    }
}