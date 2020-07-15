package com.example.ulink.timetable

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.ulink.repository.Subject

class TimeTableEditorAdapter(fragmentActivity: FragmentActivity, val fragmentList : MutableList<Fragment> = arrayListOf()) : FragmentStateAdapter(fragmentActivity){


    fun setFragments(){
        fragmentList.add(TimeTableFilterSearchFragment())
        fragmentList.add(TimeTableCandidateFragment())
    }


    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {

        return when(position){
            0 -> {
                fragmentList[position]
            }
            else -> {
                fragmentList[position]
            }
        }
    }


}