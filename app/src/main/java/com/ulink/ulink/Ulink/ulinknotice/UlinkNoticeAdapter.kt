package com.ulink.ulink.Ulink.ulinknotice

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ulink.ulink.R

class UlinkNoticeAdapter (private val context: Context) : RecyclerView.Adapter<UlinkNoticeAdapter.VHolder>() {
    var datas:MutableList<UlinkNoticeData> = mutableListOf<UlinkNoticeData>()

    private lateinit var noticeClickListener : NoticeClickListener

    interface NoticeClickListener {
        fun onClick(view: View, position:Int)
    }

    fun setNoticeClickListener(noticeClickListener : NoticeClickListener){
        this.noticeClickListener = noticeClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_ulink_notice_data,parent,false)
        return VHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        holder.bind(datas[position])
        holder.itemView.setOnClickListener { noticeClickListener.onClick(it, position) }
    }

    class VHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imgCategory = itemView.findViewById<ImageView>(R.id.img_notice_category)
        private val tvDate = itemView.findViewById<TextView>(R.id.tv_notice_date)
        private val tvTitle = itemView.findViewById<TextView>(R.id.tv_notice_name)
        private val tvTime = itemView.findViewById<TextView>(R.id.tv_notice_time)

        fun bind(ulinkNoticeData : UlinkNoticeData){
            tvTitle.text = ulinkNoticeData.title
            categoryBackground(ulinkNoticeData.category, ulinkNoticeData.date, imgCategory)
            dateTextView(ulinkNoticeData, tvDate)
            timeTextView(ulinkNoticeData, tvTime)
            expiredDate(ulinkNoticeData, tvDate, tvTitle, tvTime)
        }
    }
}