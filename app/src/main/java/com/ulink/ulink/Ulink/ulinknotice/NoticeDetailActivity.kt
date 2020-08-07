package com.ulink.ulink.Ulink.ulinknotice

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import com.ulink.ulink.R
import com.ulink.ulink.register.CollectAgreeActivity
import com.ulink.ulink.repository.DataRepository
import com.ulink.ulink.repository.RetrofitService
import com.ulink.ulink.utils.DialogBuilder
import kotlinx.android.synthetic.main.activity_notice_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NoticeDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice_detail)

        val subjectName = intent.getStringExtra("subjectName")
        val noticeIdx = intent.getIntExtra("noticeIdx", 0)
        var isMine = false

        if(subjectName!="")
            tv_toolbar_title.text = subjectName+" 공지"

        btn_back.setOnClickListener{
            finish()
        }

        RetrofitService.service.getDetailNotice(DataRepository.token, noticeIdx).enqueue(object :
            Callback<ResponseDetailNotice> {
            override fun onFailure(call: Call<ResponseDetailNotice>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<ResponseDetailNotice>,
                response: Response<ResponseDetailNotice>
            ) {
                response.body()?.let {
                    if (it.status == 200) {
                        categoryBackground(it.data.category, it.data.date, img_category)
                        tv_title.text = it.data.title
                        tv_date.text = noticeDetailDate(it.data.date)
                        timeTextView2(it.data.startTime, it.data.endTime, it.data.category, tv_time)
                        tv_content.text = it.data.content
                        tv_nickname.text = it.data.nickname
                        //isMine = it.data.isMine
                    }
                }
            }
        })

        btn_detail.setOnClickListener{
            val builder = android.app.AlertDialog.Builder(this)
            val layout = LayoutInflater.from(this).inflate(R.layout.dialog_request_update_notice, null)

            builder.setView(layout)

            var dialog = builder.create()

            var tvRequestUpdate = layout.findViewById<TextView>(R.id.tv_request_update)
            var tvRequestReport = layout.findViewById<TextView>(R.id.tv_request_report)

            if(!isMine) {
                tvRequestUpdate.text = "수정"
                tvRequestReport.text = "삭제"

                tvRequestUpdate.setOnClickListener {
                    dialog.dismiss()
                    val intent = Intent(this, NoticeWriteActivity::class.java)
                    intent.putExtra("subjectName", subjectName)
                    intent.putExtra("noticeIdx", noticeIdx)
                    startActivity(intent)
                }

                tvRequestReport.setOnClickListener {
                    dialog.dismiss()
                }
            }
            else {
                tvRequestUpdate.setOnClickListener {
                    dialog.dismiss()
                    val intent = Intent(this, NoticeUpdateRequestActivity::class.java)
                    intent.putExtra("noticeIdx", noticeIdx)
                    startActivity(intent)
                }

                tvRequestReport.setOnClickListener {
                    dialog.dismiss()
                }
            }

            val back = ColorDrawable(Color.TRANSPARENT)
            val inset = InsetDrawable(back, 30)
            dialog.window?.setBackgroundDrawable(inset)
            dialog.show()
        }
    }
}