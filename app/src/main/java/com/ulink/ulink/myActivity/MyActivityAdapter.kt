package com.ulink.ulink.myActivity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MyActivityAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity){

    val fragmentList : MutableList<Fragment> = arrayListOf()

    init {
        for (i in 0 until 3){
            fragmentList.add(MyActivityFragment())
        }
    }


    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}