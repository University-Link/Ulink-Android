package com.example.ulink.timetable

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ulink.R
import com.example.ulink.repository.TimeTable

class TimeTableListAdapter() : RecyclerView.Adapter<TimeTableListAdapter.VHolder>() {

    val arrangedList: MutableList<MutableList<TimeTable>> = arrayListOf()

//    데이터 받은거 보고 가공해서 semester별로 뿌리기

    init {
        val list: MutableList<TimeTable> = arrayListOf()
        val tt = TimeTable(1, "2020-1", "시간표시간표", null, true)
        val tt2 = TimeTable(1, "2020-1", "시간표시간표2", null, false)
        val tt3 = TimeTable(1, "2020-1", "시간표시간표3", null, false)
        list.apply {
            add(tt)
            add(tt2)
            add(tt3)
        }
        val list2: MutableList<TimeTable> = arrayListOf()
        val tt4 = TimeTable(1, "2020-2", "시간표시간표1", null, true)
        val tt5 = TimeTable(1, "2020-2", "시간표시간표2", null, false)
        val tt6 = TimeTable(1, "2020-2", "시간표시간표3", null, false)
        list2.apply {
            add(tt4)
            add(tt5)
            add(tt6)
        }
        arrangedList.add(list)
        arrangedList.add(list2)
    }


    class VHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val recyclerview = itemView.findViewById<RecyclerView>(R.id.rv_timetablelist)

        fun setHolder(timeTableList: MutableList<TimeTable>) {
            itemView.findViewById<TextView>(R.id.tv_semester).text = timeTableList[0].semseter
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        return VHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_timetablelist, parent, false))
    }

    override fun getItemCount(): Int {
        return arrangedList.size
    }

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        holder.setHolder(arrangedList[position])
        holder.recyclerview.adapter = TimeTableInnerListAdapter(arrangedList[position])
    }
}