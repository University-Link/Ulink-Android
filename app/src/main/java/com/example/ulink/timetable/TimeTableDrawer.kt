package com.example.ulink.timetable

import android.content.Context
import android.util.DisplayMetrics
import android.util.Log
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

const val MIN_CELL_HEIGHT = 60.0f

class TimeTableDrawer(val context: Context, val layoutInflater: LayoutInflater) {

    constructor(context: Context, layoutInflater: LayoutInflater, onClick: TimeTableFragment.subjectOnClick) : this(context, layoutInflater) {
        this.onClick = onClick
    }

    constructor(context: Context, layoutInflater: LayoutInflater, timeTable: TimeTable) : this(context, layoutInflater) {
        this.timeTable = timeTable
    }


    lateinit var onClick: TimeTableFragment.subjectOnClick

    lateinit var timeTable: TimeTable

    val endhour = 18
    val starthour = 9

    var timecolumnwidth = 0.0f
    var dayheight = 0.0f
    var columnwidth = 0.0f
    var rowheight = 0.0f


    fun draw(frameLayout: FrameLayout) {
        val root = frameLayout.findViewById<LinearLayout>(R.id.timetable_root)

        root.removeAllViews()
        drawHorizontalLine(root)
        drawTimeColumn(root)
        drawColums(root)
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
                    Log.d("tag", "rowheight = $i  ==  " + rowheight.toString())
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

        timecolumn.findViewById<TextView>(R.id.tv_header)
                .setBackgroundResource(R.drawable.text_line_bottom_timecolumn)

//      TODO 여기 바꾸기!!!!

        for (i in starthour..endhour) {
            val timeColumnEa =
                    layoutInflater.inflate(R.layout.cell_timeindex, timecolumn, false) as TextView

            timeColumnEa.minHeight =
                    TypedValue.applyDimension(1, MIN_CELL_HEIGHT, context.resources.displayMetrics)
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
                Log.d("tag", "timecolumnwidth =  " + timecolumnwidth.toString())
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
//          이거 해줘야 나중에 과목(cell time) weight가 시간표 비율에 맞춰짐
            linearlayoutfor.weightSum = 4 * (endhour - starthour).toFloat()

            val mGlobalListner = ViewTreeObserver.OnGlobalLayoutListener {
                if (linearlayoutfor.measuredWidth > 0.0) {
                    columnwidth = linearlayoutfor.width.toFloat()
                    Log.d("tag", "columnwidth $i =  " + columnwidth.toString())
                }
            }
            linearlayoutfor.viewTreeObserver.addOnGlobalLayoutListener(mGlobalListner)


//            val linearlayoutpreviewfor =
//                dayRow.findViewById<LinearLayout>(R.id.day_column_root_preview)
//

            val daylist = arrayListOf("월", "화", "수", "목", "금", "토", "일")
            val day = linearlayoutfor.findViewById<TextView>(R.id.tv_header)
            day.text = daylist[i]
            day.setBackgroundResource(R.drawable.bottom_line)

            val mGlobalListner2 = ViewTreeObserver.OnGlobalLayoutListener {
                if (day.measuredHeight > 0.0) {
                    dayheight = day.measuredHeight.toFloat()
                    Log.d("tag", "dayheight =  " + day.measuredHeight.toString())
                }
            }
            day.viewTreeObserver.addOnGlobalLayoutListener(mGlobalListner2)

            drawColumn(linearlayoutfor, i)

            linearLayout.addView(dayRow)
            if (i < 4) {
                drawVerticalLine(linearLayout)
            }
        }
    }

    fun drawColumn(linearLayout: LinearLayout, i: Int) {

        val subjectsize = timeTable.subjectList.size
        var subjectstarttime = 0f
        var presubjectendtimeortablestarttime = 0f

//        i가 월화수목금
//        subjectsize가 20이라 치면 a는 20번돔, i는 5번 day가 같은애들만 그 날 그림

        subjectsize.let {
            for (a in 0 until it) {
//            a에 따라 그날 subject list에서 subjectlist[a]
                val daylist = arrayListOf("mon", "tue", "wed", "thu", "fri", "sat", "sun")
                if (timeTable.subjectList[a].day == daylist[i]) {

                    if (a == 0) {
                        subjectstarttime = formatToFloat(timeTable.subjectList[a].starttime) * 4
                        presubjectendtimeortablestarttime = formatToFloat(timeTable.startTime) * 4
                    } else {
                        subjectstarttime = formatToFloat(timeTable.subjectList[a].starttime) * 4
                        presubjectendtimeortablestarttime = formatToFloat((timeTable.subjectList[a - 1].endtime)) * 4
                    }

                    DrawDummy(linearLayout, subjectstarttime - presubjectendtimeortablestarttime)
                    DrawSubject(linearLayout, timeTable.subjectList[a])
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

        devideSubjects(linearLayout)
//        cell = 0일때 오류
        val celllayout = layoutInflater.inflate( R.layout.cell_subject, linearLayout, false) as LinearLayout
        celllayout.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 7.5F)

//        TODO 여기서 color를 int값에 따라 골라서 넣어주기!!
        celllayout.setBackgroundResource(R.drawable.bg_round_border_subject)
        celllayout.findViewById<TextView>(R.id.tv_cell_subject).text = subject.name
        celllayout.findViewById<TextView>(R.id.tv_cell_custom).apply {
            text = subject.place
        }

//        TODO 여기 온클릭
        celllayout.setOnClickListener {
            onClick.onClick()
        }
        linearLayout.addView(celllayout)
        devideSubjects(linearLayout)
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
    }


    fun dptopx(dp: Int): Float {
        val metrics = context.resources.displayMetrics
        return dp * ((metrics.densityDpi.toFloat()) / DisplayMetrics.DENSITY_DEFAULT)
    }


    fun formatToFloat(time: String): Float {
        val timesplit = time.split(":")
        return timesplit[0].toFloat() + (timesplit[1].toFloat() - timesplit[1].toFloat() % 15) / 60
    }

}