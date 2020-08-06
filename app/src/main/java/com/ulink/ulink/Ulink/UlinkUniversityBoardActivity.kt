package com.ulink.ulink.Ulink

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ulink.ulink.R
import com.ulink.ulink.Ulink.AllBoardRecycler.AllBoardAdapter
import com.ulink.ulink.Ulink.BoardCommentRecycler.BoardDetailActivity
import kotlinx.android.synthetic.main.activity_ulink_university_board.*
import kotlinx.android.synthetic.main.toolbar_ulink_inside.*


class UlinkUniversityBoardActivity : AppCompatActivity() {
    lateinit var board_adapter : AllBoardAdapter
    val datas : MutableList<BoardData> = mutableListOf<BoardData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ulink_university_board)

        tv_classname.text = "학교게시판"

        btn_back.setOnClickListener {
            finish()
        }

        btn_search.setOnClickListener {
            val intent = Intent(this, BoardSearchActivity::class.java)
            startActivity(intent)

        }

        btn_plus.setOnClickListener {
            val intent = Intent(this, UlinkBoardWriteActivity::class.java)
            startActivity(intent)
        }

        board_adapter = AllBoardAdapter(this,1)
        rv_ulink_board.adapter = board_adapter

        datas.apply{
            add(
                BoardData(
                    board_idx = 0,
                    title = "총장직선제 개선촉구 시위 마지막 공지",
                    initial = "",
                    nickname = "형광펜포스트잇23",
                    content = "안녕하세요 시위 TF팀입니다. 한달 지난 시점에 죄송합니다.",
                    likeCount = 0,
                    commentCount = 0,
                    userIdx = 0,
                    createdAt = "5분",
                    updatedAt = "",
                    isLike = false
                )
            )
            add(
                BoardData(
                    board_idx = 0,
                    title = "총장직선제 개선촉구 시위 마지막 공지",
                    initial = "",
                    nickname = "형광펜포스트잇23",
                    content = "안녕하세요 시위 TF팀입니다. 한달 지난 시점에 죄송합니다.",
                    likeCount = 0,
                    commentCount = 0,
                    userIdx = 0,
                    createdAt = "5분",
                    updatedAt = "",
                    isLike = false
                )
            )

            board_adapter.datas_university = datas
            board_adapter.notifyDataSetChanged()
        }

        board_adapter.setItemClickLIstener(object : AllBoardAdapter.ItemClickListener{
            override fun onClick(view: View, position: Int) {
                val intent = Intent(this@UlinkUniversityBoardActivity, BoardDetailActivity::class.java)
                intent.putExtra("viewtype", 1)
                startActivity(intent)
            }

        })
    }
}
