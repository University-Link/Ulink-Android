package com.example.ulink

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

       /* val sharedPref : SharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE)
        val sharedEdit = sharedPref.edit()

        if(sharedPref.getBoolean("save", false))
            et_id.setText(sharedPref.getString("id",""))*/

        btn_login.setOnClickListener {
            /*sharedEdit.putBoolean("save", checkbox_id_save.isChecked)
            if (sharedPref.getBoolean("save", false)) {
                sharedEdit.putString("id", et_id.text.toString())
                sharedEdit.commit()
            }
            else{
                sharedEdit.clear()
                sharedEdit.commit()
            }*/

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            /*if(et_id.text.isNullOrBlank() || et_pw.text.isNullOrBlank()){
                       showToast("아이디와 비밀번호를 확인하세요.")
                   }else {
                       requestLoginToServer.service.requestLogin(
                           RequestLogin(
                               id = id.text.toString(),
                               password = password.text.toString()
                           )
                       ).customEnqueue(
                           onError = { showToast("올바르지 못한 요청입니다")},
                           onSuccess = {
                               if(it.success){
                                   startActivity<LoginSuccessActivity>()
                                   finish()
                               }else {
                                   showToast("아이디와 비밀번호를 확인하세요.")
                               }
                           }
                       )
                   }*/
        }

    }
}