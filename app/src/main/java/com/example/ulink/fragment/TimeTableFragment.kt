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
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.ulink.ChattingActivity
import com.example.ulink.NoticeActivity
import com.example.ulink.NotificationActivity
import com.example.ulink.R
import com.example.ulink.repository.DataRepository
import com.example.ulink.repository.Subject
import com.example.ulink.repository.TimeTable
import com.example.ulink.timetable.*
import com.example.ulink.utils.deepCopy
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import kotlinx.android.synthetic.main.fragment_time_table.*
import org.w3c.dom.Text

const val REQUEST_TIMETABLE_LIST_ACTIVITY = 777
const val REQUEST_TIMETABLE_EDIT_ACITYVITY = 111

class TimeTableFragment : Fragment(), onRefreshListener {

    override fun onRefresh() {
        refresh = true
        refreshMainTable()
    }

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

    val onClick = object : subjectOnClick {
        override fun onClick(subject: Subject) {

            Log.d("idx", subject.toString())

            val builder = AlertDialog.Builder(context)
            val layout =
                    LayoutInflater.from(context).inflate(R.layout.dialog_timetable_subject, null)

            layout.findViewById<TextView>(R.id.tv_class_name).text = subject.name
//                TODO 이거 table받아와서 classname으로 일주일에 몇번 수업인지 알아서 표시하기 vs 어뜨카지

            for (i in 0 until subject.startTime.size) {
                layout.findViewById<TextView>(R.id.tv_time).text =
                        layout.findViewById<TextView>(R.id.tv_time).text.toString() + getDay(subject.day[i]) + " " + subject.startTime[i] + " - " + subject.endTime[i]
                if (subject.startTime.size > 1 && i < subject.startTime.size - 1) {
                    var text = layout.findViewById<TextView>(R.id.tv_time).text
                    val text2 = "$text, " + getDay(subject.day[i])
                    layout.findViewById<TextView>(R.id.tv_time).text = text2
                }
            }


            for (i in 0 until subject.place.size) {
                layout.findViewById<TextView>(R.id.tv_place).text =
                        layout.findViewById<TextView>(R.id.tv_place).text.toString() + subject.place[i]

                if (subject.place.size > 1 && i < subject.place.size - 1) {
                    var text = layout.findViewById<TextView>(R.id.tv_place).text
                    val text2 = "$text, "
                    layout.findViewById<TextView>(R.id.tv_place).text = text2
                }
            }

            layout.findViewById<TextView>(R.id.tv_customizing).setOnClickListener {
                val bottomsheet = CustomizingBottomSheetFragment(subject)
                fragmentManager?.let { it -> bottomsheet.show(it, bottomsheet.tag) }

            }
            if (subject.subject == true) {
                layout.findViewById<TextView>(R.id.tv_tochat).setOnClickListener {
                    //val idx = subject.id.toString()
                    val intent = Intent(view?.context, ChattingActivity::class.java) //과목명
                    intent.putExtra("class", subject.name)
                    intent.putExtra("idx", subject.subjectIdx.toString())
                    Log.d("idx", subject.subjectIdx.toString())
                    startActivity(intent)
                }

                layout.findViewById<TextView>(R.id.tv_checkassignment).setOnClickListener {
                    //val idx = subject.id.toString()
                    val intent = Intent(view?.context, NoticeActivity::class.java)
                    intent.putExtra("class", subject.name)
                    intent.putExtra("idx", subject.subjectIdx.toString())
                    intent.putExtra("check", "add")
                    Log.d("idx", subject.subjectIdx.toString())
                    startActivity(intent)
                }
            } else{
                layout.findViewById<ImageView>(R.id.ic_tochat).visibility=View.INVISIBLE
                layout.findViewById<TextView>(R.id.tv_tochat).visibility=View.INVISIBLE
                layout.findViewById<ImageView>(R.id.ic_checkassignment).visibility=View.GONE
                layout.findViewById<TextView>(R.id.tv_checkassignment).visibility=View.GONE
                layout.findViewById<ImageView>(R.id.ic_name_update).visibility=View.VISIBLE
                layout.findViewById<TextView>(R.id.tv_name_update).visibility=View.VISIBLE
            }


            builder.setView(layout)
            val dialog = builder.create()

            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()

        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        mainTable = TimeTable(1, "2020-1", "시간표1", 1, "09:00", "16:00")


//        서버랑 통신해서 TimeTable가져옴


        btn_plus.setOnClickListener {
            val intent = Intent(context, TimeTableEditActivity::class.java)
            val list: ArrayList<TimeTable> = arrayListOf()
            intent.putExtra("timeTable", mainTable)
            startActivityForResult(intent, REQUEST_TIMETABLE_EDIT_ACITYVITY)
        }

        btn_alarm.setOnClickListener {
            refresh = true
            startActivity(Intent(context, NotificationActivity::class.java))
        }

        btn_list.setOnClickListener {
            startActivityForResult(Intent(context, TimeTableListActivity::class.java), REQUEST_TIMETABLE_LIST_ACTIVITY)
        }

        btn_setting.setOnClickListener {
            val bottomsheet = BottomSheetFragment(mainTable, this)
            fragmentManager?.let { it -> bottomsheet.show(it, bottomsheet.tag) }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_TIMETABLE_LIST_ACTIVITY && resultCode == 200) {
            if (data != null) {

                val table: TimeTable = deepCopy(data.getParcelableExtra("timeTable"))

//                시간표 요청
                DataRepository.getTimeTableWithId(table.id,
                        onSuccess = { table ->

                            timetableDrawer.timeTable = deepCopy(table)

                            refresh = false

                            val t = timetableDrawer.timeTable.semester.split("-")
                            if (t.size == 2) {
                                tv_semister.text = "${t[0]}년 ${t[1]}학기"
                            }

                            mainTable = deepCopy(table)
                            view?.findViewById<FrameLayout>(R.id.layout_timetable)?.let { timetableDrawer.draw(it) }
                        },
                        onFailure = {
                            Log.d("tag", it)
                        })
            }
        } else if (requestCode == REQUEST_TIMETABLE_EDIT_ACITYVITY && resultCode == 200) {
            Log.d("tag","nullllllll")

            if (data != null) {
                val table: TimeTable = deepCopy(data.getParcelableExtra("timeTable"))
//                시간표 요청
                Log.d("tag1111111111",table.toString())

                DataRepository.getTimeTableWithId(table.id,
                        onSuccess = { table ->

                            Log.d("tag2222222",table.toString())

                            timetableDrawer.timeTable = deepCopy(table)

                            refresh = false

                            val t = timetableDrawer.timeTable.semester.split("-")
                            if (t.size == 2) {
                                tv_semister.text = "${t[0]}년 ${t[1]}학기"
                            }

                            mainTable = deepCopy(table)
                            view?.findViewById<FrameLayout>(R.id.layout_timetable)?.let { timetableDrawer.draw(it) }
                        },
                        onFailure = {
                            Log.d("tag", it)
                        })
            }

        }
    }

    override fun onResume() {
        super.onResume()


        Log.d("tag", refresh.toString())
        if (refresh) {
            DataRepository.getMainTimeTable(
                    onSuccess = {
                        this.mainTable = it
                        Log.d("tag", it.toString())
                        timetableDrawer = TimeTableDrawer(requireContext(), LayoutInflater.from(context), onClick, mainTable)
                        view?.findViewById<FrameLayout>(R.id.layout_timetable)?.let { it1 -> timetableDrawer.draw(it1) }
                    },
                    onFailure = {
                        mainTable = TimeTable(1, "2020-1", "시간표1", 1, "09:00", "16:00")
                        timetableDrawer = TimeTableDrawer(requireContext(), LayoutInflater.from(context), onClick, mainTable)
                        view?.findViewById<FrameLayout>(R.id.layout_timetable)?.let { it1 -> timetableDrawer.draw(it1) }
                    }
            )
        }
        refresh = false
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

    fun refreshMainTable(){
        if (refresh) {
            DataRepository.getMainTimeTable(
                    onSuccess = {
                        this.mainTable = it
                        Log.d("tag", it.toString())
                        timetableDrawer = TimeTableDrawer(requireContext(), LayoutInflater.from(context), onClick, mainTable)
                        view?.findViewById<FrameLayout>(R.id.layout_timetable)?.let { it1 -> timetableDrawer.draw(it1) }
                    },
                    onFailure = {
                        mainTable = TimeTable(1, "2020-1", "시간표1", 1, "09:00", "16:00")
                        timetableDrawer = TimeTableDrawer(requireContext(), LayoutInflater.from(context), onClick, mainTable)
                        view?.findViewById<FrameLayout>(R.id.layout_timetable)?.let { it1 -> timetableDrawer.draw(it1) }
                    }
            )
        }
        refresh = false
    }


}

interface onRefreshListener{
    fun onRefresh()
}