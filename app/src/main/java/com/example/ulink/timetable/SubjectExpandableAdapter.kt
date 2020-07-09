package com.example.ulink.timetable

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.example.ulink.R
import com.example.ulink.repository.Subject

class SubjectExpandableAdapter(val context : Context) : BaseExpandableListAdapter() {

//    TODO 여기서 !!때문에 에러날수 있는곳 fail 테스트 만들기

//    group에서 필요한 데이터 과목명, 대표교수및 교수몇명인지
//    child에서 필요한 데이터 group의 과목명에 맞는 subject


    class SubjectRep(
            var subjectName : String,
            var professorNum : Int,
            var professor : String
    )

    lateinit var subjectDataList : HashMap<String, List<Subject>>
    lateinit var subjectNameList : List<SubjectRep>

    override fun getGroup(groupPosition: Int): Any {
        return this.subjectNameList[groupPosition]
    }


    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        val subjectRep = getGroup(groupPosition) as SubjectRep
        var view = convertView
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_subject_group, null)
        }
        view!!.findViewById<TextView>(R.id.tv_class_name).text = subjectRep.subjectName
        view.findViewById<TextView>(R.id.tv_professors).text = subjectRep.professor +"외 ${subjectRep.professorNum}명"
        return view
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return subjectDataList[subjectNameList[groupPosition].subjectName]!!.size
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return subjectDataList[subjectNameList[groupPosition].subjectName]!![childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        val subject = getChild(groupPosition, childPosition) as Subject
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_subject_child, null)
        }
        view!!.findViewById<TextView>(R.id.tv_class_name).text = subject.name
        view.findViewById<TextView>(R.id.tv_professor_name).text = subject.professor
        view.findViewById<TextView>(R.id.tv_time).text = subject.starttime + subject.endtime
        view.findViewById<TextView>(R.id.tv_place).text = subject.place
        view.findViewById<TextView>(R.id.tv_category).text = subject.course
        view.findViewById<TextView>(R.id.tv_credit).text = subject.credit.toString()

        return view
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getGroupCount(): Int {
        return this.subjectNameList.size
    }

}