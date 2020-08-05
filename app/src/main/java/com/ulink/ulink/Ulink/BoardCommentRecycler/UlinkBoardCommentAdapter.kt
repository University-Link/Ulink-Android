package com.ulink.ulink.Ulink.BoardCommentRecycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ulink.ulink.R
import com.ulink.ulink.Ulink.AllBoardRecycler.AllBoardClassViewHolder
import com.ulink.ulink.Ulink.AllBoardRecycler.AllBoardViewHolder
import com.ulink.ulink.Ulink.BoardData

class UlinkBoardCommentAdapter (private val context: Context,val viewtype : Int) : RecyclerView.Adapter<UlinkBoardCommentViewHolder>() {
    var data_ulink:MutableList<BoardData> = mutableListOf<BoardData>()
    var data_university:MutableList<BoardData> = mutableListOf<BoardData>()
    var data_class:MutableList<BoardData> = mutableListOf<BoardData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UlinkBoardCommentViewHolder {
        return UlinkBoardCommentViewHolder(LayoutInflater.from(context).inflate(R.layout.item_class_board_comment,parent,false))
    }
    private lateinit var itemClickListener : ItemClickListener
    interface ItemClickListener {
        fun onClick(view: View, position:Int)
    }
    fun setItemClickLIstener(itemClickListener: ItemClickListener){
        this.itemClickListener = itemClickListener
    }
    override fun getItemCount(): Int {
        when(viewtype){
            0->return data_ulink.size
            1-> return data_university.size
            else -> return data_class.size
        }
    }

    override fun onBindViewHolder(holder: UlinkBoardCommentViewHolder, position: Int) {
        when(viewtype){
            0-> holder.bind(data_ulink[position],0)
            1-> holder.bind(data_university[position],1)
            else-> holder.bind(data_class[position],2)

        }
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

}