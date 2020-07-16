package com.example.ulink.TimeTable_Search_Recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ulink.R

class TimeTable_Search_Adapter (private val context: Context):RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    lateinit var view:View
    val recent_search= 0
    val search = 1
    var searchdatas:MutableList<SearchData> = mutableListOf<SearchData>()
    var recentdatas : MutableList<String> = mutableListOf()

//TODO search가 기록 searched가 검색된거
    
    var viewType = 0

    interface ItemClick
    {
        fun onClick(view: View, position: Int)
    }
    var itemClick: ItemClick? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType){
            0->{
                return TimeTable_Search_ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recent_search,parent,false))
            }
            else->{
                return TimeTable_Searched_ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_search,parent,false))
            }
        }
    }

    override fun getItemCount(): Int {
        return searchdatas.size
    }

    override fun getItemViewType(position: Int): Int {
        return viewType
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (viewType == 0){
            if (recentdatas.size>0){
                (holder as TimeTable_Search_ViewHolder).bind(recentdatas[position])
            }
        } else {
            (holder as TimeTable_Searched_ViewHolder).bind(searchdatas[position])

        }
        if(itemClick != null)
        {
            holder?.itemView?.setOnClickListener { v ->
                itemClick?.onClick(v, position)
            }
        }
    }


}