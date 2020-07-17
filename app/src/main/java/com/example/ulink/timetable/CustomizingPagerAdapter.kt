package com.example.ulink.timetable

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import kotlinx.android.synthetic.main.layout_timetable_bottomsheet_customize1.*
import kotlinx.android.synthetic.main.layout_timetable_bottomsheet_customize2.*
import kotlinx.android.synthetic.main.layout_timetable_bottomsheet_customize3.*

class CustomizingPagerAdapter (fm : Fragment, val fragmentList: List<Fragment>) : FragmentStateAdapter(fm){


    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> fragmentList[0]
            1-> fragmentList[1]
            else -> fragmentList[2]
        }
    }


}