package com.example.ulink.ClassRecycler

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ulink.R
import kotlinx.android.synthetic.main.class_item.view.*
import org.w3c.dom.Text

class ClassViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val img_class : ImageView = itemView.findViewById(R.id.img_class)
    val tv_class : TextView = itemView.findViewById(R.id.tv_classname)
    val tv_partition : TextView = itemView.findViewById(R.id.tv_partition)
    val count : TextView = itemView.findViewById(R.id.tv_count)

    fun bind(ClassData : ClassData){
        Glide.with(itemView).load(ClassData.ClassImage).into(img_class)
        tv_class.text = ClassData.ClassName
        tv_partition.setText("참여 (${ClassData.now}/${ClassData.total})")
        count.setText("${ClassData.count}")
    }


}
