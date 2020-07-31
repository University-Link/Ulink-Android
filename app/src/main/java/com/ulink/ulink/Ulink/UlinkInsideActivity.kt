package com.ulink.ulink.Ulink

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ulink.ulink.R
import kotlinx.android.synthetic.main.activity_ulink_inside.*
import kotlinx.android.synthetic.main.toolbar_ulink_inside.*


class UlinkInsideActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ulink_inside)


        val ulinkInsideAdapter =
            UlinkInsideAdapter(supportFragmentManager)
        vp_ulink_inside.adapter = ulinkInsideAdapter
        tablayout_ulink_inside.setupWithViewPager(vp_ulink_inside)
        btn_search.setOnClickListener {
            val intent = Intent(this, BoardSearchActivity::class.java)
            startActivity(intent)

        }
        btn_back.setOnClickListener {
            finish()
        }
    }
}