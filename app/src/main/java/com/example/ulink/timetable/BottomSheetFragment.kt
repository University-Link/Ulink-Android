package com.example.ulink.timetable

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.ulink.R
import com.example.ulink.fragment.onRefreshListener
import com.example.ulink.repository.*
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.dialog_timetable_name.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BottomSheetFragment(val mainTable : TimeTable, val onRefreshListener: onRefreshListener) : BottomSheetDialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_timetable_bottomsheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


            view.findViewById<TextView>(R.id.tv_setasmain).setOnClickListener {

            RetrofitService.service.updateMainTimeTable(DataRepository.token, mainTable.id.toString()).enqueue(object : Callback<ResponseupdateMainTimeTable>{
                override fun onFailure(call: Call<ResponseupdateMainTimeTable>, t: Throwable) {
                    Log.d("대표시간표 설정 실패",t.message.toString())
                }

                override fun onResponse(
                    call: Call<ResponseupdateMainTimeTable>,
                    response: Response<ResponseupdateMainTimeTable>
                ) {
                    response.body().let{
                        Toast.makeText(context,"대표시간표로 설정되었습니다.",Toast.LENGTH_SHORT).show()
                        val fragmentManager = activity!!.supportFragmentManager
                        fragmentManager.beginTransaction().remove(this@BottomSheetFragment).commit()
                        fragmentManager.popBackStack()
                        onRefreshListener.onRefresh()

                    }
                }

            })



        }
        view.findViewById<TextView>(R.id.tv_changename).setOnClickListener {
            val builder = AlertDialog.Builder(context)
            val layout = LayoutInflater.from(context).inflate(R.layout.dialog_timetable_name, null)
            builder.setView(layout)
            val dialog = builder.create()
            dialog.show()
            layout.findViewById<TextView>(R.id.tv_ok).setOnClickListener {
                //TODO 시간표 이름 바꾸기
                RetrofitService.service.updateTimeTableName(DataRepository.token,mainTable.id,
                    RequestupdateTimeTableName(
                        name =  dialog.et_name.text.toString()
                    )
                ).enqueue(object : Callback<ResponseupdateTimeTableName>{
                    override fun onFailure(call: Call<ResponseupdateTimeTableName>, t: Throwable) {
                       // Log.d("이름변경실패",t.message.toString())
                    }

                    override fun onResponse(
                        call: Call<ResponseupdateTimeTableName>,
                        response: Response<ResponseupdateTimeTableName>
                    ) {
                        response.body()?.let{
                            dialog.dismiss()
                            val fragmentManager = activity!!.supportFragmentManager
                            fragmentManager.beginTransaction().remove(this@BottomSheetFragment).commit()
                            fragmentManager.popBackStack()
                            onRefreshListener.onRefresh()

                        }

                    }

                })

            }
            layout.findViewById<TextView>(R.id.tv_cancel).setOnClickListener {
                dialog.dismiss()
            }
        }
        view.findViewById<TextView>(R.id.tv_saveasimage).setOnClickListener {
            val builder = AlertDialog.Builder(context)
            val layout = LayoutInflater.from(context).inflate(R.layout.dialog_timetable_preparing, null)
            builder.setView(layout)
            val dialog = builder.create()
            dialog.show()
            layout.findViewById<TextView>(R.id.btn_ok).setOnClickListener {
                dialog.dismiss()


            }
        }
        view.findViewById<TextView>(R.id.tv_delete).setOnClickListener {
            val builder = AlertDialog.Builder(context)
            val layout = LayoutInflater.from(context).inflate(R.layout.dialog_timetable_delete, null)
            builder.setView(layout)
            val dialog = builder.create()
            dialog.show()
            layout.findViewById<TextView>(R.id.tv_cancel).setOnClickListener {
                dialog.dismiss()
            }
            layout.findViewById<TextView>(R.id.tv_delete).setOnClickListener {
                //TODO 시간표 삭제
                RetrofitService.service.deleteMainTimeTable(DataRepository.token,mainTable.id.toString()).enqueue(object : Callback<ResponsedeleteMainTimeTable>{
                    override fun onFailure(call: Call<ResponsedeleteMainTimeTable>, t: Throwable) {
                        Log.d("삭제실패",t.message.toString())
                    }

                    override fun onResponse(
                        call: Call<ResponsedeleteMainTimeTable>,
                        response: Response<ResponsedeleteMainTimeTable>
                    ) {
                        response.body().let {
                            dialog.dismiss()
                            val fragmentManager = activity!!.supportFragmentManager
                            fragmentManager.beginTransaction().remove(this@BottomSheetFragment).commit()
                            fragmentManager.popBackStack()
                            onRefreshListener.onRefresh()


                        }
                    }

                })

            }
        }
    }
}

private fun <T> Call<T>.enqueue(callback: Callback<ResponseupdateTimeTableName>) {

}
