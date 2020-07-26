package com.example.ulink.timetable

import android.util.Log
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

    override fun getItemId(position: Int): Long {
        if(position==0){
           // onGetPositionListener.ongetPosition(0)
            Log.d("0바뀜","00")
        }else{
            //onGetPositionListener.ongetPosition(1)
            Log.d("1바뀜","11")
        }

        return super.getItemId(position)

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