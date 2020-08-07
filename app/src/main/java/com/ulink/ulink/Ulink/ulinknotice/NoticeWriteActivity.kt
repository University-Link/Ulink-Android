package com.ulink.ulink.Ulink.ulinknotice

import android.app.DatePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.NumberPicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ulink.ulink.R
import com.ulink.ulink.register.btnNextSelector
import kotlinx.android.synthetic.main.activity_notice_add.*
import kotlinx.android.synthetic.main.activity_notice_detail.*
import kotlinx.android.synthetic.main.activity_notice_write.*
import kotlinx.android.synthetic.main.activity_notice_write.tv_category
import kotlinx.android.synthetic.main.activity_notice_write.tv_toolbar_title
import kotlinx.android.synthetic.main.fragment_year.*
import java.text.SimpleDateFormat
import java.util.*

class NoticeWriteActivity : AppCompatActivity() {

    var cal = Calendar.getInstance()
    private var datePickerYear = cal.get(Calendar.YEAR)
    private var datePickerMonth = cal.get(Calendar.MONTH)+1
    private var datePickerDay =  cal.get(Calendar.DATE)
    var category = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice_write)

        var categoryList = arrayOf("과제", "수업", "시험")
        var categoryChecked = false

        val subjectName = intent.getStringExtra("subjectName")
        if(subjectName!="")
            tv_toolbar_title.text = subjectName+" 공지"

        tv_write_date.text = SimpleDateFormat("yyyy년 M월 d일").format(System.currentTimeMillis())

        layout_select_category.setOnClickListener {
            val builder = android.app.AlertDialog.Builder(this)
            val layout = LayoutInflater.from(this).inflate(R.layout.custom_category_picker, null)

            val categoryPicker = layout.findViewById<NumberPicker>(R.id.category_picker)
            categoryPicker.displayedValues = categoryList
            categoryPicker.minValue=0
            categoryPicker.maxValue=2
            categoryPicker.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS

            for(i in 0 until categoryList.size) {
                if (categoryList[i] == "수업")
                    categoryPicker.value = i
            }

            builder.setView(layout)

            var dialog = builder.create()

            layout.findViewById<TextView>(R.id.tv_category_cancel).setOnClickListener {
                dialog.dismiss()
            }

            layout.findViewById<TextView>(R.id.tv_category_confirm).setOnClickListener {
                categoryChecked = true
                if(categoryPicker.value==0)
                    category="과제"
                else if(categoryPicker.value==1)
                    category="수업"
                else
                    category="시험"

                tv_category.text = categoryList[categoryPicker.value]
                dialog.dismiss()
            }

            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()

            var width = resources.getDimensionPixelSize(R.dimen.picker_width)
            var height = resources.getDimensionPixelSize(R.dimen.picker_height)
            dialog.window?.setLayout(width, height)
        }

        val dateSetListener = DatePickerDialog.OnDateSetListener{view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val format = "yyyy년 M월 d일"
            val sdf = SimpleDateFormat(format, Locale.US)
            tv_write_date.text = sdf.format(cal.time)
            val strDate = sdf.format(cal.time).split("년", "월", "일")
            datePickerYear = Integer.parseInt(strDate[0].trim())
            datePickerMonth = Integer.parseInt(strDate[1].trim())
            datePickerDay = Integer.parseInt(strDate[2].trim())
        }

        tv_write_date.setOnClickListener {
            DatePickerDialog(this, dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }
    }
}