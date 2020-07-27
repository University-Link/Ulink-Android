package com.ulink.ulink.ClassRecycler

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ulink.ulink.R
import com.ulink.ulink.chatImgSelector

class ClassViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    val tv_class : TextView = itemView.findViewById(R.id.tv_class_name)

    fun bind(ClassData : ClassData) {
        tv_class.text = ClassData.name
    }
}