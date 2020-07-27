package com.ulink.ulink.TimeTableDirectRecycler

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.ulink.ulink.R
import com.ulink.ulink.timetable.onClickListener
import kotlin.properties.Delegates

class TimeTableDirectAdapter(private val context: Context, val onClickListener: onClickListener): RecyclerView.Adapter<TimeTableDirectAdapter.TimeTalbeDirectViewHolder>(){
    lateinit var time : String
    var check : Boolean = false
    var datas:MutableList<TimeTableDirectData> = mutableListOf<TimeTableDirectData>()

    lateinit var date:String
    var spinner_position by Delegates.notNull<Int>()

    lateinit var days : String
    lateinit var start : String
    lateinit var end: String

    inner class TimeTalbeDirectViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val start_time : TextView = itemView.findViewById(R.id.tv_start_time)
        val end_time : TextView = itemView.findViewById(R.id.tv_end_time)

        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(TimeTableDirectData:TimeTableDirectData){

            start_time.text = TimeTableDirectData.start_time
            end_time.text = TimeTableDirectData.end_time


            val myAdapter = ArrayAdapter.createFromResource(context, R.array.days,R.layout.item_spinner)
            myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            val spinner_days = itemView.findViewById<Spinner>(R.id.spinner_days)
            spinner_days.adapter = myAdapter

            val tv_start_time = itemView.findViewById<TextView>(R.id.tv_start_time)
            val tv_end_time = itemView.findViewById<TextView>(R.id.tv_end_time)

            val btn_delete_personal_plan = itemView.findViewById<ImageButton>(R.id.btn_delete_person_plan)

            spinner_days.setSelection(TimeTableDirectData.day)


            // 타임피커 보이도록

            tv_start_time.setOnClickListener {
                check = false
                onClickListener.onClick(adapterPosition,check)
                datas[adapterPosition].day =  spinner_days.getSelectedItemPosition()

            }
            tv_end_time.setOnClickListener {
                check = true
                onClickListener.onClick(adapterPosition,check)
                datas[adapterPosition].day =  spinner_days.getSelectedItemPosition()

            }

            btn_delete_personal_plan.setOnClickListener {
                //TODO 드래그해서 추가일때, 삭제하고 돌아가면 잡힌 영역도 사라지도록 수정
                datas.remove(datas[adapterPosition])
                notifyItemRemoved(adapterPosition)
                notifyItemRangeChanged(adapterPosition, datas.size)
            }

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeTalbeDirectViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_time_add,parent,false)
        return TimeTalbeDirectViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: TimeTalbeDirectViewHolder, position: Int) {
        holder.bind(datas[position])
    }


    fun formatToFloat(time: String): Float {
        val timesplit = time.split(":")
        return timesplit[0].toFloat() + (timesplit[1].toFloat() - timesplit[1].toFloat() % 15) / 60
    }

}

