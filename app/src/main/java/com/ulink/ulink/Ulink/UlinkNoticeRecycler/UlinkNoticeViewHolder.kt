package com.ulink.ulink.Ulink.UlinkNoticeRecycler

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ulink.ulink.R

class UlinkNoticeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val notice_kind : ImageView = itemView.findViewById(R.id.img_notice_kind)
    val notice_date : TextView = itemView.findViewById(R.id.tv_notice_date)
    val notice_name : TextView = itemView.findViewById(R.id.tv_notice_name)
    val notice_time : TextView = itemView.findViewById(R.id.tv_notice_time)
  //  val notice_new : ImageView = itemView.findViewById(R.id.img_notice_new)


    fun bind(UlinkNoticeData: UlinkNoticeData){
       // Glide.with(itemView).load(UlinkNoticeData.img_profile).into(img_profile)
        notice_date.text = UlinkNoticeData.date
        notice_name.text = UlinkNoticeData.notice_name
        notice_time.text = UlinkNoticeData.start_time + "-" + UlinkNoticeData.end_time
        //notice_new
    }

}
