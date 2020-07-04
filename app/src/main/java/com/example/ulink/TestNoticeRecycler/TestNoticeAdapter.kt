package com.example.ulink.TestNoticeRecycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ulink.R

class TestNoticeAdapter (private val context: Context) : RecyclerView.Adapter<TestNoticeViewHolder>(){
    var datas : MutableList<TestNoticeData> = mutableListOf<TestNoticeData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestNoticeViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_test_notice,parent,false)
        return TestNoticeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size

    }

    override fun onBindViewHolder(holder: TestNoticeViewHolder, position: Int) {
        holder.bind(datas[position])
    }
}