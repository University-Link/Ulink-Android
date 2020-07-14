package com.example.ulink.repository

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.ulink.EvaluationActivity
import com.example.ulink.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TimeTableCandidateDetailAdapter : RecyclerView.Adapter<TimeTableCandidateDetailAdapter.VHolder>() {
    var cartDataList : MutableList<SubjectDetail> = arrayListOf()

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        holder.setHolder(cartDataList[position], cartDataList)
    }

    override fun getItemCount(): Int {
        return cartDataList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        return VHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_subject_child, parent, false))
    }

    class VHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        fun setHolder(subject : SubjectDetail, cartDataList : MutableList<SubjectDetail>){
            var token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4IjoxLCJuYW1lIjoi6rmA67O067CwIiwic2Nob29sIjoi7ZWc7JaR64yA7ZWZ6rWQIiwibWFqb3IiOiLshoztlITtirjsm6jslrQiLCJpYXQiOjE1OTQ3NDgyNTQsImV4cCI6MTU5NjE4ODI1NCwiaXNzIjoiYm9iYWUifQ.dFU9h8EZLqoMekAfRNTfGQkUAbq_CXoQmA5Jl7KsQ70"

            itemView.findViewById<TextView>(R.id.tv_class_name).text = subject.name
            itemView.findViewById<TextView>(R.id.tv_professor_name).text = subject.professor
            itemView.findViewById<TextView>(R.id.tv_category).text = subject.course //전공필수등
            itemView.findViewById<TextView>(R.id.tv_credit).text = subject.credit.toString() + "학점"
            itemView.findViewById<TextView>(R.id.tv_classnumber).text = subject.subjectCode

            var layout = itemView.findViewById<ConstraintLayout>(R.id.layout_cart)
            var selector : Boolean = false

            layout.setOnClickListener {
                if(!selector) {
                    layout.setBackgroundResource(R.drawable.candidate_suject_bg_selected)
                    itemView.findViewById<Button>(R.id.btn_assess).visibility = View.VISIBLE
                    itemView.findViewById<Button>(R.id.btn_delete).visibility = View.VISIBLE
                    itemView.findViewById<Button>(R.id.btn_totable).visibility = View.VISIBLE
                    selector = true
                }
                else{
                    layout.setBackgroundColor(Color.parseColor("#ffffff"))
                    itemView.findViewById<Button>(R.id.btn_assess).visibility = View.GONE
                    itemView.findViewById<Button>(R.id.btn_delete).visibility = View.GONE
                    itemView.findViewById<Button>(R.id.btn_totable).visibility = View.GONE
                    selector = false
                }
            }

            itemView.findViewById<Button>(R.id.btn_assess).setOnClickListener {
                val intent = Intent(itemView.context, EvaluationActivity::class.java)
                itemView.context.startActivity(intent)
            }

            itemView.findViewById<Button>(R.id.btn_delete).setOnClickListener {
                RetrofitService.service.deleteCartList(token, "1").enqueue(object : Callback<ResponseDeleteCartList> {
                    override fun onFailure(call: Call<ResponseDeleteCartList>, t: Throwable) {
                    }

                    override fun onResponse(
                        call: Call<ResponseDeleteCartList>,
                        response: Response<ResponseDeleteCartList>
                    ) {
                        response.body()?.let {
                            if (it.status == 200) {
                                cartDataList.remove(cartDataList[adapterPosition])
                            }
                            else {
                            }
                        }
                    }
                })
            }
        }
    }
}