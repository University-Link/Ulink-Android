package com.example.ulink.ExperimentNoticeRecycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ulink.ClassRecycler.ClassData
import com.example.ulink.ClassRecycler.ClassViewHolder
import com.example.ulink.R

class ExperimentNoticeAdapter (private val context: Context) : RecyclerView.Adapter<ExperimentNoticeViewHolder>(){
    var datas : MutableList<ExperimentNoticeData> = mutableListOf<ExperimentNoticeData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExperimentNoticeViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_class_notice,parent,false)
        return ExperimentNoticeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size

    }

    override fun onBindViewHolder(holder: ExperimentNoticeViewHolder, position: Int) {
        holder.bind(datas[position])
    }
}