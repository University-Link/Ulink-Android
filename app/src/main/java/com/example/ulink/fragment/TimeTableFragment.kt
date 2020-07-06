package com.example.ulink.fragment

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.ulink.MainActivity
import com.example.ulink.NotificationActivity
import com.example.ulink.R
import com.example.ulink.repository.Subject
import com.example.ulink.repository.TimeTable
import com.example.ulink.timetable.BottomSheetFragment
import com.example.ulink.timetable.TimeTableDrawer
import com.example.ulink.timetable.TimeTableEdit
import com.example.ulink.timetable.TimeTableListActivity
import kotlinx.android.synthetic.main.fragment_time_table.*
import java.text.SimpleDateFormat
import java.util.*


class TimeTableFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_time_table, container, false)

//        클릭시 좌표로 subject list에서 찾기! 몇시 과목인지로!
    }

    interface subjectOnClick{
        fun onClick()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        서버랑 통신해서 TimeTable가져옴

        val subjectList : MutableList<Subject> = arrayListOf()
        subjectList.add(Subject(1,"과목이름","09:00","12:00","mon","과목장소",1,true))
        subjectList.add(Subject(2,"과목이름","11:00","13:00","tue","과목장소",1,true))
        subjectList.add(Subject(3,"과목이름","14:00","16:00","wed","과목장소",1,true))
        val timeTable = TimeTable(1,"2020-1","시간표이름",subjectList,true,"09:00","16:00")



        val onClick = object : subjectOnClick {
            override fun onClick() {
                val builder = AlertDialog.Builder(context)
                val layout = LayoutInflater.from(context).inflate(R.layout.dialog_timetable_subject, null)
                builder.setView(layout)
                val dialog = builder.create()
//          기타 클릭 이벤트
                layout.setOnClickListener {
                    Toast.makeText(context,"dfasdf", Toast.LENGTH_SHORT).show()
                }

                dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog.show()
            }
        }

        val timetableDrawer = TimeTableDrawer(requireContext(), LayoutInflater.from(context), onClick)
        timetableDrawer.timeTable = timeTable
        timetableDrawer.draw(view.findViewById<FrameLayout>(R.id.layout_timetable))

        btn_plus.setOnClickListener {

            val builder = AlertDialog.Builder(context)
            val layout = LayoutInflater.from(context).inflate(R.layout.dialog_timetable_checkgrade, null)



            builder.setView(layout)

            val dialog = builder.create()

            val intent = Intent(context, TimeTableEdit::class.java)
            layout.findViewById<Button>(R.id.btn_grade1).setOnClickListener {
                intent.putExtra("grade", 1)
                startActivity(intent)
                dialog.dismiss()
            }
            layout.findViewById<Button>(R.id.btn_grade2).setOnClickListener {
                intent.putExtra("grade", 2)
                startActivity(intent)
                dialog.dismiss()

            }
            layout.findViewById<Button>(R.id.btn_grade3).setOnClickListener {
                intent.putExtra("grade", 3)
                startActivity(intent)
                dialog.dismiss()

            }
            layout.findViewById<Button>(R.id.btn_grade4).setOnClickListener {
                intent.putExtra("grade", 4)
                startActivity(intent)
                dialog.dismiss()

            }
            layout.findViewById<Button>(R.id.btn_grade5).setOnClickListener {
                intent.putExtra("grade", 5)
                startActivity(intent)
                dialog.dismiss()
            }


            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()

        }

        btn_alarm.setOnClickListener {
            startActivity(Intent(context, NotificationActivity::class.java))
        }

        btn_list.setOnClickListener {
            startActivity(Intent(context, TimeTableListActivity::class.java))
        }
        btn_setting.setOnClickListener {
            val bottomsheet = BottomSheetFragment()
            fragmentManager?.let { it -> bottomsheet.show(it,bottomsheet.tag) }
        }

    }
}