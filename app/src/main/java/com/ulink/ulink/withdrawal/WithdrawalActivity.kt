package com.ulink.ulink.withdrawal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.ulink.ulink.R
import kotlinx.android.synthetic.main.activity_withdrawal.*

class WithdrawalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_withdrawal)

        setOnClick()
    }

    fun setOnClick(){

        et_id.setOnFocusChangeListener { v, hasFocus ->
            btn_ok.setBackgroundColor(resources.getColor(R.color.mainButton))
            btn_ok.setTextColor(resources.getColor(R.color.white))
        }

        btn_ok.setOnClickListener {
            if (!et_id.text.isNullOrBlank()&&!et_password.text.isNullOrBlank()){
//           TODO 서버와 연결해서 회원탈퇴

//                TODO 비밀번호가 틀릴경우 Dialog
//                DialogBuilder().apply {
//                    build(this@WithdrawalActivity)
//                    setContent("비밀번호를 확인해 주세요.")
//                    setClickListener {
//                        dismiss()
//                    }
//                    show()
//                }


//                btn_ok.setOnClickListener(null)
                supportFragmentManager.beginTransaction().add(R.id.layout_container, WithdrawalFragment()).commit()
                Log.d("tag","1")
                btn_ok.visibility = View.GONE

            } else {
                Toast.makeText(this, "아이디와 비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
            }
        }
    }
}