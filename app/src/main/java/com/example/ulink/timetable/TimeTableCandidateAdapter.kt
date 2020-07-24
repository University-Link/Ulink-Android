package com.example.ulink.timetable

import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.ulink.Activity.EvaluationActivity
import com.example.ulink.R
import com.example.ulink.repository.Subject

class TimeTableCandidateAdapter : RecyclerView.Adapter<TimeTableCandidateAdapter.VHolder>() {

    val candidateList: MutableList<Subject> = arrayListOf()

    fun addToList(subject: Subject) {
        candidateList.add(subject)
    }

    class VHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setHolder(subject: Subject) {
            itemView.findViewById<TextView>(R.id.tv_class_name).text = subject.name
            itemView.findViewById<TextView>(R.id.tv_professor_name).text = subject.professor

            for (i in 0 until subject.startTime.size) {
                itemView.findViewById<TextView>(R.id.tv_time).text =
                    itemView.findViewById<TextView>(R.id.tv_time).text.toString() + getDay(subject.day[i]) + " " + subject.startTime[i] + " - " + subject.endTime[i]
                if (subject.startTime.size > 1 && i < subject.startTime.size - 1) {
                    var text = itemView.findViewById<TextView>(R.id.tv_time).text
                    val text2 = "$text, "
                    itemView.findViewById<TextView>(R.id.tv_time).text = text2
                }
            }


            for (i in 0 until subject.place.size) {
                itemView.findViewById<TextView>(R.id.tv_place).text =
                    itemView.findViewById<TextView>(R.id.tv_place).text.toString() + subject.place[i]
                if (subject.place.size > 1 && i < subject.place.size - 1) {
                    var text = itemView.findViewById<TextView>(R.id.tv_place).text
                    val text2 = "$text, "
                    itemView.findViewById<TextView>(R.id.tv_place).text = text2
                }
            }

//

            itemView.findViewById<TextView>(R.id.tv_category).text = subject.course
            itemView.findViewById<TextView>(R.id.tv_credit).text = subject.credit.toString()

            itemView.findViewById<Button>(R.id.btn_assess).visibility = View.VISIBLE
            itemView.findViewById<Button>(R.id.btn_delete).visibility = View.VISIBLE
            itemView.findViewById<Button>(R.id.btn_totable).visibility = View.VISIBLE
            itemView.findViewById<ConstraintLayout>(R.id.layout_cart).setBackgroundColor(Color.parseColor("#ffffff"))


            itemView.findViewById<Button>(R.id.btn_assess).setOnClickListener {
                val intent = Intent(itemView.context, EvaluationActivity::class.java)
                itemView.context.startActivity(intent)
            }
            itemView.findViewById<Button>(R.id.btn_delete).setOnClickListener{
            }

        }



        fun getDay(day: Int): String {
            return when (day) {
                0 -> "월"
                1 -> "화"
                2 -> "수"
                3 -> "목"
                4 -> "금"
                5 -> "토"
                6 -> "일"
                else -> "월"
            }
        }

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        return VHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_subject_child, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return candidateList.size
    }

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        holder.setHolder(candidateList[position])

    }

}