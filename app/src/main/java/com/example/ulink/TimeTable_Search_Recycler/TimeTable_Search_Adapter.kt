package com.example.ulink.TimeTable_Search_Recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ulink.R

class TimeTable_Search_Adapter (private val context: Context):RecyclerView.Adapter<TimeTable_Search_ViewHolder>(){
    lateinit var view:View
    val recent_search= 0
    val search = 1
    var searchdatas:MutableList<SearchData> = mutableListOf<SearchData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeTable_Search_ViewHolder {
        when(viewType){
            0->{
                return TimeTable_Search_ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recent_search,parent,false))
            }
            else->{
                return TimeTable_Search_ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_search,parent,false))
            }
        }
    }

    override fun getItemCount(): Int {
        return searchdatas.size
    }

    override fun onBindViewHolder(holder: TimeTable_Search_ViewHolder, position: Int) {
        holder.bind(searchdatas[position])
       // holder.bind2(searchdatas[position])

    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }
}