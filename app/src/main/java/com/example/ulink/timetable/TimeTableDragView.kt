package com.example.ulink.timetable

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.DisplayMetrics
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.view.GestureDetectorCompat
import com.example.ulink.repository.Subject
import java.util.*
import kotlin.math.floor
import kotlin.math.roundToInt

class TimeTableDragView(context: Context, root: View, val timecolumnWidth: Float?, val dayHeight: Float?, val columnWidth: Float?, val rowHeight: Float?, val columnGap: Float?) : View(context), GestureDetector.OnGestureListener {

    val days = 5

    val starthour = 9
    val endhour = 21

    var realstartx = x
    var realstarty = y

    var pointx = x
    var pointy = y
    var islongpressed = false
    var visible = false
    var draw: RectF = RectF(0f, 0f, 0f, 0f)
    val paint = Paint()

    var nomore = false


    var drawlist: MutableList<RectF> = arrayListOf()
    var perviewlist: MutableList<RectF> = arrayListOf()

    private lateinit var mGestureDetector: GestureDetectorCompat

    init {
        mGestureDetector = GestureDetectorCompat(context, this)
    }
    lateinit var onDrawListener: onDrawListener

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (visible) {
            paint.setColor(Color.parseColor("#4d000000"))
            val xbase = timecolumnWidth!!
            val ybase = dayHeight!!
            val xindex = columnWidth!!
            val yindex = rowHeight!! / 4

            var xfix = ((realstartx - xbase) / xindex).toInt()
            var yfix = ((realstarty) / (rowHeight)).toInt()

            realstartx = realstartx - (realstartx - xbase) % xindex

            if (xfix == days){
                realstartx = realstartx - (realstartx - xbase) % xindex - xindex
                xfix = days -1
            }

            realstartx += (xfix + 1) * columnGap!!

            Log.d("tag",realstartx.toString())


            if (realstarty - yindex > 0) {
                realstarty = realstarty - (realstarty) % yindex
            } else {
                realstarty = realstarty - (realstarty) % yindex
            }


            pointy = (realstarty + (1 + floor(((pointy - realstarty)) / yindex)) * yindex)

            draw.left = realstartx
            draw.top = realstarty - yfix
            draw.right = realstartx + xindex
            draw.bottom = pointy + yindex - yfix

            for (i in 0 until drawlist.size) {
                if (drawlist.size == 0) {
                    break
                }
                canvas?.drawRoundRect(drawlist[i], dptopx(8), dptopx(8), paint)
            }

            if (drawlist.size > 0) {
                if (drawlist[drawlist.size - 1].contains(draw)) {
                    return
                } else if (draw.contains(drawlist[drawlist.size - 1])) {
                    return
                }
            }

            if (nomore) {
                return
            }

            canvas?.drawRoundRect(draw, dptopx(8), dptopx(8), paint)
        }
    }

    @ExperimentalStdlibApi
    fun rollBack() {

        if (drawlist.size > 0) {
            drawlist.removeAt(drawlist.size - 1)
            invalidate()
        }
    }

    fun convertDrawToSubject(draw: RectF): Subject {

        var dayIndex = 0

        for (i in 0 until days) {
            if (draw.centerX() < timecolumnWidth!! + columnGap!! * (i + 1) + columnWidth!! * (i + 1)) {
                dayIndex += 1
            }
        }

        dayIndex = days - dayIndex

//        for ()
//        월화수목금.. 리스트에서 index를 나타냄!

        val yindex = rowHeight!! / 4

        var top = 0
        var bottom = 0

        if (draw.top%yindex > yindex/2){
            top = ((draw.top - draw.top % yindex + yindex)/yindex).roundToInt()
        } else{
            top = ((draw.top - draw.top % yindex)/yindex).roundToInt()
        }

        if(draw.bottom%yindex>yindex/2){
            bottom =  ((draw.bottom - draw.bottom % yindex + yindex)/yindex).roundToInt()
        } else {
            bottom =  ((draw.bottom - draw.bottom % yindex)/yindex).roundToInt()
        }

        val topHour = starthour + top * 0.25f
        val bottomHour = starthour + bottom*0.25f
        val daylist = arrayListOf(0,1,2,3,4,5,6)

        val subject = Subject(1, "과목이름", listOf(formatToString(topHour)), listOf(formatToString(bottomHour)), listOf(daylist[dayIndex]), listOf("과목장소"), 1, true)

        return subject
    }

    fun formatToString(time : Float) : String{
        val hour = time - time%1
        val min = time%1 * 60
        return "${hour.toInt()}:${min.toInt()}"
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {

        if (islongpressed) {
            parent.requestDisallowInterceptTouchEvent(true)
        }

        if (mGestureDetector.onTouchEvent(event)) {
            return true
        } else {
            when (event?.action) {
                MotionEvent.ACTION_MOVE -> {
                    if (islongpressed) {
                        pointx = event.x
                        pointy = event.y
                        invalidate()
                    }
                }
                MotionEvent.ACTION_UP -> {
                    val draw2 = RectF(draw.left, draw.top, draw.right, draw.bottom)
                    drawlist.add(draw2)
                    nomore = true
                    onDrawListener.onDrawed(drawlist.size)
                }
            }
        }
        return true
    }

    override fun onShowPress(e: MotionEvent?) {
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        return true
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onDown(e: MotionEvent?): Boolean {
        visible = false
        islongpressed = false
        return true
    }

    override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent?,
            velocityX: Float,
            velocityY: Float
    ): Boolean {
        return true
    }

    override fun onScroll(
            e1: MotionEvent?,
            e2: MotionEvent?,
            distanceX: Float,
            distanceY: Float
    ): Boolean {
        return true
    }

    override fun onLongPress(e: MotionEvent?) {
        visible = true
        islongpressed = true
        nomore = false

//        TODO 여기 버전에 따라서 다르게 구성!! 이게 오레오 이상
        (context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator).vibrate(VibrationEffect.createOneShot(100, 30))

        realstartx = e?.x!!
        realstarty = e.y
        pointx = e.x
        pointy = e.y
        invalidate()
    }


    fun dptopx(dp: Int): Float {
        val metrics = resources.displayMetrics
        return dp * ((metrics.densityDpi.toFloat()) / DisplayMetrics.DENSITY_DEFAULT)
    }
}