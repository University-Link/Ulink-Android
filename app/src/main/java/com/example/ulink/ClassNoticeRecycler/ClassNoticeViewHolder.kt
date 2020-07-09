package com.example.ulink.ClassNoticeRecycler

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ulink.R

class ClassNoticeViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tv_date : TextView = itemView.findViewById(R.id.tv_date)
    val tv_class_name : TextView = itemView.findViewById(R.id.tv_class_name)
    val tv_class : TextView = itemView.findViewById(R.id.tv_class)

    fun bind(ClassNoticeData : ClassNoticeData){
        tv_date.setText("${ClassNoticeData.StartDate}/${ClassNoticeData.EndDate}")
        tv_class_name.text = ClassNoticeData.ClassName
        tv_class.setText("${ClassNoticeData.StartTime} ~ ${ClassNoticeData.EndTime}")

    }

}