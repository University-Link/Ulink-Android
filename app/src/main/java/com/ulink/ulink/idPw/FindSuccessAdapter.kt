package com.ulink.ulink.idPw

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class FindSuccessAdapter (fm: FragmentManager, name : String, id : String) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    val name = name
    val id = id

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> IdSuccessFragment(name, id)
            else -> PwSuccessFragment()
        }
    }

    override fun getCount()=2

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "아이디"
            else -> "비밀번호"
        }
    }
}