package com.example.ulink.ChattingRecycler

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ulink.R

class ChattingViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    val img_profile : ImageView = itemView.findViewById(R.id.img_profile)
    val tv_username : TextView = itemView.findViewById(R.id.tv_username)
    val tv_message : TextView = itemView.findViewById(R.id.tv_message)
    val tv_count : TextView = itemView.findViewById(R.id.tv_count)
    val tv_time : TextView = itemView.findViewById(R.id.tv_time)

    fun bind(ChattingData:ChattingData){
        Glide.with(itemView).load(ChattingData.profile).into(img_profile)
        tv_username.text = ChattingData.username
        tv_message.text=ChattingData.message
        tv_count.setText("${ChattingData.count}")
        tv_time.text = ChattingData.time

    }

}

