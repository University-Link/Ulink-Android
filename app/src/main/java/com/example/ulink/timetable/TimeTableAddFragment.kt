package com.example.ulink.timetable

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import com.example.ulink.R
import com.example.ulink.repository.Subject
import com.example.ulink.repository.TimeTable
import kotlinx.android.synthetic.main.fragment_time_table_add.*


//      TODO Scrollview안에 textview있어서 height계산할때 까먹지말기!!
//      여기로 subject받아서 draw subject랑 draw preview하기

class TimeTableAddFragment : Fragment() {

    lateinit var subjectAddListener: SubjectAddListener

    lateinit var timeTable: TimeTable


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_time_table_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        layout_timetable_scrollable.overScrollMode = View.OVER_SCROLL_NEVER


        timeTable = arguments?.getParcelable<TimeTable>("timetable")!!
        val tableName = arguments?.getString("tablename")

        tv_tablename.text = tableName

        val timeTableDrawer = TimeTableDrawer(requireContext(), LayoutInflater.from(requireContext()), timeTable)
        timeTableDrawer.minHeight = 50.0f
        timeTableDrawer.draw(view.findViewById<FrameLayout>(R.id.layout_timetable))
        view.findViewById<NestedScrollView>(R.id.layout_timetable_scrollable).isVerticalScrollBarEnabled = false
    }

    fun setTable(timeTable: TimeTable) {
        this.timeTable = timeTable
    }

    fun drawTable() {
        val timeTableDrawer = TimeTableDrawer(requireContext(), LayoutInflater.from(requireContext()), timeTable)
        timeTableDrawer.minHeight = 50.0f
        view?.findViewById<FrameLayout>(R.id.layout_timetable)?.let { timeTableDrawer.draw(it) }
        view?.findViewById<NestedScrollView>(R.id.layout_timetable_scrollable)!!.isVerticalScrollBarEnabled = false
    }

    fun scrollToTop() {
        view?.findViewById<NestedScrollView>(R.id.layout_timetable_scrollable)!!.scrollTo(0, 0)
    }

    fun scrollToPosition(subject: Subject) {
        val scrollView = view?.findViewById<NestedScrollView>(R.id.layout_timetable_scrollable)
//        subjectPosition 이 0.25 3.75 8 9 10이런식으로 나옴 이걸 전체 Table의 시간에서 좌표로 구해야함

        var starthour = 9
        var endhour = 18

//        TODO 이런거 삼항연산자

        if ((formatToFloat(timeTable.startTime)).toInt() < 9) {
            starthour = (formatToFloat(timeTable.startTime)).toInt()
        }

        if ((formatToFloat(timeTable.endTime)).toInt() > 18) {
            endhour = (formatToFloat(timeTable.endTime)).toInt()
        }

        var gap: Float = 0f

        scrollView?.let { sv ->
            if (sv.height > sv.getChildAt(0)?.measuredHeight?.times((formatToFloat(subject.endtime) - formatToFloat(subject.starttime)) / (endhour - starthour))!!)
                gap = sv.height?.minus(sv.getChildAt(0)?.measuredHeight?.times((formatToFloat(subject.endtime) - formatToFloat(subject.starttime)) / (endhour - starthour))!!)

            (sv.getChildAt(0)?.measuredHeight)?.times(
                    (formatToFloat(subject.starttime) - starthour) /
                            (endhour - starthour)
            )?.let {
                if (gap == 0f) {
                    sv.scrollTo(0, it.toInt())

                } else {
                    sv.scrollTo(0, it.toInt() - (gap / 2).toInt())
                }
            }
        }

    }

    fun formatToFloat(time: String): Float {
        val timesplit = time.split(":")
        return timesplit[0].toFloat() + (timesplit[1].toFloat() - timesplit[1].toFloat() % 15) / 60
    }

}