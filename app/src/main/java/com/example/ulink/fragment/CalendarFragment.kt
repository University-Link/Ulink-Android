package com.example.ulink.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.CalendarAdapter
import com.example.ulink.CalendarRecycler.CalendarDataInit
import com.example.ulink.CalendarRecycler.CalendarNextMonth
import com.example.ulink.CalendarRecycler.CalendarPrevMonth
import com.example.ulink.R
import kotlinx.android.synthetic.main.calendar_layout.*
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

        var now_CalendarData = CalendarDataInit(tv_month) // now_month

        calendar_viewPager.setUserInputEnabled(false)
        calendar_viewPager.adapter = CalendarAdapter(view.context, now_CalendarData)

        btn_left_month.setOnClickListener(){
            now_CalendarData = CalendarPrevMonth(tv_month, now_CalendarData.month, now_CalendarData.year) // prev_month
            calendar_viewPager.adapter = CalendarAdapter(view.context, now_CalendarData)
        }

        btn_right_month.setOnClickListener() {
            now_CalendarData = CalendarNextMonth(tv_month, now_CalendarData.month, now_CalendarData.year) // next_month
            calendar_viewPager.adapter = CalendarAdapter(view.context, now_CalendarData)
        }
    }
}