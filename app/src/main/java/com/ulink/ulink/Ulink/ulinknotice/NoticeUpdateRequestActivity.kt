package com.ulink.ulink.Ulink.ulinknotice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ulink.ulink.R
import com.ulink.ulink.register.btnNextReset
import com.ulink.ulink.register.btnNextSelector
import com.ulink.ulink.textChangedListener
import kotlinx.android.synthetic.main.activity_notice_update_request.*

class NoticeUpdateRequestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice_update_request)

        val noticeIdx = intent.getIntExtra("noticeIdx", 0)
        var checkContent = false
        var checkDate = false
        var checkTime = false
        var checkTitle = false
        var checkCategory = false
        var checkEtc = false
        var checkList = arrayListOf<Int>()
        var contentList = arrayListOf<String>()

        btn_back.setOnClickListener {
            finish()
        }

        btn_inaccurate_content.setOnClickListener {
            checkContent = !checkContent

            editTextFocus(btn_inaccurate_content, et_inaccurate_content)

            if(checkContent || checkDate || checkTime || checkTitle || checkCategory || checkEtc)
                btn_confirm.btnNextSelector()
            else
                btn_confirm.btnNextReset()
        }

        et_inaccurate_content.textChangedListener {
            tv_inaccurate_content.text = "("+et_inaccurate_content.text.length.toString() + "/100)"
        }


        btn_inaccurate_date.setOnClickListener{
            checkDate = !checkDate

            editTextFocus(btn_inaccurate_date, et_inaccurate_date)

            if(checkContent || checkDate || checkTime || checkTitle || checkCategory || checkEtc)
                btn_confirm.btnNextSelector()
            else
                btn_confirm.btnNextReset()
        }

        et_inaccurate_date.textChangedListener {
            tv_inaccurate_date.text = "("+et_inaccurate_date.text.length.toString() + "/100)"
        }

        btn_inaccurate_time.setOnClickListener{
            checkTime = !checkTime

            editTextFocus(btn_inaccurate_time, et_inaccurate_time)

            if(checkContent || checkDate || checkTime || checkTitle || checkCategory || checkEtc)
                btn_confirm.btnNextSelector()
            else
                btn_confirm.btnNextReset()
        }

        et_inaccurate_time.textChangedListener {
            tv_inaccurate_time.text = "("+et_inaccurate_time.text.length.toString() + "/100)"
        }

        btn_inaccurate_title.setOnClickListener{
            checkTitle = !checkTitle

            editTextFocus(btn_inaccurate_title, et_inaccurate_title)

            if(checkContent || checkDate || checkTime || checkTitle || checkCategory || checkEtc)
                btn_confirm.btnNextSelector()
            else
                btn_confirm.btnNextReset()
        }

        et_inaccurate_title.textChangedListener {
            tv_inaccurate_title.text = "("+et_inaccurate_title.text.length.toString() + "/100)"
        }

        btn_inaccurate_category.setOnClickListener{
            checkCategory = !checkCategory

            editTextFocus(btn_inaccurate_category, et_inaccurate_category)

            if(checkContent || checkDate || checkTime || checkTitle || checkCategory || checkEtc)
                btn_confirm.btnNextSelector()
            else
                btn_confirm.btnNextReset()
        }

        et_inaccurate_category.textChangedListener {
            tv_inaccurate_category.text = "("+et_inaccurate_category.text.length.toString() + "/100)"
        }


        btn_direct_input.setOnClickListener{
            checkEtc = !checkEtc

            editTextFocus(btn_direct_input, et_direct_input)

            if(checkContent || checkDate || checkTime || checkTitle || checkCategory || checkEtc)
                btn_confirm.btnNextSelector()
            else
                btn_confirm.btnNextReset()
        }

        et_direct_input.textChangedListener {
            tv_inaccurate_etc.text = "("+et_direct_input.text.length.toString() + "/100)"
        }

        btn_confirm.setOnClickListener{
            if(checkContent) {
                checkList.add(1)
                contentList.add(et_inaccurate_content.text.toString())
            }
            if(checkDate) {
                checkList.add(2)
                contentList.add(et_inaccurate_date.text.toString())
            }
            if(checkTime) {
                checkList.add(3)
                contentList.add(et_inaccurate_time.text.toString())
            }
            if(checkTitle) {
                checkList.add(4)
                contentList.add(et_inaccurate_title.text.toString())
            }
            if(checkCategory) {
                checkList.add(5)
                contentList.add(et_inaccurate_category.text.toString())
            }
            if(checkEtc) {
                checkList.add(6)
                contentList.add(et_direct_input.text.toString())
            }

            val body = RequestNoticeModify(noticeIdx = noticeIdx, reasons = checkList, contents = contentList)

            finish()
        }
    }
}