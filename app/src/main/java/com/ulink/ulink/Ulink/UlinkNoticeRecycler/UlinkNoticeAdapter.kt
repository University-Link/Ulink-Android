package com.ulink.ulink.Ulink.UlinkNoticeRecycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ulink.ulink.R

class UlinkNoticeAdapter (private val context: Context) : RecyclerView.Adapter<UlinkNoticeViewHolder>() {
    var datas:MutableList<UlinkNoticeData> = mutableListOf<UlinkNoticeData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UlinkNoticeViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_ulink_notice_data,parent,false)
        return UlinkNoticeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: UlinkNoticeViewHolder, position: Int) {
        holder.bind(datas[position])
    }

}