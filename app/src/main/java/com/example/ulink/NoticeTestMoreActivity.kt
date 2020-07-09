package com.example.ulink

import android.os.Bundle
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.ulink.TaskNoticeRecycler.TaskNoticeAdapter
import com.example.ulink.TaskNoticeRecycler.TaskNoticeData
import com.example.ulink.TestNoticeRecycler.TestNoticeAdapter
import com.example.ulink.TestNoticeRecycler.TestNoticeData
import kotlinx.android.synthetic.main.activity_notice.*
import kotlinx.android.synthetic.main.activity_schedule.*
import kotlinx.android.synthetic.main.toolbar_notice_class_more.*

class NoticeTestMoreActivity : AppCompatActivity(){
    lateinit var TestNoticeAdapter : TestNoticeAdapter
    val datas : MutableList<TestNoticeData> = mutableListOf<TestNoticeData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice_test_more)

        btn_back.setOnClickListener{
            finish()
        }

        TestNoticeAdapter = TestNoticeAdapter(this)
        rv_test_notice.adapter = TestNoticeAdapter
        loadDatas()

    }
    private fun loadDatas() {
        datas.apply {
            add(
                TestNoticeData(
                    StartDate = 5,
                    EndDate = 13,
                    TestName = "중간고사",
                    StartTime = "13:00",
                    EndTime = "14:00"
                )
            )
            add(
                TestNoticeData(
                    StartDate = 5,
                    EndDate = 13,
                    TestName = "중간고사",
                    StartTime = "13:00",
                    EndTime = "14:00"


                )
            )
            add(
                TestNoticeData(
                    StartDate = 5,
                    EndDate = 13,
                    TestName = "중간고사",
                    StartTime = "13:00",
                    EndTime = "14:00"


                )
            )
            add(
                TestNoticeData(
                    StartDate = 5,
                    EndDate = 13,
                    TestName = "중간고사",
                    StartTime = "13:00",
                    EndTime = "14:00"

                )
            )
            add(
                TestNoticeData(
                    StartDate = 5,
                    EndDate = 13,
                    TestName = "중간고사",
                    StartTime = "13:00",
                    EndTime = "14:00"

                )
            )
        }
        TestNoticeAdapter.datas = datas
        TestNoticeAdapter.notifyDataSetChanged()
    }
}
