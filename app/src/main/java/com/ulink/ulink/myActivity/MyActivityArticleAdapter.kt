package com.ulink.ulink.myActivity

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ulink.ulink.R
import com.ulink.ulink.Ulink.UlinkBoardRecycler.UlinkBoardData

class MyActivityArticleAdapter (private val context: Context) : RecyclerView.Adapter<MyActivityArticleAdapter.VHolder>() {

    var datas:MutableList<UlinkBoardData> = arrayListOf()

    class VHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val imgProfile : ImageView = itemView.findViewById(R.id.img_profile)
        val tvNickname : TextView = itemView.findViewById(R.id.tv_nickname)
        val tvTime : TextView = itemView.findViewById(R.id.tv_time)
        val tvContent : TextView = itemView.findViewById(R.id.tv_content)
        val btn_heart : ImageButton = itemView.findViewById(R.id.btn_heart)
        val tvCommentCount : TextView = itemView.findViewById(R.id.tv_comment_count)
        val tvHeartCount : TextView = itemView.findViewById(R.id.tv_heart_count)
        val tvCategory = itemView.findViewById<TextView>(R.id.tv_category)

        fun setHolder(item : UlinkBoardData){
            itemView.findViewById<TextView>(R.id.tv_category).visibility = View.VISIBLE
            itemView.findViewById<View>(R.id.divider_1).visibility = View.VISIBLE

            Glide.with(itemView).load(item.img_profile).into(imgProfile)
            tvNickname.text = item.nickname
            tvTime.text = item.time
            tvContent.text = item.content
            tvCommentCount.text = item.comment_count
            tvHeartCount.text = item.heart_count
            tvCategory.text = item.category

        }
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        return VHolder(LayoutInflater.from(context).inflate(R.layout.item_ulink_board_data, parent, false))
    }

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        holder.setHolder(datas[position])
    }

}