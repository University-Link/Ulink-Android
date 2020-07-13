package com.example.ulink.timetable

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ulink.EvaluationActivity
import com.example.ulink.R
import com.example.ulink.repository.Subject
import kotlinx.android.synthetic.main.fragment_time_table_candidate.*
import kotlinx.android.synthetic.main.item_subject_child.*


class TimeTableCandidateFragment() : Fragment(){

    val mAdapter = TimeTableCandidateAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_time_table_candidate, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_candidate.adapter =mAdapter

    }





    //    여기로 subject list 받아서 recyclerview로 표시하기
//    onResume마다 db에서 로딩
    override fun onResume() {
        super.onResume()
//        btn_assess.setOnClickListener {
//            val intent = Intent(context,EvaluationActivity::class.java)
//            startActivity(intent)
//        }

        mAdapter.addToList(Subject(1, "전자회로I", "09:00", "12:00", "mon", "과목장소", 1, true))
        mAdapter.notifyDataSetChanged()
    }



}