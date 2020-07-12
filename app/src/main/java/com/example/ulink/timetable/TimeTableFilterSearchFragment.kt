package com.example.ulink.timetable

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.ulink.R
import com.example.ulink.repository.Subject
import kotlinx.android.synthetic.main.fragment_timetablefiltersearch.*

class TimeTableFilterSearchFragment() : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_timetablefiltersearch, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


////       TODO 서버에서 받은 데이터(아마 SubjectDetail 을  시간표에 추가해줄때는 Subject클래스로 변환해주기
//        이거 대신에 Subject를 사용하되 SubjectRep로 변환해서 groupview에 사용

        val subjectRepList: MutableList<SubjectExpandableAdapter.SubjectRep> = arrayListOf(
                SubjectExpandableAdapter.SubjectRep("전자회로I", 3, "박상규"),
                SubjectExpandableAdapter.SubjectRep("전자회로II", 2, "박상규"),
                SubjectExpandableAdapter.SubjectRep("전자회로I", 1, "박상규"),
                SubjectExpandableAdapter.SubjectRep("전자회로I", 3, "박상규"),
                SubjectExpandableAdapter.SubjectRep("전자회로I", 3, "박상규"),
                SubjectExpandableAdapter.SubjectRep("전자회로I", 3, "박상규"),
                SubjectExpandableAdapter.SubjectRep("전자회로I", 3, "박상규"),
                SubjectExpandableAdapter.SubjectRep("전자회로I", 3, "박상규"),
                SubjectExpandableAdapter.SubjectRep("전자회로I", 3, "박상규"),
                SubjectExpandableAdapter.SubjectRep("전자회로I", 3, "박상규")
        )

        val subjectList: MutableList<Subject> = arrayListOf()
        subjectList.add(Subject(1, "전자회로I", "09:00", "12:00", "mon", "과목장소", 1, true, isSample = true))
        subjectList.add(Subject(2, "전자회로I", "12:30", "13:00", "mon", "과목장소", 1, true, isSample = true))
        subjectList.add(Subject(3, "전자회로I", "11:00", "13:00", "fri", "과목장소", 1, true, isSample = true))
        subjectList.add(Subject(4, "전자회로I", "10:00", "12:00", "wed", "과목장소", 1, true, isSample = true))
        subjectList.add(Subject(4, "전자회로I", "13:00", "16:00", "thu", "과목장소", 1, true, isSample = true))
        subjectList.add(Subject(4, "전자회로I", "13:00", "17:30", "wed", "과목장소", 1, true, isSample = true))
        subjectList.add(Subject(4, "전자회로I", "13:00", "17:30", "wed", "과목장소", 1, true, isSample = true))
        subjectList.add(Subject(4, "전자회로I", "13:00", "17:30", "wed", "과목장소", 1, true, isSample = true))

        val subjectList2: MutableList<Subject> = arrayListOf()
        subjectList2.add(Subject(1, "전자회로II", "09:00", "12:00", "mon", "과목장소", 1, true))
        subjectList2.add(Subject(2, "전자회로II", "14:00", "16:00", "mon", "과목장소", 1, true))
        subjectList2.add(Subject(3, "전자회로II", "11:00", "13:00", "tue", "과목장소", 1, true))
        subjectList2.add(Subject(4, "전자회로II", "14:00", "16:00", "wed", "과목장소", 1, true))


        val dataList: HashMap<String, List<Subject>> = hashMapOf(
                "전자회로I" to subjectList, "전자회로II" to subjectList2
        )


        val adapter = SubjectExpandableAdapter(requireContext())
        adapter.subjectNameList = subjectRepList
        adapter.subjectDataList = dataList

        ev_filterandsearch.setAdapter(adapter)


        val previewList: MutableList<View> = arrayListOf()

        ev_filterandsearch.setOnGroupClickListener { parent, v, groupPosition, id ->
            (activity as TimeTableEditActivity).rollBack()

            if (parent.isGroupExpanded(groupPosition)) {
                parent.collapseGroup(groupPosition)
            } else {
                parent.expandGroup(groupPosition, false)
            }

            for (i in previewList) {
                i.visibility = View.GONE
            }
            previewList.clear()

            true
        }

        val previousGroup = -1
        val previousChild = -1
        val previousViewList: MutableList<View> = arrayListOf()

        
//        TODO 정리
//         전체 onclick은 닫혀있는거 처음으로 열때만
//         한번 열린것은 안에서 관리

        ev_filterandsearch.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->

            Log.d("tag", "클릭됨 groupposi = $groupPosition")
            Log.d("tag", "클릭됨 childposi = $childPosition")

            val assess = v.findViewById<Button>(R.id.btn_assess)
            val cart = v.findViewById<Button>(R.id.btn_cart)
            val totable = v.findViewById<Button>(R.id.btn_totable)


//            ev_filterandsearch.setSelectedChild(groupPosition,childPosition,false)

            (activity as TimeTableEditActivity).rollBack()
            (activity as TimeTableEditActivity).addToSampleTable(dataList[subjectRepList[groupPosition].subjectName]!![childPosition])
//            Log.d("tag", " other clicked")
//            Log.d("tag", "add to table1")

            if (previewList.size>0){
                Log.d("tag","새로 접었다 폈다 첫클릭 아님 밑에거 ㄴㄴ ")

                val presassess = previewList.get(0)
                val precart = previewList.get(1)
                val pretotable = previewList.get(2)

                presassess.visibility = View.GONE
                precart.visibility = View.GONE
                pretotable.visibility = View.GONE

                previewList.clear()

                previewList.add(assess)
                previewList.add(cart)
                previewList.add(totable)

            } else {
                previewList.clear()
                previewList.add(assess)
                previewList.add(cart)
                previewList.add(totable)
            }

            assess.visibility = View.VISIBLE
            cart.visibility = View.VISIBLE
            totable.visibility = View.VISIBLE


            v.setOnClickListener {
                Log.d("tag", v.toString())

                Log.d("tag","이미 저장된거 접었다 폈다")
                //                한번씩 돌고 같은뷰 접었다 폈다
                if(previewList.size>0){
                    if (previewList[0] == v.findViewById(R.id.btn_assess)) {

                        previewList.clear()

                        if (assess.visibility == View.VISIBLE) {
                            assess.visibility = View.GONE
                        } else {
                            assess.visibility = View.VISIBLE
                        }
                        if (cart.visibility == View.VISIBLE) {
                            cart.visibility = View.GONE
                        } else {
                            cart.visibility = View.VISIBLE
                        }
                        if (totable.visibility == View.VISIBLE) {
                            totable.visibility = View.GONE
                        } else {
                            totable.visibility = View.VISIBLE
                        }

                        previewList.add(assess)
                        previewList.add(cart)
                        previewList.add(totable)

                        if (assess.visibility == View.GONE ){
//                    rollback()
                            (activity as TimeTableEditActivity).rollBack()
//                        Log.d("tag", "rollback")
                        } else {
                            (activity as TimeTableEditActivity).addToSampleTable(dataList[subjectRepList[groupPosition].subjectName]!![childPosition])
//                        Log.d("tag", "add to table2")
                        }

//                    todo A 펼쳤다가 A 접었다가 a 펼치고 B 펼치면 A 롤백

//                한번씩 돌고 다른 뷰를 펼쳤을때
                    } else {
                        val preassess = previewList.get(0)
                        val precart = previewList.get(1)
                        val pretotable = previewList.get(2)

                        preassess.visibility = View.GONE
                        precart.visibility = View.GONE
                        pretotable.visibility = View.GONE


                        previewList.clear()


                        assess.visibility = View.VISIBLE
                        cart.visibility = View.VISIBLE
                        totable.visibility = View.VISIBLE

                        (activity as TimeTableEditActivity).rollBack()
                        (activity as TimeTableEditActivity).addToSampleTable(dataList[subjectRepList[groupPosition].subjectName]!![childPosition])
//
//                    Log.d("tag", "rollback")
//                    Log.d("tag", "add to table3")

                        previewList.add(assess)
                        previewList.add(cart)
                        previewList.add(totable)
                    }
                } else {
                    if (assess.visibility == View.VISIBLE) {
                        assess.visibility = View.GONE
                    } else {
                        assess.visibility = View.VISIBLE
                    }
                    if (cart.visibility == View.VISIBLE) {
                        cart.visibility = View.GONE
                    } else {
                        cart.visibility = View.VISIBLE
                    }
                    if (totable.visibility == View.VISIBLE) {
                        totable.visibility = View.GONE
                    } else {
                        totable.visibility = View.VISIBLE
                    }

                    previewList.add(assess)
                    previewList.add(cart)
                    previewList.add(totable)
                }

            }



            v.findViewById<Button>(R.id.btn_cart).setOnClickListener {
                val builder = AlertDialog.Builder(context)
                val layout = LayoutInflater.from(context).inflate(R.layout.dialog_timetable_add_to_candidate, null)
                builder.setView(layout)
                val dialog = builder.create()
                layout.findViewById<TextView>(R.id.tv_added).text = "후보가 등록되었습니다"
                dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog.show()

//                db에 저장
//                dataList[subjectRepList[groupPosition].subjectName]!![childPosition]
            }

//          여기는 시간표로 완전 등록
            v.findViewById<Button>(R.id.btn_totable).setOnClickListener {
                (activity as TimeTableEditActivity).addToTable(dataList[subjectRepList[groupPosition].subjectName]!![childPosition])
                Log.d("tag,", "plus clicked")
            }

            true
        }


    }

}