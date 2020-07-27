package com.ulink.ulink.ChattingRecycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ulink.ulink.R

class ChattingAdapter (private val context: Context) : RecyclerView.Adapter<ChattingViewHolder>() {
   var datas:MutableList<ChattingData> = mutableListOf<ChattingData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChattingViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_chatting,parent,false)
        return ChattingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: ChattingViewHolder, position: Int) {
        holder.bind(datas[position])
    }

}
