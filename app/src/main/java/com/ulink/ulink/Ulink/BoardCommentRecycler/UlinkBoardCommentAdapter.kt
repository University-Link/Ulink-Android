package com.ulink.ulink.Ulink.BoardCommentRecycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ulink.ulink.R
import com.ulink.ulink.Ulink.UlinkBoardData

class UlinkBoardCommentAdapter (private val context: Context) : RecyclerView.Adapter<UlinkBoardCommentViewHolder>() {
    var datas:MutableList<UlinkBoardData> = mutableListOf<UlinkBoardData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UlinkBoardCommentViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_board_comment,parent,false)
        return UlinkBoardCommentViewHolder(view)
    }
    private lateinit var itemClickListener : UlinkBoardCommentAdapter.ItemClickListener
    interface ItemClickListener {
        fun onClick(view: View, position:Int)
    }
    fun setItemClickLIstener(itemClickListener: UlinkBoardCommentAdapter.ItemClickListener){
        this.itemClickListener = itemClickListener
    }
    override fun getItemCount(): Int {
        return datas.size

    }

    override fun onBindViewHolder(holder: UlinkBoardCommentViewHolder, position: Int) {
        holder.bind(datas[position])
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

}