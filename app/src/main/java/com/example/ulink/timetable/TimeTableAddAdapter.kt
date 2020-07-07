package com.example.ulink.timetable
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.ulink.repository.TimeTable

class TimeTableAddAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

//  TODO 이 리스트는 어떻게 관리할까?
    val timetableList : MutableList<TimeTable> = arrayListOf()

    fun toIndex(position : Int){
        
    }

    fun setList(list : MutableList<TimeTable>){
        timetableList.clear()
        timetableList.addAll(list)
    }

    override fun getItemCount(): Int {
        return timetableList.size+1
    }

    override fun createFragment(position: Int): Fragment {

        val args = Bundle()

        if (position == timetableList.size){
            args.putParcelable("timetable",null)
        } else {
            args.putParcelable("timetable", timetableList[position])
        }
        val fragment = TimeTableAddFragment()
        fragment.arguments = args
        return fragment
    }

}