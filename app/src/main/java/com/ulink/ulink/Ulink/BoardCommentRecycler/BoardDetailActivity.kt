package com.ulink.ulink.Ulink.BoardCommentRecycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import com.ulink.ulink.R
import com.ulink.ulink.Ulink.AllBoardRecycler.AllBoardAdapter
import com.ulink.ulink.Ulink.AllBoardRecycler.AllBottomBoardAdapter
import com.ulink.ulink.Ulink.BoardData
import com.ulink.ulink.timetable.getGradeClickListener
import com.ulink.ulink.utils.DialogBuilder
import kotlinx.android.synthetic.main.activity_board_comment.*
import kotlinx.android.synthetic.main.toolbar_board_comment.*
import kotlinx.android.synthetic.main.toolbar_ulink_inside.btn_back

class BoardDetailActivity : AppCompatActivity() {
    lateinit var board_adapter : AllBottomBoardAdapter
    lateinit var comment_adapter : UlinkBoardCommentAdapter
    val datas : MutableList<BoardData> = mutableListOf<BoardData>()
    val datas1 : MutableList<BoardData> = mutableListOf<BoardData>()
    val datas2 : MutableList<BoardData> = mutableListOf<BoardData>()

    val comment_datas : MutableList<BoardData> = mutableListOf<BoardData>()
    val comment_datas1 : MutableList<BoardData> = mutableListOf<BoardData>()
    val comment_datas2 : MutableList<BoardData> = mutableListOf<BoardData>()

    val accuse_dialog = DialogBuilder()
    val choice_dialog = DialogBuilder()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_comment)

        val class_name = intent.getStringExtra("class")
        val class_id = intent.getStringExtra("idx")
        Log.d("BDActivity",""+class_name)

        btn_back.setOnClickListener {
            finish()
        }
        var viewtype = 0
        viewtype = intent.getIntExtra("viewtype", 0)

        comment_adapter = UlinkBoardCommentAdapter(this, viewtype)
        rv_board_comment.adapter = comment_adapter

        when(viewtype)
        {
            0->{
                Log.d("viewtype","000")
                tv_board_name.text = "Ulink게시판"
                 comment_datas.apply {
                     add(
                         BoardData(
                             board_idx = 0,
                             title = "님들 점심 추천",
                             initial = "",
                             nickname = "유링크좋아요",
                             content = "222김찌랑 된찌랑 둘중에 고민이에",
                             likeCount = 0,
                             commentCount = 0,
                             userIdx = 0,
                             createdAt = "",
                             updatedAt = "",
                             isLike = false
                         )
                     )

                     comment_adapter.data_ulink = comment_datas
                     comment_adapter.notifyDataSetChanged()
                 }
            }
            1->{
                Log.d("viewtype","111")

                tv_board_name.text = "학교게시판"
                comment_datas1.apply {
                    add(
                        BoardData(
                            board_idx = 0,
                            title = "님들 점심 추천",
                            initial = "",
                            nickname = "유링크좋아요",
                            content = "222김찌랑 된찌랑 둘중에 고민이에",
                            likeCount = 0,
                            commentCount = 0,
                            userIdx = 0,
                            createdAt = "",
                            updatedAt = "",
                            isLike = false
                        )
                    )

                    comment_adapter.data_university = comment_datas1
                    comment_adapter.notifyDataSetChanged()
                }
            }
            else -> {
                Log.d("viewtype","222")
                tv_board_name.text = class_name
                comment_datas2.apply {
                    add(
                        BoardData(
                            board_idx = 0,
                            title = "님들 점심 추천",
                            initial = "",
                            nickname = "유링크좋아요",
                            content = "222김찌랑 된찌랑 둘중에 고민이에",
                            likeCount = 0,
                            commentCount = 0,
                            userIdx = 0,
                            createdAt = "",
                            updatedAt = "",
                            isLike = false
                        )
                    )

                    comment_adapter.data_class = comment_datas2
                    comment_adapter.notifyDataSetChanged()
                }
            }
        }
//        choice_dialog.layout = LayoutInflater.from(this@BoardDetailActivity).inflate(R.layout.dialog_choice1, null)
//        choice_dialog.build(this@BoardDetailActivity)
//
//        choice_dialog.layout.findViewById<TextView>(R.id.tv_cancel).setOnClickListener {
//            choice_dialog.dismiss()
//        }
//        choice_dialog.layout.findViewById<TextView>(R.id.tv_accuse).setOnClickListener {
//            //TODO 신고 기능
//            choice_dialog.dismiss()
//        }
//
//        btn_siren.setOnClickListener {
//            accuse_dialog.layout = LayoutInflater.from(this@BoardDetailActivity).inflate(R.layout.dialog_board_accuse, null)
//            accuse_dialog.build(this@BoardDetailActivity)
//            accuse_dialog.show()
//
//            accuse_dialog.layout.findViewById<TextView>(R.id.tv_choice1).setOnClickListener {
//                //욕설
//                choice_dialog.show()
//
//            }
//            accuse_dialog.layout.findViewById<TextView>(R.id.tv_choice2).setOnClickListener {
//                //욕설
//                choice_dialog.layout.findViewById<TextView>(R.id.tv_insertname).setText("음란물 또는 불건전한 대화")
//                choice_dialog.setContent("청소년유해매체물 혹은 음란물, 음담패설 등\n타 사용자들에게 불쾌감을 주는 게시물로\n신고하시겠습니까?\n\n신고 접수까지 일정 시간이 소요됩니다.")
//                choice_dialog.show()
//
//
//            }
//            accuse_dialog.layout.findViewById<TextView>(R.id.tv_choice3).setOnClickListener {
//                //욕설
//                choice_dialog.layout.findViewById<TextView>(R.id.tv_insertname).setText("상업적인 광고 및 판매글")
//                choice_dialog.setContent("허가받지 않은 타 서비스 홍보, 이벤트 등의\n" + "광고/홍보 게시물로 신고하시겠습니까?\n" + "\n" + "신고 접수까지 일정 시간이 소요됩니다.")
//                choice_dialog.show()
//
//            }
//            accuse_dialog.layout.findViewById<TextView>(R.id.tv_choice4).setOnClickListener {
//                //욕설
//                choice_dialog.layout.findViewById<TextView>(R.id.tv_insertname).setText("특정 정당, 정치인 비하 혹은\n" +"선거운동")
//                choice_dialog.setContent("청소년유해매체물 혹은 음란물, 음담패설 등\n타 사용자들에게 불쾌감을 주는 게시물로\n신고하시겠습니까?\n\n신고 접수까지 일정 시간이 소요됩니다.")
//                choice_dialog.show()
//
//            }
//            accuse_dialog.layout.findViewById<TextView>(R.id.tv_choice5).setOnClickListener {
//                //욕설
//                choice_dialog.layout.findViewById<TextView>(R.id.tv_insertname).setText("사칭 혹은 사기가 의심되는 글")
//                choice_dialog.setContent("개인 혹은 관리자를 사칭하여 타인의 권리를\n" + "침해하고 사용자들에게 혼란을 야기하는\n" + "게시물로 신고하시겠습니까?\n" + "\n" +"신고 접수까지 일정 시간이 소요됩니다.")
//                choice_dialog.show()
//
//            }
//            accuse_dialog.layout.findViewById<TextView>(R.id.tv_choice6).setOnClickListener {
//                //욕설
//                choice_dialog.layout.findViewById<TextView>(R.id.tv_insertname).setText("기타 사유")
//                choice_dialog.setContent("사용자들의 권리를 침해하는 기타 사유로\n" +"이 게시물을 신고하시겠습니까?\n" + "\n" + "신고 접수까지 일정 시간이 소요됩니다.")
//                choice_dialog.show()
//
//            }
//
//        }


        board_adapter = AllBottomBoardAdapter(this, viewtype)
        rv_ulink_board.adapter = board_adapter
        //TODO 이전의 게시판 데이터 넘겨서 받아온뒤 띄워주기

        when (viewtype) {
            0 -> datas.apply{
                add(
                    BoardData(
                        board_idx = 0,
                        title = "111님들 점심 추천",
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
            1 -> datas1.apply {
                add(
                    BoardData(
                        board_idx = 0,
                        title = "111총장직선제 개선촉구 시위 마지막 공지",
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

                board_adapter.datas_university = datas1
                board_adapter.notifyDataSetChanged()
            }
            2 ->datas2.apply{
                add(
                    BoardData(
                        board_idx = 0,
                        title = "님들 점심 추천",
                        initial = "",
                        nickname = "유링크좋아요",
                        content = "111김찌랑 된찌랑 둘중에 고민이에",
                        likeCount = 0,
                        commentCount =0,
                        userIdx = 0,
                        createdAt = "",
                        updatedAt = "",
                        isLike = false
                    )
                )

                board_adapter.datas_class = datas2
                board_adapter.notifyDataSetChanged()
            }
        }
    }


}
