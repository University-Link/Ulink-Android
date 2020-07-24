package com.example.ulink.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import com.example.ulink.MainActivity
import com.example.ulink.R
import com.example.ulink.repository.DataRepository
import com.example.ulink.repository.RequestLogin
import com.example.ulink.repository.ResponseLogin
import com.example.ulink.repository.RetrofitService
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

   // val requestLoginToServer = RequestLoginToServer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //Glide.with(this).load(R.drawable.io_loginview).into(gif_login);
//        json_login.setAnimation("lan_login_s10")
//        json_login.playAnimation()

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

            if (et_id.text.isNullOrBlank() || et_pw.text.isNullOrBlank()) {
                //loginPageDialog()
            } else {
                RetrofitService.service.requestLogin(
                    RequestLogin(
                        id = et_id.text.toString(),
                        password = et_pw.text.toString()
                    )

                ).enqueue(object : Callback<ResponseLogin>{
                    override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                        Log.d("로그인에러",t.message.toString())
                    }

                    override fun onResponse(
                        call: Call<ResponseLogin>,
                        response: Response<ResponseLogin>
                    ) {
                        response.body().let {
                            DataRepository.token = it?.data?.accessToken.toString()
                        }
                    }

                })
            }
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