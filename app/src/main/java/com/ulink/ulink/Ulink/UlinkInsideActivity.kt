package com.ulink.ulink.Ulink

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ulink.ulink.R
import com.ulink.ulink.Ulink.ClassBoard.UlinkBoardFragment
import kotlinx.android.synthetic.main.activity_ulink_inside.*
import kotlinx.android.synthetic.main.toolbar_ulink_inside.*


class UlinkInsideActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ulink_inside)

        val className = intent.getStringExtra("class")
        val classIdx = intent.getStringExtra("idx")

        Log.d("UIActivity",""+className)

        val bundle =  Bundle()
        bundle.putString("class",className)
        bundle.putString("idx",classIdx)
        val ulink_board_fragment = UlinkBoardFragment()
        ulink_board_fragment.setArguments(bundle)

        if(className!="")
            tv_classname.setText(className)

        val ulinkInsideAdapter = UlinkInsideAdapter(supportFragmentManager, className, classIdx)
        vp_ulink_inside.adapter = ulinkInsideAdapter
        tablayout_ulink_inside.setupWithViewPager(vp_ulink_inside)

        btn_search.setOnClickListener {
            val intent = Intent(this, BoardSearchActivity::class.java)
            startActivity(intent)
        }

        btn_plus.setOnClickListener {
            val intent = Intent(this, UlinkBoardWriteActivity::class.java)
            startActivity(intent)
        }

        btn_back.setOnClickListener {
            finish()
        }
    }
}