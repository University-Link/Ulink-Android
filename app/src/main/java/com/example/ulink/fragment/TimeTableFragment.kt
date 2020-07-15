package com.example.ulink.fragment

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.ulink.ChattingActivity
import com.example.ulink.NoticeActivity
import com.example.ulink.NotificationActivity
import com.example.ulink.R
import com.example.ulink.repository.DataRepository
import com.example.ulink.repository.Subject
import com.example.ulink.repository.TimeTable
import com.example.ulink.timetable.BottomSheetFragment
import com.example.ulink.timetable.TimeTableDrawer
import com.example.ulink.timetable.TimeTableEditActivity
import com.example.ulink.timetable.TimeTableListActivity
import com.example.ulink.utils.deepCopy
import kotlinx.android.synthetic.main.fragment_time_table.*

const val REQUEST_TIMETABLE_LIST_ACTIVITY = 777

class TimeTableFragment : Fragment() {

    lateinit var mainTable: TimeTable

    var refresh = true


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_time_table, container, false)

//        클릭시 좌표로 subject list에서 찾기! 몇시 과목인지로!
    }

    interface subjectOnClick {
        fun onClick(subject: Subject)
    }

    lateinit var timetableDrawer: TimeTableDrawer


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        mainTable = TimeTable(1, "2020-1", "시간표1", 1, "09:00", "16:00")


//        서버랑 통신해서 TimeTable가져옴


        btn_plus.setOnClickListener {
            val intent = Intent(context, TimeTableEditActivity::class.java)
            val list: ArrayList<TimeTable> = arrayListOf()
            list.add(mainTable)
            intent.putParcelableArrayListExtra("timeTableList", list)
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
            fragmentManager?.let { it -> bottomsheet.show(it, bottomsheet.tag) }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_TIMETABLE_LIST_ACTIVITY && resultCode == 200) {
            if (data != null) {
                timetableDrawer.timeTable = deepCopy(data.getParcelableExtra("timeTable"))
                mainTable = deepCopy(data.getParcelableExtra("timeTable"))

//                시간표 요청
                DataRepository.getTimeTableWithId(mainTable.id,
                        onSuccess = {

                        },
                        onFailure = {
                            Log.d("tag",it)

                })

//              TODO 마지막 시간표 id 저장하고 그 id로 계속 요청하자
                refresh = false

                val t = timetableDrawer.timeTable.semester.split("-")
                if (t.size == 2) {
                    tv_semister.text = "${t[0]}년 ${t[1]}학기"
                }
            }
            view?.findViewById<FrameLayout>(R.id.layout_timetable)?.let { timetableDrawer.draw(it) }

        }
    }

    override fun onResume() {
        super.onResume()
        val onClick = object : subjectOnClick {
            override fun onClick(subject: Subject) {
                val builder = AlertDialog.Builder(context)
                val layout = LayoutInflater.from(context).inflate(R.layout.dialog_timetable_subject, null)

                layout.findViewById<TextView>(R.id.tv_class_name).text = subject.name
//                TODO 이거 table받아와서 classname으로 일주일에 몇번 수업인지 알아서 표시하기 vs 어뜨카지

                layout.findViewById<TextView>(R.id.tv_time).text = subject.startTime + "  " + subject.endTime
                layout.findViewById<TextView>(R.id.tv_place).text = subject.place + " "
                layout.findViewById<TextView>(R.id.tv_professor_name).text = subject.professor
                layout.findViewById<TextView>(R.id.tv_class_name).text = subject.name

                layout.findViewById<TextView>(R.id.tv_tochat).setOnClickListener {
                    //val idx = subject.id.toString()
                    val intent = Intent(view?.context, ChattingActivity::class.java) //과목명
                    intent.putExtra("class", subject.name)
                    //intent.putExtra("idx", subject.id.toString())
                    startActivity(intent)
                }

                layout.findViewById<TextView>(R.id.tv_checkassignment).setOnClickListener {
                    val className = subject.name
                    //val idx = subject.id.toString()
                    val intent = Intent(view?.context, NoticeActivity::class.java)
                    intent.putExtra("class", subject.name)
                    //intent.putExtra("idx", "4")
                    startActivity(intent)
                }

                builder.setView(layout)
                val dialog = builder.create()

                dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog.show()
            }
        }

        if (refresh) {
            DataRepository.getMainTimeTable(
                    onSuccess = {
                        this.mainTable = it
                        timetableDrawer = TimeTableDrawer(requireContext(), LayoutInflater.from(context), onClick, mainTable)
                        view?.findViewById<FrameLayout>(R.id.layout_timetable)?.let { it1 -> timetableDrawer.draw(it1) }
                        Log.d("tag", "size" + mainTable)
                    },
                    onFailure = {
                        mainTable = TimeTable(1, "2020-1", "시간표1", 1, "09:00", "16:00")
                        timetableDrawer = TimeTableDrawer(requireContext(), LayoutInflater.from(context), onClick, mainTable)
                        view?.findViewById<FrameLayout>(R.id.layout_timetable)?.let { it1 -> timetableDrawer.draw(it1) }
                    }
            )
        }

    }
}