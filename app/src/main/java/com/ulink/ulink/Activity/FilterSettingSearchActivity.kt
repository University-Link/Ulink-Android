package com.ulink.ulink.Activity

import android.content.Context
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
import com.ulink.ulink.R
import com.ulink.ulink.TimeTable_Search_Recycler.SearchData
import com.ulink.ulink.TimeTable_Search_Recycler.TimeTable_Search_Adapter
import com.ulink.ulink.repository.*
import com.ulink.ulink.textChangedListener
import com.ulink.ulink.textResetButton
import kotlinx.android.synthetic.main.activity_filtersetting_search.*
import java.util.ArrayList


const val RESULT_SEARCHED = 200
const val RESULT_CLICKED = 300


class FilterSettingSearchActivity : AppCompatActivity() {

    lateinit var timetableSearchAdapter : TimeTable_Search_Adapter
    lateinit var filterName :String

    val datas : MutableList<SearchData> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filtersetting_search)

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.filter_setting,
            android.R.layout.simple_spinner_item
        )

        btn_reset.textResetButton(edit) //검색x버튼추가

        timetableSearchAdapter = TimeTable_Search_Adapter(this)
        rv_search.adapter = timetableSearchAdapter

        val lm = LinearLayoutManager(this)
        rv_search.layoutManager = lm
        rv_search.setHasFixedSize(true)

        val sharedPreferences = getSharedPreferences("recentSearch", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

//        TODO 나중에 recentSearch에도 단순히 name말고 옆에 type도 같이 저장할 경우 이거 사용
//        val collectionType = object : TypeToken<ArrayList<String>>() {}.type
//        val recentList = Gson().fromJson<ArrayList<String>>(pref.getString("recentSearch", ""), collectionType)

        val recentList: MutableSet<String>?
        recentList = sharedPreferences.getStringSet("recentSearch", mutableSetOf())

        if (recentList != null) {
            val list = recentList.reversed()
            timetableSearchAdapter.recentdatas.addAll(list)
            timetableSearchAdapter.viewType = 0
            timetableSearchAdapter.notifyDataSetChanged()
        }
        Log.d("tag",recentList.toString())

        timetableSearchAdapter.itemClick = object : TimeTable_Search_Adapter.ItemClick{
            override fun onClick(className : String) {
                //list[position]을 TimeTableFilterSearchFragment 에전달 후 띄워줘야함
                DataRepository.getSubjectWithword(className,
                onSuccess = {

                    recentList?.add(className)
                    editor.clear()
                    editor.putStringSet("recentSearch", recentList)
                    editor.apply()

                    val intent = Intent()
                    intent.putParcelableArrayListExtra("searchedData", it as ArrayList<out Parcelable>)
                    intent.putExtra("et_class_name",edit.text.toString())
                    setResult(RESULT_CLICKED,intent)
                    finish()
                },
                onFailure = {

                })
            }
        }

        btn_back.setOnClickListener {
            finish()
        }

        edit.textChangedListener {
            if (it.isNullOrBlank()){
                timetableSearchAdapter.viewType = 0
                timetableSearchAdapter.notifyDataSetChanged()
                tv_recentSearch.visibility = View.VISIBLE
            } else{
                tv_recentSearch.visibility = View.GONE
                DataRepository.getSubjectRecommendWithKeyword(edit.text.toString(),
                        onSuccess = {
                            datas.clear()
                            for (i in it){
                                datas.add(SearchData(i,""))
                            }

                            timetableSearchAdapter.searchdatas = datas
                            timetableSearchAdapter.viewType = 1
                            timetableSearchAdapter.notifyDataSetChanged()
                        },
                        onFailure = {

                        })
            }
        }

        edit.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH){

                DataRepository.getSubjectWithword(edit.text.toString(),
                onSuccess = {

                    recentList?.add(edit.text.toString())
                    editor.clear()
                    editor.putStringSet("recentSearch", recentList)
                    editor.apply()

                    val intent = Intent()
                    intent.putParcelableArrayListExtra("searchedData",it as ArrayList<out Parcelable>)
                    intent.putExtra("et_class_name",edit.text.toString())
                    setResult(RESULT_SEARCHED,intent)
                    finish()
                },
                onFailure = {
//                  TODO 검색 실패시 검색결과 없음!!
                    finish()
                })

                return@setOnEditorActionListener true
            }

            return@setOnEditorActionListener false
        }


        spinner_filter_setting.adapter = adapter
        spinner_filter_setting.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long){
                timetableSearchAdapter.viewType = 0
                timetableSearchAdapter.notifyDataSetChanged()
                when(position){
                    0 ->{
                        filterName ="과목명"
                    }
                    1->{
                        filterName ="교수명"
                    }
                    2->{
                        filterName ="학수번호"
                    }
                }
            }
        }
    }

}
