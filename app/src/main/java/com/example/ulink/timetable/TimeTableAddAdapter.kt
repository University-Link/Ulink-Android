package com.example.ulink.timetable

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder
import com.example.ulink.repository.Subject
import com.example.ulink.repository.TimeTable
import com.example.ulink.utils.deepCopy

class TimeTableAddAdapter(fragmentActivity: FragmentActivity, val fragmentList : MutableList<Fragment> = arrayListOf()) : FragmentStateAdapter(fragmentActivity) {

    //  TODO 이 리스트는 어떻게 관리할까?

    val timeTableList: MutableList<TimeTable> = arrayListOf()
    val timeTableSampleList: MutableList<TimeTable> = arrayListOf()

    var timeTableAddListener: TimeTableAddListener? = null


    fun setList(list: MutableList<TimeTable>) {
        timeTableList.clear()
        fragmentList.clear()
        timeTableSampleList.clear()

        for (i in list){
            timeTableList.add(deepCopy(i))
            timeTableSampleList.add(deepCopy(i))
        }

        for (i in 0 until timeTableList.size){
            val fragment = TimeTableAddFragment()
            fragmentList.add(fragment)
        }
        notifyDataSetChanged()
    }

    fun addToList(timeTable: TimeTable) {
        timeTableList.add(deepCopy(timeTable))
        timeTableSampleList.add(deepCopy(timeTable))
        fragmentList.add(TimeTableAddFragment())
        notifyDataSetChanged()
    }


    fun replaceAtList(position: Int, timeTable : TimeTable){
        timeTableList.removeAt(position)
//      TODO 이거 샘플도 바꾸는지?
        timeTableList.add(position,deepCopy(timeTable))
        (fragmentList[position] as TimeTableAddFragment).setTable(deepCopy(timeTable))
    }

    fun replaceAtSampleList(position: Int, timeTable : TimeTable){
        timeTableSampleList.removeAt(position)
        timeTableSampleList.add(position,deepCopy(timeTable))
        (fragmentList[position] as TimeTableAddFragment).setTable(deepCopy(timeTable))
    }

    fun scrollToTop(position : Int){
        (fragmentList[position] as TimeTableAddFragment).scrollToTop()
    }

    fun scrollToPosition(position : Int, subject : Subject){
        (fragmentList[position] as TimeTableAddFragment).scrollToPosition(subject)
    }

    fun reDrawFragment(position: Int){
        (fragmentList[position] as TimeTableAddFragment).drawTable()
    }

    override fun onBindViewHolder(holder: FragmentViewHolder, position: Int, payloads: MutableList<Any>) {
        if (position>0){
            super.onBindViewHolder(holder, position-1, payloads)
        }
        super.onBindViewHolder(holder, position, payloads)
    }

    override fun getItemCount(): Int {
        return timeTableList.size + 1
    }

    override fun createFragment(position: Int): Fragment {

        if (position == fragmentList.size){
            val fragment =  TimeTableAddBlankFragment()
            fragment.setTimeTableAddListner(object : TimeTableAddListener{
                override fun onAdded(timeTable: TimeTable) {

                    addToList(timeTable)
                    //                  이 listener은 그냥 move용!
                    timeTableAddListener?.onAdded(timeTable)
                }
            })
            return fragment
        }
        else{
            val args = Bundle()
            args.putParcelable("timetable", timeTableList[position])
            args.putString("tablename", timeTableList[position].name)
            fragmentList[position].arguments = args
            return fragmentList[position]
        }
    }

}