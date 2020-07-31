package com.ulink.ulink.register

import android.app.DatePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import android.widget.TextView
import com.ulink.ulink.R
import com.ulink.ulink.utils.DialogBuilder
import kotlinx.android.synthetic.main.fragment_year.*
import kotlinx.android.synthetic.main.fragment_year.btn_back
import kotlinx.android.synthetic.main.fragment_year.btn_next
import java.text.SimpleDateFormat
import java.util.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
var cal = Calendar.getInstance()

class YearFragment : Fragment() {
    private var university: String = ""
    private var major: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            university = it.getString(ARG_PARAM1).toString()
            major = it.getString(ARG_PARAM2).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_year, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var yearChecked = false

        btn_back.setOnClickListener{
            (activity as RegisterActivity?)!!.finishFragment(this)
        }

        btn_next.setOnClickListener{
            if(yearChecked)
                (activity as RegisterActivity?)!!.replaceFragment(AgreeFragment.newInstance(university, major, tv_year.text.toString()))
            else{
                DialogBuilder().apply {
                    build(view.context)
                    setContent(getString(R.string.year_fail))
                    setClickListener {
                        dismiss()
                    }
                    show()
                }
            }
        }

        tv_year.setOnClickListener{
            val builder = android.app.AlertDialog.Builder(context)
            val layout = LayoutInflater.from(context).inflate(R.layout.custom_year_picker, null)

            val yearPicker = layout.findViewById<NumberPicker>(R.id.year_picker)
            yearPicker.minValue=2010
            yearPicker.maxValue=cal.get(Calendar.YEAR)
            yearPicker.value=cal.get(Calendar.YEAR)
            yearPicker.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS

            builder.setView(layout)

            var dialog = builder.create()

            layout.findViewById<TextView>(R.id.tv_year_cancel).setOnClickListener {
                dialog.dismiss()
            }

            layout.findViewById<TextView>(R.id.tv_year_confirm).setOnClickListener {
                yearChecked = true
                tv_year.text = yearPicker.value.toString()
                tv_year.setTextColor(Color.parseColor("#363636"))
                btn_next.setBackgroundResource(R.drawable.signup_btn_next_activated)
                btn_next.setTextColor(Color.parseColor("#ffffff"))
                dialog.dismiss()
            }

            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()

            var width = resources.getDimensionPixelSize(R.dimen.yearPicker_width)
            var height = resources.getDimensionPixelSize(R.dimen.yearPicker_height)
            dialog.window?.setLayout(width, height)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            YearFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}