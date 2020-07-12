package com.example.ulink

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_class_evaluation.*

class EvaluationActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class_evaluation)

        cardview.setBackgroundResource(R.drawable.class_evaluation_upper_bg)
        cardview2.setBackgroundResource(R.drawable.class_eval_bg)

    }

}