package com.example.ulink

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ulink.ChattingRecycler.ChattingAdapter
import com.example.ulink.ChattingRecycler.ChattingData
import com.example.ulink.TimeTable_Search_Recycler.SearchData
import com.example.ulink.TimeTable_Search_Recycler.TimeTable_Search_Adapter
import com.example.ulink.timetable.TimeTableAddAdapter
import kotlinx.android.synthetic.main.activity_filtersetting.*
import kotlinx.android.synthetic.main.activity_main_content.*

class FilterSettingActivity : AppCompatActivity() {
    val datas : MutableList<SearchData> = mutableListOf<SearchData>()
    lateinit var TimeTable_Search_Adapter : TimeTable_Search_Adapter
    lateinit var filter_name :String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filtersetting)

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

        loadDatas()

        spinner_filter_setting.adapter = adapter
        spinner_filter_setting.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long){
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
                    search_result = "전자기학"

                )
            )

        }
        TimeTable_Search_Adapter.searchdatas = datas
        TimeTable_Search_Adapter.notifyDataSetChanged()
    }

}