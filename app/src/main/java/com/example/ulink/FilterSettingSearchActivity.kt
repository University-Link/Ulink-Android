package com.example.ulink

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ulink.TimeTable_Search_Recycler.SearchData
import com.example.ulink.TimeTable_Search_Recycler.TimeTable_Search_Adapter
import com.example.ulink.repository.ResponsegetSubjectWithWord
import com.example.ulink.repository.RetrofitService
import com.example.ulink.repository.SearchedData
import com.example.ulink.timetable.TimeTableFilterSearchFragment
import com.example.ulink.timetable.token
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_filtersetting_search.*
import kotlinx.android.synthetic.main.fragment_timetablefiltersearch.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList


class FilterSettingSearchActivity : AppCompatActivity() {
    val datas : MutableList<SearchData> = mutableListOf<SearchData>()
    lateinit var TimeTable_Search_Adapter : TimeTable_Search_Adapter
    lateinit var filter_name :String
    val list : MutableList<SearchedData> = arrayListOf()

//    TODO 아이템 클릭이나 검색버튼 클릭하면 setresult

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filtersetting_search)

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.filter_setting,
            android.R.layout.simple_spinner_item
        )

        //btn_reset.textResetButton(edit) 검색x버튼추가

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
        //loadDatas()

        TimeTable_Search_Adapter.itemClick = object : TimeTable_Search_Adapter.ItemClick{
            override fun onClick(view: View, position: Int) {
                //list[position]을 TimeTableFilterSearchFragment 에전달 후 띄워줘야함
                val intent = Intent()
                intent.putExtra("item",list[position])
                setResult(300,intent)
                intent.putExtra("et_class_name",edit.text.toString())
                finish()

               // finish()
            }

        }

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

        edit.textChangedListener {

            RetrofitService.service.getSubjectWithWord(token,edit.text.toString()).enqueue(object : Callback<ResponsegetSubjectWithWord>{
                override fun onFailure(call: Call<ResponsegetSubjectWithWord>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<ResponsegetSubjectWithWord>,
                    response: Response<ResponsegetSubjectWithWord>
                ) {
                    response.body()?.let{
                        if(it.status == 200){
                            //Log.d("검색성공",it.toString())

                            list.clear()
                            datas.clear()

                            if(edit.text.toString()!=""){
                                list.addAll(it.data)
                            }
                            for (i in 0 until list.size) {
                                Log.d("데이터", it.toString())
                                datas.apply {
                                    add(
                                        SearchData(
                                            search_result = list[i].name,
                                            search_type = ""
                                        )
                                    )
                                }
                            }
//                                list.clear()

                            TimeTable_Search_Adapter.searchdatas = datas
                            TimeTable_Search_Adapter.viewType = 1
                            TimeTable_Search_Adapter.notifyDataSetChanged()
                            editor.commit()

                        }else{
                            Log.d("검색실패",it.toString())
                        }
                    }
                }
            })
        }

        edit.setOnEditorActionListener { v, actionId, event ->

            Log.d("tag",actionId.toString())
            if (actionId == EditorInfo.IME_ACTION_SEARCH){

//                recentList?.add(v.text.toString())
//                editor.putStringSet("recentSearch", recentList)

//                Log.d("search클릭","search")
//                TimeTable_Search_Adapter.viewType = 1
//                TimeTable_Search_Adapter.notifyDataSetChanged()
//                editor.commit()

                //datas 넘겨주기
                val intent = Intent()
                intent.putParcelableArrayListExtra("list",list as ArrayList<out Parcelable>)
                setResult(200,intent)
                intent.putExtra("et_class_name",edit.text.toString())
                finish()

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
                    search_type = ""
                )
            )

        }
        TimeTable_Search_Adapter.searchdatas = datas
        TimeTable_Search_Adapter.notifyDataSetChanged()
    }
}
