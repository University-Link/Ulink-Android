package com.ulink.ulink.Ulink

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ulink.ulink.R
import com.ulink.ulink.Ulink.UlinkUlinkBoardRecycler.UlinkUlinkBoardAdapter
import kotlinx.android.synthetic.main.activity_ulink_all_board.*
import kotlinx.android.synthetic.main.toolbar_ulink_inside.*


class UlinkUlinkBoardActivity : AppCompatActivity() {
    lateinit var board_adapter : UlinkUlinkBoardAdapter
    val datas : MutableList<UlinkBoardData> = mutableListOf<UlinkBoardData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ulink_all_board)
        tv_classname.text = "Ulink게시판"
        btn_back.setOnClickListener {
            finish()
        }

        btn_search.setOnClickListener {
            val intent = Intent(this, BoardSearchActivity::class.java)
            startActivity(intent)

        }
        board_adapter =
            UlinkUlinkBoardAdapter(this)
        rv_ulink_board.adapter = board_adapter

        datas.apply{
            add(
                UlinkBoardData(
                    img_profile = "",
                    nickname = "조개탕수만",
                    time = "방금",
                    content = "ㅁㅇㄴㄹㅁㄴㅇㄹ",
                    like = true,
                    comment_count = "2",
                    heart_count = "999+"

                )
            )
            add(
                UlinkBoardData(
                    img_profile = "",
                    nickname = "조개탕수만",
                    time = "방금",
                    content = "ㅁㅇㄴㄹㅁㄴㅇㄹ",
                    like = true,
                    comment_count = "2",
                    heart_count = "999+"

                )
            )
            add(
                UlinkBoardData(
                    img_profile = "",
                    nickname = "조개탕수만",
                    time = "방금",
                    content = "ㅁㅇㄴㄹㅁㄴㅇㄹ",
                    like = true,
                    comment_count = "2",
                    heart_count = "999+"

                )
            )
            add(
                UlinkBoardData(
                    img_profile = "",
                    nickname = "조개탕수만",
                    time = "방금",
                    content = "ㅁㅇㄴㄹㅁㄴㅇㄹ",
                    like = true,
                    comment_count = "2",
                    heart_count = "999+"

                )
            )

            board_adapter.datas = datas
            board_adapter.notifyDataSetChanged()
        }
    }
}
