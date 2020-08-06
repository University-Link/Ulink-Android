package com.ulink.ulink.ClassRecycler
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ulink.ulink.R
import com.ulink.ulink.repository.BoardSubject

class ClassViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val tv_class : TextView = itemView.findViewById(R.id.tv_class_name)
    fun bind(BoardSubject : BoardSubject) {
        tv_class.text = BoardSubject.name
    }
}

