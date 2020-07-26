package com.example.ulink.TimeTable_Search_Recycler

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ulink.R

class TimeTable_Search_Adapter (private val context: Context):RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    lateinit var view:View

    var searchdatas:MutableList<SearchData> = arrayListOf()
    var recentdatas : MutableList<String> = arrayListOf()

//TODO search가 기록 searched가 검색된거
    
    var viewType = 0

    interface ItemClick
    {
        fun onClick(className : String)
    }

    interface deleteClick{
        fun onClick(position: Int)
    }

    var itemClick: ItemClick? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            0->{
                TimeTable_Search_ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recent_search,parent,false))
            }
            else->{
                TimeTable_Searched_ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_search,parent,false))
            }
        }
    }

    override fun getItemCount(): Int {
        return if (viewType == 0){
            recentdatas.size
        } else {
            searchdatas.size
        }
    }

    override fun getItemViewType(position: Int): Int {
        return viewType
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (viewType == 0){
            if (recentdatas.size>0){
                (holder as TimeTable_Search_ViewHolder).bind(recentdatas[position], object : deleteClick{
                    override fun onClick(pos: Int) {
                        val sharedPreferences = context.getSharedPreferences("recentSearch", Context.MODE_PRIVATE)
                        val editor = sharedPreferences.edit()
                        recentdatas.removeAt(pos)
                        editor.clear()
                        val list : MutableSet<String>? = mutableSetOf()
                        for (i in recentdatas.reversed()){
                            list?.add(i)
                        }
                        editor.putStringSet("recentSearch",list)
                        editor.apply()
                        notifyDataSetChanged()
                    }
                })
            }
        } else {
            (holder as TimeTable_Searched_ViewHolder).bind(searchdatas[position])

        }
        if(itemClick != null){
            holder?.itemView?.setOnClickListener {

                if (viewType == 0){
                    itemClick!!.onClick(recentdatas[position])
                } else{
                    itemClick!!.onClick(searchdatas[position].search_result)
                }

            }
        }
    }


}