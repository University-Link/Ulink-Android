package com.ulink.ulink.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ulink.ulink.MainActivity
import com.ulink.ulink.R
import com.ulink.ulink.register.RegisterActivity
import com.ulink.ulink.repository.DataRepository
import com.ulink.ulink.repository.RequestLogin
import com.ulink.ulink.utils.DialogBuilder
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val sharedPref: SharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE)
        val sharedEdit = sharedPref.edit()


        var id = intent.getStringExtra("id")
        var password = intent.getStringExtra("password")

        if(id!=null && password!=null) {
            et_id.setText(id)
            et_pw.setText(password)
        }

        tv_register.setOnClickListener(){
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        btn_login.setOnClickListener {
            if (et_id.text.isNullOrBlank() || et_pw.text.isNullOrBlank()) {
                loginPageDialog()
            } else {
                DataRepository.requestLogin(
                        RequestLogin(
                                id = et_id.text.toString(),
                                password = et_pw.text.toString()
                        ),
                        onSuccess = {

                            sharedEdit.putBoolean("autoLogin", true)
                            sharedEdit.putString("passWord", et_pw.text.toString())
                            sharedEdit.putString("id", et_id.text.toString())
                            sharedEdit.putString("accessToken", it)

                            sharedEdit.commit()
                            DataRepository.token = it
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        },
                        onFailure = {
                            loginPageDialog()
                        }
                )
            }
        }
    }

    private fun loginPageDialog() {
        DialogBuilder().apply {
            build(this@LoginActivity)
            setContent(getString(R.string.login_popup))
            setClickListener {
                dismiss()
            }
            show()
        }
    }
}