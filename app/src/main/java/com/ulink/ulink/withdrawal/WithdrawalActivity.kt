package com.ulink.ulink.withdrawal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import com.ulink.ulink.R
import kotlinx.android.synthetic.main.activity_withdrawal.*

class WithdrawalActivity : AppCompatActivity() {

    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_withdrawal)

        setOnClick()
    }


    fun setOnClick(){
        et_etc.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                tv_etc_count.text = "(${et_etc.text.toString().length}/100)"
            }
        })

        btn_1.setOnClickListener {
            setFilter(it)
        }
        btn_2.setOnClickListener {
            setFilter(it)
        }
        btn_3.setOnClickListener {
            setFilter(it)

        }
        btn_4.setOnClickListener {
            setFilter(it)

        }

        btn_5.setOnClickListener {
            setFilter(it)
        }

        btn_6.setOnClickListener {
            setFilter(it)
            if (it.isSelected){
                et_etc.visibility = View.VISIBLE
                tv_etc_count.visibility = View.VISIBLE

            } else{
                et_etc.visibility = View.INVISIBLE
                tv_etc_count.visibility = View.INVISIBLE

            }
        }



        btn_ok.setOnClickListener {
            Log.d("tag", "11")
            supportFragmentManager.beginTransaction().replace(R.id.layout_container, WithdrawalFragment()).addToBackStack(null).commit()
//            btn_ok.visibility = View.GONE
        }
    }

    fun setFilter(it : View){
        it.isSelected = !it.isSelected

        if (it.isSelected){
            count +=1
        } else {
            count -= 1
        }

        btn_ok.setBackgroundColor(resources.getColor(R.color.mainButton))
        btn_ok.setTextColor(resources.getColor(R.color.white))

        if (count == 0){
            btn_ok.setBackgroundResource(R.drawable.mypage_withdrawal_unactivated_img_textbox)
            btn_ok.setTextColor(resources.getColor(R.color.btnIcon3))
        }
    }
}