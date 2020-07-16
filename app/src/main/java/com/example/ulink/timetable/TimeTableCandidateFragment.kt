package com.example.ulink.timetable

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ulink.R
import com.example.ulink.repository.GetCartData
import com.example.ulink.repository.ResponseGetCartList
import com.example.ulink.repository.RetrofitService
import kotlinx.android.synthetic.main.fragment_time_table_candidate.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TimeTableCandidateFragment() : Fragment() {

    var token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4IjoxLCJuYW1lIjoi6rmA67O067CwIiwic2Nob29sIjoi7ZWc7JaR64yA7ZWZ6rWQIiwibWFqb3IiOiLshoztlITtirjsm6jslrQiLCJpYXQiOjE1OTQ3NDgyNTQsImV4cCI6MTU5NjE4ODI1NCwiaXNzIjoiYm9iYWUifQ.dFU9h8EZLqoMekAfRNTfGQkUAbq_CXoQmA5Jl7KsQ70"
    lateinit var cartAdapter : TimeTableCandidateDetailAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_time_table_candidate, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cartAdapter = TimeTableCandidateDetailAdapter(requireContext())
        rv_candidate.adapter = cartAdapter
    }

    override fun onResume() {
        super.onResume()
        var cartDatas: MutableList<GetCartData> = mutableListOf()
        //getCartList 후보 등록
        // TODO SEMESTER넘겨주기

        

        RetrofitService.service.getCartList(token, "2020-1").enqueue(object : Callback<ResponseGetCartList> {
            override fun onFailure(call: Call<ResponseGetCartList>, t: Throwable) {
                Log.d("cart", t.localizedMessage)
            }

            override fun onResponse(
                    call: Call<ResponseGetCartList>,
                    response: Response<ResponseGetCartList>
            ) {
                response.body()?.let {
                    if (it.status == 200) {
                        if (it.data.isNotEmpty()) {
                            Log.d("cart", it.toString())

                            for(i in it.data){
                                cartDatas.add(i)
                            }

                            cartAdapter.cartDataList = cartDatas
                            cartAdapter.notifyDataSetChanged()
                        }
                    }
                } ?: Log.d("cart", response.message())
            }
        })
    }
}