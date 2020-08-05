package com.ulink.ulink.Ulink

import android.app.PendingIntent.getActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ulink.ulink.R
import com.ulink.ulink.Ulink.AllBoardRecycler.AllBoardAdapter
import com.ulink.ulink.Ulink.BoardCommentRecycler.BoardDetailActivity
import kotlinx.android.synthetic.main.activity_ulink_all_board.*
import kotlinx.android.synthetic.main.toolbar_ulink_inside.*


class UlinkUlinkBoardActivity : AppCompatActivity() {
    lateinit var board_adapter : AllBoardAdapter
    val datas : MutableList<BoardData> = mutableListOf<BoardData>()
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
        btn_plus.setOnClickListener {
            val intent = Intent(this, UlinkBoardWriteActivity::class.java)
            startActivity(intent)
        }
        board_adapter =
            AllBoardAdapter(this,0)
        rv_ulink_board.adapter = board_adapter



        datas.apply{
            add(
                BoardData(
                    board_idx = 0,
                    title = "님들 점심 추천",
                    initial = "",
                    nickname = "유링크좋아요",
                    content = "김찌랑 된찌랑 둘중에 고민이에",
                    likeCount = 0,
                    commentCount = 0,
                    userIdx = 0,
                    createdAt = "방금",
                    updatedAt = "",
                    isLike = false
                )
            )
            add(
                BoardData(
                    board_idx = 0,
                    title = "님들 점심 추천",
                    initial = "",
                    nickname = "유링크좋아요",
                    content = "김찌랑 된찌랑 둘중에 고민이에",
                    likeCount = 0,
                    commentCount = 0,
                    userIdx = 0,
                    createdAt = "방금",
                    updatedAt = "",
                    isLike = false
                )
            )

            board_adapter.datas_ulink = datas
            board_adapter.notifyDataSetChanged()
        }

        board_adapter.setItemClickLIstener(object : AllBoardAdapter.ItemClickListener{
            override fun onClick(view: View, position: Int) {
                val intent = Intent(this@UlinkUlinkBoardActivity, BoardDetailActivity::class.java)
                intent.putExtra("viewtype", 0)
                startActivity(intent)
            }

        })
    }
}
