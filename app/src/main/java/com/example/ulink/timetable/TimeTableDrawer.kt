package com.example.ulink.timetable

import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import com.example.ulink.R
import com.example.ulink.fragment.TimeTableFragment
import com.example.ulink.repository.Subject
import com.example.ulink.repository.TimeTable
import java.text.SimpleDateFormat
import java.util.*


class TimeTableDrawer(val context: Context, val layoutInflater: LayoutInflater) {

    constructor(context: Context, layoutInflater: LayoutInflater, onClick: TimeTableFragment.subjectOnClick) : this(context, layoutInflater) {
        this.onClick = onClick
    }

    constructor(context: Context, layoutInflater: LayoutInflater, timeTable: TimeTable) : this(context, layoutInflater) {
        this.timeTable = timeTable
    }

    constructor(context: Context, layoutInflater: LayoutInflater, onClick: TimeTableFragment.subjectOnClick, timeTable: TimeTable) : this(context, layoutInflater, onClick) {
        this.timeTable = timeTable
    }

    var onClick: TimeTableFragment.subjectOnClick? = null

    var timeTable = TimeTable(0, "", "", false, "", "")

    var endhour = 18
    var starthour = 9

    var timecolumnwidth = 0.0f
    var dayheight = 0.0f
    var columnwidth = 0.0f
    var rowheight = 0.0f


    var minHeight = 60.0f


    fun draw(frameLayout: FrameLayout) {
        val root = frameLayout.findViewById<LinearLayout>(R.id.timetable_root)

//        시간 9.0 9.25 이런식!
        if ((formatToFloat(timeTable.startTime)).toInt() < 9) {
            starthour = (formatToFloat(timeTable.startTime)).toInt()
        }

        if ((formatToFloat(timeTable.endTime)).toInt() > 18) {
            endhour = (formatToFloat(timeTable.endTime)).toInt()
        }

        val rowroot = (frameLayout.parent.parent as LinearLayout).findViewById<LinearLayout>(R.id.layout_dayrow)

        root.removeAllViews()
        rowroot.removeAllViews()

        drawDayRow(rowroot)
        drawHorizontalLine(root)
        drawTimeColumn(root)
        drawColums(root)
    }

    fun drawDayRow(linearLayout: LinearLayout) {
        val dayOfWeek = 5
        val daylist = arrayListOf("월", "화", "수", "목", "금", "토", "일")

        val index = layoutInflater.inflate(R.layout.cell_top_left_index, linearLayout, false)
        linearLayout.addView(index)

        for (i in 0 until dayOfWeek) {
            val day = layoutInflater.inflate(R.layout.cell_day, linearLayout, false) as TextView
            day.text = daylist[i]
            day.setBackgroundResource(R.drawable.bottom_line)

            linearLayout.addView(day)

            val mGlobalListner2 = ViewTreeObserver.OnGlobalLayoutListener {
                if (day.measuredHeight > 0.0) {
                    dayheight = day.measuredHeight.toFloat()
                }
            }
            day.viewTreeObserver.addOnGlobalLayoutListener(mGlobalListner2)
        }

    }

    fun drawHorizontalLine(linearLayout: LinearLayout) {

        val linearLayoutWhole =
                (linearLayout.parent as View).findViewById<LinearLayout>(R.id.timetable_grid)
//      밑에서 whole에다가 tilmetable_grid추가해줄거니까 미리 지워줘야함
        linearLayoutWhole.removeAllViews()


//     이게 whole에 들어가있는 vertical 여기다가 쌓는거
        val linearLayoutRow = layoutInflater.inflate(
                R.layout.timetable_grid,
                linearLayout,
                false
        ) as LinearLayout

        linearLayoutRow.weightSum = 4 * (endhour - starthour).toFloat()

//        until 안쓰는 이유 = 밑에 한칸 추가해주기
        for (i in starthour..endhour) {
            val row = layoutInflater.inflate(
                    R.layout.cell_timeindex,
                    linearLayoutRow,
                    false
            ) as TextView
            row.setBackgroundResource(R.drawable.bottom_line)
//          이걸로 최소 줄 크기 조절!
            linearLayoutRow.addView(row)

            val mGlobalListner = ViewTreeObserver.OnGlobalLayoutListener {
                if (row.measuredHeight > 0.0) {
                    rowheight = row.measuredHeight.toFloat()
                }
            }
            row.viewTreeObserver.addOnGlobalLayoutListener(mGlobalListner)


        }

//      view를 추가할때 이미 다른곳에 추가되어있으면 에러나니까 위에 infalte할때 attachtoroot는 false로!
        linearLayoutWhole.addView(linearLayoutRow)
    }


    fun drawTimeColumn(linearLayout: LinearLayout) {
        val timecolumn = layoutInflater.inflate(
                R.layout.day_column_for_time,
                linearLayout,
                false
        ) as LinearLayout

        timecolumn.weightSum = 4 * (endhour - starthour).toFloat()
        val simpleDateFormat = SimpleDateFormat("h")
        val instance = Calendar.getInstance()

//      TODO 여기 바꾸기!!!!

        for (i in starthour..endhour) {
            val timeColumnEa =
                    layoutInflater.inflate(R.layout.cell_timeindex, timecolumn, false) as TextView

            timeColumnEa.minHeight =
                    TypedValue.applyDimension(1, minHeight, context.resources.displayMetrics)
                            .toInt()
            instance.set(Calendar.HOUR_OF_DAY, i)

            timeColumnEa.gravity = Gravity.RIGHT
            timeColumnEa.setPadding(0, dptopx(5).toInt(), dptopx(4).toInt(), 0)
            timeColumnEa.text = simpleDateFormat.format(instance.time)
            timecolumn.addView(timeColumnEa)

        }
        linearLayout.addView(timecolumn)

        val mGlobalListner = ViewTreeObserver.OnGlobalLayoutListener {
            if (timecolumn.measuredWidth > 0.0) {
                timecolumnwidth = timecolumn.measuredWidth.toFloat()
            }
        }
        timecolumn.viewTreeObserver.addOnGlobalLayoutListener(mGlobalListner)

    }

    fun drawColums(linearLayout: LinearLayout) {
        val dayOfWeek = 5

        drawVerticalLine(linearLayout)

        for (i in 0 until dayOfWeek) {
//          맨위에 월화수목금
            val dayRow =
                    layoutInflater.inflate(R.layout.day_column, linearLayout, false) as FrameLayout

            val linearlayoutfor = dayRow.findViewById<LinearLayout>(R.id.day_column_root)
            val linearlayoutSample = dayRow.findViewById<LinearLayout>(R.id.day_column_root_sample)

//          이거 해줘야 나중에 과목(cell time) weight가 시간표 비율에 맞춰짐
            linearlayoutfor.weightSum = 4 * (endhour - starthour).toFloat()
            linearlayoutSample.weightSum = 4 * (endhour - starthour).toFloat()

            val mGlobalListner = ViewTreeObserver.OnGlobalLayoutListener {
                if (linearlayoutfor.measuredWidth > 0.0) {
                    columnwidth = linearlayoutfor.width.toFloat()
                }
            }
            linearlayoutfor.viewTreeObserver.addOnGlobalLayoutListener(mGlobalListner)

            drawColumn(linearlayoutfor, i)
            drawSampleColumn(linearlayoutSample, i)

            linearLayout.addView(dayRow)
            if (i < 4) {
                drawVerticalLine(linearLayout)
            }
        }
    }

//    TODO
//      i에따라 draw할 수 있게 지금은 0~4 즉 월~금 고정인데 주말도 포함해야함

    fun drawColumn(linearLayout: LinearLayout, i: Int) {

        val subjectsize = timeTable.subjectList?.size
        var subjectstarttime = 0f
        var presubjectendtimeortablestarttime = 0f

//        subject sort한번 해줘야함

        subjectsize.let { itit ->
            val daylist = arrayListOf("mon", "tue", "wed", "thu", "fri", "sat", "sun")
            val list0: MutableList<Subject> = arrayListOf()
            val list1: MutableList<Subject> = arrayListOf()
            val list2: MutableList<Subject> = arrayListOf()
            val list3: MutableList<Subject> = arrayListOf()
            val list4: MutableList<Subject> = arrayListOf()


            for (a in 0 until itit) {

                when (timeTable.subjectList.get(a).day) {
                    daylist[0] -> list0.add(timeTable.subjectList[a])
                    daylist[1] -> list1.add(timeTable.subjectList[a])
                    daylist[2] -> list2.add(timeTable.subjectList[a])
                    daylist[3] -> list3.add(timeTable.subjectList[a])
                    daylist[4] -> list4.add(timeTable.subjectList[a])
                }
            }

//            list들 정렬하기 시간순으로


//            TODO 여기 정리하기! daylist를 list로 묶어서 i 사용하기!
            var list: MutableList<Subject> = arrayListOf()
            when (i) {
                0 -> {
                    list = list0
                    list.retainAll { !it.isSample }
                    list.sortBy { formatToFloat(it.starttime) }
                    for (a in 0 until list.size) {
                        if (a == 0) {
                            subjectstarttime = formatToFloat(list[a].starttime) * 4
                            presubjectendtimeortablestarttime = formatToFloat(timeTable.startTime) * 4
                        } else {
                            subjectstarttime = formatToFloat(list[a].starttime) * 4
                            presubjectendtimeortablestarttime = formatToFloat((list[a - 1].endtime)) * 4
                        }
                        DrawDummy(linearLayout, subjectstarttime - presubjectendtimeortablestarttime)
                        DrawSubject(linearLayout, list[a])
                    }
                }
                1 -> {
                    list = list1
                    list.retainAll { !it.isSample }

                    list.sortBy { formatToFloat(it.starttime) }
                    for (a in 0 until list.size) {
                        if (a == 0) {
                            subjectstarttime = formatToFloat(list[a].starttime) * 4
                            presubjectendtimeortablestarttime = formatToFloat(timeTable.startTime) * 4
                        } else {
                            subjectstarttime = formatToFloat(list[a].starttime) * 4
                            presubjectendtimeortablestarttime = formatToFloat((list[a - 1].endtime)) * 4
                        }
                        DrawDummy(linearLayout, subjectstarttime - presubjectendtimeortablestarttime)
                        DrawSubject(linearLayout, list[a])
                    }
                }
                2 -> {
                    list = list2
                    list.retainAll { !it.isSample }

                    list.sortBy { formatToFloat(it.starttime) }
                    for (a in 0 until list.size) {
                        if (a == 0) {
                            subjectstarttime = formatToFloat(list[a].starttime) * 4
                            presubjectendtimeortablestarttime = formatToFloat(timeTable.startTime) * 4
                        } else {
                            subjectstarttime = formatToFloat(list[a].starttime) * 4
                            presubjectendtimeortablestarttime = formatToFloat((list[a - 1].endtime)) * 4
                        }
                        DrawDummy(linearLayout, subjectstarttime - presubjectendtimeortablestarttime)
                        DrawSubject(linearLayout, list[a])
                    }
                }
                3 -> {
                    list = list3
                    list.retainAll { !it.isSample }

                    list.sortBy { formatToFloat(it.starttime) }
                    for (a in 0 until list.size) {
                        if (a == 0) {
                            subjectstarttime = formatToFloat(list[a].starttime) * 4
                            presubjectendtimeortablestarttime = formatToFloat(timeTable.startTime) * 4
                        } else {
                            subjectstarttime = formatToFloat(list[a].starttime) * 4
                            presubjectendtimeortablestarttime = formatToFloat((list[a - 1].endtime)) * 4
                        }
                        DrawDummy(linearLayout, subjectstarttime - presubjectendtimeortablestarttime)
                        DrawSubject(linearLayout, list[a])
                    }
                }
                4 -> {
                    list = list4
                    list.retainAll { !it.isSample }

                    list.sortBy { formatToFloat(it.starttime) }
                    for (a in 0 until list.size) {
                        if (a == 0) {
                            subjectstarttime = formatToFloat(list[a].starttime) * 4
                            presubjectendtimeortablestarttime = formatToFloat(timeTable.startTime) * 4
                        } else {
                            subjectstarttime = formatToFloat(list[a].starttime) * 4
                            presubjectendtimeortablestarttime = formatToFloat((list[a - 1].endtime)) * 4
                        }
                        DrawDummy(linearLayout, subjectstarttime - presubjectendtimeortablestarttime)
                        DrawSubject(linearLayout, list[a])
                    }
                }
            }
        }
    }


    fun drawSampleColumn(linearLayout: LinearLayout, i: Int) {

        val subjectsize = timeTable.subjectList.size
        var subjectstarttime = 0f
        var presubjectendtimeortablestarttime = 0f


        subjectsize?.let { itit ->
            val daylist = arrayListOf("mon", "tue", "wed", "thu", "fri", "sat", "sun")
            val list0: MutableList<Subject> = arrayListOf()
            val list1: MutableList<Subject> = arrayListOf()
            val list2: MutableList<Subject> = arrayListOf()
            val list3: MutableList<Subject> = arrayListOf()
            val list4: MutableList<Subject> = arrayListOf()

            for (a in 0 until itit) {

                when (timeTable.subjectList?.get(a)?.day) {
                    daylist[0] -> list0.add(timeTable.subjectList!![a])
                    daylist[1] -> list1.add(timeTable.subjectList!![a])
                    daylist[2] -> list2.add(timeTable.subjectList!![a])
                    daylist[3] -> list3.add(timeTable.subjectList!![a])
                    daylist[4] -> list4.add(timeTable.subjectList!![a])
                }
            }

//            TODO 여기 정리하기! daylist를 list로 묶어서 i 사용하기!

            var list: MutableList<Subject> = arrayListOf()

            when (i) {
                0 -> {
                    list = list0
                    list.retainAll { it.isSample }
                    list.sortBy { formatToFloat(it.starttime) }
                    for (a in 0 until list.size) {
                        if (a == 0) {
                            subjectstarttime = formatToFloat(list[a].starttime) * 4
                            presubjectendtimeortablestarttime = formatToFloat(timeTable.startTime) * 4
                        } else {
                            subjectstarttime = formatToFloat(list[a].starttime) * 4
                            presubjectendtimeortablestarttime = formatToFloat((list[a - 1].endtime)) * 4
                        }
                        DrawDummy(linearLayout, subjectstarttime - presubjectendtimeortablestarttime)
                        DrawSubject(linearLayout, list[a])
                    }
                }
                1 -> {
                    list = list1
                    list.filter { it.isSample }

                    list.sortBy { formatToFloat(it.starttime) }
                    for (a in 0 until list.size) {
                        if (a == 0) {
                            subjectstarttime = formatToFloat(list[a].starttime) * 4
                            presubjectendtimeortablestarttime = formatToFloat(timeTable.startTime) * 4
                        } else {
                            subjectstarttime = formatToFloat(list[a].starttime) * 4
                            presubjectendtimeortablestarttime = formatToFloat((list[a - 1].endtime)) * 4
                        }
                        DrawDummy(linearLayout, subjectstarttime - presubjectendtimeortablestarttime)
                        DrawSubject(linearLayout, list[a])
                    }
                }
                2 -> {
                    list = list2
                    list.filter { it.isSample }

                    list.sortBy { formatToFloat(it.starttime) }
                    for (a in 0 until list.size) {
                        if (a == 0) {
                            subjectstarttime = formatToFloat(list[a].starttime) * 4
                            presubjectendtimeortablestarttime = formatToFloat(timeTable.startTime) * 4
                        } else {
                            subjectstarttime = formatToFloat(list[a].starttime) * 4
                            presubjectendtimeortablestarttime = formatToFloat((list[a - 1].endtime)) * 4
                        }
                        DrawDummy(linearLayout, subjectstarttime - presubjectendtimeortablestarttime)
                        DrawSubject(linearLayout, list[a])
                    }
                }
                3 -> {
                    list = list3
                    list.filter { it.isSample }

                    list.sortBy { formatToFloat(it.starttime) }
                    for (a in 0 until list.size) {
                        if (a == 0) {
                            subjectstarttime = formatToFloat(list[a].starttime) * 4
                            presubjectendtimeortablestarttime = formatToFloat(timeTable.startTime) * 4
                        } else {
                            subjectstarttime = formatToFloat(list[a].starttime) * 4
                            presubjectendtimeortablestarttime = formatToFloat((list[a - 1].endtime)) * 4
                        }
                        DrawDummy(linearLayout, subjectstarttime - presubjectendtimeortablestarttime)
                        DrawSubject(linearLayout, list[a])
                    }
                }
                4 -> {
                    list = list4
                    list.filter { it.isSample }

                    list.sortBy { formatToFloat(it.starttime) }
                    for (a in 0 until list.size) {
                        if (a == 0) {
                            subjectstarttime = formatToFloat(list[a].starttime) * 4
                            presubjectendtimeortablestarttime = formatToFloat(timeTable.startTime) * 4
                        } else {
                            subjectstarttime = formatToFloat(list[a].starttime) * 4
                            presubjectendtimeortablestarttime = formatToFloat((list[a - 1].endtime)) * 4
                        }
                        DrawDummy(linearLayout, subjectstarttime - presubjectendtimeortablestarttime)
                        DrawSubject(linearLayout, list[a])
                    }
                }
            }
        }


    }


    private fun DrawDummy(linearLayout: LinearLayout, i: Float) {
        if (i > 0) {
            val view = layoutInflater.inflate(R.layout.cell_subject, linearLayout, false) as LinearLayout
            view.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, i)
            linearLayout.addView(view)
        }
    }

    private fun devideSubjects(linearLayout: LinearLayout) {
        val view = layoutInflater.inflate(R.layout.cell_subject, linearLayout, false) as LinearLayout
        view.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 0.25.toFloat())
        linearLayout.addView(view)
    }

    fun DrawSubject(linearLayout: LinearLayout, subject: Subject) {

        if (subject.isSample) {
            devideSubjects(linearLayout)
//        cell = 0일때 오류
            val celllayout = layoutInflater.inflate(R.layout.cell_subject, linearLayout, false) as LinearLayout
            celllayout.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0,
                    4 * (formatToFloat(subject.endtime) - formatToFloat(subject.starttime)) - 0.5f)

//        TODO 여기서 color를 int값에 따라 골라서 넣어주기!!
            celllayout.setBackgroundResource(R.drawable.bg_round_border_subject_sample)

//        TODO 여기 온클릭
            celllayout.setOnClickListener {
                onClick?.onClick(subject)
            }

            linearLayout.addView(celllayout)
            devideSubjects(linearLayout)


        } else {
            devideSubjects(linearLayout)
//        cell = 0일때 오류
            val celllayout = layoutInflater.inflate(R.layout.cell_subject, linearLayout, false) as LinearLayout
            celllayout.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0,
                    4 * (formatToFloat(subject.endtime) - formatToFloat(subject.starttime)) - 0.5f)

//        TODO 여기서 color를 int값에 따라 골라서 넣어주기!!
//            celllayout.setBackgroundColor(Color.parseColor("#de91ff"))

            celllayout.setBackgroundResource(getColors(subject.color))
            celllayout.findViewById<TextView>(R.id.tv_cell_subject).text = subject.name
            celllayout.findViewById<TextView>(R.id.tv_cell_custom).apply {
                text = subject.place
            }

//        TODO 여기 온클릭
            celllayout.setOnClickListener {
                onClick?.onClick(subject)
            }

            linearLayout.addView(celllayout)
            devideSubjects(linearLayout)
        }
    }


    fun drawVerticalLine(linearLayout: LinearLayout) {

        val verticallines = layoutInflater.inflate(R.layout.timetable_verticallayout, linearLayout, false) as LinearLayout
        verticallines.weightSum = 4 * (endhour - starthour).toFloat()

//      가로줄 투명하게 끊어서!
//      위에만 따로 없애주기!! 지금 vertical로 그대로 쌓는데 지금 matchparent에서 dayheight만큼 짤라서 적용!
        layoutInflater.inflate(R.layout.timetable_verticalline_index, verticallines, true)

        for (i in starthour..endhour) {
            val verticalline = layoutInflater.inflate(R.layout.cell_timeindex, verticallines, false) as TextView
            verticalline.setBackgroundResource(R.drawable.bg_vertical_bottom_line)
            verticallines.addView(verticalline)
        }
        linearLayout.addView(verticallines)
        val mGlobalListner = ViewTreeObserver.OnGlobalLayoutListener {
            if (verticallines.measuredWidth > 0.0) {
                columnwidth = verticallines.width.toFloat()
            }
        }
        verticallines.viewTreeObserver.addOnGlobalLayoutListener(mGlobalListner)

    }


    fun dptopx(dp: Int): Float {
        val metrics = context.resources.displayMetrics
        return dp * ((metrics.densityDpi.toFloat()) / DisplayMetrics.DENSITY_DEFAULT)
    }


    fun formatToFloat(time: String): Float {
        val timesplit = time.split(":")
        return timesplit[0].toFloat() + (timesplit[1].toFloat() - timesplit[1].toFloat() % 15) / 60
    }


    fun getColors(type: Int): Int {

        return when (type) {
            1 -> R.drawable.bg_round_border_subject_color_1
            2 -> R.drawable.bg_round_border_subject_color_2
            3 -> R.drawable.bg_round_border_subject_color_3
            4 -> R.drawable.bg_round_border_subject_color_4
            5 -> R.drawable.bg_round_border_subject_color_5
            6 -> R.drawable.bg_round_border_subject_color_6
            7 -> R.drawable.bg_round_border_subject_color_7
            8 -> R.drawable.bg_round_border_subject_color_8
            9 -> R.drawable.bg_round_border_subject_color_9
            10 -> R.drawable.bg_round_border_subject_color_10
            else -> R.drawable.bg_round_border_subject
        }
    }
}