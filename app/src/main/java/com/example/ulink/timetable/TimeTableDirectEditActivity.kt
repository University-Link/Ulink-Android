package com.example.ulink.timetable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.core.widget.NestedScrollView
import com.example.ulink.R
import com.example.ulink.repository.TimeTable
import com.example.ulink.utils.deepCopy
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


//    TODO 그리고 type갔다가 다시 돌아와서 type가려하면 중복이라고 나옴!!
//    예상 그리고 type 갈때 getsubject로 여기 테이블에 더하고 보내는데 이거때문인듯!!
//     밑에 getAddedTable로 들어가는데 subject랑 원래 테이블 따로 보내주기! 거기서 구별을 못함 ㅠ


    lateinit var timeTableDrawerDrag : TimeTableDrawerDrag
    lateinit var timeTable : TimeTable

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

//            TODO 잘 들어갔으면!
            if (timeTableDrawerDrag.getAddedTable() == null){
                Toast.makeText(this,"중복된 과목이 있습니다", Toast.LENGTH_SHORT).show()
            } else {
                intent.putExtra("timeTable", timeTableDrawerDrag.getAddedTable())
                setResult(200, intent)
            }

        }

//       수정 눌렀다가 취소해서 그대로 돌아온 경우

        findViewById<Button>(R.id.btn_modify).setOnClickListener {
            val intent = Intent(this, TimeTableDirectTypeActivity::class.java)
            if (timeTableDrawerDrag.getAddedSubject() == null){
                Toast.makeText(this,"중복된 과목이 있습니다", Toast.LENGTH_SHORT).show()
            } else {
                intent.putParcelableArrayListExtra("subjects", timeTableDrawerDrag.getAddedSubject() as ArrayList<out Parcelable>)
                intent.putExtra("timeTable", timeTableDrawerDrag.getAddedTable())
                startActivityForResult(intent, REQUEST_DIRECT_TYPE_ACTIVITY)
            }
        }
    }


//    drag에서 drawlist에 쌓이고 다시 draw하기 전까지는 testview유지
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_DIRECT_TYPE_ACTIVITY){
            if (resultCode == 200){
                findViewById<Button>(R.id.btn_ok).visibility = View.VISIBLE
                findViewById<Button>(R.id.btn_modify).visibility = View.GONE
                intent.getParcelableExtra<TimeTable>("timeTable")
//           TODO 여기서 timetablelist에 넣어줘야함 그리고 deepcopy꼭하기!! 그리고 다시 그려주기!!
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

}