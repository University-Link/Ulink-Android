package com.ulink.ulink.Ulink.BoardRecycler

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ulink.ulink.R
import com.ulink.ulink.Ulink.BoardData
import com.ulink.ulink.Ulink.onClickLike

class BoardViewHolder(itemView: View,val mListener: onClickLike?) : RecyclerView.ViewHolder(itemView) {
    val tv_title: TextView = itemView.findViewById(R.id.tv_title)
    val tv_nickname: TextView = itemView.findViewById(R.id.tv_nickname)
    val tv_time: TextView = itemView.findViewById(R.id.tv_time)
    val tv_content: TextView = itemView.findViewById(R.id.tv_content)
    val tv_comment_count: TextView = itemView.findViewById(R.id.tv_comment_count)
    val tv_heart_count: TextView = itemView.findViewById(R.id.tv_heart_count)
    val img_tag: ImageView = itemView.findViewById(R.id.img_uni_tag)
    val btn_heart : ImageView = itemView.findViewById(R.id.btn_heart)


    fun bind(boardData: BoardData, showBoardName : Boolean) {

        Glide.with(itemView).load(R.drawable.class_board_detail_ic_heart_unactivated).into(btn_heart)

        btn_heart.setOnClickListener{
            mListener?.onClick(adapterPosition)
            if(boardData.isLike){
                btn_heart.setImageResource(R.drawable.class_board_ic_heart)
            }else{
                btn_heart.setImageResource(R.drawable.class_board_detail_ic_heart_unactivated)
            }
        }


        if (boardData.category==0) {
            img_tag.visibility = View.VISIBLE
            tv_title.text = boardData.title
            tv_nickname.text = boardData.nickname
            tv_time.text = boardData.createdAt
            tv_content.text = boardData.content
            tv_comment_count.text = "댓글 " + boardData.commentCount
            tv_heart_count.text = boardData.likeCount.toString()

            if (showBoardName){
                val img_line : ImageView = itemView.findViewById(R.id.img_line)
                val tv_boardCategory : TextView = itemView.findViewById(R.id.tv_board_category)

                img_line.visibility = View.VISIBLE
                tv_boardCategory.visibility =View.VISIBLE
                tv_boardCategory.text = "Ulink 게시판???"


            }

            if(boardData.isLike){
                btn_heart.setImageResource(R.drawable.class_board_ic_heart)
            }else{
                btn_heart.setImageResource(R.drawable.class_board_detail_ic_heart_unactivated)
            }
        } else if(boardData.category==1){

            Log.d("좋아요 이미지",boardData.isLike.toString())
            img_tag.visibility = View.GONE
            tv_title.text = boardData.title
            tv_nickname.text = boardData.nickname
            tv_time.text = boardData.createdAt
            tv_content.text = boardData.content
            tv_comment_count.text = "댓글 " + boardData.commentCount
            tv_heart_count.text = boardData.likeCount.toString()

            if (showBoardName){
                val img_line : ImageView = itemView.findViewById(R.id.img_line)
                val tv_boardCategory : TextView = itemView.findViewById(R.id.tv_board_category)

                img_line.visibility = View.VISIBLE
                tv_boardCategory.visibility =View.VISIBLE
                tv_boardCategory.text = "학교게시판"


                if(boardData.isLike){
                    btn_heart.setImageResource(R.drawable.class_board_ic_heart)
                }else{
                    btn_heart.setImageResource(R.drawable.class_board_detail_ic_heart_unactivated)
                }
            }
            if(boardData.isLike){
                btn_heart.setImageResource(R.drawable.class_board_ic_heart)
            }else{
                btn_heart.setImageResource(R.drawable.class_board_detail_ic_heart_unactivated)
            }
        }
    }

}

