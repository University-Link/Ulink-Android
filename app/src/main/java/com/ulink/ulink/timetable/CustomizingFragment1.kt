package com.ulink.ulink.timetable

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ulink.ulink.R
import com.ulink.ulink.fragment.onRefreshListener
import com.ulink.ulink.repository.*
import kotlinx.android.synthetic.main.layout_timetable_bottomsheet_customize1.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CustomizingFragment1(subject : Subject, val onRefreshListener: onRefreshListener, val onClick : ()-> Unit) : Fragment() {
    val subject = subject
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_timetable_bottomsheet_customize1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_name.text = subject.name

        var color = 0
        layout_color1.setOnClickListener(){
            color = 0
            Toast.makeText(view.context, "올리브 그린", Toast.LENGTH_SHORT).show()
            changeColor(color, subject)
        }
        layout_color2.setOnClickListener(){
            color = 1
            Toast.makeText(view.context, "말차 그린", Toast.LENGTH_SHORT).show()
            changeColor(color, subject)
        }
        layout_color3.setOnClickListener(){
            color = 2
            Toast.makeText(view.context, "포레스트 그린", Toast.LENGTH_SHORT).show()
            changeColor(color, subject)
        }
        layout_color4.setOnClickListener(){
            color = 3
            Toast.makeText(view.context, "제주 그린", Toast.LENGTH_SHORT).show()
            changeColor(color, subject)
        }
        layout_color5.setOnClickListener(){
            color = 4
            Toast.makeText(view.context, "민트 그린", Toast.LENGTH_SHORT).show()
            changeColor(color, subject)
        }
        layout_color6.setOnClickListener(){
            color = 5
            Toast.makeText(view.context, "오션 블루", Toast.LENGTH_SHORT).show()
            changeColor(color, subject)
        }
        layout_color7.setOnClickListener(){
            color = 6
            Toast.makeText(view.context, "스카이 블루", Toast.LENGTH_SHORT).show()
            changeColor(color, subject)
        }
        layout_color8.setOnClickListener(){
            color = 7
            Toast.makeText(view.context, "마티니 블루", Toast.LENGTH_SHORT).show()
            changeColor(color, subject)
        }


        btn_ok.setOnClickListener(){
            onClick()
        }
    }
    fun changeColor(color : Int, subject : Subject){
        var body = RequestChangeColor(color=color)
        RetrofitService.service.updateChangeColor(DataRepository.token, subject.id.toString(), subject.subject, body).enqueue(object : Callback<ResponseChangeColor> {
            override fun onFailure(call: Call<ResponseChangeColor>, t: Throwable) {
                Log.d("tag", t.message.toString())
            }

            override fun onResponse(
                call: Call<ResponseChangeColor>,
                response: Response<ResponseChangeColor>
            ) {
                response.body()?.let{
                    if(it.status == 201){
                        onRefreshListener.onRefresh()
                    }
                } ?: Log.d("tag", response.message())
            }
        })
    }
}

