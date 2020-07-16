package com.example.ulink.ScheduleRecycler

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ulink.R
import com.example.ulink.ResponseNoticeData

class CalendarSmallScheduleAdapter: RecyclerView.Adapter<CalendarSmallScheduleAdapter.VHolder> (){


    var list : List<ResponseNoticeData> = arrayListOf()

    class VHolder(itemView : View): RecyclerView.ViewHolder(itemView){

        fun setHolder(item : ResponseNoticeData){

            val textView = itemView.findViewById<TextView>(R.id.schedule)
            textView.text = item.name
            textView.setBackgroundResource(getColors(item.color))
            Log.d("tagggggggggggggg",item.toString())
        }

        fun getColors(type: Int): Int {
            return when (type) {
                0 -> R.drawable.bg_round_border_subject_color_1
                1 -> R.drawable.bg_round_border_subject_color_2
                2 -> R.drawable.bg_round_border_subject_color_3
                3 -> R.drawable.bg_round_border_subject_color_4
                4 -> R.drawable.bg_round_border_subject_color_5
                5 -> R.drawable.bg_round_border_subject_color_6
                6 -> R.drawable.bg_round_border_subject_color_7
                7 -> R.drawable.bg_round_border_subject_color_8
                8 -> R.drawable.bg_round_border_subject_color_9
                9 -> R.drawable.bg_round_border_subject_color_10
                else -> R.drawable.bg_round_border_subject
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
       return VHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_small_schedule, parent, false))
    }

    override fun getItemCount(): Int{
        if (list.size ==0){
            return 0
        }  else {
            return list!!.size
        }
    }


    override fun onBindViewHolder(holder: VHolder, position: Int) {
        holder.setHolder(list!![position])
    }

}