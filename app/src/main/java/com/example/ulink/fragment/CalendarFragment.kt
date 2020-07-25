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
import com.example.ulink.Activity.ScheduleActivity
import com.example.ulink.ScheduleRecycler.nowDay
import kotlinx.android.synthetic.main.fragment_calendar.*

class CalendarFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    override fun onResume() {
        super.onResume()

        var nowCalendarData = calendarDataInit(tv_month) // now_month

        calendar_viewPager.setUserInputEnabled(false)
        calendar_viewPager.adapter = view?.context?.let { CalendarAdapter(it, nowCalendarData, view!!) }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        lateinit var dialog: AlertDialog

        btn_today.text = nowDay.toString()

        var nowCalendarData = calendarDataInit(tv_month) // now_month

        calendar_viewPager.setUserInputEnabled(false)
        calendar_viewPager.adapter = CalendarAdapter(view.context, nowCalendarData, view)

        btn_left_month.setOnClickListener() {
            nowCalendarData = calendarPrevMonth(
                tv_month,
                nowCalendarData.month,
                nowCalendarData.year
            ) // prev_month
            calendar_viewPager.adapter = CalendarAdapter(view.context, nowCalendarData, view)
        }

        btn_right_month.setOnClickListener() {
            nowCalendarData = calendarNextMonth(
                tv_month,
                nowCalendarData.month,
                nowCalendarData.year
            ) // next_month
            calendar_viewPager.adapter = CalendarAdapter(view.context, nowCalendarData, view)
        }

        btn_today.setOnClickListener() {
            nowCalendarData = calendarDataInit(tv_month) // today(현재 달로 이동)
            calendar_viewPager.adapter = CalendarAdapter(view.context, nowCalendarData, view)
        }

        btn_schedule.setOnClickListener() { // 일정 뷰 이동
            val intent = Intent(activity, ScheduleActivity::class.java)
            startActivity(intent)
        }

        btn_question.setOnClickListener() {
            val builder = android.app.AlertDialog.Builder(context)
            val layout =
                LayoutInflater.from(context).inflate(R.layout.calendar_question_popup_layout, null)

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