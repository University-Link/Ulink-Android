package com.example.ulink.fragment

import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.widget.NestedScrollView
import com.example.ulink.R
import java.text.SimpleDateFormat
import java.util.*

const val MIN_CELL_HEIGHT = 60.0f

class TimeTableFragment : Fragment() {


    val endhour = 18
    val starthour = 9

    var timecolumnwidth : Float = 0.0f
    var dayheight = 0.0f
    var columnwidth = 0.0f
    var rowheight = 0.0f

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

//        fragment의 timetable
        return inflater.inflate(R.layout.fragment_time_table, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val timetableview = view.findViewById<NestedScrollView>(R.id.sv_timetable)

        val root = timetableview.findViewById<LinearLayout>(R.id.timetable_root)

        root.removeAllViews()
        drawBackground(root)
        drawHorizontalLine(root)
        drawTimeColumn(root)
        drawColums(root)
    }

    fun getData() : ArrayList<Float> {
        return arrayListOf(timecolumnwidth, dayheight,  columnwidth, rowheight)
    }



    fun drawBackground(linearLayout: LinearLayout) {
        ((linearLayout.parent as View).parent as View).findViewById<RelativeLayout>(R.id.timetable_round_border)
                .setBackgroundResource(R.drawable.bg_round_border_line)
//        view1.setBackgroundColor()
    }

    fun drawHorizontalLine(linearLayout: LinearLayout) {

        val linearLayoutWhole =
                (linearLayout.parent as View).findViewById<LinearLayout>(R.id.timetable_grid_horizontal)
//      밑에서 whole에다가 row추가해줄거니까 미리 지워야함
        linearLayoutWhole.removeAllViews()

        val linearLayoutRow = layoutInflater.inflate(
                R.layout.day_column_for_horizontal_grid,
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
            row.setBackgroundResource(R.drawable.text_line_bottom_default)
//          이걸로 최소 줄 크기 조절!
            row.minHeight =
                    TypedValue.applyDimension(1, MIN_CELL_HEIGHT, requireContext().resources.displayMetrics)
                            .toInt()
            linearLayoutRow.addView(row)


            val mGlobalListner = ViewTreeObserver.OnGlobalLayoutListener {
                if(row.measuredHeight>0.0){
                    rowheight = row.measuredHeight.toFloat()
                    Log.d("tag","rowheight = $i  ==  "+rowheight.toString())
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
                .setBackgroundResource(R.drawable.text_line_bottom_default)

        for (i in starthour..endhour) {
            val timeColumnEa =
                    layoutInflater.inflate(R.layout.cell_timeindex, timecolumn, false) as TextView
            timeColumnEa.minHeight =
                    TypedValue.applyDimension(1, MIN_CELL_HEIGHT, requireContext().resources.displayMetrics)
                            .toInt()
            instance.set(Calendar.HOUR_OF_DAY, i)
            timeColumnEa.gravity = Gravity.RIGHT
            timeColumnEa.setPadding(0,1,1,0)
            timeColumnEa.text = simpleDateFormat.format(instance.time)
            timeColumnEa.setTextSize(1, 11.0f)
            timecolumn.addView(timeColumnEa)
        }
        linearLayout.addView(timecolumn)
        drawVerticalLine(linearLayout)

        val mGlobalListner = ViewTreeObserver.OnGlobalLayoutListener {
            if(timecolumn.measuredWidth>0.0){
                timecolumnwidth = timecolumn.measuredWidth.toFloat()
                Log.d("tag","timecolumnwidth =  "+ timecolumnwidth.toString())
            }
        }
        timecolumn.viewTreeObserver.addOnGlobalLayoutListener(mGlobalListner)

    }

    fun drawColums(linearLayout: LinearLayout) {
        val dayOfWeek = 5

        val instance = Calendar.getInstance()
        for (i in 0 until dayOfWeek) {

//          맨위에 월화수목금
            val dayRow =
                    layoutInflater.inflate(R.layout.day_column, linearLayout, false) as FrameLayout

            val linearlayoutfor = dayRow.findViewById<LinearLayout>(R.id.day_column_root)
//          이거 해줘야 나중에 과목(cell time) weight가 시간표 비율에 맞춰짐
            linearlayoutfor.weightSum = 4 * (endhour - starthour).toFloat()

            val mGlobalListner = ViewTreeObserver.OnGlobalLayoutListener {
                if(linearlayoutfor.measuredWidth>0.0){
                    columnwidth = linearlayoutfor.width.toFloat()
                    Log.d("tag","columnwidth $i =  "+ columnwidth.toString())
                }
            }
            linearlayoutfor.viewTreeObserver.addOnGlobalLayoutListener(mGlobalListner)


//            val linearlayoutpreviewfor =
//                dayRow.findViewById<LinearLayout>(R.id.day_column_root_preview)
//

            val day = linearlayoutfor.findViewById<TextView>(R.id.tv_header)
            instance.set(Calendar.DAY_OF_WEEK, (i + 2) % 7)
            day.text = "금"
            day.setTextSize(1, 11.0f)
            day.setBackgroundResource(R.drawable.text_line_bottom_default)
            day.setTextSize(1, 11.0f)

            val mGlobalListner2 = ViewTreeObserver.OnGlobalLayoutListener {
                if(day.measuredHeight>0.0){
                    dayheight = day.measuredHeight.toFloat()
                    Log.d("tag","dayheight =  "+ day.measuredHeight.toString())
                }
            }
            day.viewTreeObserver.addOnGlobalLayoutListener(mGlobalListner2)



            drawColumn1(linearlayoutfor, i)
//            drawColumn2(linearlayoutpreviewfor, i)

            linearLayout.addView(dayRow)
            if (i < 4) {
                drawVerticalLine(linearLayout)
            }
        }
    }

    fun drawColumn1(linearLayout: LinearLayout, i: Int) {
//      하루에 3과목
        val subjectsize = 2
        var subjectstarttime = 0
        var presubjectendtimeortablestarttime = 0
        for (a in 0 until subjectsize) {
//            a에 따라 그날 subject list에서 subjectlist[a]
            if (a == 0) {
                subjectstarttime = 12 * 9
                presubjectendtimeortablestarttime = 12 * 9
            } else {

                subjectstarttime = (12 * (11))  // + Random().nextInt(10)*5)
                presubjectendtimeortablestarttime = 12 * 11
            }
//         Subject 사이의 시간에 투명한 더미로 채워줌!
            DrawDummy(linearLayout, subjectstarttime - presubjectendtimeortablestarttime)
////          subject의 시간은 두시간 고정하자!
            DrawSubject(linearLayout)

        }

    }

    //    drawdummy로 과목 사이사이에 빈칸 그려줘야하는데 그 크기는 다음과목시작시간과 전과목 끝시간 사이
    fun DrawDummy(linearLayout: LinearLayout, i :Int) {
        if (i > 0) {
            val dummycell = R.layout.cell_time_24
            val view = layoutInflater.inflate(dummycell,linearLayout, false) as LinearLayout
            view.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, i.toFloat())
            linearLayout.addView(view)
        }
    }

    fun DrawDummybtw(linearLayout: LinearLayout) {
        val dummycell = R.layout.cell_time_24
        val view = layoutInflater.inflate(dummycell,linearLayout, false) as LinearLayout
        view.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 0.25.toFloat())
        linearLayout.addView(view)
    }

    fun DrawSubject(linearLayout: LinearLayout) {
        val subjectcell = R.layout.cell_time_24

        DrawDummybtw(linearLayout)

//        cell = 0일때 오류
        val celllayout = layoutInflater.inflate(subjectcell, linearLayout, false) as LinearLayout

        celllayout.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0,7.5F)

        celllayout.setBackgroundResource(R.drawable.bg_round_border2)

        val celltvsubject = celllayout.findViewById<TextView>(R.id.tv_cell_subject)
        val celltvcustom = celllayout.findViewById<TextView>(R.id.tv_cell_custom)

        val contenttype = 1
        val str = "장소"
        val textsize = 13.0f

        celltvsubject.text = "과목명"
        celltvsubject.setTextSize(1, textsize)

        celltvcustom.text = str
        celltvcustom.setTextSize(1, textsize - 2.0f)
        linearLayout.addView(celllayout)

        DrawDummybtw(linearLayout)

    }

    fun drawVerticalLine(linearLayout: LinearLayout) {
        layoutInflater.inflate(R.layout.timetable_verticalline, linearLayout, true)
    }


}