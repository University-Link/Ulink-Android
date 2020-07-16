package com.example.ulink.timetable

import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.ulink.EvaluationActivity
import com.example.ulink.R
import com.example.ulink.repository.DataRepository
import com.example.ulink.repository.RequestAddSchoolPlan
import com.example.ulink.repository.Subject


class TimeTableClassAdapter(val context: Context, val onItemClickListener: TimeTableFilterSearchFragment.onItemClickListener) : RecyclerView.Adapter<TimeTableClassAdapter.VHolder>() {

    var subjectList: MutableList<Subject> = arrayListOf()

    val mSelectedItems: HashMap<Int, Boolean> = HashMap()

    fun addToList(list: MutableList<Subject>) {
        subjectList.addAll(list)
    }

    val preClickList: MutableList<View> = arrayListOf()
    var visible = false

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

        @RequiresApi(Build.VERSION_CODES.N)
        fun setHolder(subject: Subject) {
            itemView.minimumHeight = 90

            Log.d("tag","그려질 과목 +$subject")

            itemView.findViewById<TextView>(R.id.tv_class_name).text = subject.name
            itemView.findViewById<TextView>(R.id.tv_professor_name).text = subject.professor

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

            itemView.findViewById<TextView>(R.id.tv_category).text = subject.course
            itemView.findViewById<TextView>(R.id.tv_credit).text = subject.credit.toString()
            itemView.findViewById<TextView>(R.id.tv_classnumber).text = subject.number
            itemView.findViewById<TextView>(R.id.tv_classnumber).visibility = View.VISIBLE


            val assess = itemView.findViewById<Button>(R.id.btn_assess)
            val cart = itemView.findViewById<Button>(R.id.btn_cart)
            val toTable = itemView.findViewById<Button>(R.id.btn_totable)

            if (mSelectedItems.getOrDefault(adapterPosition, false)){
                assess.visibility = View.VISIBLE
                cart.visibility = View.VISIBLE
                toTable.visibility = View.VISIBLE
            } else {
                assess.visibility = View.GONE
                cart.visibility = View.GONE
                toTable.visibility = View.GONE
            }


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
                        for (i in preClickList){
                            i.visibility = View.GONE
                        }
                    }

                    preClickList.clear()
                    preClickList.add(assess)
                    preClickList.add(cart)
                    preClickList.add(toTable)

//                  이전에 눌린거 바로 안누르고 다른거 누른ㄴ뒤에 누르면
//                  옛날에 눌린적 있는게 바로 전에 눌린건가 확인 아니면 의미 없다

                } else {
                    mSelectedItems.clear()
                    mSelectedItems.put(adapterPosition, true)
                    Log.d("tag","${adapterPosition}clicked to true")
//                   전에 눌린 기록이 false
                    if (preClickList.size>0){
                        for (i in preClickList){
                            i.visibility = View.GONE
                        }
                    }

                    (context as TimeTableEditActivity).rollBack()
//                    TODO 여기 주시
                    (context as TimeTableEditActivity).addToSampleTable(subject)

                    assess.visibility = View.VISIBLE
                    cart.visibility = View.VISIBLE
                    toTable.visibility = View.VISIBLE
                    Log.d("tag","${adapterPosition} is visible")

                    visible = true


                    preClickList.clear()
                    preClickList.add(assess)
                    preClickList.add(cart)
                    preClickList.add(toTable)
                }

                notifyDataSetChanged()
            }

            assess.setOnClickListener {
                val intent = Intent(context,EvaluationActivity::class.java)
                context.startActivity(intent)
            }

            cart.setOnClickListener {
//               TODO DB에 저장!

            }

            toTable.setOnClickListener {
//                등록되면 올리기
                (context as TimeTableEditActivity).addToTable(subject)
            }
        }

        fun setVisible(){
            val assess = itemView.findViewById<Button>(R.id.btn_assess)
            val cart = itemView.findViewById<Button>(R.id.btn_cart)
            val toTable = itemView.findViewById<Button>(R.id.btn_totable)
            assess.visibility = View.VISIBLE
            cart.visibility = View.VISIBLE
            toTable.visibility = View.VISIBLE
        }
        fun setInvisible(){
            val assess = itemView.findViewById<Button>(R.id.btn_assess)
            val cart = itemView.findViewById<Button>(R.id.btn_cart)
            val toTable = itemView.findViewById<Button>(R.id.btn_totable)
            assess.visibility = View.GONE
            cart.visibility = View.GONE
            toTable.visibility = View.GONE
        }
    }

    fun redrawItemSelected(position : Int){
        if (mSelectedItems.getOrDefault(position, false) == true){
            mSelectedItems.remove(position)
            notifyItemChanged(position)
        } else {
            mSelectedItems.put(position,true)
            notifyItemChanged(position)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        return VHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_subject_child, parent, false))
    }

    override fun getItemCount(): Int = subjectList.size


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: VHolder, position: Int) {
//        if (mSelectedItems.getOrDefault(position, false)){
//            holder.setInvisible()
//            Log.d("tag", "visible")
//        } else {
//            holder.setVisible()
//            Log.d("tag", "Invisible")
//        }
        holder.setHolder(subjectList[position])
    }

}