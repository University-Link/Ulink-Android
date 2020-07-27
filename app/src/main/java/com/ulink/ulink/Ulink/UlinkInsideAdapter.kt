package com.ulink.ulink.Ulink

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class UlinkInsideAdapter (fm: FragmentManager) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> UlinkBoardFragment()
            else -> UlinkNoticeFragment()
        }
    }

    override fun getCount()=2

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "게시판"
            else -> {return "공지"}
        }
    }
}