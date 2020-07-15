package com.example.ulink.timetable
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ulink.FilterMajorActivity
import com.example.ulink.FilterNormalActivity
import com.example.ulink.FilterSettingSearchActivity
import com.example.ulink.R
import com.example.ulink.repository.Subject
import kotlinx.android.synthetic.main.fragment_timetablefiltersearch.*
const val REQUEST_FILTER_SETTING_SEARCH_ACTIVITY = 666
const val REQUEST_FILTER_MAJOR_ACTIVITY = 555
class TimeTableFilterSearchFragment() : Fragment() {


    lateinit var mAdapter : TimeTableClassAdapter
    var subjectList: MutableList<Subject> = arrayListOf()

    var prevent = true
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_timetablefiltersearch, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


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
            val intent = Intent(context,FilterMajorActivity::class.java)
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
    }
    interface onItemClickListener{
        fun onItemClicked(position : Int)
    }
}