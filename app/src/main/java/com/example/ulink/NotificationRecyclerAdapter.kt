package com.example.ulink

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ulink.repository.Notification

class NotificationRecyclerAdapter : RecyclerView.Adapter<NotificationRecyclerAdapter.VHolder>(){

    val notifications : MutableList<Notification> = arrayListOf()

    init {
        notifications.add(Notification(1,"전자기학 과제 공지가 등록되었어요","내용내용내용","1분전","#8e98ff", false))
        notifications.add(Notification(2,"전자기학 과제 공지가 등록되었어요","내용내용내용","1분전","#8e98ff", true))
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        return VHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_notification, parent,false))
    }

    override fun getItemCount(): Int {
        return notifications.size
    }

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        holder.setHolder(notifications[position])
    }


    class VHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setHolder(item : Notification){
            itemView.findViewById<View>(R.id.ic_type).setBackgroundResource(R.drawable.red_circle_for_alert)
            itemView.findViewById<TextView>(R.id.tv_title).text = item.title
            itemView.findViewById<TextView>(R.id.tv_content).text = item.content
            itemView.findViewById<TextView>(R.id.tv_time).text = item.time


            if (!item.read) {
                itemView.setBackgroundColor(Color.parseColor("#f2f0ff"))
            }
        }
    }


}