package com.example.ulink.timetable

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ulink.R
import com.example.ulink.repository.*
import kotlinx.android.synthetic.main.fragment_time_table_candidate.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class TimeTableCandidateFragment() : Fragment(){

    var token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4IjoxLCJuYW1lIjoi6rmA67O067CwIiwic2Nob29sIjoi7ZWc7JaR64yA7ZWZ6rWQIiwibWFqb3IiOiLshoztlITtirjsm6jslrQiLCJpYXQiOjE1OTQ3NDgyNTQsImV4cCI6MTU5NjE4ODI1NCwiaXNzIjoiYm9iYWUifQ.dFU9h8EZLqoMekAfRNTfGQkUAbq_CXoQmA5Jl7KsQ70"
    var cartAdapter =
        TimeTableCandidateDetailAdapter()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_time_table_candidate, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_candidate.adapter = cartAdapter
    }

    override fun onResume() {
        super.onResume()
        var cartDatas : MutableList<SubjectDetail> = mutableListOf()
        //getCartList 후보 등록  TODO SEMESTER넘겨주기
        RetrofitService.service.getCartList(token, "2020-1").enqueue(object : Callback<ResponseGetCartList> {
            override fun onFailure(call: Call<ResponseGetCartList>, t: Throwable) {
            }
            override fun onResponse(
                call: Call<ResponseGetCartList>,
                response: Response<ResponseGetCartList>
            ) {
                response.body()?.let {
                    if (it.status == 200) {
                        if (it.data.isNotEmpty()) {
                            Log.d("cart", it.toString())
                            var size = it.data.size
                            for (i in 0 until size) {
                                cartDatas.apply {
                                    add(
                                        SubjectDetail(
                                            cartIdx = it.data[i].cartIdx,
                                            userIdx = it.data[i].userIdx,
                                            subjectIdx = it.data[i].subjectIdx,
                                            semester = it.data[i].semester,
                                            subjectCode = it.data[i].subjectCode,
                                            name = it.data[i].name,
                                            nameAtomic = it.data[i].nameAtomic,
                                            professor = it.data[i].professor,
                                            school = it.data[i].school,
                                            college = it.data[i].college,
                                            major = it.data[i].major,
                                            grade = it.data[i].grade,
                                            credit = it.data[i].credit,
                                            people = it.data[i].people,
                                            course = it.data[i].course
                                        )
                                    )
                                }
                                cartAdapter.cartDataList = cartDatas
                                cartAdapter.notifyDataSetChanged()
                            }
                        }
                    }
                }
            }
        })
    }
}