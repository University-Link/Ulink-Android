package com.example.ulink.ClassNoticeRecycler

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ulink.R

class ClassNoticeViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tv_date : TextView = itemView.findViewById(R.id.tv_date)
    val tv_test_name : TextView = itemView.findViewById(R.id.tv_test_name)
    val tv_time : TextView = itemView.findViewById(R.id.tv_time)
    val tv_test_range : TextView = itemView.findViewById(R.id.tv_test_range)

    fun bind(ClassNoticeData : ClassNoticeData){
        tv_date.setText("${ClassNoticeData.StartDate}/${ClassNoticeData.EndDate}")
        tv_test_name.text = ClassNoticeData.NoticeName
        tv_time.setText("${ClassNoticeData.StartTime} ~ ${ClassNoticeData.EndTime}")
        tv_test_range.setText("${ClassNoticeData.StartTask}~${ClassNoticeData.EndTask}")

    }


}