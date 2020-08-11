package com.ulink.ulink.Ulink.ClassBoard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ulink.ulink.R
import com.ulink.ulink.Ulink.ulinknotice.*
import com.ulink.ulink.repository.DataRepository
import com.ulink.ulink.repository.RetrofitService
import kotlinx.android.synthetic.main.fragment_ulink_notice.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UlinkNoticeFragment(subjectName : String, subjectIdx : String) : Fragment() {
    lateinit var noticeAdapter : UlinkNoticeAdapter
    val noticeDatas : MutableList<UlinkNoticeData> = mutableListOf<UlinkNoticeData>()
    var subjectName = subjectName
    var subjectIdx = subjectIdx

    override fun onResume() {
        super.onResume()
        RetrofitService.service.getSubjectNotice(DataRepository.token, subjectIdx).enqueue(object :
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
                        if(noticeAdapter.datas.size==0){
                            tv_notice_nothing.visibility=View.VISIBLE
                            rv_subject_notice.visibility=View.GONE
                        }
                        else{
                            tv_notice_nothing.visibility=View.GONE
                            rv_subject_notice.visibility=View.VISIBLE
                        }
                    }
                }
            }
        })
    }

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

        Log.d("subject", subjectName+" "+subjectIdx)

        RetrofitService.service.getSubjectNotice(DataRepository.token, subjectIdx).enqueue(object :
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
                        if(noticeAdapter.datas.size==0){
                            tv_notice_nothing.visibility=View.VISIBLE
                            rv_subject_notice.visibility=View.GONE
                        }
                        else{
                            tv_notice_nothing.visibility=View.GONE
                            rv_subject_notice.visibility=View.VISIBLE
                        }
                    }
                }
            }
        })

        noticeAdapter.setNoticeClickListener(object : UlinkNoticeAdapter.NoticeClickListener{
            override fun onClick(view : View, position : Int){
                val intent = Intent(view.context, NoticeDetailActivity::class.java)
                intent.putExtra("subjectName", subjectName)
                intent.putExtra("noticeIdx", noticeAdapter.datas[position].noticeIdx)
                intent.putExtra("subjectIdx", subjectIdx)
                startActivity(intent)
            }
        })
    }
}