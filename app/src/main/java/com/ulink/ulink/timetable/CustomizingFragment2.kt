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
import kotlinx.android.synthetic.main.layout_timetable_bottomsheet_customize1.tv_name
import kotlinx.android.synthetic.main.layout_timetable_bottomsheet_customize2.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CustomizingFragment2(subject : Subject, val onRefreshListener: onRefreshListener) :Fragment() {
    val subject = subject
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_timetable_bottomsheet_customize2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_name.text = subject.name

        var color = 0
        layout_color9.setOnClickListener(){
            color = 8
            Toast.makeText(view.context, "진 블루", Toast.LENGTH_SHORT).show()
        }
        layout_color10.setOnClickListener(){
            color = 9
            Toast.makeText(view.context, "애쉬 퍼플", Toast.LENGTH_SHORT).show()
        }
        layout_color11.setOnClickListener(){
            color = 10
            Toast.makeText(view.context, "문라이트 퍼플", Toast.LENGTH_SHORT).show()
        }
        layout_color12.setOnClickListener(){
            color = 11
            Toast.makeText(view.context, "와인 퍼플", Toast.LENGTH_SHORT).show()
        }
        layout_color13.setOnClickListener(){
            color = 12
            Toast.makeText(view.context, "라일락 퍼플", Toast.LENGTH_SHORT).show()
        }
        layout_color14.setOnClickListener(){
            color = 13
            Toast.makeText(view.context, "베이비 핑크", Toast.LENGTH_SHORT).show()
        }
        layout_color15.setOnClickListener(){
            color = 14
            Toast.makeText(view.context, "라즈베리 핑크", Toast.LENGTH_SHORT).show()
        }
        layout_color16.setOnClickListener(){
            color = 15
            Toast.makeText(view.context, "코랄 핑크", Toast.LENGTH_SHORT).show()
        }


        btn_ok2.setOnClickListener(){
            var body = RequestChangeColor(color = color)

            RetrofitService.service.updateChangeColor(DataRepository.token, subject.id.toString(), subject.subject, body).enqueue(object : Callback<ResponseChangeColor> {
                override fun onFailure(call: Call<ResponseChangeColor>, t: Throwable) {
                    Log.d("tag", "1")
                }

                override fun onResponse(
                    call: Call<ResponseChangeColor>,
                    response: Response<ResponseChangeColor>
                ) {
                    response.body()?.let{
                        if(it.status == 201){
                            Log.d("dlwldms", it.toString())
                            onRefreshListener.onRefresh()
                        }
                    } ?: Log.d("tag", response.message())
                }
            })
        }
    }

}

