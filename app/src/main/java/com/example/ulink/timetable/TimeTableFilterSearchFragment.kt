package com.example.ulink.timetable

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ulink.Activity.*
import com.example.ulink.R
import com.example.ulink.repository.SearchedData
import com.example.ulink.repository.Subject
import kotlinx.android.synthetic.main.fragment_timetablefiltersearch.*
const val REQUEST_FILTER_SETTING_SEARCH_ACTIVITY = 666
const val REQUEST_FILTER_MAJOR_ACTIVITY = 555
class TimeTableFilterSearchFragment() : Fragment(), onCartAddClickListener {
    lateinit var mAdapter : TimeTableClassAdapter
    var subjectList: MutableList<Subject> = arrayListOf()
    var prevent = true


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_timetablefiltersearch, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val bundle = arguments
        val message = bundle?.getSerializable("item")

        super.onViewCreated(view, savedInstanceState)

        mAdapter = TimeTableClassAdapter(requireContext(), object : onItemClickListener{
            override fun onItemClicked(position: Int) {
            }
        }, this)
        rv_classes.adapter = mAdapter
        mAdapter.addToList(subjectList)
        et_class_name.setOnFocusChangeListener { v, hasFocus ->
            if (prevent){
                val intent = Intent(context,
                    FilterSettingSearchActivity::class.java)
                startActivityForResult(intent, REQUEST_FILTER_SETTING_SEARCH_ACTIVITY)
                prevent = false
                et_class_name.clearFocus()
            }
        }

        btn_fitler_major.setOnClickListener {
            val intent = Intent(context,
                FilterMajorActivity::class.java)
            startActivityForResult(intent, REQUEST_FILTER_MAJOR_ACTIVITY)
        }

        btn_filter_normal.setOnClickListener {
            val intent = Intent(context, FilterNormalActivity::class.java)
            startActivityForResult(intent, REQUEST_FILTER_MAJOR_ACTIVITY)
        }
    }
    override fun onResume() {
        super.onResume()
        prevent = true
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_FILTER_SETTING_SEARCH_ACTIVITY){
            et_class_name.setText(data?.getStringExtra("query"))
        }

        if(resultCode== RESULT_SEARCHED){
            val list = data?.getParcelableArrayListExtra<SearchedData>("searchedData")
            val class_name = data?.getStringExtra("et_class_name")
            et_class_name.setText(class_name)

            subjectList.clear()
            for(i in 0 until list!!.size) {
                subjectList.add(Subject(
                    0,
                    list[i].name,
                    list[i].startTime,
                    list[i].endTime,
                    list[i].day,
                    list[i].content,
                    1,
                    true,
                    list[i].credit,
                    list[i].professor,
                    list[i].course,
                    true,
                    list[i].subjectCode,  //학수번호
                    list[i].subjectIdx.toInt()))
            }
            mAdapter.notifyDataSetChanged()

        }
        if(resultCode== RESULT_CLICKED){
            val list = data?.getParcelableArrayListExtra<SearchedData>("searchedData")
            val class_name = data?.getStringExtra("et_class_name")
            rv_classes.adapter = mAdapter
            et_class_name.setText(class_name)
            if(list!=null) {
                subjectList.clear()
                for(i in 0 until list!!.size) {
                    subjectList.add(Subject(
                            0,
                            list[i].name,
                            list[i].startTime,
                            list[i].endTime,
                            list[i].day,
                            list[i].content,
                            1,
                            true,
                            list[i].credit,
                            list[i].professor,
                            list[i].course,
                            true,
                            list[i].subjectCode,  //학수번호
                            list[i].subjectIdx.toInt()))
                }
                mAdapter.notifyDataSetChanged()
            }

        }

    }
    interface onItemClickListener{
        fun onItemClicked(position : Int)
    }

    override fun onClicked() : String{
        return(context as TimeTableEditActivity).getSemesterFromActivity()
    }

}

interface onCartAddClickListener{
    fun onClicked() : String
}
