package com.example.ulink.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ulink.ChattingActivity
import com.example.ulink.ClassRecycler.ClassAdapter
import com.example.ulink.ClassRecycler.ClassData
import com.example.ulink.R
import kotlinx.android.synthetic.main.fragment_class.*

class ClassFragment : Fragment() {
    lateinit var ClassAdapter : ClassAdapter
    val datas : MutableList<ClassData> = mutableListOf<ClassData>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_class, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ClassAdapter = ClassAdapter(view.context)
        ClassAdapter.setItemClickLIstener(object:ClassAdapter.ItemClickListener{
            override fun onClick(view:View, position:Int){
                //Log.d("click","${position}번 리스트 선택")
                val intent = Intent(getActivity(), ChattingActivity::class.java)
                startActivity(intent)
            }
        })
        rv_class.adapter = ClassAdapter
        loadDatas()
    }

    private fun loadDatas(){
        datas.apply{
            add(
                ClassData(
                    ClassImage="https://img1.daumcdn.net/thumb/R720x0/?fname=https%3A%2F%2Ft1.daumcdn.net%2Fliveboard%2Fholapet%2F0e5f90af436e4c218343073164a5f657.JPG",
                    ClassName = "전자기학1",
                    now = 17,
                    total = 20,
                    count = 6
                )
            )
            add(
                ClassData(
                    ClassImage="https://img1.daumcdn.net/thumb/R720x0/?fname=https%3A%2F%2Ft1.daumcdn.net%2Fliveboard%2Fholapet%2F0e5f90af436e4c218343073164a5f657.JPG",
                    ClassName = "전자기학1",
                    now = 17,
                    total = 20,
                    count = 6
                )
            )
            add(
                ClassData(
                    ClassImage="https://img1.daumcdn.net/thumb/R720x0/?fname=https%3A%2F%2Ft1.daumcdn.net%2Fliveboard%2Fholapet%2F0e5f90af436e4c218343073164a5f657.JPG",
                    ClassName = "전자기학1",
                    now = 17,
                    total = 20,
                    count = 6
                )
            )
            add(
                ClassData(
                    ClassImage="https://img1.daumcdn.net/thumb/R720x0/?fname=https%3A%2F%2Ft1.daumcdn.net%2Fliveboard%2Fholapet%2F0e5f90af436e4c218343073164a5f657.JPG",
                    ClassName = "전자기학1",
                    now = 17,
                    total = 20,
                    count = 6
                )
            )
            add(
                ClassData(
                    ClassImage="https://img1.daumcdn.net/thumb/R720x0/?fname=https%3A%2F%2Ft1.daumcdn.net%2Fliveboard%2Fholapet%2F0e5f90af436e4c218343073164a5f657.JPG",
                    ClassName = "전자기학1",
                    now = 17,
                    total = 20,
                    count = 6
                )
            )
            add(
                ClassData(
                    ClassImage="https://img1.daumcdn.net/thumb/R720x0/?fname=https%3A%2F%2Ft1.daumcdn.net%2Fliveboard%2Fholapet%2F0e5f90af436e4c218343073164a5f657.JPG",
                    ClassName = "전자기학1",
                    now = 17,
                    total = 20,
                    count = 6
                )
            )
            add(
                ClassData(
                    ClassImage="https://img1.daumcdn.net/thumb/R720x0/?fname=https%3A%2F%2Ft1.daumcdn.net%2Fliveboard%2Fholapet%2F0e5f90af436e4c218343073164a5f657.JPG",
                    ClassName = "전자기학1",
                    now = 17,
                    total = 20,
                    count = 6
                )
            )
            add(
                ClassData(
                    ClassImage="https://img1.daumcdn.net/thumb/R720x0/?fname=https%3A%2F%2Ft1.daumcdn.net%2Fliveboard%2Fholapet%2F0e5f90af436e4c218343073164a5f657.JPG",
                    ClassName = "전자기학1",
                    now = 17,
                    total = 20,
                    count = 6
                )
            )
            add(
                ClassData(
                    ClassImage="https://img1.daumcdn.net/thumb/R720x0/?fname=https%3A%2F%2Ft1.daumcdn.net%2Fliveboard%2Fholapet%2F0e5f90af436e4c218343073164a5f657.JPG",
                    ClassName = "전자기학1",
                    now = 17,
                    total = 20,
                    count = 6
                )
            )
            add(
                ClassData(
                    ClassImage="https://img1.daumcdn.net/thumb/R720x0/?fname=https%3A%2F%2Ft1.daumcdn.net%2Fliveboard%2Fholapet%2F0e5f90af436e4c218343073164a5f657.JPG",
                    ClassName = "전자기학1",
                    now = 17,
                    total = 20,
                    count = 6
                )
            )

        }
        ClassAdapter.datas = datas
        ClassAdapter.notifyDataSetChanged()
    }


}