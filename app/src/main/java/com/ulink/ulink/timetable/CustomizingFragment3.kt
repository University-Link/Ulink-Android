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
import kotlinx.android.synthetic.main.layout_timetable_bottomsheet_customize3.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CustomizingFragment3(subject : Subject, val onRefreshListener: onRefreshListener, val onClick : ()-> Unit) : Fragment() {
    val subject = subject
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_timetable_bottomsheet_customize3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_name.text = subject.name

        var color = 0
        layout_color17.setOnClickListener(){
            color = 16
            Toast.makeText(view.context, "자몽 오렌지", Toast.LENGTH_SHORT).show()
            changeColor(color, subject)
        }
        layout_color18.setOnClickListener(){
            color = 17
            Toast.makeText(view.context, "감귤 오렌지", Toast.LENGTH_SHORT).show()
            changeColor(color, subject)
        }
        layout_color19.setOnClickListener(){
            color = 18
            Toast.makeText(view.context, "망고 오렌지", Toast.LENGTH_SHORT).show()
            changeColor(color, subject)
        }
        layout_color20.setOnClickListener(){
            color = 19
            Toast.makeText(view.context, "허니 옐로우", Toast.LENGTH_SHORT).show()
            changeColor(color, subject)
        }

        btn_ok3.setOnClickListener(){
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

