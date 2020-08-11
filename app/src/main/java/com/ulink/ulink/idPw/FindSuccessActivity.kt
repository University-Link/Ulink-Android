package com.ulink.ulink.idPw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ulink.ulink.R
import com.ulink.ulink.Ulink.UlinkInsideAdapter
import kotlinx.android.synthetic.main.activity_find_id_pw.*
import kotlinx.android.synthetic.main.activity_find_success.*
import kotlinx.android.synthetic.main.activity_find_success.btn_back
import kotlinx.android.synthetic.main.activity_ulink_inside.*

class FindSuccessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_success)

        val name = intent.getStringExtra("name")
        val id = intent.getStringExtra("id")

        val adapter = FindSuccessAdapter(supportFragmentManager, name, id)
        vp_id_pw.adapter = adapter
        layout_id_pw.setupWithViewPager(vp_id_pw)

        btn_back.setOnClickListener{
            finish()
        }
    }
}