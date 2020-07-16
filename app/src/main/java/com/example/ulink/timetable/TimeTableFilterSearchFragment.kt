package com.example.ulink.timetable
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ulink.FilterSettingSearchActivity
import com.example.ulink.R
import com.example.ulink.repository.SearchedData
import com.example.ulink.repository.Subject
import kotlinx.android.synthetic.main.fragment_timetablefiltersearch.*


const val REQUEST_FILTER_SETTING_SEARCH_ACTIVITY = 666
const val REQUEST_FILTER_MAJOR_ACTIVITY = 555
class TimeTableFilterSearchFragment() : Fragment() {
    lateinit var mAdapter : TimeTableClassAdapter
    var prevent = true
    val subjectList: MutableList<Subject> = arrayListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_timetablefiltersearch, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val bundle = arguments
        val message = bundle?.getSerializable("item")

        super.onViewCreated(view, savedInstanceState)
        subjectList.add(Subject(1, "전자회로I", "09:00", "12:00", 1, "과목장소", 1, true, isSample = true, number = "342"))
        subjectList.add(Subject(2, "전자회로2", "12:30", "13:00", 1, "과목장소", 1, true, isSample = true))
        subjectList.add(Subject(3, "전자회로3", "11:00", "13:00", 4, "과목장소", 1, true, isSample = true))
        subjectList.add(Subject(4, "전자회로4", "10:00", "12:00", 3, "과목장소", 1, true, isSample = true))
        subjectList.add(Subject(4, "전자회로5", "13:00", "16:00", 4, "과목장소", 1, true, isSample = true))
        subjectList.add(Subject(4, "전자회로6", "13:00", "17:30", 2, "과목장소", 1, true, isSample = true))
        mAdapter = TimeTableClassAdapter(requireContext(), object : onItemClickListener{
            override fun onItemClicked(position: Int) {
            }
        })
        rv_classes.adapter = mAdapter
        mAdapter.addToList(subjectList)
        et_class_name.setOnFocusChangeListener { v, hasFocus ->
            if (prevent){
                val intent = Intent(context,FilterSettingSearchActivity::class.java)
                startActivityForResult(intent, REQUEST_FILTER_SETTING_SEARCH_ACTIVITY)
                prevent = false
                et_class_name.clearFocus()
            }
        }


        btn_fitler_major.setOnClickListener {
//            TODO
//            val intent = Intent(context,TimeTableClassFilterActivity::class.java)
//            startActivityForResult(intent, REQUEST_FILTER_MAJOR_ACTIVITY)
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
        if(resultCode==200){ //한개 클릭 후 한개 리턴
            val list = data?.getParcelableArrayListExtra<SearchedData>("list")
            val class_name = data?.getStringExtra("et_class_name")
            et_class_name.setText(class_name)



        }
        if(resultCode==300){//해당하는 모든 검색결과 리턴
            val item = data?.getParcelableExtra<SearchedData>("item")
            val class_name = data?.getStringExtra("et_class_name")
            rv_classes.adapter = mAdapter
            et_class_name.setText(class_name)


        }

    }

    interface onItemClickListener{
        fun onItemClicked(position : Int)
    }
}