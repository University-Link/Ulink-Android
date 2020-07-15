package com.example.ulink

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ulink.TimeTable_Search_Recycler.SearchData
import com.example.ulink.TimeTable_Search_Recycler.TimeTable_Search_Adapter
import com.example.ulink.repository.ResponsegetSubjectWithWord
import com.example.ulink.repository.RetrofitService
import com.example.ulink.repository.SearchedData
import com.example.ulink.repository.SubjectListByGrade
import com.example.ulink.timetable.token
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_filtersetting_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilterSettingSearchActivity : AppCompatActivity() {
    val datas : MutableList<SearchData> = mutableListOf<SearchData>()
    lateinit var TimeTable_Search_Adapter : TimeTable_Search_Adapter
    lateinit var filter_name :String
    val list : MutableList<SearchedData> = arrayListOf()

    val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4IjoxLCJuYW1lIjoi6rmA67O067CwIiwic2Nob29sIjoi7ZWc7JaR64yA7ZWZ6rWQIiwibWFqb3IiOiLshoztlITtirjsm6jslrQiLCJpYXQiOjE1OTQ4MTY1NzQsImV4cCI6MTU5NjI1NjU3NCwiaXNzIjoiYm9iYWUifQ.JwRDELH1lA1Fb8W1ltTmhThpmgFrUTQZVocUTATv3so"
//    TODO 아이템 클릭이나 검색버튼 클릭하면 setresult

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filtersetting_search)

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.filter_setting,
            android.R.layout.simple_spinner_item
        )

        TimeTable_Search_Adapter = TimeTable_Search_Adapter(this)
        rv_search.adapter = TimeTable_Search_Adapter

        val lm = LinearLayoutManager(this)
        rv_search.layoutManager = lm
        rv_search.setHasFixedSize(true)

        val pref = getSharedPreferences("recentSearch", 0)
        val editor = pref.edit()

//        val collectionType = object : TypeToken<ArrayList<String>>() {}.type
//        val recentList = Gson().fromJson<ArrayList<String>>(pref.getString("recentSearch", ""), collectionType)


        var recentList: MutableSet<String>? = HashSet()
        recentList = pref.getStringSet("recentSearch", null)
        if (recentList != null) {
            TimeTable_Search_Adapter.recentdatas.addAll(recentList)
            Log.d("tag","added")
        }
        loadDatas()



        btn_back.setOnClickListener {
            finish()
        }

//        edit.setOnFocusChangeListener { v, hasFocus ->
//            //Log.d("단어??",v.toString())
//            Log.d("단어",edit.text.toString())
//
//
//            TimeTable_Search_Adapter.viewType = 1
//            TimeTable_Search_Adapter.notifyDataSetChanged()
////         foucs전에는 최근검색
////         focus후에는 자동완성
////         스피너 아이템 별로 sharepref저장
//        }

//        edit.textChangedListener {
//
//        }
        edit.setOnEditorActionListener { v, actionId, event ->
            Log.d("검색단어",v.text.toString())

            RetrofitService.service.getSubjectWithWord(token,edit.text.toString()).enqueue(object : Callback<ResponsegetSubjectWithWord>{
                override fun onFailure(call: Call<ResponsegetSubjectWithWord>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<ResponsegetSubjectWithWord>,
                    response: Response<ResponsegetSubjectWithWord>
                ) {
                    response.body()?.let{
                        if(it.status == 200){
                            Log.d("검색성공",it.toString())
                            list.clear()
                            list.addAll(it.data)
                            for(i in 0 until list.size)
                            {
                                Log.d("데이",list[i].toString())
                                datas.apply {
                                    add(
                                        SearchData(
                                            search_result = list[i].name,
                                            search_type = "1"
                                        )
                                    )

                                }
                            }

                            TimeTable_Search_Adapter.searchdatas = datas
                            TimeTable_Search_Adapter.notifyDataSetChanged()
                        }else{
                            Log.d("검색실패",it.toString())

                        }
                    }
                }
            })

            Log.d("tag",actionId.toString())
            if (actionId == EditorInfo.IME_ACTION_DONE){
                list.clear()

                recentList?.add(v.text.toString())
                editor.putStringSet("recentSearch", recentList)

//                TODO 서버 search해서 adapter로 데이터 넣어주기! 리스트 아예 새로 만들어줘야 안섞임 notify도 잘하기


                TimeTable_Search_Adapter.viewType = 1
                TimeTable_Search_Adapter.notifyDataSetChanged()
                editor.commit()

                return@setOnEditorActionListener true


            }

            return@setOnEditorActionListener false
        }


        spinner_filter_setting.adapter = adapter
        spinner_filter_setting.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long){
                TimeTable_Search_Adapter.viewType = 0
                TimeTable_Search_Adapter.notifyDataSetChanged()
                when(position){
                    0 ->{
                        filter_name ="과목명"
                    }
                    1->{
                        filter_name ="교수명"
                    }
                    2->{
                        filter_name ="학수번호"
                    }
                }
            }
        }





    }
    private fun loadDatas() {
        datas.apply {
            add(
                SearchData(
                    search_result = "전자기학",
                        search_type = "0"
                )
            )

        }
        TimeTable_Search_Adapter.searchdatas = datas
        TimeTable_Search_Adapter.notifyDataSetChanged()
    }
}