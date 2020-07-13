package com.example.ulink.timetable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.widget.NestedScrollView
import com.example.ulink.R
import com.example.ulink.repository.TimeTable
import java.util.ArrayList

interface onDrawListener{
    fun onDrawed(size : Int)
}

class TimeTableDirectEditActivity : AppCompatActivity(), onDrawListener {

    override fun onDrawed(size: Int) {
        if (size>0){
            findViewById<TextView>(R.id.tv_ok).visibility = View.GONE
            findViewById<TextView>(R.id.tv_modify).visibility = View.VISIBLE
        }
    }

    lateinit var timeTableDrawerDrag : TimeTableDrawerDrag

    @ExperimentalStdlibApi
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_table_direct_edit)

        val timeTable = intent.getParcelableExtra<TimeTable>("timeTable")

        timeTableDrawerDrag = TimeTableDrawerDrag(this, LayoutInflater.from(this), timeTable)
        timeTableDrawerDrag.onDrawListener = this
        findViewById<TextView>(R.id.tv_ok).visibility = View.VISIBLE

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

        findViewById<TextView>(R.id.tv_ok).setOnClickListener {
            val intent = Intent()
            intent.putExtra("timeTable", timeTableDrawerDrag.getAddedTable())
            setResult(200, intent)
        }
        findViewById<TextView>(R.id.tv_modify).setOnClickListener {
            val intent = Intent()
            val size = timeTableDrawerDrag.getSubject().size


            intent.putParcelableArrayListExtra("subjectList", timeTableDrawerDrag.getSubject() as ArrayList<out Parcelable>)
            intent.putExtra("timeTable", timeTableDrawerDrag.getAddedTable())
            startActivityForResult(intent, REQUEST_DIRECT_TYPE_ACTIVITY)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_DIRECT_TYPE_ACTIVITY && resultCode == 200){
            timeTableDrawerDrag.testview.drawlist.clear()
            findViewById<TextView>(R.id.tv_ok).visibility = View.VISIBLE
            findViewById<TextView>(R.id.tv_modify).visibility = View.GONE
//            intent.getParcelableExtra<TimeTable>()
//           TODO 여기서 timetablelist에 넣어줘야함 그리고 deepcopy꼭하기!!
        }
    }

    @ExperimentalStdlibApi
    fun rollBack(){
        timeTableDrawerDrag.rollBack()
    }

    fun setDragView(){
        timeTableDrawerDrag.setDragView((findViewById<LinearLayout>(R.id.timetable_root)))
    }

}