package com.ulink.ulink

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ulink.ulink.fragment.*


class MainPagerAdapter (fm: FragmentManager):
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position:Int): Fragment {
        return when(position){
            0-> TimeTableFragment()
            1-> ClassFragment()
            2-> CalendarFragment()
            else-> MyFragment()
        }
    }

    override fun getCount() = 4
}