package com.example.ulink

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ulink.TimeTable_Search_Recycler.SearchData
import com.example.ulink.TimeTable_Search_Recycler.TimeTable_Search_Adapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_filtersetting_search.*

class FilterSettingSearchActivity : AppCompatActivity() {
    val datas : MutableList<SearchData> = mutableListOf<SearchData>()
    lateinit var TimeTable_Search_Adapter : TimeTable_Search_Adapter
    lateinit var filter_name :String

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





        edit.setOnFocusChangeListener { v, hasFocus ->
            TimeTable_Search_Adapter.viewType = 1
            TimeTable_Search_Adapter.notifyDataSetChanged()
//         foucs전에는 최근검색
//         focus후에는 자동완성
//         스피너 아이템 별로 sharepref저장
        }
        edit.setOnEditorActionListener { v, actionId, event ->
            Log.d("tag1",v.text.toString())

            if (actionId == EditorInfo.IME_ACTION_DONE){

                Log.d("tag2",v.text.toString())
                recentList?.add(v.text.toString())
                editor.putStringSet("recentSearch", recentList)
                editor.commit()
//                TODO 서버 search해서 adapter로 데이터 넣어주기! 리스트 아예 새로 만들어줘야 안섞임 notify도 잘하기

                TimeTable_Search_Adapter.viewType = 1
                TimeTable_Search_Adapter.notifyDataSetChanged()
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