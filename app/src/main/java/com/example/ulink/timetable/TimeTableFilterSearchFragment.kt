package com.example.ulink.timetable

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.ulink.FilterNormalActivity
import com.example.ulink.R
import com.example.ulink.repository.Subject
import kotlinx.android.synthetic.main.fragment_timetablefiltersearch.*

class TimeTableFilterSearchFragment() : Fragment() {

    lateinit var mAdapter : TimeTableClassAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_timetablefiltersearch, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val subjectList: MutableList<Subject> = arrayListOf()
        subjectList.add(Subject(1, "전자회로I", "09:00", "12:00", "mon", "과목장소", 1, true, isSample = true, number = "342"))
        subjectList.add(Subject(2, "전자회로2", "12:30", "13:00", "mon", "과목장소", 1, true, isSample = true))
        subjectList.add(Subject(3, "전자회로3", "11:00", "13:00", "fri", "과목장소", 1, true, isSample = true))
        subjectList.add(Subject(4, "전자회로4", "10:00", "12:00", "wed", "과목장소", 1, true, isSample = true))
        subjectList.add(Subject(4, "전자회로5", "13:00", "16:00", "thu", "과목장소", 1, true, isSample = true))
        subjectList.add(Subject(4, "전자회로6", "13:00", "17:30", "wed", "과목장소", 1, true, isSample = true))
        subjectList.add(Subject(4, "전자회로7", "13:00", "17:30", "wed", "과목장소", 1, true, isSample = true))
        subjectList.add(Subject(4, "전자회로7", "13:00", "17:30", "wed", "과목장소", 1, true, isSample = true))
        subjectList.add(Subject(4, "전자회로7", "13:00", "17:30", "wed", "과목장소", 1, true, isSample = true))
        subjectList.add(Subject(4, "전자회로7", "13:00", "17:30", "wed", "과목장소", 1, true, isSample = true))
        subjectList.add(Subject(4, "전자회로7", "13:00", "17:30", "wed", "과목장소", 1, true, isSample = true))
        subjectList.add(Subject(4, "전자회로7", "13:00", "17:30", "wed", "과목장소", 1, true, isSample = true))
        subjectList.add(Subject(4, "전자회로7", "13:00", "17:30", "wed", "과목장소", 1, true, isSample = true))
        subjectList.add(Subject(4, "전자회로8", "13:00", "17:30", "wed", "과목장소", 1, true, isSample = true))


//        TODO 자동 스크롤 고치기
        mAdapter = TimeTableClassAdapter(requireContext(), object : onItemClickListener{
            override fun onItemClicked(position: Int) {

            }
        })
        rv_classes.adapter = mAdapter

        mAdapter.addToList(subjectList)

        btn_fitler_major.setOnClickListener {
            //TODO 전공필터 만들면 연결
        }

        btn_filter_normal.setOnClickListener {
            val intent = Intent(activity,FilterNormalActivity::class.java)
            startActivity(intent)
        }
    }

    interface onItemClickListener{
        fun onItemClicked(position : Int)
    }


}