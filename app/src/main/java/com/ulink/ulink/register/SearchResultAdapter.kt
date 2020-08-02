package com.ulink.ulink.register

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ulink.ulink.R

class SearchResultAdapter(private val context : Context) : RecyclerView.Adapter<SearchResultAdapter.VHolder>() {
    var datas = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_search_result, parent, false)
        return VHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        holder.bind(datas[position])
    }

    class VHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvSearchResult = itemView.findViewById<TextView>(R.id.tv_search_result)
        fun bind(result : String){
            tvSearchResult.text = result
        }
    }
}
