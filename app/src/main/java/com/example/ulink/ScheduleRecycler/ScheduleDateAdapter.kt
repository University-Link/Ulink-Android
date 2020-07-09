package com.example.ulink.ScheduleRecycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ulink.R

class ScheduleDateAdapter(private val context : Context) : RecyclerView.Adapter<ScheduleDateViewHolder>(){
    var dateDatas = mutableListOf<ScheduleDateData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleDateViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.schedule_day_item,parent,false)
        return ScheduleDateViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dateDatas.size
    }

    override fun onBindViewHolder(holder: ScheduleDateViewHolder, position: Int) {
        holder.bind(dateDatas[position])

        val scheduleList: MutableList<MutableList<ScheduleItemData>> = arrayListOf()

        //val scheduleItemDataList : MutableList<ScheduleItemData> = arrayListOf()

        val scheduleItemDataList1 : MutableList<ScheduleItemData> = arrayListOf()
        val scheduleItemDataList2 : MutableList<ScheduleItemData> = arrayListOf()
        val scheduleItemDataList3 : MutableList<ScheduleItemData> = arrayListOf()
        val scheduleItemDataList4 : MutableList<ScheduleItemData> = arrayListOf()
        val scheduleItemDataList5 : MutableList<ScheduleItemData> = arrayListOf()
        val scheduleItemDataList6 : MutableList<ScheduleItemData> = arrayListOf()
        val scheduleItemDataList7 : MutableList<ScheduleItemData> = arrayListOf()
        val scheduleItemDataList8 : MutableList<ScheduleItemData> = arrayListOf()
        val scheduleItemDataList9 : MutableList<ScheduleItemData> = arrayListOf()
        val scheduleItemDataList10 : MutableList<ScheduleItemData> = arrayListOf()

        scheduleItemDataList1.add(
            ScheduleItemData(
                date = "2020-07-10",
                category = "시험",
                classname = "유링크",
                content = "앱잼",
                startTime = "11:00",
                endTime = "13:00"
            )
        )

        scheduleItemDataList1.add(
            ScheduleItemData(
                date = "2020-07-10",
                category = "시험",
                classname = "안드로이드",
                content = "개발힘드네",
                startTime = "11:00",
                endTime = "13:00"
            )
        )

        scheduleItemDataList2.add(
            ScheduleItemData(
                date = "2020-07-27",
                category = "과제",
                classname = "유링크",
                content = "캘린더뷰",
                startTime = "11:00",
                endTime = "13:00"
            )
        )

        scheduleItemDataList3.add(
            ScheduleItemData(
                date = "2020-07-27",
                category = "수업",
                classname = "유링크",
                content = "안드개발",
                startTime = "11:00",
                endTime = "13:00"
            )
        )


        scheduleItemDataList4.add(
            ScheduleItemData(
                date = "2020-07-27",
                category = "과제",
                classname = "유링크",
                content = "캘린더뷰",
                startTime = "11:00",
                endTime = "13:00"
            )
        )

        scheduleItemDataList5.add(
            ScheduleItemData(
                date = "2020-07-27",
                category = "과제",
                classname = "유링크",
                content = "캘린더뷰",
                startTime = "11:00",
                endTime = "13:00"
            )
        )

        scheduleItemDataList6.add(
            ScheduleItemData(
                date = "2020-07-27",
                category = "시험",
                classname = "유링크",
                content = "캘린더뷰",
                startTime = "11:00",
                endTime = "13:00"
            )
        )

        scheduleItemDataList7.add(
            ScheduleItemData(
                date = "2020-07-27",
                category = "수업",
                classname = "유링크",
                content = "캘린더뷰",
                startTime = "11:00",
                endTime = "13:00"
            )
        )

        scheduleItemDataList8.add(
            ScheduleItemData(
                date = "2020-07-27",
                category = "시험",
                classname = "유링크",
                content = "캘린더뷰",
                startTime = "11:00",
                endTime = "13:00"
            )
        )

        scheduleItemDataList9.add(
            ScheduleItemData(
                date = "2020-07-27",
                category = "시험",
                classname = "유링크",
                content = "캘린더뷰",
                startTime = "11:00",
                endTime = "13:00"
            )
        )


        scheduleItemDataList10.add(
            ScheduleItemData(
                date = "2020-07-27",
                category = "수업",
                classname = "유링크",
                content = "캘린더뷰",
                startTime = "11:00",
                endTime = "13:00"
            )
        )

        scheduleList.add(scheduleItemDataList1)
        scheduleList.add(scheduleItemDataList2)
        scheduleList.add(scheduleItemDataList3)
        scheduleList.add(scheduleItemDataList4)
        scheduleList.add(scheduleItemDataList5)
        scheduleList.add(scheduleItemDataList6)
        scheduleList.add(scheduleItemDataList7)
        scheduleList.add(scheduleItemDataList8)
        scheduleList.add(scheduleItemDataList9)
        scheduleList.add(scheduleItemDataList10)

        holder.recyclerView.adapter = ScheduleItemAdapter(context, scheduleList[position])
    }
}