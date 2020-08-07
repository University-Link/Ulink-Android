package com.ulink.ulink.Ulink

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ulink.ulink.R
import kotlinx.android.synthetic.main.toolbar_create_board.*

class ClassBoardWriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class_board_write)

        btn_back.setOnClickListener{
            finish()
        }

        btn_check.setOnClickListener {
            //TODO 새 게시판 추가하기
            finish()
        }
    }
}