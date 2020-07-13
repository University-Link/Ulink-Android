package com.example.ulink.timetable

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ulink.R
import com.example.ulink.repository.Subject

class TimeTableCandidateAdapter : RecyclerView.Adapter<TimeTableCandidateAdapter.VHolder>() {

    val candidateList : MutableList<Subject> = arrayListOf()

    fun addToList(subject : Subject){
        candidateList.add(subject)
    }

    class VHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        fun setHolder(subject : Subject){
            itemView.findViewById<TextView>(R.id.tv_class_name).text = subject.name
            itemView.findViewById<TextView>(R.id.tv_professor_name).text = subject.professor
            itemView.findViewById<TextView>(R.id.tv_time).text = subject.startTime + subject.endTime
            itemView.findViewById<TextView>(R.id.tv_place).text = subject.place
            itemView.findViewById<TextView>(R.id.tv_category).text = subject.course
            itemView.findViewById<TextView>(R.id.tv_credit).text = subject.credit.toString()

            itemView.findViewById<Button>(R.id.btn_assess).visibility = View.VISIBLE
            itemView.findViewById<Button>(R.id.btn_delete).visibility = View.VISIBLE
            itemView.findViewById<Button>(R.id.btn_totable).visibility = View.VISIBLE


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        return VHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_subject_child, parent, false))
    }

    override fun getItemCount(): Int {
        return candidateList.size
    }

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        holder.setHolder(candidateList[position])
        Log.d("tag", "bindholder")

    }

}