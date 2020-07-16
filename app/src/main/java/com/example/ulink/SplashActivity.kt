package com.example.ulink

import android.animation.Animator
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import kotlinx.android.synthetic.main.splash_activity.*


class SplashActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)
        lottie.setAnimation("io_ulink_splash_galaxy10.json")
         lottie.addAnimatorListener (object :
             Animator.AnimatorListener {
             override fun onAnimationRepeat(animation: Animator?) {
             }

             override fun onAnimationEnd(animation: Animator?) {
                 val intent = Intent(baseContext, LoginActivity::class.java)
                 startActivity(intent)             }

             override fun onAnimationCancel(animation: Animator?) {
             }

             override fun onAnimationStart(animation: Animator?) {
             }

         })
        lottie.playAnimation()



    }
}