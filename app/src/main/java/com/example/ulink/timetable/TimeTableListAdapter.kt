package com.example.ulink.timetable

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ulink.R
import com.example.ulink.repository.TimeTable

class TimeTableListAdapter(val arrangedList: MutableList<MutableList<TimeTable>>, val timeTableOnClickListener: TimeTableOnClickListener) : RecyclerView.Adapter<TimeTableListAdapter.VHolder>() {

//    데이터 받은거 보고 가공해서 semester별로 뿌리기


    class VHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val recyclerview = itemView.findViewById<RecyclerView>(R.id.rv_timetablelist)

        fun setHolder(timeTableList: MutableList<TimeTable>) {
            itemView.findViewById<TextView>(R.id.tv_semester).text = timeTableList[0].semester
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
        holder.recyclerview.adapter = TimeTableInnerListAdapter(arrangedList[position], timeTableOnClickListener)
    }
}