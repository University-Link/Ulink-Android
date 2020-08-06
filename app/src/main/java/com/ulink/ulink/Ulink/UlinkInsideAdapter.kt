package com.ulink.ulink.Ulink

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ulink.ulink.Ulink.ClassBoard.UlinkBoardFragment
import com.ulink.ulink.Ulink.ClassBoard.UlinkNoticeFragment

class UlinkInsideAdapter (fm: FragmentManager, className : String, classIdx : String) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    var className = className
    var classIdx = classIdx

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> UlinkBoardFragment()
            else -> UlinkNoticeFragment(className, classIdx)
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