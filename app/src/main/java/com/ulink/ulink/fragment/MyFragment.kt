package com.ulink.ulink.fragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ulink.ulink.R
import kotlinx.android.synthetic.main.fragment_my.*

class MyFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_school_certification.setOnClickListener(){
            myPageDialog()
        }
        btn_personal_information.setOnClickListener(){
            myPageDialog()
        }
        btn_letter.setOnClickListener(){
            myPageDialog()
        }
        btn_inquiry.setOnClickListener(){
            myPageDialog()
        }
        btn_charge.setOnClickListener(){
            myPageDialog()
        }
    }

    fun myPageDialog(){
        val builder = android.app.AlertDialog.Builder(context)
        val layout = LayoutInflater.from(context).inflate(R.layout.dialog_my_page_layout, null)

        builder.setView(layout)

        var dialog = builder.create()

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        var width = getResources().getDimensionPixelSize(R.dimen.my_popup_width)
        var height = getResources().getDimensionPixelSize(R.dimen.my_popup_height)
        dialog.window?.setLayout(width, height)

        layout.findViewById<TextView>(R.id.tv_check).setOnClickListener {
            dialog.dismiss()
        }
    }

    fun myPencilDialog(){
        val builder = android.app.AlertDialog.Builder(context)
        val layout = LayoutInflater.from(context).inflate(R.layout.dialog_my_page_layout, null)

        builder.setView(layout)

        var dialog = builder.create()

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        var width = getResources().getDimensionPixelSize(R.dimen.my_popup_width)
        var height = getResources().getDimensionPixelSize(R.dimen.my_popup_height)
        dialog.window?.setLayout(width, height)
    }

}