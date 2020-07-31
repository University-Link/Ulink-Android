package com.ulink.ulink.Ulink.ClassBoard

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ulink.ulink.R
import com.ulink.ulink.Ulink.BoardDetailActivity
import com.ulink.ulink.Ulink.UlinkBoardRecycler.UlinkBoardAdapter
import com.ulink.ulink.Ulink.UlinkBoardRecycler.UlinkBoardData
import kotlinx.android.synthetic.main.fragment_ulink_board.*


class UlinkBoardFragment : Fragment() {
    lateinit var board_adapter : UlinkBoardAdapter
    val datas : MutableList<UlinkBoardData> = mutableListOf<UlinkBoardData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ulink_board, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        board_adapter = UlinkBoardAdapter(view.context)

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

        board_adapter.setItemClickLIstener(object: UlinkBoardAdapter.ItemClickListener{
            override fun onClick(view:View, position:Int){
                val intent = Intent(getActivity(), BoardDetailActivity::class.java)
                //intent.putExtra("idx", datas[position].subjectIdx.toString())
                startActivity(intent)
            }
        })

    }



}