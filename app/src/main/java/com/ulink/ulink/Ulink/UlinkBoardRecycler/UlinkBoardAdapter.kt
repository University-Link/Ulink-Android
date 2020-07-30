package com.ulink.ulink.Ulink.UlinkBoardRecycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ulink.ulink.ClassRecycler.ClassAdapter
import com.ulink.ulink.R

class UlinkBoardAdapter (private val context: Context) : RecyclerView.Adapter<UlinkBoardViewHolder>() {
    var datas:MutableList<UlinkBoardData> = mutableListOf<UlinkBoardData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UlinkBoardViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_ulink_board_data,parent,false)
        return UlinkBoardViewHolder(view)
    }
    private lateinit var itemClickListener : UlinkBoardAdapter.ItemClickListener
    interface ItemClickListener {
        fun onClick(view: View, position:Int)
    }
    fun setItemClickLIstener(itemClickListener: UlinkBoardAdapter.ItemClickListener){
        this.itemClickListener = itemClickListener
    }
    override fun getItemCount(): Int {
        return datas.size

    }

    override fun onBindViewHolder(holder: UlinkBoardViewHolder, position: Int) {
        holder.bind(datas[position])
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

}