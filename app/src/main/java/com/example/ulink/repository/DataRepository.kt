package com.example.ulink.repository

import android.util.Log
import com.example.ulink.ScheduleRecycler.ScheduleItemData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object DataRepository {

    val retrofit = RetrofitService.service

    val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4IjoxLCJuYW1lIjoi6rmA67O067CwIiwic2Nob29sIjoi7ZWc7JaR64yA7ZWZ6rWQIiwibWFqb3IiOiLshoztlITtirjsm6jslrQiLCJpYXQiOjE1OTQ3MTk0NjQsImV4cCI6MTU5NjE1OTQ2NCwiaXNzIjoiYm9iYWUifQ.I0OuZ02a6iC1R9pUMOTKKreqMwqJD4m3G45u275bGNo"

    fun getMainTimeTable(onSuccess: (TimeTable) -> Unit, onFailure: (String) -> Unit) {

        retrofit.getMainTimeTable(token).enqueue(object : Callback<ResponseMainTimeTable> {
            override fun onFailure(call: Call<ResponseMainTimeTable>, t: Throwable) {
                Log.d("tag", t.localizedMessage)
            }
            override fun onResponse(call: Call<ResponseMainTimeTable>, response: Response<ResponseMainTimeTable>) {
                response.body()?.let {
                    if (it.status == 200) {

                        val subjectList: MutableList<Subject> = arrayListOf()
                        subjectList.apply {
                            addAll(it.data.subjects.mon)
                            addAll(it.data.subjects.tue)
                            addAll(it.data.subjects.wed)
                            addAll(it.data.subjects.thu)
                            addAll(it.data.subjects.fri)
                        }
                        val timeTable = TimeTable(it.data.timeTable.id, it.data.timeTable.semseter, it.data.timeTable.name, true, subjectList = subjectList)
                        onSuccess(timeTable)
                    } else {
                        onFailure(response.errorBody().toString())
                    }
                } ?: onFailure(response.message())
            }
        })
    }

    fun addTimeTable(semester : String, name : String, onSuccess : (ResponseAddTimeTable) -> Unit, onFailure :(String) -> Unit){
        retrofit.addTimeTable(token, RequestAddTimeTable(semester, name)).enqueue(object : Callback<ResponseAddTimeTable>{
            override fun onFailure(call: Call<ResponseAddTimeTable>, t: Throwable) {
                Log.d("tag", t.localizedMessage)
            }

            override fun onResponse(call: Call<ResponseAddTimeTable>, response: Response<ResponseAddTimeTable>) {
                response.body()?.let {
                    onSuccess(it)
                } ?: onFailure(response.errorBody().toString())
            }
        })
    }



    fun getAllNotice(start: String, end: String, onSuccess: (Map<String, List<ScheduleItemData>>) -> Unit, onFailure: (String) -> Unit) {
        retrofit.getAllNotice(token, start, end).enqueue(object : Callback<ResponseCalendar> {
            override fun onFailure(call: Call<ResponseCalendar>, t: Throwable) {
                Log.d("tag", t.localizedMessage)
            }
            override fun onResponse(call: Call<ResponseCalendar>, response: Response<ResponseCalendar>) {
            }
        })
    }

}