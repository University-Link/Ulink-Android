package com.ulink.ulink.Ulink.UlinkNotice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ulink.ulink.R
import com.ulink.ulink.repository.DataRepository
import com.ulink.ulink.repository.RetrofitService
import kotlinx.android.synthetic.main.activity_notice_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NoticeDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice_detail)

        val className = intent.getStringExtra("className")
        val noticeIdx = intent.getIntExtra("noticeIdx", 0)

        if(className!="")
            tv_toolbar_title.text = className+"공지"

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
                    }
                }
            }
        })
    }
}