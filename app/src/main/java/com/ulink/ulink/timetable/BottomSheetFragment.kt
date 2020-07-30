package com.ulink.ulink.timetable

import android.app.AlertDialog
import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.ulink.ulink.R
import com.ulink.ulink.fragment.onRefreshListener
import com.ulink.ulink.repository.*
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.dialog_timetable_name.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*


class BottomSheetFragment(val mainTable : TimeTable, val onRefreshListener: onRefreshListener) : BottomSheetDialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_timetable_bottomsheet, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
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


            val fragment = SaveToImageFragment()
            val bundle = Bundle()
            bundle.putParcelable("timeTable", mainTable)
            fragment.arguments = bundle

            fragmentManager?.beginTransaction()?.add(android.R.id.content, fragment)?.addToBackStack(null)?.commit()




//
//            val sdf = SimpleDateFormat( "yyyyMMddHHmmss"); //년,월,일,시간 포멧 설정
//            val time = Date(); //파일명 중복 방지를 위해 사용될 현재시간
//            val fileName = sdf.format(time);
//
//            val values = ContentValues().apply {
//                put(MediaStore.Images.Media.DISPLAY_NAME, mainTable.name)
//                put(MediaStore.Images.Media.MIME_TYPE, "image/*")
//            }
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//                values.put(MediaStore.Images.Media.IS_PENDING, 1);
//            }
//
//            val collection = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
//            val item = requireContext().contentResolver.insert(collection, values)!!
//
//            requireContext().contentResolver.openFileDescriptor(item, "w", null).use {
//                FileOutputStream(it!!.fileDescriptor).use { fos ->
//                    val table = tableView.findViewById<LinearLayout>(R.id.layout_timetablewhole) as View
//                    table.findViewById<NestedScrollView>(R.id.layout_timetable_scrollable)
//                    var bitmap = Bitmap.createBitmap(table.measuredWidth,
//                            table.findViewById<NestedScrollView>(R.id.layout_timetable_scrollable).getChildAt(0).measuredHeight,
//                            Bitmap.Config.ARGB_8888)
//                    var canvas = Canvas(bitmap)
//                    val bgDrawable: Drawable = table.background
//                    if (bgDrawable != null) bgDrawable.draw(canvas) else canvas.drawColor(Color.WHITE)
//
//                    table.findViewById<NestedScrollView>(R.id.layout_timetable_scrollable).getChildAt(0).draw(canvas)
//                    table.draw(canvas)
//
//                    bitmap.compress(Bitmap.CompressFormat.PNG, 80, fos)
//                    fos.close()
//                }
//            }
//            values.clear()
//            values.put(MediaStore.Images.Media.IS_PENDING, 0)
//            requireContext().contentResolver.update(item, values, null, null)

            dismiss()
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

interface fragmentListener {
    fun onGenerated(fragment: Fragment)
}