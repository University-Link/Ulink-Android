package com.example.ulink.register

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.ulink.fragment.ClassFragment

class RegisterPagerAdapter (fm: FragmentManager):
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position:Int): Fragment {
        return when(position){
            0-> UniversityFragment()
            1-> MajorFragment()
            else-> ClassFragment()
        }
    }

    override fun getCount() = 2
}