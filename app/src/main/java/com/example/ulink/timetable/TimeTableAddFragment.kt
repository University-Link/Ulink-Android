package com.example.ulink.timetable

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import com.example.ulink.R
import com.example.ulink.repository.TimeTable
import kotlinx.android.synthetic.main.fragment_time_table_add.*


//      TODO Scrollview안에 textview있어서 height계산할때 까먹지말기!!

class TimeTableAddFragment : Fragment() {

    lateinit var timeTableAddListener: timeTableAddListener

    fun setTimeTableAddListner(timeTableAddListener: timeTableAddListener) {
        this.timeTableAddListener = timeTableAddListener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_time_table_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        layout_timetable_scrollable.overScrollMode = View.OVER_SCROLL_NEVER


        val timeTable = arguments?.getParcelable<TimeTable>("timetable")!!
        val tableName = arguments?.getString("tablename")

        tv_tablename.text = tableName

        val timeTableDrawer = TimeTableDrawer(requireContext(), LayoutInflater.from(requireContext()), timeTable)
        timeTableDrawer.minHeight = 50.0f
        timeTableDrawer.draw(view.findViewById<FrameLayout>(R.id.layout_timetable))
        view.findViewById<NestedScrollView>(R.id.layout_timetable_scrollable).isVerticalScrollBarEnabled = false
    }

}