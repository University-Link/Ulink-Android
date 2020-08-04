package com.ulink.ulink.Ulink.UlinkUlinkBoardRecycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ulink.ulink.R
import com.ulink.ulink.Ulink.UlinkBoardData

class UlinkUlinkBoardAdapter (private val context: Context) : RecyclerView.Adapter<UlinkUlinkBoardViewHolder>() {
    var datas:MutableList<UlinkBoardData> = mutableListOf<UlinkBoardData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UlinkUlinkBoardViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_ulink_all_board_data,parent,false)
        return UlinkUlinkBoardViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: UlinkUlinkBoardViewHolder, position: Int) {
        holder.bind(datas[position])
    }

}