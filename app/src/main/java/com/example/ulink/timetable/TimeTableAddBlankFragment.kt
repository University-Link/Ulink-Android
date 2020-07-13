package com.example.ulink.timetable

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
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.example.ulink.R
import com.example.ulink.repository.TimeTable

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

        layout.findViewById<Button>(R.id.btn_ok).setOnClickListener {
            val timeTable = TimeTable(1,"2020-2",et.text.toString(),false,"09:00","18:00")
            timeTableAddListener.onAdded(timeTable)
            dialog.dismiss()
        }

        layout.findViewById<Button>(R.id.tv_cancel).setOnClickListener {
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