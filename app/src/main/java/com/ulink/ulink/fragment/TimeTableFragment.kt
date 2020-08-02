package com.ulink.ulink.fragment

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
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.ulink.ulink.Activity.ChattingActivity
import com.ulink.ulink.Activity.NoticeActivity
import com.ulink.ulink.Activity.NotificationActivity
import com.ulink.ulink.R
import com.ulink.ulink.Ulink.UlinkInsideActivity
import com.ulink.ulink.repository.DataRepository
import com.ulink.ulink.repository.Subject
import com.ulink.ulink.repository.TimeTable
import com.ulink.ulink.timetable.*
import com.ulink.ulink.utils.deepCopy
import kotlinx.android.synthetic.main.fragment_time_table.*

const val REQUEST_TIMETABLE_LIST_ACTIVITY = 777
const val REQUEST_TIMETABLE_EDIT_ACITYVITY = 111


//메인 시간표
class TimeTableFragment : Fragment(), onRefreshListener {

    
    override fun onRefresh() {
        refreshLastTimeTable()
        refresh = false
    }

    interface subjectOnClick {
        fun onClick(subject: Subject)
    }
    
    lateinit var mainTable: TimeTable

    lateinit var timetableDrawer: TimeTableDrawer
    
    var lastTimeTableId = 0
    var refresh = true

//    과목 클릭 콜백
    val onClick = object : subjectOnClick {
        override fun onClick(subject: Subject) {

            val builder = AlertDialog.Builder(context)
            val layout =
                    LayoutInflater.from(context).inflate(R.layout.dialog_timetable_subject, null)

            layout.findViewById<TextView>(R.id.tv_class_name).text = subject.name
            builder.setView(layout)
            val dialog = builder.create()

//            TODO 이 과목 이름으로 tabelist에서 찾아서 같은 시간 다 표시!!
//               장소가 같은경우와 다른경우 나누기

            for(i in mainTable.subjectList){
                if (subject.id == i.id){
//                 같은게 있는 경우
                    Log.d("tag",i.toString())

                } else {
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
                    
                    
                }
            }

           

            layout.findViewById<ImageView>(R.id.ic_color).setBackgroundResource(getColors(subject.color))

            layout.findViewById<TextView>(R.id.tv_customizing).setOnClickListener {
                val bottomsheet = CustomizingBottomSheetFragment(subject, object : onRefreshListener {
                    override fun onRefresh() {
                        refresh = true
                        refreshLastTimeTable()
                    }
                })
                fragmentManager?.let { it -> bottomsheet.show(it, bottomsheet.tag)
                    dialog.dismiss()}
            }
            if (subject.subject == true) {
                layout.findViewById<TextView>(R.id.tv_tochat).setOnClickListener {
                    //val idx = subject.id.toString()
                    val intent = Intent(view?.context, UlinkInsideActivity::class.java)
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
            } else {
                layout.findViewById<ImageView>(R.id.ic_tochat).visibility = View.INVISIBLE
                layout.findViewById<TextView>(R.id.tv_tochat).visibility = View.INVISIBLE
                layout.findViewById<ImageView>(R.id.ic_checkassignment).visibility = View.GONE
                layout.findViewById<TextView>(R.id.tv_checkassignment).visibility = View.GONE
                layout.findViewById<ImageView>(R.id.ic_name_update).visibility = View.VISIBLE
                layout.findViewById<TextView>(R.id.tv_name_update).visibility = View.VISIBLE
            }

            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()

        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_time_table, container, false)

//        클릭시 좌표로 subject list에서 찾기! 몇시 과목인지로!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        mainTable = TimeTable(0, "2020-1", "시간표1", 1, "09:00", "18:00")

//        서버랑 통신해서 TimeTable가져옴
        refreshMainTable()

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

        val tableView = view.findViewById<ConstraintLayout>(R.id.layout_timetablefragment)

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
                lastTimeTableId = table.id

                DataRepository.getTimeTableWithId(table.id,
                        onSuccess = { table ->
                            timetableDrawer.timeTable = deepCopy(table)
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

            if (data != null) {
                val table: TimeTable = deepCopy(data.getParcelableExtra("timeTable"))
                Log.d("tag", table.toString())
                lastTimeTableId = table.id
                refreshLastTimeTable()
            }

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

    fun refreshMainTable() {
        DataRepository.getMainTimeTable(
                onSuccess = {
                    this.mainTable = it
                    Log.d("tag2", it.toString())
                    timetableDrawer = TimeTableDrawer(requireContext(), LayoutInflater.from(context), onClick, mainTable)
                    view?.findViewById<FrameLayout>(R.id.layout_timetable)?.let { it1 -> timetableDrawer.draw(it1) }
                    lastTimeTableId = it.id
                    val t = timetableDrawer.timeTable.semester.split("-")
                    if (t.size == 2) {
                        tv_semister.text = "${t[0]}년 ${t[1]}학기"
                    }
                },
                onFailure = {
                    mainTable = TimeTable(1, "2020-1", "시간표1", 1, "09:00", "16:00")
                    timetableDrawer = TimeTableDrawer(requireContext(), LayoutInflater.from(context), onClick, mainTable)
                    view?.findViewById<FrameLayout>(R.id.layout_timetable)?.let { it1 -> timetableDrawer.draw(it1) }
                }
        )
    }

    fun refreshLastTimeTable(){
        DataRepository.getTimeTableWithId(lastTimeTableId,
                onSuccess = {
                    this.mainTable = it
                    Log.d("tag refreshedtimetable", it.toString())
                    timetableDrawer = TimeTableDrawer(requireContext(), LayoutInflater.from(context), onClick, mainTable)
                    view?.findViewById<FrameLayout>(R.id.layout_timetable)?.let { it1 -> timetableDrawer.draw(it1) }
                    lastTimeTableId = it.id
                    val t = timetableDrawer.timeTable.semester.split("-")
                    if (t.size == 2) {
                        tv_semister.text = "${t[0]}년 ${t[1]}학기"
                    }

                },
                onFailure = {

                })
    }


    fun getColors(type: Int): Int {
        return when (type) {
            0 -> R.drawable.bg_round_border_subject_color_1
            1 -> R.drawable.bg_round_border_subject_color_2
            2 -> R.drawable.bg_round_border_subject_color_3
            3 -> R.drawable.bg_round_border_subject_color_4
            4 -> R.drawable.bg_round_border_subject_color_5
            5 -> R.drawable.bg_round_border_subject_color_6
            6 -> R.drawable.bg_round_border_subject_color_7
            7 -> R.drawable.bg_round_border_subject_color_8
            8 -> R.drawable.bg_round_border_subject_color_9
            9 -> R.drawable.bg_round_border_subject_color_10
            10 -> R.drawable.bg_round_border_subject_color_11
            11 -> R.drawable.bg_round_border_subject_color_12
            12 -> R.drawable.bg_round_border_subject_color_13
            13 -> R.drawable.bg_round_border_subject_color_14
            14 -> R.drawable.bg_round_border_subject_color_15
            15 -> R.drawable.bg_round_border_subject_color_16
            16 -> R.drawable.bg_round_border_subject_color_17
            17 -> R.drawable.bg_round_border_subject_color_18
            18 -> R.drawable.bg_round_border_subject_color_19
            19 -> R.drawable.bg_round_border_subject_color_20
            else -> R.drawable.bg_round_border_subject
        }
    }

}

interface onRefreshListener {
    fun onRefresh()
}