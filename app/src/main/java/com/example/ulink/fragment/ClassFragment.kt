package com.example.ulink.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        rv_class.adapter = ClassAdapter
        loadDatas()
    }

    private fun loadDatas(){
        datas.apply{
            add(
                ClassData(
                    ClassImage = "",
                    ClassName = "전자기학1",
                    now = 17,
                    total = 20,
                    count = 6
                )
            )
            add(
                ClassData(
                    ClassImage = "",
                    ClassName = "전자기학1",
                    now = 17,
                    total = 20,
                    count = 6
                )
            )
            add(
                ClassData(
                    ClassImage = "",
                    ClassName = "전자기학1",
                    now = 17,
                    total = 20,
                    count = 6
                )
            )
            add(
                ClassData(
                    ClassImage = "",
                    ClassName = "전자기학1",
                    now = 17,
                    total = 20,
                    count = 6
                )
            )
            add(
                ClassData(
                    ClassImage = "",
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