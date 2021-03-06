package com.ulink.ulink.Activity

import android.animation.Animator
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import com.ulink.ulink.MainActivity
import com.ulink.ulink.R
import com.ulink.ulink.repository.DataRepository
import com.ulink.ulink.repository.RequestLogin
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val sharedPref: SharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE)
        val sharedEdit = sharedPref.edit()

        lottie.setAnimation("io_ulink_splash_galaxy10.json")
        lottie.addAnimatorListener (object :
             Animator.AnimatorListener {
             override fun onAnimationRepeat(animation: Animator?) {
             }

             override fun onAnimationEnd(animation: Animator?) {
                 if (sharedPref.getBoolean("autoLogin",false)){
                     DataRepository.requestLogin(
                             RequestLogin(
                                     id = sharedPref.getString("id", "")!!,
                                     password = sharedPref.getString("passWord","")!!
                             ),
                             onSuccess = {
                                 sharedEdit.putString("accessToken", it)
                                 sharedEdit.apply()
                                 DataRepository.token = it
                                 val intent = Intent(this@SplashActivity, MainActivity::class.java)
                                 startActivity(intent)
                                 finish()
                                 overridePendingTransition(0,0)
                             },
                             onFailure = {
                                 val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                                 startActivity(intent)
                                 finish()
                                 overridePendingTransition(0,0)
                             }
                     )
                 }else{
                     val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                     startActivity(intent)
                     finish()
                     overridePendingTransition(0,0)

                 }
             }

             override fun onAnimationCancel(animation: Animator?) {
             }

             override fun onAnimationStart(animation: Animator?) {
             }
         })
        lottie.playAnimation()
    }
}