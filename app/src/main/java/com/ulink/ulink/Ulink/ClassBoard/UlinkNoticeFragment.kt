package com.ulink.ulink.Ulink.ClassBoard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ulink.ulink.CalendarRecycler.today
import com.ulink.ulink.R
import com.ulink.ulink.Ulink.UlinkNotice.*
import com.ulink.ulink.repository.DataRepository
import com.ulink.ulink.repository.RetrofitService
import kotlinx.android.synthetic.main.fragment_ulink_notice.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class UlinkNoticeFragment(className : String, classIdx : String) : Fragment() {
    lateinit var noticeAdapter : UlinkNoticeAdapter
    val noticeDatas : MutableList<UlinkNoticeData> = mutableListOf<UlinkNoticeData>()
    var className = className
    var classIdx = classIdx

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ulink_notice, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noticeAdapter = UlinkNoticeAdapter(view.context)
        noticeAdapter.datas = noticeDatas
        rv_subject_notice.adapter = noticeAdapter

        Log.d("subject", className+" "+classIdx)

        RetrofitService.service.getSubjectNotice(DataRepository.token, classIdx).enqueue(object :
            Callback<ResponseUlinkNotice> {
            override fun onFailure(call: Call<ResponseUlinkNotice>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<ResponseUlinkNotice>,
                response: Response<ResponseUlinkNotice>
            ) {
                response.body()?.let {
                    if(it.status==200){
                        noticeDatas.clear()
                        for (i in 0 until it.data.size){
                            noticeDatas.apply {
                                if(noticeDateCompare(it.data[i].date))
                                add(
                                    UlinkNoticeData(
                                        noticeIdx = it.data[i].noticeIdx,
                                        category = it.data[i].category,
                                        title = it.data[i].title,
                                        startTime = it.data[i].startTime,
                                        endTime = it.data[i].endTime,
                                        date = it.data[i].date
                                    )
                                )
                            }
                        }
                        noticeAdapter.notifyDataSetChanged()
                        for(i in 0 until it.data.size){
                            noticeDatas.apply {
                                if(!noticeDateCompare(it.data[i].date))
                                add(
                                    UlinkNoticeData(
                                        noticeIdx = it.data[i].noticeIdx,
                                        category = it.data[i].category,
                                        title = it.data[i].title,
                                        startTime = it.data[i].startTime,
                                        endTime = it.data[i].endTime,
                                        date = it.data[i].date
                                    )
                                )
                            }
                        }
                        noticeAdapter.notifyDataSetChanged()
                    }
                }
            }
        })

        noticeAdapter.setNoticeClickListener(object : UlinkNoticeAdapter.NoticeClickListener{
            override fun onClick(view : View, position : Int){
                val intent = Intent(view.context, NoticeDetailActivity::class.java)
                intent.putExtra("className", className)
                intent.putExtra("noticeIdx", noticeAdapter.datas[position].noticeIdx)
                startActivity(intent)
            }
        })
    }
}