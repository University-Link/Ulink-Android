package com.example.ulink.timetable

import android.opengl.Visibility
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ulink.R
import com.example.ulink.repository.TimeTable

class TimeTableInnerListAdapter(mutableList: MutableList<TimeTable>) : RecyclerView.Adapter<TimeTableInnerListAdapter.VHolder>() {

    val timeTableList: MutableList<TimeTable> = mutableList

//    리스트 대표인거 제일 위로 올리기!!

    fun arrangeList(list : MutableList<TimeTable>) : MutableList<TimeTable>{
        for (i in 0 until list.size){
            if (list[i].isMain){
                list.add(0,list[i])
                list.removeAt(i+1)
            }
        }
        return list
    }


    class VHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setHolder(timeTable: TimeTable) {
            itemView.findViewById<TextView>(R.id.tv_timetablename).text = timeTable.name
            if (timeTable.isMain){
                itemView.findViewById<TextView>(R.id.tv_main).visibility = View.VISIBLE
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        return VHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_timetableinnerlist, parent, false))
    }

    override fun getItemCount(): Int {
        return timeTableList.size
    }

    override fun onBindViewHolder(holder: VHolder, position: Int) {

        holder.setHolder(arrangeList(timeTableList)[position])
//        여기서 대

    }
}