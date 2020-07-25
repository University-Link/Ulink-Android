package com.example.ulink.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import com.example.ulink.MainActivity
import com.example.ulink.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val sharedPref : SharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE)
        et_id.setText(sharedPref.getString("id", ""))

        btn_login.setOnClickListener {
            val sharedPref: SharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE)
            val sharedEdit = sharedPref.edit()

            if (checkbox_id_save.isChecked) {
                sharedEdit.putString("id", et_id.text.toString())
                sharedEdit.commit()
            } else {
                sharedEdit.clear()
                sharedEdit.commit()
            }

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

//            if (et_id.text.isNullOrBlank() || et_pw.text.isNullOrBlank()) {
//                loginPageDialog()
//            } else {
//                requestLoginToServer.service.requestLogin(
//                    RequestLogin(
//                        id = et_id.text.toString(),
//                        password = et_pw.text.toString()
//                    )
//                ).customEnqueue(
//                    onError = { loginPageDialog() },
//                    onSuccess = {
//                        if (it.success) {
//                            val intent = Intent(this, MainActivity::class.java)
//                            startActivity(intent)
//                        } else {
//                              loginPageDialog()
//                        }
//                    }
//                )
//            }*/
        }
    }

    fun loginPageDialog(){
        val builder = android.app.AlertDialog.Builder(this)
        val layout = LayoutInflater.from(this).inflate(R.layout.dialog_my_page_layout, null)

        builder.setView(layout)

        var dialog = builder.create()

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        var width = getResources().getDimensionPixelSize(R.dimen.my_popup_width)
        var height = getResources().getDimensionPixelSize(R.dimen.my_popup_height)
        dialog.window?.setLayout(width, height)

        layout.findViewById<TextView>(R.id.tv_my_dialog).text="아이디와 비밀번호를 확인해주세요."
        layout.findViewById<TextView>(R.id.tv_check).setOnClickListener {
            dialog.dismiss()
        }
    }
}