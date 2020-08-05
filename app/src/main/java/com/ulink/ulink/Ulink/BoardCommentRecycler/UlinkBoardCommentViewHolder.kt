package com.ulink.ulink.Ulink.BoardCommentRecycler

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ulink.ulink.R
import com.ulink.ulink.Ulink.BoardData

class UlinkBoardCommentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val img_profile : ImageView = itemView.findViewById(R.id.img_profile)
    val tv_nickname : TextView = itemView.findViewById(R.id.tv_nickname)
    val tv_time : TextView = itemView.findViewById(R.id.tv_time)
    val tv_content : TextView = itemView.findViewById(R.id.tv_content)
   // val btn_heart : ImageButton = itemView.findViewById(R.id.btn_heart)
    //val tv_comment_count : TextView = itemView.findViewById(R.id.tv_comment_count)
    val tv_heart_count : TextView = itemView.findViewById(R.id.tv_heart_count)

    fun bind(BoardData: BoardData,tag:Int){
        when(tag){
            0->{
                //TODO 대학교받아와서 대학교별로 태그 바꾸기
                Log.d("viewtype2 ","00")
                img_profile.setBackgroundResource(R.drawable.ulinkboard_ic_unis)
            }
            1->{
                Log.d("viewtype2 ","11")
                img_profile.visibility = View.GONE
            }
            else->{
                Log.d("viewtype2 ","22")
                img_profile.setBackgroundResource(R.drawable.class_board_detail_reply_ic_replyprofile)
            }
        }
        //TODO tag 보이고 안보이고 (언급?태그여부 판별)
        tv_nickname.text = BoardData.nickname
        tv_time.text = BoardData.createdAt
        tv_content.text = BoardData.content
        tv_heart_count.text = BoardData.likeCount.toString()
    }

}
