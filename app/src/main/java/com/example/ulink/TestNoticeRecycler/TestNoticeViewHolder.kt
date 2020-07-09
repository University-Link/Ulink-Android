package com.example.ulink.TestNoticeRecycler

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ulink.R

class TestNoticeViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tv_date : TextView = itemView.findViewById(R.id.tv_date)
    val tv_test_name : TextView = itemView.findViewById(R.id.tv_test_name)
    val tv_time : TextView = itemView.findViewById(R.id.tv_time)

    fun bind(ClassNoticeData : TestNoticeData){
        tv_date.setText("${ClassNoticeData.StartDate}/${ClassNoticeData.EndDate}")
        tv_test_name.text = ClassNoticeData.NoticeName


        if (ClassNoticeData.StartTime == "시간정보 없음"){
            tv_time.setText("시간정보 없음")
        } else {
            tv_time.setText("${ClassNoticeData.StartTime} ~ ${ClassNoticeData.EndTime}")

        }

    }


}