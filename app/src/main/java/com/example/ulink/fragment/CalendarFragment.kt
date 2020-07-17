package com.example.ulink.fragment

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.ulink.CalendarRecycler.*
import com.example.ulink.ClassRecycler.ClassAdapter
import com.example.ulink.ClassRecycler.ClassData
import com.example.ulink.R
import com.example.ulink.ScheduleActivity
import com.example.ulink.ScheduleRecycler.nowDay
import com.example.ulink.repository.Notice
import com.example.ulink.repository.ResponseCalendar
import com.example.ulink.repository.ResponseChatting
import com.example.ulink.repository.RetrofitService
import kotlinx.android.synthetic.main.fragment_calendar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
            nowCalendarData = calendarDataInit(tv_month) // today
            calendar_viewPager.adapter = CalendarAdapter(view.context, nowCalendarData, view)
        }

        btn_schedule.setOnClickListener() {
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

    fun getYearMonth() : String{
        return view?.findViewById<TextView>(R.id.tv_month)?.text.toString()
    }
}