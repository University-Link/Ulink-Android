package com.example.ulink.TimeTable_Search_Recycler

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ulink.R

class TimeTable_Search_ViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){
    val tv_recent_search : TextView = itemView.findViewById(R.id.tv_recent_search)

    fun bind(SearchData : String){
        tv_recent_search.text = SearchData
    }

}