package com.example.ulink.timetable

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import com.example.ulink.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BottomSheetFragment : BottomSheetDialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_timetable_bottomsheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.tv_setasmain).setOnClickListener {
            Toast.makeText(context,"대표시간표로 설정되었습니다.",Toast.LENGTH_SHORT).show()

        }
        view.findViewById<TextView>(R.id.tv_changename).setOnClickListener {
            val builder = AlertDialog.Builder(context)
            val layout = LayoutInflater.from(context).inflate(R.layout.dialog_timetable_name, null)
            builder.setView(layout)
            val dialog = builder.create()
            dialog.show()
            layout.findViewById<TextView>(R.id.btn_ok).setOnClickListener {
                //TODO 시간표 이름 바꾸기
                dialog.dismiss()
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
                dialog.dismiss()
            }
        }
    }
}