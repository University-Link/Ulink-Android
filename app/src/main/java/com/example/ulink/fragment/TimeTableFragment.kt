package com.example.ulink.fragment

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.ulink.NotificationActivity
import com.example.ulink.R
import com.example.ulink.repository.Subject
import com.example.ulink.repository.TimeTable
import com.example.ulink.timetable.*
import com.example.ulink.utils.deepCopy
import kotlinx.android.synthetic.main.fragment_time_table.*
import kotlin.collections.ArrayList

const val REQUEST_TIMETABLE_LIST_ACTIVITY = 777

class TimeTableFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_time_table, container, false)

//        클릭시 좌표로 subject list에서 찾기! 몇시 과목인지로!
    }

    interface subjectOnClick{
        fun onClick(subject: Subject)
    }

    lateinit var timetableDrawer : TimeTableDrawer


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        서버랑 통신해서 TimeTable가져옴

        val subjectList : MutableList<Subject> = arrayListOf()
        subjectList.add(Subject(1,"유링크","09:00","12:00","mon","이태원",1,true, professor = "최상일"))
        subjectList.add(Subject(2,"김영민","14:00","16:00","mon","녹사평",2,true))
        subjectList.add(Subject(3,"박규희","11:00","13:00","tue","한강진",3,true))
        subjectList.add(Subject(4,"이지은","14:00","16:00","wed","버티고개",4,true))

        val timeTable = TimeTable(1,"2020-1","시간표이름",true,"09:00","16:00",subjectList)
        val timeTable2 = TimeTable(2,"2020-2","시간표이름2",true,"09:00","16:00",subjectList)


        val onClick = object : subjectOnClick {
            override fun onClick(subject: Subject) {
                val builder = AlertDialog.Builder(context)
                val layout = LayoutInflater.from(context).inflate(R.layout.dialog_timetable_subject, null)

                layout.findViewById<TextView>(R.id.tv_class_name).text = subject.name
//                TODO 이거 table받아와서 classname으로 일주일에 몇번 수업인지 알아서 표시하기 vs 어뜨카지

                layout.findViewById<TextView>(R.id.tv_time).text = subject.startTime +"  "+  subject.endTime
                layout.findViewById<TextView>(R.id.tv_place).text = subject.place + ", "
                layout.findViewById<TextView>(R.id.tv_professor_name).text = subject.professor + "교수"
                layout.findViewById<TextView>(R.id.tv_class_name).text = subject.name

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

        timetableDrawer = TimeTableDrawer(requireContext(), LayoutInflater.from(context), onClick, timeTable)

        timetableDrawer.draw(view.findViewById<FrameLayout>(R.id.layout_timetable))

        btn_plus.setOnClickListener {

            val intent = Intent(context, TimeTableEditActivity::class.java)
            val list: ArrayList<TimeTable> = arrayListOf()
            list.add(timeTable)
            list.add(timeTable2)
            intent.putParcelableArrayListExtra("timeTableList",list)
            startActivity(intent)

        }

        btn_alarm.setOnClickListener {
            startActivity(Intent(context, NotificationActivity::class.java))
        }

        btn_list.setOnClickListener {

            startActivityForResult(Intent(context, TimeTableListActivity::class.java), REQUEST_TIMETABLE_LIST_ACTIVITY)
        }

        btn_setting.setOnClickListener {
            val bottomsheet = BottomSheetFragment()
            fragmentManager?.let { it -> bottomsheet.show(it,bottomsheet.tag) }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode== REQUEST_TIMETABLE_LIST_ACTIVITY && resultCode == 200){

            if (data != null) {
                timetableDrawer.timeTable = deepCopy(data.getParcelableExtra("timeTable"))
                tv_semister.text = timetableDrawer.timeTable.semseter

            }
            view?.findViewById<FrameLayout>(R.id.layout_timetable)?.let { timetableDrawer.draw(it) }
        }
    }
}