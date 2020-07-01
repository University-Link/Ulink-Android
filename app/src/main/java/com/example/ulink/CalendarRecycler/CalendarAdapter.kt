package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ulink.*
import com.example.ulink.CalendarRecycler.CalendarDayAdapter
import com.example.ulink.CalendarRecycler.CalendarDayData
import com.example.ulink.CalendarRecycler.firstIndex

class CalendarAdapter(private val context : Context, data : CalendarData) : RecyclerView.Adapter<CalendarAdapter.Vholder>(){
    var data : CalendarData = data
    var endDay = arrayOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vholder {

        val view =  LayoutInflater.from(context).inflate(R.layout.calendar_layout,parent,false)
        return Vholder(view)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder:Vholder, position: Int) {
        holder.bind(data)
    }

    inner class Vholder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(data : CalendarData){
            val rv_calendar = itemView as RecyclerView
            val rvAdapter =
                CalendarDayAdapter(context)
            rv_calendar.adapter = rvAdapter

            //윤년
            if ((data.year.toInt()%4==0 && data.year.toInt()%100!=0 || data.year.toInt()%400==0)) endDay[1] = 29
            else endDay[1] = 28

            //month
            var index = firstIndex(
                data.year,
                data.month
            )

            var lastindex = endDay[data.month.toInt()-1]

            //previous_month
            var prev_empty_index : Int
            if(data.month==1)
                prev_empty_index = endDay[11] - index + 1
            else
                prev_empty_index = endDay[data.month.toInt()-2] - index + 1

            //next_month
            var last_empty = index+lastindex
            var last_empty_index = 1

            rvAdapter.datas.apply {

                //previous_month
                var dateindex = 0
                for(i in 0 until index) {
                    add(CalendarDayData(
                        day = prev_empty_index.toString(),
                        check = false,
                        date = dateindex
                    ))
                    dateindex += 1
                    prev_empty_index += 1
                }
                //month
                for(i in 1..lastindex) {
                    add(CalendarDayData(
                        day = i.toString(),
                        check = true,
                        date = dateindex
                    ))
                    dateindex += 1
                }
                //next_month
                while(true) {
                    if (last_empty % 7 != 0) {
                        add(
                            CalendarDayData(
                                day = last_empty_index.toString(),
                                check = false,
                                date = dateindex
                            ))
                        last_empty_index += 1
                        last_empty += 1
                        dateindex += 1
                    }
                    else break;
                }

                rvAdapter.notifyDataSetChanged()
            }
        }
    }
}

data class CalendarData(
    val year : Int,
    val month : Int,
    val day : Int
)