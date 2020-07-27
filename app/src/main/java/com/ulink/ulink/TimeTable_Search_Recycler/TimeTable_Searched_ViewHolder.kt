package com.ulink.ulink.TimeTable_Search_Recycler

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ulink.ulink.R

class TimeTable_Searched_ViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){
    val tv_search : TextView = itemView.findViewById(R.id.tv_search)

    fun bind(SearchData : SearchData){
        tv_search.text = SearchData.search_result
    }

}