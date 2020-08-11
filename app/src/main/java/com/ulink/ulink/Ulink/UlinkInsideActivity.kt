package com.ulink.ulink.Ulink

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ulink.ulink.R
import com.ulink.ulink.Ulink.BoardSearchRecycler.BoardSearchActivity
import com.ulink.ulink.Ulink.ClassBoard.UlinkBoardFragment
import com.ulink.ulink.Ulink.ulinknotice.NoticeWriteActivity
import kotlinx.android.synthetic.main.activity_ulink_inside.*
import kotlinx.android.synthetic.main.toolbar_ulink_inside.*


class UlinkInsideActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ulink_inside)

        val subjectName = intent.getStringExtra("class")
        val subjectIdx = intent.getStringExtra("idx")

        Log.d("UIActivity",""+subjectName)

        val bundle =  Bundle()
        bundle.putString("class",subjectName)
        bundle.putString("idx",subjectIdx)
        val ulink_board_fragment = UlinkBoardFragment()
        ulink_board_fragment.setArguments(bundle)

        if(subjectName!="")
            tv_classname.setText(subjectName)

        val ulinkInsideAdapter = UlinkInsideAdapter(supportFragmentManager, subjectName, subjectIdx)
        vp_ulink_inside.adapter = ulinkInsideAdapter
        tablayout_ulink_inside.setupWithViewPager(vp_ulink_inside)

        btn_search.setOnClickListener {
            val intent = Intent(this, BoardSearchActivity::class.java)
            intent.putExtra("boardCategory",2)
            startActivity(intent)
        }

        btn_plus.setOnClickListener {
            if(vp_ulink_inside.currentItem == 0){
                val intent = Intent(this, ClassBoardWriteActivity::class.java)
                startActivity(intent)
            }
            else {
                val intent = Intent(this, NoticeWriteActivity::class.java)
                intent.putExtra("subjectName", subjectName)
                intent.putExtra("subjectIdx", subjectIdx)
                intent.putExtra("mode", "add")
                startActivity(intent)
            }
        }

        btn_back.setOnClickListener {
            finish()
        }
    }
}