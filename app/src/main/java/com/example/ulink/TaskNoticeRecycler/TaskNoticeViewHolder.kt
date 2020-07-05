package com.example.ulink.TaskNoticeRecycler

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ulink.R

class TaskNoticeViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tv_date : TextView = itemView.findViewById(R.id.tv_date)
    val tv_task_name : TextView = itemView.findViewById(R.id.tv_task_name)
    val tv_task : TextView = itemView.findViewById(R.id.tv_task)

    fun bind(TaskNoticeData : TaskNoticeData){
        tv_date.setText("${TaskNoticeData.StartDate}/${TaskNoticeData.EndDate}")
        tv_task_name.text = TaskNoticeData.TaskName
        tv_task.text = TaskNoticeData.Task

    }

}