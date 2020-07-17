package com.example.ulink.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ulink.repository.ResponseCalendar
import com.example.ulink.ChattingActivity
import com.example.ulink.ClassRecycler.ClassAdapter
import com.example.ulink.ClassRecycler.ClassData
import com.example.ulink.R
import com.example.ulink.repository.DataRepository
import com.example.ulink.repository.ResponseChatting
import com.example.ulink.repository.RetrofitService
import kotlinx.android.synthetic.main.fragment_class.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        // TODO INDEX가 0일 경우 어떻게 처리할까? if 0일 경우는 무시 else 뿌리기 outofIndex error
        RetrofitService.service.getChatList(DataRepository.token).enqueue(object : Callback<ResponseChatting> {
            override fun onFailure(call: Call<ResponseChatting>, t: Throwable) {
                Log.d("지혜", "바보")
            }

            override fun onResponse(
                call: Call<ResponseChatting>,
                response: Response<ResponseChatting>
            ) {
                response.body()?.let{
                    if(it.status == 200){
                        if(it.data.chat.isNotEmpty()) {
                            //Log.d("dlwldms", it.data.chat[0].subjectIdx.toString())
                                var size = it.data.chat.size
                                for(i in 0 until size)
                                {
                                datas.apply{
                                    add(
                                        ClassData(
                                            subjectIdx = it.data.chat[i].subjectIdx,
                                            name = it.data.chat[i].name,
                                            color = it.data.chat[i].color,
                                            total = it.data.chat[i].total,
                                            current = it.data.chat[i].current
                                        )
                                    )
                                }
                                ClassAdapter.datas = datas
                                ClassAdapter.notifyDataSetChanged()
                            }
                        }
                    }
                } ?: Log.d("tag", response.message())
            }
        })

        ClassAdapter.setItemClickLIstener(object:ClassAdapter.ItemClickListener{
            override fun onClick(view:View, position:Int){
                val intent = Intent(getActivity(), ChattingActivity::class.java)
                intent.putExtra("class", datas[position].name) //과목명
                intent.putExtra("current", datas[position].current) //현재원
                intent.putExtra("idx", datas[position].subjectIdx.toString())
                startActivity(intent)
            }
        })
    }
}