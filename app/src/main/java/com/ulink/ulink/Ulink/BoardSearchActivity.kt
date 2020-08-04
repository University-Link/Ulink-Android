package com.ulink.ulink.Ulink

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ulink.ulink.R
import com.ulink.ulink.Ulink.UlinkBoardRecycler.BoardSearchAdapter
import com.ulink.ulink.textResetButton
import kotlinx.android.synthetic.main.activity_board_search.*
import kotlinx.android.synthetic.main.activity_board_search.btn_back
import kotlinx.android.synthetic.main.activity_board_search.btn_reset
import kotlinx.android.synthetic.main.activity_board_search.edit

class BoardSearchActivity : AppCompatActivity() {
    lateinit var board_search_adapter : BoardSearchAdapter
    val datas : MutableList<UlinkBoardData> = mutableListOf<UlinkBoardData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_search)

        btn_back.setOnClickListener {
            finish()
        }

        btn_reset.textResetButton(edit)

        board_search_adapter =
            BoardSearchAdapter(this)
        rv_board_search.adapter = board_search_adapter

        datas.apply{
            add(
                UlinkBoardData(
                    img_profile = "",
                    nickname = "조개탕수만",
                    time = "방금",
                    content = "교필 비판적사고 수강신청하기가 1학년 아니면 많이 힘들까?",
                    like = true,
                    comment_count = "2",
                    heart_count = "1",
                    board_category = "Ulink게시판"

                )
            )

            board_search_adapter.datas = datas
            board_search_adapter.notifyDataSetChanged()
        }

    }
}