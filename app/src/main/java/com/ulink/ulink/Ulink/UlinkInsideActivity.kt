package com.ulink.ulink.Ulink

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ulink.ulink.R
import com.ulink.ulink.Ulink.BoardSearchRecycler.BoardSearchActivity
import com.ulink.ulink.Ulink.ClassBoard.UlinkBoardFragment
import kotlinx.android.synthetic.main.activity_ulink_inside.*
import kotlinx.android.synthetic.main.toolbar_ulink_inside.*


class UlinkInsideActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ulink_inside)

        val class_name = intent.getStringExtra("class")
        val class_id = intent.getStringExtra("id")

        Log.d("UIActivity",""+class_name)

        val bundle =  Bundle()
        bundle.putString("class",class_name)
        bundle.putString("idx",class_id)
        val ulink_board_fragment = UlinkBoardFragment()
        ulink_board_fragment.setArguments(bundle)

        tv_classname.setText(class_name)

        val ulinkInsideAdapter =
            UlinkInsideAdapter(supportFragmentManager)
        vp_ulink_inside.adapter = ulinkInsideAdapter
        tablayout_ulink_inside.setupWithViewPager(vp_ulink_inside)

        btn_search.setOnClickListener {
            val intent = Intent(this, BoardSearchActivity::class.java)
            intent.putExtra("boardCategory",2)
            startActivity(intent)

        }
        btn_plus.setOnClickListener {
            val intent = Intent(this, ClassBoardWriteActivity::class.java)
            startActivity(intent)
        }
        btn_back.setOnClickListener {
            finish()
        }
    }
}