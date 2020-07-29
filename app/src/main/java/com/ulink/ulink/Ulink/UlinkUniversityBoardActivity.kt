package com.ulink.ulink.Ulink

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ulink.ulink.R
import com.ulink.ulink.Ulink.UlinkBoardRecycler.UlinkBoardAdapter
import com.ulink.ulink.Ulink.UlinkBoardRecycler.UlinkBoardData
import kotlinx.android.synthetic.main.fragment_ulink_board.*
import kotlinx.android.synthetic.main.toolbar_ulink_inside.*
import kotlinx.android.synthetic.main.toolbar_ulink_inside.tv_classname

class UlinkUniversityBoardActivity : AppCompatActivity() {
    lateinit var board_adapter : UlinkBoardAdapter
    val datas : MutableList<UlinkBoardData> = mutableListOf<UlinkBoardData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ulink_university_board)
        tv_classname.text = "학교게시판"
        btn_back.setOnClickListener {
            finish()
        }

        board_adapter = UlinkBoardAdapter(this)
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
                    heart_count ="999+"

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
                    heart_count ="999+"

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
                    heart_count ="999+"

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
                    heart_count ="999+"

                )
            )

            board_adapter.datas = datas
            board_adapter.notifyDataSetChanged()
        }
    }
}
