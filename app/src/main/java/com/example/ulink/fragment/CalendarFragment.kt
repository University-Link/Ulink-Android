package com.example.ulink.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.CalendarAdapter
import com.example.myapplication.CalendarData
import com.example.ulink.R
import kotlinx.android.synthetic.main.fragment_calendar.*
import java.util.*

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

        var cal: Calendar = Calendar.getInstance()
        var year_data = cal.get(Calendar.YEAR)
        var now_month = cal.get(Calendar.MONTH) + 1

        tv_month.setText(year_data.toString() + "년" + now_month.toString() + "월")

        var now_CalendarData = CalendarData(year_data, now_month, 20)
        var CalendarAdapter = CalendarAdapter(view.context, now_CalendarData)

        calendar_viewPager.setUserInputEnabled(false)

        calendar_viewPager.adapter = CalendarAdapter

        btn_left_month.setOnClickListener() {
            now_month -= 1

            if (now_month == 0) {
                now_month = 12
                year_data -= 1
            }

            now_CalendarData = CalendarData(year_data, now_month, 20)
            CalendarAdapter = CalendarAdapter(view.context, now_CalendarData)

            calendar_viewPager.adapter = CalendarAdapter

            tv_month.setText(now_month.toString() + "월")
        }

        btn_right_month.setOnClickListener() {
            now_month += 1

            if (now_month == 13) {
                now_month = 1
                year_data += 1
            }

            now_CalendarData = CalendarData(year_data, now_month, 20)
            CalendarAdapter = CalendarAdapter(view.context, now_CalendarData)

            calendar_viewPager.adapter = CalendarAdapter

            tv_month.setText(now_month.toString() + "월")
        }
    }
}