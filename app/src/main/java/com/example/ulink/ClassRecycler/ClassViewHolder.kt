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

    val img_class : ImageView = itemView.findViewById(R.id.img_class)
    val tv_class : TextView = itemView.findViewById(R.id.tv_classname)
    val tv_partition : TextView = itemView.findViewById(R.id.tv_partition)

    fun bind(ClassData : ClassData) {
        tv_class.text = ClassData.name
        tv_partition.setText("참여 (${ClassData.current}/${ClassData.total})")
        Log.d("color", ClassData.color.toString())
        img_class.setBackgroundResource(chatImgSelector(ClassData.color))
    }
}