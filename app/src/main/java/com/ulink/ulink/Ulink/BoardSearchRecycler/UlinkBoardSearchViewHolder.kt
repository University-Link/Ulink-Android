package com.ulink.ulink.Ulink.UlinkBoardRecycler

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ulink.ulink.R
import com.ulink.ulink.Ulink.UlinkBoardData

class UlinkBoardSearchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val img_profile : ImageView = itemView.findViewById(R.id.img_profile)
    val tv_nickname : TextView = itemView.findViewById(R.id.tv_nickname)
    val tv_time : TextView = itemView.findViewById(R.id.tv_time)
    val tv_content : TextView = itemView.findViewById(R.id.tv_content)
   // val btn_heart : ImageButton = itemView.findViewById(R.id.btn_heart)
    val tv_comment_count : TextView = itemView.findViewById(R.id.tv_comment_count)
    val tv_heart_count : TextView = itemView.findViewById(R.id.tv_heart_count)
    val tv_board_category : TextView = itemView.findViewById(R.id.tv_board_category)

    fun bind(BoardData: UlinkBoardData){
        Glide.with(itemView).load(BoardData.img_profile).into(img_profile)
        tv_nickname.text = BoardData.nickname
        tv_time.text = BoardData.time
        tv_content.text = BoardData.content
        tv_comment_count.text = BoardData.comment_count
        tv_heart_count.text = BoardData.heart_count
        tv_board_category.text = BoardData.board_category
    }

}
