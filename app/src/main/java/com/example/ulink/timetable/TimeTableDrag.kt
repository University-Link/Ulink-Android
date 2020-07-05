package com.example.ulink.timetable

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.core.view.GestureDetectorCompat
import kotlin.math.floor

class TimeTableDrag(context: Context, root: View, val timecolumnWidth: Float?, val dayHeight: Float?, val columnWidth: Float?, val rowHeight : Float?) : View(context), GestureDetector.OnGestureListener{

    var realstartx = x
    var realstarty = y

    var pointx = x
    var pointy = y
    var islongpressed = false
    var visible = false

    var list : ArrayList<Float?> = arrayListOf()



    private lateinit var mGestureDetector: GestureDetectorCompat

    init {
        mGestureDetector = GestureDetectorCompat(context, this)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (visible){
            val paint = Paint()
            paint.setColor(Color.RED)
            val xbase = timecolumnWidth!!
            val ybase = dayHeight!!
            val xindex = columnWidth!!
            val yindex = rowHeight!!/4

            var xfix = ((realstartx-xbase)/xindex).toInt()
            var yfix = ((realstarty-ybase)/(rowHeight)).toInt()

            realstartx = realstartx - (realstartx-xbase)%xindex
            realstartx += xfix*2+2

            realstarty = realstarty - (realstarty-ybase)%yindex

            pointy = (realstarty + (1+ floor(((pointy-realstarty)) /yindex))*yindex)

            canvas?.drawRect(realstartx, realstarty-yfix, realstartx+xindex, pointy+yindex-yfix, paint)

        }


//      정해진 범위안에서 ******
//      양옆 크기는 고정이고 위아래로 사이즈만 달라지게 즉 x좌표랑은 상관 ㄴㄴ
//        나중에 내렸을때는 위에 offset이 있을거 +로!! 이거 무시하기 #4 참고
//        그냥 x,y는 현재 뷰 위치
//        event.x가 터치 위치 이게 그냥 스크롤뷰 포함해서 전체 위치 정해줌
//        스크롤 크기 구하기 -> 내가 터치한 시간대 구하기 -> 거기서부터 그려서 끝나는 시간대 구하기 -> 여기까지 canvas draw로 보여주기 -> motionevent.actionup하면
//        바로 timetablefragment에서 해당 시간대에 subjcet넣어주기! -> 아까 그린거 없애기

//        #1 정해진 범위에서 구하려면 left right 를 고정시켜줘야함 이것도 width 받아서 x범위 짤라주기
//        x
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        parent.requestDisallowInterceptTouchEvent(true)

        if (mGestureDetector.onTouchEvent(event)){
            return true
        } else {
            when (event?.action) {
                MotionEvent.ACTION_MOVE -> {
                    if (islongpressed){
                        pointx = event.x
                        pointy = event.y
                        invalidate()
                    }
                }
                MotionEvent.ACTION_UP-> {
//                    여기서 Activity 열기
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

    override fun onDown(e: MotionEvent?): Boolean {
        visible = false
        islongpressed = false
        Log.d("tag", "false")
        invalidate()
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


//        TODO 여기 버전에 따라서 다르게 구성!! 이게 오레오 이상
        (context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator).vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE))

        realstartx = e?.x!!
        realstarty = e.y
        pointx = e.x
        pointy = e.y
        invalidate()
    }
}