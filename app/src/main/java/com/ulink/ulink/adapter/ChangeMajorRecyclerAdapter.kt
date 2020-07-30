package com.ulink.ulink.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ulink.ulink.R

class ChangeMajorRecyclerAdapter (context: Context, val onClick : (String) -> Unit) : RecyclerView.Adapter<ChangeMajorRecyclerAdapter.VHolder>() {

    private val data : MutableList<String> = arrayListOf()

    fun addData(item : String){
        data.add(item)
    }

    inner class VHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        fun setHolder(item : String){
            itemView.findViewById<TextView>(R.id.tv_major).text = item
            itemView.setOnClickListener {
                onClick(item)
            }
        }
    }



    override fun getItemCount(): Int {
       return data.size
    }

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        holder.setHolder(data[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        return VHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_major, parent, false))
    }


}