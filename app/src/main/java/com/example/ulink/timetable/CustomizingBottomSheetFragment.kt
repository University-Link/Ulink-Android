package com.example.ulink.timetable

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.ulink.R
import com.example.ulink.fragment.onRefreshListener
import com.example.ulink.repository.*
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_time_table_edit.*
import kotlinx.android.synthetic.main.dialog_timetable_name.*
import kotlinx.android.synthetic.main.fragment_customizing_bottomsheet.*
import kotlinx.android.synthetic.main.fragment_customizing_bottomsheet.tl_indicator
import kotlinx.android.synthetic.main.layout_timetable_bottomsheet_customize1.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CustomizingBottomSheetFragment(subject : Subject, val onRefreshListener: onRefreshListener) : BottomSheetDialogFragment() {
    val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4IjoxLCJuYW1lIjoi6rmA67O067CwIiwic2Nob29sIjoi7ZWc7JaR64yA7ZWZ6rWQIiwibWFqb3IiOiLshoztlITtirjsm6jslrQiLCJpYXQiOjE1OTQ4MTY1NzQsImV4cCI6MTU5NjI1NjU3NCwiaXNzIjoiYm9iYWUifQ.JwRDELH1lA1Fb8W1ltTmhThpmgFrUTQZVocUTATv3so"
    val subject = subject
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_customizing_bottomsheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        val fragment_list : MutableList<Fragment> = arrayListOf()
        fragment_list.addAll(listOf(CustomizingFragment1(subject, object : onRefreshListener{
            override fun onRefresh() {
                onRefreshListener.onRefresh()
                dismiss()
            }
        }),CustomizingFragment2(subject, object : onRefreshListener{
            override fun onRefresh() {
                onRefreshListener.onRefresh()
                dismiss()
            }
        }),CustomizingFragment3(subject, object : onRefreshListener{
            override fun onRefresh() {
                onRefreshListener.onRefresh()
                dismiss()
            }
        })))
        val adapter = CustomizingPagerAdapter(this, fragment_list)
        vp_custom.adapter = adapter


        TabLayoutMediator(tl_indicator, vp_custom) { v, p ->
            Unit
        }.attach()

        vp_custom.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
               // lastpage = position
            }
        })
    }
}

