package com.ulink.ulink.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.ulink.ulink.R
import com.ulink.ulink.textChangedListener
import kotlinx.android.synthetic.main.activity_change_major.*
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
                ActivityCompat.finishAffinity(this)
                val sharedPref: SharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE)
                val sharedEdit = sharedPref.edit()
                sharedEdit.putBoolean("autoLogin", false)
                sharedEdit.commit()

                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)

            } else {
                Toast.makeText(this, "아이디와 비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
            }
        }
    }
}