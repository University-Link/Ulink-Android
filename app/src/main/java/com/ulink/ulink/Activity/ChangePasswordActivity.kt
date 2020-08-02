package com.ulink.ulink.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ulink.ulink.R
import com.ulink.ulink.textChangedListener
import kotlinx.android.synthetic.main.activity_change_password.*

class ChangePasswordActivity : AppCompatActivity() {

    var validate = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)



        setOnClick()

        et_password_check.textChangedListener {
            if (et_password.text.toString() != et_password_check.text.toString()){
                tv_wrongpassword.visibility = View.VISIBLE
            } else{
                tv_wrongpassword.visibility = View.GONE
            }
        }
    }

    private fun setOnClick(){


        btn_ok.setOnClickListener {

//          TODO 모든 칸이 채워져있고 비밀번호도 같은경우 버튼 활성화!


//          TODO 서버랑 통신하여 비밀번호 변경 시도하기
            finish()

        }

        btn_back.setOnClickListener {
            finish()
        }


    }

}