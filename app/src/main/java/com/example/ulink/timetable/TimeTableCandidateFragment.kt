package com.example.ulink.timetable

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ulink.R
import com.example.ulink.repository.Subject
import kotlinx.android.synthetic.main.fragment_time_table_candidate.*


class TimeTableCandidateFragment : Fragment() , TimeTableSubjectClickListener{


    val mAdapter =  TimeTableCandidateAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_time_table_candidate, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_candidate.adapter = mAdapter
    }

    override fun onClick(subject: Subject) {
        mAdapter.addToList(subject)
    }

    //    여기로 subject list 받아서 recyclerview로 표시하기
    
}