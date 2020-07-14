package com.example.ulink.timetable

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.example.ulink.R

class TimeTableExpandableAdapter(val context : Context) : BaseExpandableListAdapter() {

//    TODO 여기서 !!때문에 에러날수 있는곳 fail 테스트 만들기

//    group에서 필요한 데이터 과목명, 대표교수및 교수몇명인지
//    child에서 필요한 데이터 group의 과목명에 맞는 subject


    lateinit var majorDataList : HashMap<String, List<String>>
    lateinit var majorNameList : List<String>

    override fun getGroup(groupPosition: Int): Any {
        return this.majorNameList[groupPosition]
    }


    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        val majorGroup = getGroup(groupPosition) as String
        var view = convertView
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_major_group, null)
        }
        view!!.findViewById<TextView>(R.id.tv_name).text = majorGroup
//        view.findViewById<TextView>(R.id.iv_arrow).text =
        return view
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return majorDataList[majorNameList[groupPosition]]!!.size
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return majorDataList[majorNameList[groupPosition]]!![childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        val majorChild = getChild(groupPosition, childPosition) as String
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_major_child, null)
        }
        view!!.findViewById<TextView>(R.id.tv_name).text = majorChild

        return view
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getGroupCount(): Int {
        return this.majorNameList.size
    }

}