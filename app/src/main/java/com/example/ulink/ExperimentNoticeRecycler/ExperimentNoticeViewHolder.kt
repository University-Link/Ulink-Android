package com.example.ulink.ExperimentNoticeRecycler

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ulink.R

class ExperimentNoticeViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tv_date : TextView = itemView.findViewById(R.id.tv_date)
    val tv_class_name : TextView = itemView.findViewById(R.id.tv_class_name)
    val tv_class : TextView = itemView.findViewById(R.id.tv_class)

    fun bind(ExperimentNoticeData  : ExperimentNoticeData ){
        tv_date.setText("${ExperimentNoticeData.StartDate}/${ExperimentNoticeData.EndDate}")
        tv_class_name.text = ExperimentNoticeData.ClassName
        tv_class.text = ExperimentNoticeData.Class

    }

}