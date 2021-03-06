package com.ulink.ulink.Ulink.BoardRecycler

import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ulink.ulink.R
import com.ulink.ulink.Ulink.BoardData
import com.ulink.ulink.Ulink.onClickLike

class BoardClassViewHolder(itemView: View,val mListener: onClickLike?): RecyclerView.ViewHolder(itemView){
    val tv_nickname : TextView = itemView.findViewById(R.id.tv_nickname)
    val tv_time : TextView = itemView.findViewById(R.id.tv_time)
    val tv_content : TextView = itemView.findViewById(R.id.tv_content)
    val tv_comment_count : TextView = itemView.findViewById(R.id.tv_comment_count)
    val tv_heart_count : TextView = itemView.findViewById(R.id.tv_heart_count)
    //val img_tag : ImageView = itemView.findViewById(R.id.img_uni_tag)
    val btn_heart : ImageButton = itemView.findViewById(R.id.btn_heart)


    fun bind(boardData: BoardData, showBoardName: Boolean){
        tv_nickname.text = boardData.nickname
        tv_time.text = boardData.createdAt
        tv_content.text = boardData.content
        tv_comment_count.text = "댓글 "+boardData.commentCount
        tv_heart_count.text = boardData.likeCount.toString()

        btn_heart.setOnClickListener{
            mListener?.onClick(adapterPosition)
        }
        if (showBoardName){
            val img_line : ImageView = itemView.findViewById(R.id.img_line)
            val tv_boardCategory : TextView = itemView.findViewById(R.id.tv_board_category)

            img_line.visibility = View.VISIBLE
            tv_boardCategory.visibility =View.VISIBLE
            tv_boardCategory.text = "과목이름"
        }
    }

}

