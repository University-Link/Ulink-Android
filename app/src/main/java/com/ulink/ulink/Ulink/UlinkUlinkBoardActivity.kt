package com.ulink.ulink.Ulink

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ulink.ulink.R
import com.ulink.ulink.Ulink.BoardRecycler.AllBoardAdapter
import com.ulink.ulink.Ulink.BoardCommentRecycler.BoardDetailActivity
import com.ulink.ulink.Ulink.BoardSearchRecycler.BoardSearchActivity
import com.ulink.ulink.repository.DataRepository
import kotlinx.android.synthetic.main.activity_ulink_all_board.*
import kotlinx.android.synthetic.main.toolbar_ulink_inside.*


class UlinkUlinkBoardActivity : AppCompatActivity() ,onClickLike{
    lateinit var board_adapter: AllBoardAdapter
    var loading = false
    var nextPage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ulink_all_board)
        tv_classname.text = "Ulink게시판"

        btn_back.setOnClickListener {
            finish()
        }

        btn_search.setOnClickListener {
            //TODO 유링크 검색
            val intent = Intent(this, BoardSearchActivity::class.java)
            intent.putExtra("boardCategory", 0)
            startActivity(intent)

        }
        btn_plus.setOnClickListener {
            val intent = Intent(this, UlinkBoardWriteActivity::class.java)
            startActivity(intent)
        }
        board_adapter =
                AllBoardAdapter(this, 0, false)
        rv_ulink_board.adapter = board_adapter
        board_adapter.setListener(this)


        DataRepository.getPublicBoard(
                onSuccess = {list, nextPage->
                    board_adapter.setUlinkData(list)
                    this.nextPage = nextPage
                    loading = true
                },
                onFailure = {

                }
        )

        val layoutManager = LinearLayoutManager(this)
        rv_ulink_board.layoutManager = layoutManager

        rv_ulink_board.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy>0){
                    if(loading){
                        if (layoutManager.childCount + layoutManager.findFirstVisibleItemPosition() >= layoutManager.itemCount){
                            loading = false
                            DataRepository.getPublicBoard(
                                    this@UlinkUlinkBoardActivity.nextPage,
                                    onSuccess = {list, nextPage->
                                        loading = list.isNotEmpty()
                                        if (loading){
                                            this@UlinkUlinkBoardActivity.nextPage = nextPage
                                            board_adapter.addUlinkData(list)
                                        }
                                    },
                                    onFailure = {}
                            )

                        }
                    }

                }
            }

        })


//        TODO 이때 포지션도 같이 넘겨줘서 onactivityresult에서 좋아요 댓글 수 갱신해주기!
        board_adapter.setItemClickLIstener(object : AllBoardAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int) {
                val intent = Intent(this@UlinkUlinkBoardActivity, BoardDetailActivity::class.java)
                intent.putExtra("boardIdx", board_adapter.datas_ulink[position].boardPublicIdx)
                intent.putExtra("boardType", 0)
                startActivity(intent)
            }

        })
    }

    override fun onClick() {
        //TODO 좋아요 클릭했을때
        Toast.makeText(this,"좋아요클릭",Toast.LENGTH_SHORT).show()
    }
}
interface onClickLike {
    fun onClick(

    )
}
