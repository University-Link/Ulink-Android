package com.ulink.ulink.Ulink.ClassBoard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ulink.ulink.R
import com.ulink.ulink.Ulink.BoardRecycler.AllBoardAdapter
import com.ulink.ulink.Ulink.BoardCommentRecycler.BoardDetailActivity
import com.ulink.ulink.Ulink.BoardData
import com.ulink.ulink.Ulink.onClickLike
import com.ulink.ulink.repository.DataRepository
import kotlinx.android.synthetic.main.fragment_ulink_board.rv_ulink_board


class UlinkBoardFragment() : Fragment() ,onClickLike{
    lateinit var board_adapter: AllBoardAdapter
    val datas: MutableList<BoardData> = mutableListOf()
    lateinit var class_name: String
    lateinit var class_id: String

    var loading = false
    var nextPage = 0

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

        board_adapter = AllBoardAdapter(view.context, 2, false)
        rv_ulink_board.adapter = board_adapter

        board_adapter.setListener(this)

        DataRepository.getSubjectBoard(
                onSuccess = {list, nextPage->
                    board_adapter.setSubjectData(list)
                    this.nextPage = nextPage
                    loading = true

                    if(list.isEmpty()){
                        tv_board_nothing.visibility=View.VISIBLE
                        rv_ulink_board.visibility=View.GONE
                    }
                    else{
                        tv_board_nothing.visibility=View.GONE
                        rv_ulink_board.visibility=View.VISIBLE
                    }
                },
                onFailure = {

                }
        )


        val layoutManager = LinearLayoutManager(requireContext())
        rv_ulink_board.layoutManager = layoutManager

        rv_ulink_board.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy>0){
                    if(loading){
                        if (layoutManager.childCount + layoutManager.findFirstVisibleItemPosition() >= layoutManager.itemCount){
                            loading = false
                            DataRepository.getSubjectBoard(
                                    this@UlinkBoardFragment.nextPage,
                                    onSuccess = {list, nextPage->
                                        loading = list.isNotEmpty()
                                        if (loading){
                                            this@UlinkBoardFragment.nextPage = nextPage
                                            board_adapter.addSubjectData(list)
                                        }
                                    },
                                    onFailure = {}
                            )

                        }
                    }

                }
            }

        })


        board_adapter.setItemClickLIstener(object : AllBoardAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int) {
                val intent = Intent(getActivity(), BoardDetailActivity::class.java)
                //TODO 수업별 게시판 검색
                intent.putExtra("boardType", 2)
                //intent.putExtra("class",class_name)
                //intent.putExtra("idx",class_id)
                startActivity(intent)
            }
        })

    }

   

    override fun onClick(position: Int) {
        Toast.makeText(context,"좋아요클릭", Toast.LENGTH_SHORT).show()
    }

}

