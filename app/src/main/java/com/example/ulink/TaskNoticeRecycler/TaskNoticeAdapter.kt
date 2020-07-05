package com.example.ulink.TaskNoticeRecycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ulink.ClassRecycler.ClassData
import com.example.ulink.ClassRecycler.ClassViewHolder
import com.example.ulink.R

class TaskNoticeAdapter (private val context: Context) : RecyclerView.Adapter<TaskNoticeViewHolder>(){
    var datas : MutableList<TaskNoticeData> = mutableListOf<TaskNoticeData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskNoticeViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_task_notice,parent,false)
        return TaskNoticeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size

    }

    override fun onBindViewHolder(holder: TaskNoticeViewHolder, position: Int) {
        holder.bind(datas[position])
    }
}