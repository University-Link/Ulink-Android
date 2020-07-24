package com.example.ulink.timetable

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.ulink.R
import com.example.ulink.fragment.onRefreshListener
import com.example.ulink.repository.*
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.dialog_timetable_name.*
import kotlinx.android.synthetic.main.layout_timetable_bottomsheet_customize1.*
import kotlinx.android.synthetic.main.layout_timetable_bottomsheet_customize1.tv_name
import kotlinx.android.synthetic.main.layout_timetable_bottomsheet_customize2.*
import kotlinx.android.synthetic.main.layout_timetable_bottomsheet_customize3.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CustomizingFragment3(subject : Subject, val onRefreshListener: onRefreshListener) : Fragment() {
    val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4IjoxLCJuYW1lIjoi6rmA67O067CwIiwic2Nob29sIjoi7ZWc7JaR64yA7ZWZ6rWQIiwibWFqb3IiOiLshoztlITtirjsm6jslrQiLCJpYXQiOjE1OTQ4MTY1NzQsImV4cCI6MTU5NjI1NjU3NCwiaXNzIjoiYm9iYWUifQ.JwRDELH1lA1Fb8W1ltTmhThpmgFrUTQZVocUTATv3so"
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
        }
        layout_color18.setOnClickListener(){
            color = 17
            Toast.makeText(view.context, "감귤 오렌지", Toast.LENGTH_SHORT).show()
        }
        layout_color19.setOnClickListener(){
            color = 18
            Toast.makeText(view.context, "망고 오렌지", Toast.LENGTH_SHORT).show()
        }
        layout_color20.setOnClickListener(){
            color = 19
            Toast.makeText(view.context, "허니 옐로우", Toast.LENGTH_SHORT).show()
        }


        btn_ok3.setOnClickListener(){
            var body = RequestChangeColor(color=color)
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
                            onRefreshListener.onRefresh()
                        }
                    } ?: Log.d("tag", response.message())
                }
            })
        }
    }
}

