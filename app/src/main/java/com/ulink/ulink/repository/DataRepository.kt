package com.ulink.ulink.repository

import android.util.Log
import com.ulink.ulink.ScheduleRecycler.ScheduleItemData
import com.ulink.ulink.utils.deepCopy
import com.ulink.ulink.utils.deepCopyRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response




object DataRepository {

    val retrofit = RetrofitService.service

    lateinit var token : String

    fun requestLogin(request : RequestLogin, onSuccess: (String) -> Unit, onFailure: (String) -> Unit){

        retrofit.requestLogin(request).enqueue(object : Callback<ResponseLogin>{
            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                onFailure(t.localizedMessage)
            }

            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                response.body()?.let {
                    onSuccess(it.data!!.accessToken)
                } ?: onFailure(response.message())
            }
        })
    }

    fun getProfile(onSuccess : (ResponseGetProfile) -> Unit, onFailure: (String) -> Unit){
        retrofit.getProfile(token).enqueue(object : Callback<ResponseGetProfile>{
            override fun onFailure(call: Call<ResponseGetProfile>, t: Throwable) {
                onFailure(t.localizedMessage)
            }

            override fun onResponse(call: Call<ResponseGetProfile>, response: Response<ResponseGetProfile>) {
                response.body()?.let {
                    onSuccess(it)
                } ?: onFailure(response.message())
            }
        })
    }

    fun updateNickname(nickName : String, onSuccess: () -> Unit, onFailure: (String) -> Unit){
        retrofit.updateNickname(token, RequestUpdateNickname(nickName)).enqueue(object : Callback<ResponseUpdateNickname>{
            override fun onFailure(call: Call<ResponseUpdateNickname>, t: Throwable) {
                onFailure(t.localizedMessage)
            }

            override fun onResponse(call: Call<ResponseUpdateNickname>, response: Response<ResponseUpdateNickname>) {
                response.body()?.let {
                    onSuccess()
                } ?: onFailure(response.message())
            }
        })
    }

    fun getNicknameSameCheck(nickName: String, majorIdx : Int, onSuccess: () -> Unit, onFailure: (String) -> Unit){
        retrofit.getNicknameSameCheck(RequestNicknameCheck(nickName,majorIdx)).enqueue(object : Callback<ResponseNicknameCheck>{
            override fun onFailure(call: Call<ResponseNicknameCheck>, t: Throwable) {
                onFailure(t.localizedMessage)

            }

            override fun onResponse(call: Call<ResponseNicknameCheck>, response: Response<ResponseNicknameCheck>) {
                response.body()?.let {
                    onSuccess()
                } ?: onFailure(response.message())
            }
        })
    }

    fun updatePassword(password : String, newPassword : String, onSuccess: () -> Unit, onFailure: (String) -> Unit){
        retrofit.updatePassword(token, RequestUpdatePassword(password, newPassword)).enqueue(object : Callback<BaseResponse>{
            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                onFailure(t.localizedMessage)
            }

            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                response.body()?.let {
                    onSuccess()
                } ?: onFailure(response.message())
            }
        })
    }

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
                        Log.d("tag",timeTable.toString())
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
                    Log.d("tag",it.data.toString())

                    for(i in 0 until it.data.size){
                        val subjectList: MutableList<Subject> = arrayListOf()
                        subjectList.apply {
                            addAll(it.data[i].subjects.mon)
                            addAll(it.data[i].subjects.tue)
                            addAll(it.data[i].subjects.wed)
                            addAll(it.data[i].subjects.thu)
                            addAll(it.data[i].subjects.fri)
                        }

                        val timeTable = TimeTable(it.data[i].timeTable.id, it.data[i].timeTable.semester, it.data[i].timeTable.name, 0, startTime = it.data[i].minTime , endTime = it.data[i].maxTime, subjectList = subjectList)
                        tableList.add(deepCopy(timeTable))
                    }


                    onSuccess(tableList)
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

    fun getAllNotice(start: String, end: String, onSuccess: (Map<String, List<ScheduleItemData>>) -> Unit, onFailure: (String) -> Unit) {
        retrofit.getAllNotice(token, start, end).enqueue(object : Callback<ResponseCalendar> {
            override fun onFailure(call: Call<ResponseCalendar>, t: Throwable) {
                Log.d("tag", t.localizedMessage)
            }
            override fun onResponse(call: Call<ResponseCalendar>, response: Response<ResponseCalendar>) {
            }
        })
    }

    fun deleteNoticeWithIdx(idx : String, onSuccess: () -> Unit, onFailure: (String) -> Unit){
        retrofit.deleteNoticeWithIdx(token, idx).enqueue(object  : Callback<ResponseDeleteNoticeWithIdx>{
            override fun onFailure(call: Call<ResponseDeleteNoticeWithIdx>, t: Throwable) {
                onFailure(t.localizedMessage)
            }

            override fun onResponse(call: Call<ResponseDeleteNoticeWithIdx>, response: Response<ResponseDeleteNoticeWithIdx>) {
                onSuccess()
            }
        })
    }

    fun getSubjectRecommendWithKeyword(name : String, onSuccess: (List<String>) -> Unit, onFailure: (String) -> Unit){
        retrofit.getSubjectRecommendWithKeyword(token, name).enqueue(object :Callback<ResponsegetSubjectWithKeyWord>{
            override fun onFailure(call: Call<ResponsegetSubjectWithKeyWord>, t: Throwable) {
                onFailure(t.localizedMessage)
            }

            override fun onResponse(call: Call<ResponsegetSubjectWithKeyWord>, response: Response<ResponsegetSubjectWithKeyWord>) {
                response.body()?.let {
                    onSuccess(it.data!!)
                } ?: onFailure(response.message())
            }
        })
    }

    fun getSubjectWithword(name: String, onSuccess: (List<SearchedData>) -> Unit, onFailure: (String) -> Unit){
        retrofit.getSubjectWithWord(token, name).enqueue(object : Callback<ResponsegetSubjectWithWord>{
            override fun onFailure(call: Call<ResponsegetSubjectWithWord>, t: Throwable) {
                onFailure(t.localizedMessage)
            }

            override fun onResponse(call: Call<ResponsegetSubjectWithWord>, response: Response<ResponsegetSubjectWithWord>) {
                response.body()?.let {
                    onSuccess(it.data)
                } ?: onFailure(response.message())
            }
        })
    }

    fun requestUniversityAuth(requestUniversityAuth: RequestUniversityAuth, onSuccess: (String) -> Unit, onFailure: (String) -> Unit){
        retrofit.requestUniversityAuth(token,requestUniversityAuth).enqueue(object : Callback<ResponseUniversityAuth>{
            override fun onFailure(call: Call<ResponseUniversityAuth>, t: Throwable) {
                onFailure(t.localizedMessage)
            }

            override fun onResponse(call: Call<ResponseUniversityAuth>, response: Response<ResponseUniversityAuth>) {
                response.body()?.let {
                    onSuccess(it.data.authNum)
                } ?: onFailure(response.message())
            }
        })
    }

    fun requestWithdraw(password : String, onSuccess: () -> Unit, onFailure: (String) -> Unit){
        retrofit.withdraw(token,RequestWithdraw(password)).enqueue(object : Callback<Response<Void>>{
            override fun onFailure(call: Call<Response<Void>>, t: Throwable) {
                onFailure(t.localizedMessage)
            }

            override fun onResponse(call: Call<Response<Void>>, response: Response<Response<Void>>) {
                response.body()?.let {
                    onSuccess()
                } ?: onFailure(response.message())
            }
        })
    }

}