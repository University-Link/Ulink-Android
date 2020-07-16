package com.example.ulink.repository

import android.util.Log
import com.example.ulink.ScheduleRecycler.ScheduleItemData
import com.example.ulink.utils.deepCopy
import com.example.ulink.utils.deepCopyRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object DataRepository {

    val retrofit = RetrofitService.service

    var token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4IjoxLCJuYW1lIjoi6rmA67O067CwIiwic2Nob29sIjoi7ZWc7JaR64yA7ZWZ6rWQIiwibWFqb3IiOiLshoztlITtirjsm6jslrQiLCJpYXQiOjE1OTQ3NDgyNTQsImV4cCI6MTU5NjE4ODI1NCwiaXNzIjoiYm9iYWUifQ.dFU9h8EZLqoMekAfRNTfGQkUAbq_CXoQmA5Jl7KsQ70"

    var token2 = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4IjoxLCJuYW1lIjoi6rmA67O067CwIiwic2Nob29sIjoi7ZWc7JaR64yA7ZWZ6rWQIiwibWFqb3IiOiLshoztlITtirjsm6jslrQiLCJpYXQiOjE1OTQ4MTY1NzQsImV4cCI6MTU5NjI1NjU3NCwiaXNzIjoiYm9iYWUifQ.JwRDELH1lA1Fb8W1ltTmhThpmgFrUTQZVocUTATv3so"


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
                        val timeTable = TimeTable(it.data.timeTable.id, it.data.timeTable.semester, it.data.timeTable.name, 1, startTime = it.data.minTime, endTime = it.data.maxTime, subjectList = subjectList)
                        onSuccess(timeTable)
                    } else {
                        onFailure(response.errorBody().toString())
                    }
                } ?: onFailure(response.message())
            }
        })
    }

    fun getTimeTableWithId(id : Int, onSuccess : (TimeTable)-> Unit, onFailure : (String) -> Unit){
        retrofit.getTimeTableWithId(token, id).enqueue(object : Callback<ResponseGetTimeTableWithId>{
            override fun onFailure(call: Call<ResponseGetTimeTableWithId>, t: Throwable) {
                onFailure(t.localizedMessage)
            }

            override fun onResponse(call: Call<ResponseGetTimeTableWithId>, response: Response<ResponseGetTimeTableWithId>) {
                response.body()?.let {


                    val subjectList: MutableList<Subject> = arrayListOf()
                    subjectList.apply {
                        addAll(it.data.subjects.mon)
                        addAll(it.data.subjects.tue)
                        addAll(it.data.subjects.wed)
                        addAll(it.data.subjects.thu)
                        addAll(it.data.subjects.fri)
                    }

//                    TODO 여기 Main 시간표 어떻게 할것인지 ismain을 해놓는거랑 차이가 있나??
                    val timeTable = TimeTable(it.data.timeTable.id, it.data.timeTable.semester, it.data.timeTable.name, 0, startTime = it.data.minTime, endTime = it.data.maxTime, subjectList = subjectList)
                    onSuccess(timeTable)

                } ?: onFailure(response.message())
            }
        })
    }

    fun getTimeTableBySemester(semester : String, onSuccess : (List<TimeTable>) -> Unit, onFailure: (String) -> Unit){
        retrofit.getTimeTableList(token, semester).enqueue(object : Callback<ResponseGetTimeTableList>{
            override fun onFailure(call: Call<ResponseGetTimeTableList>, t: Throwable) {
                onFailure(t.localizedMessage)
            }

            override fun onResponse(call: Call<ResponseGetTimeTableList>, response: Response<ResponseGetTimeTableList>) {
                response.body()?.let {
                    val tableList : MutableList<TimeTable> = arrayListOf()

                    if (it != null && it.data.isNotEmpty()) {
                        for (i in it.data){
                            tableList.add(i)
                        }
                    }
                    onSuccess(tableList)
                } ?: onFailure(response.message())
            }
        })
    }

    fun addTimeTable(semester : String, name : String, onSuccess : (ResponseAddTimeTable) -> Unit, onFailure :(String) -> Unit){
        retrofit.addTimeTable(token, RequestAddTimeTable(semester, name)).enqueue(object : Callback<ResponseAddTimeTable>{
            override fun onFailure(call: Call<ResponseAddTimeTable>, t: Throwable) {
                Log.d("tag", t.localizedMessage)
                onFailure(t.localizedMessage)
            }

            override fun onResponse(call: Call<ResponseAddTimeTable>, response: Response<ResponseAddTimeTable>) {
                response.body()?.let {
                    onSuccess(it)
                } ?: onFailure(response.errorBody().toString())
            }
        })
    }

    fun addSchoolPlan(request : RequestAddSchoolPlan, onSuccess: () -> Unit, onFailure: (String) -> Unit){
        retrofit.addSchoolPlan(token, request).enqueue(object : Callback<ResponseAddSchoolPlan>{
            override fun onFailure(call: Call<ResponseAddSchoolPlan>, t: Throwable) {
                onFailure(t.localizedMessage)
            }
            override fun onResponse(call: Call<ResponseAddSchoolPlan>, response: Response<ResponseAddSchoolPlan>) {
                response.body()?.let {
                    onSuccess()
                }
            }
        })
    }

    fun addPersonalPlan(request : RequestAddPersonalPlan, onSuccess : () ->Unit, onFailure : (String) -> Unit){
        retrofit.addPersonalPlan(token,request).enqueue(object : Callback<ResponseAddPersonalPlan>{
            override fun onFailure(call: Call<ResponseAddPersonalPlan>, t: Throwable) {
                Log.d("tag", t.localizedMessage)
                onFailure(t.localizedMessage)
            }

            override fun onResponse(call: Call<ResponseAddPersonalPlan>, response: Response<ResponseAddPersonalPlan>) {
                response.body()?.let {
                    onSuccess()
                } ?: onFailure(response.message())
            }
        })
    }

    fun getAllTimeTableList(onSuccess : (MutableList<MutableList<TimeTable>>) -> Unit, onFailure : (String) -> Unit){
        retrofit.getAllTimeTableList(token).enqueue(object :Callback<ResponseGetAllTimeTableList>{
            override fun onFailure(call: Call<ResponseGetAllTimeTableList>, t: Throwable) {
                onFailure(t.localizedMessage)
                Log.d("tag", t.localizedMessage)

            }

            override fun onResponse(call: Call<ResponseGetAllTimeTableList>, response: Response<ResponseGetAllTimeTableList>) {
                response.body().let {
                    val tableList : MutableList<TimeTable> = arrayListOf()
                    val semesterList : MutableList<MutableList<TimeTable>> = arrayListOf()
                    
//                    TODO 나중에 비어있을때 어떻게 처리할까

                    if (it != null && it.data.isNotEmpty()) {
                        for (i in it.data.indices){
                            if (it.data[i].timeTableList.isNotEmpty()){
                                tableList.clear()
                                for (a in it.data[i].timeTableList.indices){
                                    tableList.add(TimeTable(it.data[i].timeTableList[a].id, it.data[i].semester, it.data[i].timeTableList[a].name,it.data[i].timeTableList[a].isMain))
                                }
//                              여기서 tablelist의 연결점을 끊어줘야함
                                semesterList.add(deepCopyRetrofit(tableList))
                            }
                        }
                    }
                    onSuccess(semesterList)
                } ?: onFailure(response.message())
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