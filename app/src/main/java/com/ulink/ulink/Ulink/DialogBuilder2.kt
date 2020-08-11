package com.ulink.ulink.Ulink

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.ulink.ulink.R

class DialogBuilder2 {

    private lateinit var builder : AlertDialog.Builder
    lateinit var layout : View
    private lateinit var dialog : AlertDialog

    fun build(context : Context) : DialogBuilder2{
        builder = AlertDialog.Builder(context)
        builder.setView(layout)
        dialog = builder.create()

        return this
    }

    fun setContent(text : String) : DialogBuilder2{
        layout.findViewById<TextView>(R.id.tv_content).text = text
        return this
    }

    fun setClickListener(onClick : () -> Unit): DialogBuilder2 {
        layout.findViewById<TextView>(R.id.btn_ok).setOnClickListener {
            onClick()
        }
        return this
    }
    fun setButtonText(text: String) : DialogBuilder2{
        layout.findViewById<TextView>(R.id.btn_ok).text = text
        return this
    }

    fun show(){
        // 양옆 마진 조절
        val back = ColorDrawable(Color.TRANSPARENT)
        val inset = InsetDrawable(back, 90)

        dialog.window?.setBackgroundDrawable(inset)
        dialog.show()
    }

    fun dismiss(){
        dialog.dismiss()
    }
}