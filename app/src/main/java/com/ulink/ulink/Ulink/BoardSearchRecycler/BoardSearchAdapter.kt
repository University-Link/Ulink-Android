package com.ulink.ulink.Ulink.UlinkClassBoardRecycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ulink.ulink.R
import com.ulink.ulink.Ulink.BoardData

class BoardSearchAdapter (private val context: Context) : RecyclerView.Adapter<UlinkBoardSearchViewHolder>() {
    var datas:MutableList<BoardData> = mutableListOf<BoardData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UlinkBoardSearchViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_ulink_board_class_data,parent,false)
        return UlinkBoardSearchViewHolder(view)
    }
    private lateinit var itemClickListener : ItemClickListener
    interface ItemClickListener {
        fun onClick(view: View, position:Int)
    }
    fun setItemClickLIstener(itemClickListener: ItemClickListener){
        this.itemClickListener = itemClickListener
    }
    override fun getItemCount(): Int {
        return datas.size

    }

    override fun onBindViewHolder(holder: UlinkBoardSearchViewHolder, position: Int) {
        holder.bind(datas[position])
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

}