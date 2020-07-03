package com.example.ulink.ClassNoticeRecycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ulink.ClassRecycler.ClassData
import com.example.ulink.ClassRecycler.ClassViewHolder
import com.example.ulink.R

class ClassNoticeAdapter (private val context: Context) : RecyclerView.Adapter<ClassNoticeViewHolder>(){
    var datas : MutableList<ClassNoticeData> = mutableListOf<ClassNoticeData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassNoticeViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_class_notice,parent,false)
        return ClassNoticeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size

    }

    override fun onBindViewHolder(holder: ClassNoticeViewHolder, position: Int) {
        holder.bind(datas[position])
    }
}