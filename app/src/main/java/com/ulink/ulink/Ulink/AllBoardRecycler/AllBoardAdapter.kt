package com.ulink.ulink.Ulink.AllBoardRecycler

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ulink.ulink.R
import com.ulink.ulink.Ulink.BoardData

class AllBoardAdapter (private val context: Context, val viewtype:Int) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var datas_ulink:MutableList<BoardData> = mutableListOf<BoardData>()
    var datas_university:MutableList<BoardData> = mutableListOf<BoardData>()
    var datas_class:MutableList<BoardData> = mutableListOf<BoardData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewtype) {
            0,1 -> return  AllBoardViewHolder(LayoutInflater.from(context).inflate(R.layout.item_ulink_board_ulink_data,parent,false))
            else ->  return AllBoardClassViewHolder(LayoutInflater.from(context).inflate(R.layout.item_ulink_board_class_data,parent,false))
        }
    }
    private lateinit var itemClickListener : ItemClickListener

    interface ItemClickListener {
        fun onClick(view: View, position:Int)
    }
    fun setItemClickLIstener(itemClickListener: ItemClickListener){
        this.itemClickListener = itemClickListener
    }
    override fun getItemCount(): Int {
        when (viewtype) {
            0 -> return  datas_ulink.size
            1 -> return  datas_university.size
            else ->  return datas_class.size
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (viewtype) {
            0 ->  (holder as AllBoardViewHolder).bind(datas_ulink[position],0) //태그있는
            1 -> (holder as AllBoardViewHolder).bind(datas_university[position],1) //태그없는
            else ->  (holder as AllBoardClassViewHolder).bind(datas_class[position])

        }
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }


}