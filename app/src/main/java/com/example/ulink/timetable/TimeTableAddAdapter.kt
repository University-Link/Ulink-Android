package com.example.ulink.timetable

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder
import com.example.ulink.R
import com.example.ulink.repository.TimeTable

class TimeTableAddAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    //  TODO 이 리스트는 어떻게 관리할까?
    val timetableList: MutableList<TimeTable> = arrayListOf()

    var timeTableAddListener: timeTableAddListener? = null

    fun setList(list: MutableList<TimeTable>) {
        timetableList.clear()
        timetableList.addAll(list)
        notifyDataSetChanged()
    }

    fun addToList(timeTable: TimeTable) {
        timetableList.add(timeTable)
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: FragmentViewHolder, position: Int, payloads: MutableList<Any>) {
        if (position>0){
            super.onBindViewHolder(holder, position-1, payloads)
        }
        super.onBindViewHolder(holder, position, payloads)
    }

    override fun getItemCount(): Int {
        return timetableList.size + 1
    }

    override fun createFragment(position: Int): Fragment {
        Log.d("tag","position = $position")

        if (position == timetableList.size){
            val fragment =  TimeTableAddBlankFragment()
            fragment.setTimeTableAddListner(object : timeTableAddListener{
                override fun onAdded(timeTable: TimeTable) {
//              왜 추가가 안되는거지?
//                FIXME 1. +눌렀을때 추가 안되는거 2. 시간표추가 눌렀을때 두번째부터 Visibility Gone인거
                    timetableList.add(timeTable)
                    notifyItemInserted(position)
                    //                  이 listner은 그냥 move용!

                    timeTableAddListener?.onAdded(timeTable)
                }
            })
            return fragment
        } else{
            val args = Bundle()
            args.putParcelable("timetable", timetableList[position])
            args.putString("tablename", timetableList[position].name)
            val fragment = TimeTableAddFragment()
            fragment.arguments = args
            return fragment
        }
    }

}