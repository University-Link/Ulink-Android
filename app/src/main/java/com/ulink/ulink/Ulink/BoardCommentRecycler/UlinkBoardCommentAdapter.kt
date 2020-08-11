package com.ulink.ulink.Ulink.BoardCommentRecycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ulink.ulink.R
import com.ulink.ulink.Ulink.BoardData

class UlinkBoardCommentAdapter (private val context: Context,val viewtype : Int, val onClickListener: onClickMore) : RecyclerView.Adapter<UlinkBoardCommentAdapter.UlinkBoardCommentViewHolder>() {
    var data_:MutableList<BoardData> = mutableListOf()
    var data_university:MutableList<BoardData> = mutableListOf()
    var data_class:MutableList<BoardData> = mutableListOf()


    inner class UlinkBoardCommentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val img_profile : ImageView = itemView.findViewById(R.id.img_profile)
        val tv_nickname : TextView = itemView.findViewById(R.id.tv_nickname)
        val tv_time : TextView = itemView.findViewById(R.id.tv_time)
        val tv_content : TextView = itemView.findViewById(R.id.tv_content)
        // val btn_heart : ImageButton = itemView.findViewById(R.id.btn_heart)
        //val tv_comment_count : TextView = itemView.findViewById(R.id.tv_comment_count)
        val tv_heart_count : TextView = itemView.findViewById(R.id.tv_heart_count)
        val btn_reply_more : Button = itemView.findViewById(R.id.btn_reply_more)

        fun bind(boardData: Any, tag:Int){
            when(tag){
                0->{
                    //TODO 대학교받아와서 대학교별로 태그 바꾸기
                    img_profile.setBackgroundResource(R.drawable.ulinkboard_ic_unis)
                }
                1->{
                    img_profile.visibility = View.GONE
                }
                else->{
                    img_profile.setBackgroundResource(R.drawable.class_board_detail_reply_ic_replyprofile)
                }
            }
            btn_reply_more.setOnClickListener {
                onClickListener.onClick()
            }
            //TODO tag 보이고 안보이고 (언급?태그여부 판별)


//          FIXME 이거 type 별로 표시하기!! commenttype
            boardData as BoardData

            tv_nickname.text = boardData.nickname
            tv_time.text = boardData.createdAt
            tv_content.text = boardData.content
            tv_heart_count.text = boardData.likeCount.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UlinkBoardCommentViewHolder {
        return UlinkBoardCommentViewHolder(LayoutInflater.from(context).inflate(R.layout.item_class_board_comment,parent,false))

    }
//    private lateinit var itemClickListener : ItemClickListener
//    interface ItemClickListener {
//        fun onClick(view: View, position:Int)
//    }
//    fun setItemClickLIstener(itemClickListener: ItemClickListener){
//        this.itemClickListener = itemClickListener
//    }
    override fun getItemCount(): Int {
        when(viewtype){
            0->return data_.size
            1-> return data_university.size
            else -> return data_class.size
        }
    }

    override fun onBindViewHolder(holder: UlinkBoardCommentViewHolder, position: Int) {
        when(viewtype){
            0-> holder.bind(data_[position],0)
            1-> holder.bind(data_university[position],1)
            else-> holder.bind(data_class[position],2)

        }
//        holder.itemView.setOnClickListener {
//            itemClickListener.onClick(it, position)
//        }
    }

}