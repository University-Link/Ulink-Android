package com.ulink.ulink.timetable

import android.content.Context
import android.util.DisplayMetrics
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.*
import com.ulink.ulink.R
import com.ulink.ulink.fragment.TimeTableFragment
import com.ulink.ulink.getColors
import com.ulink.ulink.repository.Subject
import com.ulink.ulink.repository.TimeTable
import java.text.SimpleDateFormat
import java.util.*


class TimeTableDrawerWidget(val context: Context, val layoutInflater: LayoutInflater) {

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

    var timeTable = TimeTable(0, "", "", 0, "", "")

    var endhour = 18
    var starthour = 9

    var timecolumnwidth = 0.0f
    var dayheight = 0.0f
    var columnwidth = 0.0f
    var rowheight = 0.0f


    fun setMinMax(){
        for (sub in timeTable.subjectList){
            for(i in sub.startTime){
                if ( formatToFloat(i) < starthour) {
                    starthour = formatToFloat(i).toInt()
                }
            }
            for (k in sub.endTime){
                if ( formatToFloat(k) > endhour){
                    endhour = formatToFloat(k).toInt() + 1
                }
            }
        }
    }


    fun drawForWidget(remoteViews : RemoteViews){

        remoteViews.removeAllViews(R.id.timetable_root)
        remoteViews.removeAllViews(R.layout.timetable_day_row)

        drawDayRow(remoteViews)
        drawHorizontalLine(remoteViews)
        drawTimeColumn(remoteViews)
        drawColums(remoteViews)
    }

    fun drawDayRow(remoteViews: RemoteViews) {
        val dayOfWeek = 5
        val daylist = arrayListOf("월", "화", "수", "목", "금", "토", "일")

        val rvIndex = RemoteViews(context.packageName, R.layout.cell_top_left_index_widget)
        remoteViews.addView(R.id.layout_dayrow, rvIndex)

        val rvBottomLine = RemoteViews(context.packageName, R.layout.timetable_horizontalline)
        rvBottomLine.setInt(R.id.layout_timetable_horizontalline, "setBackgroundColor", R.color.graybase )


        for (i in 0 until dayOfWeek) {
            val rvDay = RemoteViews(context.packageName, R.layout.cell_day_widget)
            rvDay.setTextViewText(R.id.tv_cellday, daylist[i])
            rvDay.setTextViewTextSize(R.id.tv_cellday, 1, 12.0f)

            rvDay.setInt(R.id.tv_cellday, "setBackgroundResource", R.drawable.bottom_line)
//              rvDay.setInt(R.id.tv_cellday, "setBackgroundColor", R.color.white)
            rvDay.addView(R.id.tv_cellday, rvBottomLine)
            remoteViews.addView(R.id.layout_dayrow, rvDay)
        }

    }

    fun drawHorizontalLine(remoteViews: RemoteViews) {

        remoteViews.removeAllViews(R.id.timetable_grid)

        val rvHorizontalRow =RemoteViews(context.packageName, R.layout.timetable_grid)
        rvHorizontalRow.setFloat(R.id.layout_timetable_grid, "setWeightSum", 4 * (endhour - starthour).toFloat())

        for (i in starthour..endhour) {
            val row = RemoteViews(context.packageName, R.layout.cell_timeindex)
            row.setInt(R.id.tv_cell, "setBackgroundResource", R.drawable.bottom_line)
            rvHorizontalRow.addView(R.id.layout_timetable_grid, row)
        }

        remoteViews.addView(R.id.timetable_grid, rvHorizontalRow)
    }


    fun drawTimeColumn(remoteViews: RemoteViews) {

        val rvTimeColumn = RemoteViews(context.packageName, R.layout.day_column_for_time)
        rvTimeColumn.setFloat(R.id.day_column_root, "setWeightSum",4 * (endhour - starthour).toFloat() )

        val simpleDateFormat = SimpleDateFormat("h")
        val instance = Calendar.getInstance()

        for (i in starthour..endhour) {
            val rvTimeColumnEa = RemoteViews(context.packageName, R.layout.cell_timeindex_widget)
            rvTimeColumnEa.setInt(R.id.layout_tv_cell_widget, "setGravity", Gravity.CENTER)
            instance.set(Calendar.HOUR_OF_DAY, i)
            rvTimeColumnEa.setTextViewTextSize(R.id.tv_cell_widget, 1, 12.0f)
            rvTimeColumnEa.setTextViewText(R.id.tv_cell_widget, simpleDateFormat.format(instance.time))
            rvTimeColumn.addView(R.id.day_column_root, rvTimeColumnEa)
        }
        remoteViews.addView(R.id.timetable_root, rvTimeColumn)
    }

    fun drawColums(remoteViews: RemoteViews) {
        val dayOfWeek = 5

        drawVerticalLine(remoteViews)

        for (i in 0 until dayOfWeek) {
//          맨위에 월화수목금
            val rvLinearLayout = RemoteViews(context.packageName, R.layout.day_column_widget)

            rvLinearLayout.setFloat(R.id.day_column_root, "setWeightSum", 4 * (endhour - starthour).toFloat())

            drawColumn(rvLinearLayout, i)

            remoteViews.addView(R.id.timetable_root, rvLinearLayout)
            if (i < 4) {
                drawVerticalLine(remoteViews)
            }
        }
    }

    fun drawColumn(remoteViews: RemoteViews, i: Int) {

        var subjectstarttime = 0f
        var presubjectendtimeortablestarttime = 0f

        var subjectList : MutableList<Subject> = arrayListOf()

        for (k in timeTable.subjectList){
            for(m in 0 until k.day.size){
                if (k.day[m] == i){
                    val b = Subject(k.id,k.name, listOf(k.startTime[m]), listOf(k.endTime[m]), listOf(k.day[m]), listOf(k.place[m]), k.color,k.subject,k.credit,k.professor,k.course,k.isSample,"",k.subjectIdx)
                    subjectList.add(b)
                }
            }
        }

        subjectList.retainAll { !it.isSample }
        subjectList.sortBy {formatToFloat(it.startTime[0])}
        for (a in 0 until subjectList.size) {
            if (a == 0) {
                subjectstarttime = formatToFloat(subjectList[a].startTime[0]) * 4
                presubjectendtimeortablestarttime = (starthour * 4).toFloat()
            } else {
                subjectstarttime = formatToFloat(subjectList[a].startTime[0]) * 4
                presubjectendtimeortablestarttime = formatToFloat((subjectList[a - 1].endTime[0])) * 4
            }

            DrawDummy(remoteViews, subjectstarttime - presubjectendtimeortablestarttime)
            DrawSubject(remoteViews, subjectList[a])
        }
    }

    private fun DrawDummy(remoteViews: RemoteViews, i: Float) {
        if (i > 0) {

            val rvDummy = RemoteViews(context.packageName, R.layout.cell_subject_widget)
            val view = rvDummy.apply(context, null)
            view.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, i)

            rvDummy.reapply(context, view)
            remoteViews.addView(R.id.day_column_root, rvDummy)

        }
    }

    private fun devideSubjects(remoteViews: RemoteViews) {

        val rvView = RemoteViews(context.packageName, R.layout.cell_subject_widget)
        val view = remoteViews.apply(context, null)

        Log.d("tag widget", view.toString())

//        val cell = view.findViewById<TextView>(R.id.tv_cell_subject)
//        cell.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 0.25.toFloat())
//        remoteViews.addView(R.id.timetable_root, rvView)
    }

    fun DrawSubject(remoteViews: RemoteViews, subject: Subject) {

        devideSubjects(remoteViews)
        val rvCellLayout = RemoteViews(context.packageName, R.layout.cell_subject_widget)
        val view = rvCellLayout.apply(context, null)
        val cellLayout = view.findViewById<LinearLayout>(R.id.tv_cell)

        cellLayout.layoutParams  = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0,
        4 * (formatToFloat(subject.endTime[0]) - formatToFloat(subject.startTime[0])) - 0.5f)
        rvCellLayout.setInt(R.id.tv_cell, "setBackgroundResource", getColors(subject.color))
        rvCellLayout.setTextViewText(R.id.tv_cell_subject, subject.name)
        rvCellLayout.setTextViewText(R.id.tv_cell_custom, subject.place[0])

        remoteViews.addView(R.id.day_column_root, rvCellLayout)
        devideSubjects(remoteViews)
    }


    fun drawVerticalLine(remoteViews: RemoteViews) {

        val rvVerticalLines = RemoteViews(context.packageName, R.layout.timetable_verticallayout)
        rvVerticalLines.setFloat(R.id.layout_line_vertical, "setWeightSum", 4 * (endhour - starthour).toFloat())

        for (i in starthour..endhour) {
            val rvVerticalLine = RemoteViews(context.packageName, R.layout.cell_timeindex)
            rvVerticalLine.setInt(R.id.tv_cell, "setBackgroundResource", R.drawable.bg_vertical_bottom_line)

            rvVerticalLines.addView(R.id.layout_line_vertical, rvVerticalLine)
        }

        remoteViews.addView(R.id.timetable_root, rvVerticalLines)
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