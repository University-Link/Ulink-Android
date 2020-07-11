package com.example.ulink.timetable

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder
import com.example.ulink.repository.TimeTable

class TimeTableAddAdapter(fragmentActivity: FragmentActivity, val fragmentList : MutableList<Fragment> = arrayListOf()) : FragmentStateAdapter(fragmentActivity) {

    //  TODO 이 리스트는 어떻게 관리할까?

    val timeTableList: MutableList<TimeTable> = arrayListOf()
    val timeTableSampleList: MutableList<TimeTable> = arrayListOf()

    var timeTableAddListener: TimeTableAddListener? = null


    fun setList(list: MutableList<TimeTable>) {
        timeTableList.clear()
        fragmentList.clear()
        timeTableSampleList.clear()

        timeTableList.addAll(list)
        timeTableSampleList.addAll(list)

        for (i in 0 until timeTableList.size){
            val fragment = TimeTableAddFragment()
            fragmentList.add(fragment)

        }
        notifyDataSetChanged()
    }

    fun addToList(timeTable: TimeTable) {
        timeTableList.add(timeTable)
        timeTableSampleList.add(timeTable)
        fragmentList.add(TimeTableAddFragment())
        notifyDataSetChanged()
    }


    fun replaceAtList(position: Int, timeTable : TimeTable){
        timeTableList.removeAt(position)
        timeTableList.add(position,timeTable)
        Log.d("tag", "list replaced")
        (fragmentList[position] as TimeTableAddFragment).setTable(timeTable)
        notifyDataSetChanged()
    }

    fun replaceAtSampleList(position: Int, timeTable : TimeTable){
        timeTableSampleList.removeAt(position)
        timeTableSampleList.add(position,timeTable)
        Log.d("tag", "sample list replaced")
        (fragmentList[position] as TimeTableAddFragment).setTable(timeTable)
        notifyDataSetChanged()
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

        Log.d("tag","position = $position")
        Log.d("tag",timeTableList.size.toString())
        Log.d("tag",fragmentList.size.toString())

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