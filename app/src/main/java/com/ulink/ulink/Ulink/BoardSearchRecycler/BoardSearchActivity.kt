package com.ulink.ulink.Ulink.BoardSearchRecycler

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ulink.ulink.R
import com.ulink.ulink.Ulink.BoardRecycler.AllBoardAdapter
import com.ulink.ulink.Ulink.BoardSubjectData
import com.ulink.ulink.Ulink.BoardUlinkData
import com.ulink.ulink.Ulink.BoardUniversityData
import com.ulink.ulink.textResetButton
import kotlinx.android.synthetic.main.activity_board_search.*

class BoardSearchActivity : AppCompatActivity() {
    lateinit var boardAdapter: AllBoardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_search)


        btn_back.setOnClickListener {
            finish()
        }

        val boardCategory = intent.getIntExtra("boardCategory", 0)

        when (boardCategory) {
            0 -> {//유링크 게시판 검색

            }
            1 -> {//학교 게시판 검색

            }
            2 -> {//수업별 게시판 검색

            }
            3 -> {//전체 게시판 검색


                boardAdapter = AllBoardAdapter(this, 3, true)
                rv_board_search.adapter = boardAdapter

//                TODO 여기서 서버랑 통신해서 data 섞인거 가져오기!
//                  검색누르면 이런식으로 나온다~
                val list: MutableList<Any> = mutableListOf(BoardUlinkData(
                        boardPublicIdx = 0,
                        title = "님들 점심 추천",
                        initial = "",
                        nickname = "유링크좋아요",
                        profileImage = null,
                        content = "김찌랑 된찌랑 둘중에 고민이에",
                        likeCount = 0,
                        commentCount = 0,
                        userIdx = 0,
                        createdAt = "방금",
                        isLike = false,
                        isMine = false
                ), BoardUniversityData(
                        boardUniversityIdx = 0,
                        title = "총장직선제 개선촉구 시위 마지막 공지",
                        initial = "",
                        nickname = "형광펜포스트잇23",
                        profileImage = null,
                        content = "안녕하세요 시위 TF팀입니다. 한달 지난 시점에 죄송합니다.",
                        likeCount = 0,
                        commentCount = 0,
                        userIdx = 0,
                        createdAt = "5분",
                        isLike = false,
                        universityIdx = 0,
                        isMine = false
                ),  BoardSubjectData(
                        boardSubjectIdx = 0,
                        title = "님들 점심 추천",
                        initial = "",
                        nickname = "유링크좋아요",
                        profileImage = null,
                        content = "김찌랑 된찌랑 둘중에 고민이에",
                        likeCount = 0,
                        commentCount = 0,
                        userIdx = 0,
                        createdAt = "",
                        isLike = false,
                        isNotice = 0,
                        subjectIdx = 0,
                        isMine = false,
                        noticeIdx = 0,
                        category = 0
                )
                )
                boardAdapter.setAllDataSearch(list)

            }
        }
        btn_reset.textResetButton(edit)
    }
}