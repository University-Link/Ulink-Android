package com.example.ulink.timetable

import android.content.Context
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

class TimeTableCandidateDetailAdapter(val context : Context,val onDeleteCartClickListener: onDeleteCartClickListener) : RecyclerView.Adapter<TimeTableCandidateDetailAdapter.VHolder>() {
    var cartDataList: MutableList<GetCartData> = arrayListOf()


    val mSelectedItems: HashMap<Int, Boolean> = HashMap()

    val preClickList: MutableList<View> = arrayListOf()


    override fun onBindViewHolder(holder: VHolder, position: Int) {
        holder.setHolder(cartDataList[position])
    }

    override fun getItemCount(): Int {
        return cartDataList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        return VHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_subject_child, parent, false)
        )
    }

    inner class VHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


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

        fun setHolder(subject: GetCartData) {
            var token =
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4IjoxLCJuYW1lIjoi6rmA67O067CwIiwic2Nob29sIjoi7ZWc7JaR64yA7ZWZ6rWQIiwibWFqb3IiOiLshoztlITtirjsm6jslrQiLCJpYXQiOjE1OTQ3NzkxODAsImV4cCI6MTU5NjIxOTE4MCwiaXNzIjoiYm9iYWUifQ.BAOeiZ_uqtIVPzFJd2oZbfVz44A2_QSXLQliNhN6pv4"

            itemView.findViewById<TextView>(R.id.tv_class_name).text = subject.name
            itemView.findViewById<TextView>(R.id.tv_professor_name).text = subject.professor
            itemView.findViewById<TextView>(R.id.tv_category).text = subject.course //전공필수등
            itemView.findViewById<TextView>(R.id.tv_credit).text = subject.credit.toString() + "학점"
            itemView.findViewById<TextView>(R.id.tv_classnumber).text = subject.subjectCode



            var layout = itemView.findViewById<ConstraintLayout>(R.id.layout_cart)
            var selector: Boolean = false

            val assess = itemView.findViewById<Button>(R.id.btn_assess)
            val delete = itemView.findViewById<Button>(R.id.btn_delete)
            val toTable = itemView.findViewById<Button>(R.id.btn_totable)


            itemView.findViewById<TextView>(R.id.tv_time).text = ""
            for (i in 0 until subject.startTime.size) {
                itemView.findViewById<TextView>(R.id.tv_time).text = itemView.findViewById<TextView>(R.id.tv_time).text.toString() + getDay(subject.day[i]) + " " + subject.startTime[i] + " - " + subject.endTime[i]
                if (subject.startTime.size>1 && i<subject.startTime.size-1) {
                    var text = itemView.findViewById<TextView>(R.id.tv_time).text
                    val text2 = "$text, "
                    itemView.findViewById<TextView>(R.id.tv_time).text = text2
                }
            }

            itemView.findViewById<TextView>(R.id.tv_place).text = ""

            for (i in 0 until subject.place.size) {
                itemView.findViewById<TextView>(R.id.tv_place).text =  itemView.findViewById<TextView>(R.id.tv_place).text.toString() + subject.place[i]
                if (subject.place.size>1 && i < subject.place.size-1){
                    var text =  itemView.findViewById<TextView>(R.id.tv_place).text
                    val text2 = "$text, "
                    itemView.findViewById<TextView>(R.id.tv_place).text = text2
                }
            }


            if (mSelectedItems.getOrDefault(adapterPosition, false)){
                assess.visibility = View.VISIBLE
                delete.visibility = View.VISIBLE
                toTable.visibility = View.VISIBLE
                layout.setBackgroundColor(Color.parseColor("#f2f0ff"))

            } else {
                assess.visibility = View.GONE
                delete.visibility = View.GONE
                toTable.visibility = View.GONE
                layout.setBackgroundColor(Color.parseColor("#ffffff"))

            }

//            if (!selector) {
//                layout.setBackgroundResource(R.drawable.candidate_suject_bg_selected)
//                selector = true
//            } else {
//                layout.setBackgroundColor(Color.parseColor("#ffffff"))
//                selector = false
//            }


            itemView.setOnClickListener {
                Log.d("tag","clicked")

                if (mSelectedItems.getOrDefault(adapterPosition, false)){
                    mSelectedItems.put(adapterPosition, false)
                    Log.d("tag","${adapterPosition}clicked to false")
                    (context as TimeTableEditActivity).rollBack()
//                   이미 누른적이 잇떤거 다시 눌렀을때 바로 눌렀든 나중에 눌렀든
//                    바로 누르면 전에거 지우고 vis 반전하고 등록
//                     나중에 누르면 리스트에서 지우면서 view gone
                    if (preClickList.size>0){
                        for (i in preClickList){ preClickList
                            if (i == preClickList.last()){
                                layout.setBackgroundColor(Color.parseColor("#ffffff"))
                                break
                            }
                            i.visibility = View.GONE
                        }
                    }

                    preClickList.clear()
                    preClickList.add(assess)
                    preClickList.add(delete)
                    preClickList.add(toTable)
                    preClickList.add(itemView)

//                  이전에 눌린거 바로 안누르고 다른거 누른ㄴ뒤에 누르면
//                  옛날에 눌린적 있는게 바로 전에 눌린건가 확인 아니면 의미 없다

                } else {
                    mSelectedItems.clear()
                    mSelectedItems.put(adapterPosition, true)
                    Log.d("tag","${adapterPosition}clicked to true")
//                   전에 눌린 기록이 false
                    if (preClickList.size>0){
                        for (i in preClickList){ preClickList
                            if (i == preClickList.last()){
                                layout.setBackgroundColor(Color.parseColor("#ffffff"))
                                break
                            }
                            i.visibility = View.GONE
                        }
                    }

                    (context as TimeTableEditActivity).rollBack()
                    layout.setBackgroundColor(Color.parseColor("#ffffff"))

//                    TODO 여기 주시

                    val carttosubject = Subject(subject.subjectIdx.toLong(), subject.name, subject.startTime, subject.endTime,subject.day, subject.place,0, true,subject.credit.toFloat(),subject.professor,subject.course,true)
                    (context as TimeTableEditActivity).addToSampleTable(carttosubject)

                    itemView.setBackgroundResource(R.drawable.candidate_suject_bg_selected)


                    assess.visibility = View.VISIBLE
                    delete.visibility = View.VISIBLE
                    toTable.visibility = View.VISIBLE
                    Log.d("tag","${adapterPosition} is visible")



                    preClickList.clear()
                    preClickList.add(assess)
                    preClickList.add(delete)
                    preClickList.add(toTable)
                    preClickList.add(itemView)

                }

                notifyDataSetChanged()
            }







            itemView.findViewById<Button>(R.id.btn_assess).setOnClickListener {
                val intent = Intent(itemView.context, EvaluationActivity::class.java)
                itemView.context.startActivity(intent)
            }

            itemView.findViewById<Button>(R.id.btn_delete).setOnClickListener {
                Log.d("후보삭semester",onDeleteCartClickListener.onClickeddelete())
                Log.d("후보삭제subjectidx", subject.subjectIdx.toString())

                RetrofitService.service.deleteCartList(token, subject.subjectIdx.toString(),
                    RequestDeleteCartList(
                    semester = onDeleteCartClickListener.onClickeddelete()
                )
                ).enqueue(object : Callback<ResponseDeleteCartList> {
                override fun onFailure(call: Call<ResponseDeleteCartList>, t: Throwable) {
                    Log.d("후보 삭제",t.message.toString())
                }

                override fun onResponse(
                    call: Call<ResponseDeleteCartList>,
                    response: Response<ResponseDeleteCartList>
                ) {
                    response.body().let {
                        Log.d("후보 삭제","성공")
                        notifyItemRemoved(adapterPosition)
                    }
                }

            })
             itemView.findViewById<Button>(R.id.btn_totable).setOnClickListener {

             }

            }
        }
    }

}
