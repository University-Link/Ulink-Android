package com.ulink.ulink.timetable

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.ulink.ulink.R

class TimeTableAddBlankFragment : Fragment() {


    lateinit var timeTableAddListener: TimeTableAddListener

    fun setTimeTableAddListner(timeTableAddListener: TimeTableAddListener) {
        this.timeTableAddListener = timeTableAddListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val layout = inflater.inflate(R.layout.fragment_time_table_add_blank, container, false)

        layout.findViewById<ImageView>(R.id.iv_plus).setOnClickListener {
            showDialog()
        }

        return layout
    }

    fun showDialog() {

        val builder = AlertDialog.Builder(context)
        val layout = LayoutInflater.from(context).inflate(R.layout.dialog_timetable_name, null)
        builder.setView(layout)
        val dialog = builder.create()

        val et = layout.findViewById<EditText>(R.id.et_name)

        layout.findViewById<TextView>(R.id.tv_ok).setOnClickListener {
            timeTableAddListener.onAdded(et.text.toString())
            dialog.dismiss()
        }

        layout.findViewById<TextView>(R.id.tv_cancel).setOnClickListener {
            dialog.dismiss()
        }


        val back = ColorDrawable(Color.TRANSPARENT)
        val inset = InsetDrawable(back, 80)

        dialog.window?.setBackgroundDrawable(inset)

        dialog.show()


    }

    fun dptopx(dp: Int): Float {
        val metrics = resources.displayMetrics
        return dp * ((metrics.densityDpi.toFloat()) / DisplayMetrics.DENSITY_DEFAULT)
    }

}