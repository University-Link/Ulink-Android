package com.ulink.ulink.Ulink.ClassBoard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ulink.ulink.R
import com.ulink.ulink.Ulink.AllBoardRecycler.AllBoardAdapter
import com.ulink.ulink.Ulink.BoardCommentRecycler.BoardDetailActivity
import com.ulink.ulink.Ulink.BoardData
import kotlinx.android.synthetic.main.fragment_ulink_board.*
import kotlinx.android.synthetic.main.toolbar_board_comment.*


class UlinkBoardFragment() : Fragment() {
    lateinit var board_adapter : AllBoardAdapter
    val datas : MutableList<BoardData> = mutableListOf<BoardData>()
    lateinit var  class_name : String
    lateinit var class_id : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ulink_board, container, false)

        //TODO 왜 값이 안넘어올까 UlinkDetailActivity에 제목 넘기기ㅠ
        val bundle = getArguments() //번들 안의 텍스트 불러오기
        class_name = bundle?.getString("class").toString()
        class_id = bundle?.getString("idx").toString()
        //Log.d("ubfragment",""+class_name)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        board_adapter = AllBoardAdapter(view.context,2,false)
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
                    commentCount =0,
                    userIdx = 0,
                    createdAt = "",
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
                    commentCount =0,
                    userIdx = 0,
                    createdAt = "",
                    updatedAt = "",
                    isLike = false
                )
            )
            board_adapter.datas_class = datas
            board_adapter.notifyDataSetChanged()
        }

        board_adapter.setItemClickLIstener(object: AllBoardAdapter.ItemClickListener{
            override fun onClick(view:View, position:Int){
                val intent = Intent(getActivity(), BoardDetailActivity::class.java)
                //TODO 수업별 게시판 검색
                intent.putExtra("viewtype",2)
                intent.putExtra("board_category",3)
                //intent.putExtra("class",class_name)
                //intent.putExtra("idx",class_id)
                startActivity(intent)
            }
        })

    }

}