package com.example.ulink.timetable

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.ulink.R
import com.example.ulink.repository.Subject


class TimeTableClassAdapter(val context : Context, val onItemClickListener: TimeTableFilterSearchFragment.onItemClickListener) : RecyclerView.Adapter<TimeTableClassAdapter.VHolder>() {

    val subjectList : MutableList<Subject> = arrayListOf()

    fun addToList(list : MutableList<Subject>){
        subjectList.addAll(list)
    }

    val preClickList : MutableList<View> = arrayListOf()
    var prePosition = -1

    inner class VHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        fun setHolder(subject: Subject, position: Int){
            itemView.minimumHeight = 90

            itemView.findViewById<TextView>(R.id.tv_class_name).text = subject.name
            itemView.findViewById<TextView>(R.id.tv_professor_name).text = subject.professor
            itemView.findViewById<TextView>(R.id.tv_time).text = subject.starttime + subject.endtime
            itemView.findViewById<TextView>(R.id.tv_place).text = subject.place
            itemView.findViewById<TextView>(R.id.tv_category).text = subject.course
            itemView.findViewById<TextView>(R.id.tv_credit).text = subject.credit.toString()
            itemView.findViewById<TextView>(R.id.tv_classnumber).text = subject.number
            itemView.findViewById<TextView>(R.id.tv_classnumber).visibility = View.VISIBLE



            val assess = itemView.findViewById<Button>(R.id.btn_assess)
            val cart = itemView.findViewById<Button>(R.id.btn_cart)
            val toTable = itemView.findViewById<Button>(R.id.btn_totable)

            assess.visibility = View.GONE


            itemView.setOnClickListener {
                onItemClickListener.onItemClicked(position)

                if (position == prePosition){
                    Log.d("tag", "pre")
                    if (assess.visibility == View.VISIBLE) {
                        assess.visibility = View.GONE
                    } else {
                        assess.visibility = View.VISIBLE
                    }
                    if (cart.visibility == View.VISIBLE) {
                        cart.visibility = View.GONE
                    } else {
                        cart.visibility = View.VISIBLE
                    }
                    if (toTable.visibility == View.VISIBLE) {
                        toTable.visibility = View.GONE
                    } else {
                        toTable.visibility = View.VISIBLE
                    }


                    preClickList.clear()
                    preClickList.add(assess)
                    preClickList.add(cart)
                    preClickList.add(toTable)

                    prePosition = position

                    if (assess.visibility == View.GONE ){
//                    rollback()
                        (context as TimeTableEditActivity).rollBack()
//                        Log.d("tag", "rollback")
                    } else{
                        (context as TimeTableEditActivity).addToSampleTable(subject)
                    }

                    return@setOnClickListener
                }


                (context as TimeTableEditActivity).rollBack()
                (context as TimeTableEditActivity).addToSampleTable(subject)

                Log.d("tag", "그냥")

                Log.d("tag visibility1", assess.visibility.toString())


                if (assess.visibility == View.VISIBLE) {
                    assess.visibility = View.GONE
                } else {
                    assess.visibility = View.VISIBLE
                }
                if (cart.visibility == View.VISIBLE) {
                    cart.visibility = View.GONE
                } else {
                    cart.visibility = View.VISIBLE
                }
                if (toTable.visibility == View.VISIBLE) {
                    toTable.visibility = View.GONE
                } else {
                    toTable.visibility = View.VISIBLE
                }


                Log.d("tag visibility2", assess.visibility.toString())


                if (preClickList.size>0){
                    for (i in preClickList){
                        i.visibility = View.GONE
                    }
                }
                preClickList.clear()
                preClickList.add(assess)
                preClickList.add(cart)
                preClickList.add(toTable)
                prePosition = position
            }

            assess.setOnClickListener {
                Toast.makeText(context, "준비중입니다", Toast.LENGTH_SHORT).show()
            }
            cart.setOnClickListener {
//               TODO DB에 저장!

            }
            toTable.setOnClickListener {
                (context as TimeTableEditActivity).addToTable(subject)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        return VHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_subject_child, parent, false))
    }

    override fun getItemCount(): Int =  subjectList.size


    override fun onBindViewHolder(holder: VHolder, position: Int) {
        holder.setHolder(subjectList[position], position)

    }


}