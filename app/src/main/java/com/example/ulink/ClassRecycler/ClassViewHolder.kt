package com.example.ulink.ClassRecycler

import android.graphics.drawable.Drawable
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.ulink.R
import com.example.ulink.chatImgSelector

class ClassViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    val tv_class : TextView = itemView.findViewById(R.id.tv_class_name)

    fun bind(ClassData : ClassData) {
        tv_class.text = ClassData.name
    }
}