package com.example.ulink

import android.os.Bundle
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.ulink.ClassNoticeRecycler.ClassNoticeAdapter
import com.example.ulink.ClassNoticeRecycler.ClassNoticeData
import com.example.ulink.TaskNoticeRecycler.TaskNoticeAdapter
import com.example.ulink.TaskNoticeRecycler.TaskNoticeData
import com.example.ulink.TestNoticeRecycler.TestNoticeAdapter
import com.example.ulink.TestNoticeRecycler.TestNoticeData
import kotlinx.android.synthetic.main.activity_notice.*
import kotlinx.android.synthetic.main.activity_schedule.*
import kotlinx.android.synthetic.main.toolbar_notice_class_more.*

class NoticeClassMoreActivity : AppCompatActivity(){
    lateinit var ClassNoticeAdapter : ClassNoticeAdapter
    val datas2: MutableList<ClassNoticeData> = mutableListOf<ClassNoticeData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice_class_more)

        btn_back.setOnClickListener{
            finish()
        }
        ClassNoticeAdapter = ClassNoticeAdapter(this)
        rv_class_notice.adapter = ClassNoticeAdapter
        loadDatas2()

    }
    private fun loadDatas2() {
        datas2.apply {
            add(
                ClassNoticeData(
                    StartDate = 5,
                    EndDate = 4,
                    ClassName = "휴강",
                    StartTime= "시간정보없음",
                    EndTime = ""
                )
            )
            add(
                ClassNoticeData(
                    StartDate = 6,
                    EndDate = 10,
                    ClassName = "수업시간변경",
                    StartTime= "16:00",
                    EndTime = "17:00"
                )
            )

        }
        ClassNoticeAdapter.datas = datas2
        ClassNoticeAdapter.notifyDataSetChanged()
    }

}
