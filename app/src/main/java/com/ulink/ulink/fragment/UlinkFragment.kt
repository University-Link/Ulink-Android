package com.example.ulink.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ulink.ulink.Activity.ChattingActivity
import com.ulink.ulink.ClassRecycler.ClassAdapter
import com.ulink.ulink.ClassRecycler.ClassData
import com.ulink.ulink.R
import com.ulink.ulink.repository.DataRepository
import com.ulink.ulink.repository.ResponseChatting
import com.ulink.ulink.repository.RetrofitService
import kotlinx.android.synthetic.main.fragment_class.*
import kotlinx.android.synthetic.main.fragment_class.rv_class
import kotlinx.android.synthetic.main.fragment_ulink.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UlinkFragment : Fragment() {
    lateinit var classAdapter : ClassAdapter
    val datas : MutableList<ClassData> = mutableListOf<ClassData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ulink, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        classAdapter = ClassAdapter(view.context)
        rv_class.adapter = classAdapter

        RetrofitService.service.getChatList(DataRepository.token).
        enqueue(object : Callback<ResponseChatting> {
            override fun onFailure(call: Call<ResponseChatting>, t: Throwable) {
                Log.d("tag", t.localizedMessage)
            }

            override fun onResponse(
                call: Call<ResponseChatting>,
                response: Response<ResponseChatting>
            ) {
                response.body()?.let{
                    if(it.status == 200){
                        if(it.data.chat.isNotEmpty()) {
                            var size = it.data.chat.size
                            for(i in 0 until size)
                            { datas.apply{
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
                                classAdapter.datas = datas
                                classAdapter.notifyDataSetChanged()
                            }
                        }
                    }
                } ?: Log.d("tag", response.message())
            }
        })

        classAdapter.setItemClickLIstener(object:ClassAdapter.ItemClickListener{
            override fun onClick(view:View, position:Int){
                val intent = Intent(getActivity(), ChattingActivity::class.java)
                intent.putExtra("class", datas[position].name) //과목명
                intent.putExtra("idx", datas[position].subjectIdx.toString())
                startActivity(intent)
            }
        })

        layout_ulink_board.setOnClickListener(){
//            val intent = Intent(getActivity(), ChattingActivity::class.java)
//            intent.putExtra("class", "Ulink")
//            intent.putExtra("idx", "0")
//            startActivity(intent)
        }

        layout_university_board.setOnClickListener(){
//            val intent = Intent(getActivity(), ChattingActivity::class.java)
//            intent.putExtra("class", "우리학교")
//            intent.putExtra("idx", "0")
//            startActivity(intent)
        }
    }
}