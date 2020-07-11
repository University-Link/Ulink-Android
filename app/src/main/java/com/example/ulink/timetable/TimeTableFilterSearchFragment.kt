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
                SubjectExpandableAdapter.SubjectRep("전자회로3", 1, "박상규"),
                SubjectExpandableAdapter.SubjectRep("전자회로4", 3, "박상규")
        )

        val subjectList: MutableList<Subject> = arrayListOf()
        subjectList.add(Subject(1, "전자회로I", "09:00", "12:00", "mon", "과목장소", 1, true, isSample = true))
        subjectList.add(Subject(2, "전자회로I", "12:30", "13:00", "mon", "과목장소", 1, true, isSample = true))
        subjectList.add(Subject(3, "전자회로I", "11:00", "13:00", "fri", "과목장소", 1, true, isSample = true))
        subjectList.add(Subject(4, "전자회로I", "10:00", "12:00", "wed", "과목장소", 1, true, isSample = true))

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

//
//        TODO 여기서 OnClick으로 subject클릭하면 preview를 시간표에 표시하기!
//            Toast.makeText(requireContext(),"${dataList[subjectRepList[groupPosition].subjectName]!!.get(childPosition)}", Toast.LENGTH_SHORT).show()

        val previewList: MutableList<View> = arrayListOf()
        var firstClick = true


        ev_filterandsearch.setOnGroupClickListener { parent, v, groupPosition, id ->
            ev_filterandsearch.collapseGroup(groupPosition)
            previewList.clear()
            firstClick = true
            false
        }


        ev_filterandsearch.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->

            (activity as TimeTableEditActivity).addToSampleTable(dataList[subjectRepList[groupPosition].subjectName]!![childPosition])
            Log.d("tag", " other clicked")
            Log.d("tag", "add to table1")

            val assess = v.findViewById<Button>(R.id.btn_assess)
            val cart = v.findViewById<Button>(R.id.btn_cart)
            val totable = v.findViewById<Button>(R.id.btn_totable)

            assess.visibility = View.VISIBLE
            cart.visibility = View.VISIBLE
            totable.visibility = View.VISIBLE

            if (!firstClick) {
                val presassess = previewList.get(0)
                val precart = previewList.get(1)
                val pretotable = previewList.get(2)
                presassess.visibility = View.GONE
                precart.visibility = View.GONE
                pretotable.visibility = View.GONE

                previewList.clear()

//               리스트에 추가


            }
            firstClick = false
            previewList.add(assess)
            previewList.add(cart)
            previewList.add(totable)
            
            v.setOnClickListener {

                //                한번씩 돌고 같은뷰 접었다 폈다
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
                        Log.d("tag", "rollback")
                    } else {
                        (activity as TimeTableEditActivity).addToSampleTable(dataList[subjectRepList[groupPosition].subjectName]!![childPosition])
                        Log.d("tag", "add to table2")

                    }


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

                    Log.d("tag", "rollback")
                    Log.d("tag", "add to table3")

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
            }

            false
        }


//        Drawable icon = getResources().getDrawable(R.drawable.btn_expandable_group_indicator_state);
//
//        exList.setGroupIndicator(icon);
//
//
//
//        넣어준다음 위치를 변경 시킬 수 잇다..
//
//
//
//        exList.setIndicatorBounds(m_view.getList().getRight() - 100, m_view.getList().getWidth() - 100);
//
//
//
//                exList.setGroupIndicator(null);
//
//
//
//        시켜준다잉..
//
//
//
//                그럼 없애고 똑같은 기능을 가진 view 를 만들라면??
//
//
//
//        @Override
//        public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent)
//        {
//
//            if (isExpanded)
//            {
//                imgIndicator.setImageResource(R.drawable.arrow_down);
//            }
//            else
//            {
//                imgIndicator.setImageResource(R.drawable.arrow_up);
//            }
//
//        }

    }

}