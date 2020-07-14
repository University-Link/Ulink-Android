package com.example.ulink.timetable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.core.widget.NestedScrollView
import com.example.ulink.R
import com.example.ulink.repository.DataRepository
import com.example.ulink.repository.RequestAddPersonalPlan
import com.example.ulink.repository.Subject
import com.example.ulink.repository.TimeTable
import com.example.ulink.utils.deepCopy
import kotlinx.android.synthetic.main.fragment_time_table.*
import java.util.ArrayList

interface onDrawListener{
    fun onDrawed(size : Int)
}

class TimeTableDirectEditActivity : AppCompatActivity(), onDrawListener {


    override fun onDrawed(size: Int) {
        if (size>0){
            findViewById<Button>(R.id.btn_ok).visibility = View.GONE
            findViewById<Button>(R.id.btn_modify).visibility = View.VISIBLE
        }
    }


//    TODO type갔다가 다시 돌아와서 type가려하면 중복이라고 나옴!!
//    예상 그리고 type 갈때 getsubject로 여기 테이블에 더하고 보내는데 이거때문인듯!!
//     밑에 getAddedTable로 들어가는데 subject랑 원래 테이블 따로 보내주기! 거기서 구별을 못함 ㅠ


    lateinit var timeTableDrawerDrag : TimeTableDrawerDrag
    lateinit var timeTable : TimeTable
    var subjectAddedList : MutableList<Subject> = arrayListOf()

    @ExperimentalStdlibApi
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_table_direct_edit)

        timeTable = intent.getParcelableExtra<TimeTable>("timeTable")

        timeTableDrawerDrag = TimeTableDrawerDrag(this, LayoutInflater.from(this), timeTable)
        timeTableDrawerDrag.onDrawListener = this
        findViewById<Button>(R.id.btn_ok).visibility = View.VISIBLE

        timeTableDrawerDrag.draw(findViewById<FrameLayout>(R.id.layout_timetable))
        findViewById<NestedScrollView>(R.id.layout_timetable_scrollable).isVerticalScrollBarEnabled = false

        Handler().postDelayed({
            setDragView()
        }, 100)

        findViewById<Button>(R.id.btn_cancel).setOnClickListener {
            finish()
        }

        findViewById<Button>(R.id.btn_retry).setOnClickListener {
            rollBack()
        }

        findViewById<Button>(R.id.btn_ok).setOnClickListener {
            val intent = Intent()
            Log.d("tag", "onclicked")
            Log.d("tag", "${subjectAddedList.size}")
//            TODO 잘 들어갔으면! 등록!!
            var requestnum =0
            if (timeTableDrawerDrag.getAddedTable() == null){
                Toast.makeText(this,"중복된 과목이 있습니다", Toast.LENGTH_SHORT).show()
            } else {
                for (i in 0 until subjectAddedList.size){
                    DataRepository.addPersonalPlan(RequestAddPersonalPlan(
                            subjectAddedList[i].name,
                            subjectAddedList[i].startTime,
                            subjectAddedList[i].endTime,
                            subjectAddedList[i].day,
                            subjectAddedList[i].place,
                            subjectAddedList[i].color,
                            timeTable.id.toInt()
                    ), onSuccess = {
                        requestnum+= 1
                        Log.d("tag", requestnum.toString())
                        if (requestnum == subjectAddedList.size){
                            intent.putExtra("timeTable", timeTable)
                            setResult(200, intent)
                        }
                        Log.d("tag", "requested")
                    }, onFailure = {
                        Log.d("tag", it)
                    })
                }
            }

        }

//       수정 눌렀다가 취소해서 그대로 돌아온 경우
        findViewById<Button>(R.id.btn_modify).setOnClickListener {
            val intent = Intent(this, TimeTableDirectTypeActivity::class.java)

            if (timeTableDrawerDrag.getAddedSubject() == null){
                Toast.makeText(this,"중복된 과목이 있습니다", Toast.LENGTH_SHORT).show()
            } else {

                val nextcolor = findNextColor(timeTableDrawerDrag.timeTable) + 1
                Log.d("tag next color", nextcolor.toString())

                intent.putParcelableArrayListExtra("subjects", timeTableDrawerDrag.getAddedSubject() as ArrayList<out Parcelable>)
                intent.putExtra("color",nextcolor)
                intent.putExtra("timeTable", deepCopy(timeTableDrawerDrag.timeTable))
                intent.putExtra("addable", false)
                startActivityForResult(intent, REQUEST_DIRECT_TYPE_ACTIVITY)
            }
        }
    }


//    drag에서 drawlist에 쌓이고 다시 draw하기 전까지는 testview유지
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_DIRECT_TYPE_ACTIVITY){
            if (resultCode == 200){
                timeTableDrawerDrag.testview.drawlist.clear()

                findViewById<Button>(R.id.btn_ok).visibility = View.VISIBLE
                findViewById<Button>(R.id.btn_modify).visibility = View.GONE
                val tt = data?.getParcelableExtra<TimeTable>("timeTable")
                val subjectList = data?.getParcelableArrayListExtra<Subject>("subjects")
                Log.d("tag", tt.toString())


                if (subjectList != null) {
                    tt?.subjectList?.addAll(subjectList)
                    subjectAddedList.addAll(subjectList)
                }

                if (tt != null){
                    timeTableDrawerDrag.timeTable = deepCopy(tt)
                    timeTable = deepCopy(tt)
                }
                timeTableDrawerDrag.draw(findViewById<FrameLayout>(R.id.layout_timetable))
            } else{
                timeTableDrawerDrag
            }

        }
    }

    @ExperimentalStdlibApi
    fun rollBack(){
        timeTableDrawerDrag.rollBack()
        if ( timeTableDrawerDrag.getSubject().size ==0 ){
            findViewById<Button>(R.id.btn_ok).visibility = View.VISIBLE
            findViewById<Button>(R.id.btn_modify).visibility = View.GONE
        }
    }

    fun setDragView(){
        timeTableDrawerDrag.setDragView((findViewById<LinearLayout>(R.id.timetable_root)))
    }

    fun findNextColor(timeTable: TimeTable) : Int{
        val size : HashMap<Int, Int> = hashMapOf()
        for (i in 0 until timeTable.subjectList.size){
            if (size.containsKey(timeTable.subjectList[i].color)){
                size.put(timeTable.subjectList[i].color, size.get(timeTable.subjectList[i].color)!!+1)
            } else {
                size.put(timeTable.subjectList[i].color, 1)
            }
        }
        var ids = size.keys.size - 1
        return ids
    }

}