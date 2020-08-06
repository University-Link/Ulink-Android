package com.ulink.ulink.Ulink.BoardSearchRecycler

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ulink.ulink.R
import com.ulink.ulink.Ulink.BoardData
import com.ulink.ulink.Ulink.UlinkClassBoardRecycler.BoardSearchAdapter
import com.ulink.ulink.textResetButton
import kotlinx.android.synthetic.main.activity_board_search.*

class BoardSearchActivity : AppCompatActivity() {
    lateinit var board_search_adapter : BoardSearchAdapter
    val datas : MutableList<BoardData> = mutableListOf<BoardData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_search)

        btn_back.setOnClickListener {
            finish()
        }

        val board_category = intent.getIntExtra("board_category",0)

        when(board_category){

            0->{//유링크 게시판 검색

            }
            1->{//학교 게시판 검색

            }
            2->{//수업별 게시판 검색

            }
            3->{//전체 게시판 검색

            }
        }
        btn_reset.textResetButton(edit)

        board_search_adapter =
            BoardSearchAdapter(this)
        rv_board_search.adapter = board_search_adapter

        datas.apply{
//            add(
//                BoardData(
//                    board_idx = 0,
//                    title = "님들 점심 추천",
//                    initial = "",
//                    nickname = "유링크좋아요",
//                    content = "김찌랑 된찌랑 둘중에 고민이에",
//                    likeCount = 0,
//                    commentCount = 0,
//                    userIdx = 0,
//                    createdAt = "방금",
//                    updatedAt = "",
//                    isLike = false
//                )
//            )

            board_search_adapter.datas = datas
            board_search_adapter.notifyDataSetChanged()
        }

    }
}