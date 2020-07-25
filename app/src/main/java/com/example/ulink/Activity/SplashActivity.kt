package com.example.ulink.Activity

import android.animation.Animator
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.ulink.R
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        lottie.setAnimation("io_ulink_splash_galaxy10.json")
        lottie.addAnimatorListener (object :
             Animator.AnimatorListener {
             override fun onAnimationRepeat(animation: Animator?) {
             }

             override fun onAnimationEnd(animation: Animator?) {
                 val intent = Intent(baseContext, LoginActivity::class.java)
                 startActivity(intent)
                 finish()
             }

             override fun onAnimationCancel(animation: Animator?) {
             }

             override fun onAnimationStart(animation: Animator?) {
             }
         })
        lottie.playAnimation()
    }
}