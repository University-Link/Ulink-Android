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
import com.example.ulink.EvaluationActivity
import com.example.ulink.R
import com.example.ulink.repository.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TimeTableCandidateDetailAdapter : RecyclerView.Adapter<TimeTableCandidateDetailAdapter.VHolder>() {
    var cartDataList: MutableList<SubjectDetail> = arrayListOf()

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        holder.setHolder(cartDataList[position], cartDataList)
    }

    override fun getItemCount(): Int {
        return cartDataList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        return VHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_subject_child, parent, false)
        )
    }

    class VHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setHolder(subject: SubjectDetail, cartDataList: MutableList<SubjectDetail>) {
            var token =
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4IjoxLCJuYW1lIjoi6rmA67O067CwIiwic2Nob29sIjoi7ZWc7JaR64yA7ZWZ6rWQIiwibWFqb3IiOiLshoztlITtirjsm6jslrQiLCJpYXQiOjE1OTQ3NzkxODAsImV4cCI6MTU5NjIxOTE4MCwiaXNzIjoiYm9iYWUifQ.BAOeiZ_uqtIVPzFJd2oZbfVz44A2_QSXLQliNhN6pv4"

            itemView.findViewById<TextView>(R.id.tv_class_name).text = subject.name
            itemView.findViewById<TextView>(R.id.tv_professor_name).text = subject.professor
            itemView.findViewById<TextView>(R.id.tv_category).text = subject.course //전공필수등
            itemView.findViewById<TextView>(R.id.tv_credit).text = subject.credit.toString() + "학점"
            itemView.findViewById<TextView>(R.id.tv_classnumber).text = subject.subjectCode

            var layout = itemView.findViewById<ConstraintLayout>(R.id.layout_cart)
            var selector: Boolean = false

            layout.setOnClickListener {
                if (!selector) {
                    layout.setBackgroundResource(R.drawable.candidate_suject_bg_selected)
                    itemView.findViewById<Button>(R.id.btn_assess).visibility = View.VISIBLE
                    itemView.findViewById<Button>(R.id.btn_delete).visibility = View.VISIBLE
                    itemView.findViewById<Button>(R.id.btn_totable).visibility = View.VISIBLE
                    selector = true
                } else {
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
               /* Log.d("100", "100")
                var semester = RequestDeleteCartList(cartDataList[adapterPosition].semester)
                        Log.d("semester", cartDataList[adapterPosition].semester)
                RetrofitService.service.deleteCartList(token, cartDataList[adapterPosition].subjectIdx.toString(), semester).enqueue(object : Callback<ResponseDeleteCartList> {
                    override fun onFailure(call: Call<ResponseDeleteCartList>, t: Throwable) {
                        Log.d("c", "B")
                    }

                    override fun onResponse(
                        call: Call<ResponseDeleteCartList>,
                        response: Response<ResponseDeleteCartList>
                    ) {
                        response.body()?.let {
                            if (it.status == 200) {
                                Log.d("a", it.toString())
                            }
                            else
                                Log.d("b", it.toString())
                        } ?: Log.d("tag", response.message())
                    }
                })*/
            }
        }
    }
}