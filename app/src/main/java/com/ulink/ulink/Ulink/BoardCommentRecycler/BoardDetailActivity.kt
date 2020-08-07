package com.ulink.ulink.Ulink.BoardCommentRecycler

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ulink.ulink.R
import com.ulink.ulink.Ulink.BoardSubjectData
import com.ulink.ulink.Ulink.BoardUlinkData
import com.ulink.ulink.Ulink.BoardUniversityData
import com.ulink.ulink.utils.DialogBuilder
import kotlinx.android.synthetic.main.activity_board_comment.*
import kotlinx.android.synthetic.main.toolbar_board_comment.*
import kotlinx.android.synthetic.main.toolbar_ulink_inside.btn_back

class BoardDetailActivity : AppCompatActivity(), onClickMore {

    lateinit var comment_adapter: UlinkBoardCommentAdapter

    val datas: MutableList<BoardUlinkData> = mutableListOf()
    val datas1: MutableList<BoardUniversityData> = mutableListOf()
    val datas2: MutableList<BoardSubjectData> = mutableListOf()

//    val comment_datas: MutableList<BoardUlinkData> = mutableListOf()
//    val comment_datas1: MutableList<BoardUniversityData> = mutableListOf()
//    val comment_datas2: MutableList<BoardSubjectData> = mutableListOf()


    private lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_comment)

        val isMine = false

        val class_name = intent.getStringExtra("class")
        val class_id = intent.getStringExtra("idx")

        var boardType = 0
        boardType = intent.getIntExtra("boardType", 0)

        btn_board_more.setOnClickListener {
            //TODO 게시글 mine notmine 판단해서 다이얼로그 띄우기
            val builder = AlertDialog.Builder(this)

            //사용자가 나일때
            if(isMine) {
             val layout_mine = LayoutInflater.from(this).inflate(R.layout.dialog_board_mine, null)
             builder.setView(layout_mine)

                dialog = builder.create()
                dialog.window?.setBackgroundDrawable(
                    InsetDrawable(
                        ColorDrawable(Color.TRANSPARENT),
                        100
                    )
                )
                dialog.show()
             layout_mine.findViewById<TextView>(R.id.btn_update).setOnClickListener{
                 //TODO 수정
                 Toast.makeText(this,"수정",Toast.LENGTH_SHORT).show()
             }
            layout_mine.findViewById<TextView>(R.id.btn_delete).setOnClickListener{
                //TODO 삭제
                Toast.makeText(this,"삭",Toast.LENGTH_SHORT).show()

            }
            }else {

                //사용자 내가 아닐때
                val layout_notmine =
                    LayoutInflater.from(this).inflate(R.layout.dialog_board_notmine, null)

                builder.setView(layout_notmine)

                dialog = builder.create()
                dialog.window?.setBackgroundDrawable(
                    InsetDrawable(
                        ColorDrawable(Color.TRANSPARENT),
                        100
                    )
                )
                dialog.show()

                layout_notmine.findViewById<TextView>(R.id.btn_send_message).setOnClickListener {
                    //TODO 쪽지 보내기
                    Toast.makeText(this, "쪽지보내기", Toast.LENGTH_SHORT).show()

                }
                layout_notmine.findViewById<TextView>(R.id.btn_assert).setOnClickListener {
                    //TODO 신고
                    MakeReportDialog(it)


                }
            }


        }


        btn_back.setOnClickListener {
            finish()
        }


        comment_adapter = UlinkBoardCommentAdapter(this, boardType, this)
        rv_board_comment.adapter = comment_adapter

//        TODO 여기 comment data apply
//        when (boardType) {
//            0 -> {
//                tv_board_name.text = "Ulink게시판"
//                comment_datas.apply {
//                    add(
//                            BoardUlinkData(
//                                    boardPublicIdx = 0,
//                                    title = "님들 점심 추천",
//                                    initial = "",
//                                    nickname = "유링크좋아요",
//                                    content = "222김찌랑 된찌랑 둘중에 고민이에",
//                                    likeCount = 0,
//                                    commentCount = 0,
//                                    userIdx = 0,
//                                    createdAt = "",
//                                    updatedAt = "",
//                                    isLike = false
//                            )
//                    )
//
//                    comment_adapter.data_ulink = comment_datas
//                    comment_adapter.notifyDataSetChanged()
//                }
//            }
//            1 -> {
//
//                tv_board_name.text = "학교게시판"
//                comment_datas1.apply {
//                    add(
//                            BoardUniversityData(
//                                    boardUniversityIdx = 0,
//                                    title = "님들 점심 추천",
//                                    initial = "",
//                                    nickname = "유링크좋아요",
//                                    content = "222김찌랑 된찌랑 둘중에 고민이에",
//                                    likeCount = 0,
//                                    commentCount = 0,
//                                    userIdx = 0,
//                                    createdAt = "",
//                                    updatedAt = "",
//                                    isLike = false,
//                                    universityIdx = 0
//                            )
//                    )
//
//                    comment_adapter.data_university = comment_datas1
//                    comment_adapter.notifyDataSetChanged()
//                }
//            }
//            else -> {
//                tv_board_name.text = class_name
//                comment_datas2.apply {
//                    add(
//                            BoardSubjectData(
//                                    boardSubjectIdx = 0,
//                                    title = "님들 점심 추천",
//                                    initial = "",
//                                    nickname = "유링크좋아요",
//                                    content = "222김찌랑 된찌랑 둘중에 고민이에",
//                                    likeCount = 0,
//                                    commentCount = 0,
//                                    userIdx = 0,
//                                    createdAt = "",
//                                    updatedAt = "",
//                                    isLike = false,
//                                    isNotice = 0,
//                                    subjectIdx = 0
//                            )
//                    )
//
//                    comment_adapter.data_class = comment_datas2
//                    comment_adapter.notifyDataSetChanged()
//                }
//            }
//        }


        //TODO 이전의 게시판 데이터 넘겨서 받아온뒤 띄워주기
        when (boardType) {
            0 ->{
                val itemView = LayoutInflater.from(this).inflate(R.layout.item_searched_ulink_board_ulink_data, layout_board, false)

                val boardData =  BoardUlinkData(
                        boardPublicIdx = 0,
                        title = "111님들 점심 추천",
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
                )

                val tv_title: TextView = itemView.findViewById(R.id.tv_title)
                val tv_nickname: TextView = itemView.findViewById(R.id.tv_nickname)
                val tv_time: TextView = itemView.findViewById(R.id.tv_time)
                val tv_content: TextView = itemView.findViewById(R.id.tv_content)
                val tv_comment_count: TextView = itemView.findViewById(R.id.tv_comment_count)
                val tv_heart_count: TextView = itemView.findViewById(R.id.tv_heart_count)
                val img_tag: ImageView = itemView.findViewById(R.id.img_uni_tag)

                img_tag.visibility = View.VISIBLE
                tv_title.text = boardData.title
                tv_nickname.text = boardData.nickname
                tv_time.text = boardData.createdAt
                tv_content.text = boardData.content
                tv_comment_count.text = "댓글 " + boardData.commentCount
                tv_heart_count.text = boardData.likeCount.toString()

                layout_board.addView(itemView)
            }
            1 ->{
                val itemView = LayoutInflater.from(this).inflate(R.layout.item_searched_ulink_board_ulink_data, layout_board, false)

                val boardData =   BoardUniversityData(
                        boardUniversityIdx = 0,
                        title = "111총장직선제 개선촉구 시위 마지막 공지",
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
                )

                val tv_title: TextView = itemView.findViewById(R.id.tv_title)
                val tv_nickname: TextView = itemView.findViewById(R.id.tv_nickname)
                val tv_time: TextView = itemView.findViewById(R.id.tv_time)
                val tv_content: TextView = itemView.findViewById(R.id.tv_content)
                val tv_comment_count: TextView = itemView.findViewById(R.id.tv_comment_count)
                val tv_heart_count: TextView = itemView.findViewById(R.id.tv_heart_count)
                val img_tag: ImageView = itemView.findViewById(R.id.img_uni_tag)

                img_tag.visibility = View.GONE
                tv_title.text = boardData.title
                tv_nickname.text = boardData.nickname
                tv_time.text = boardData.createdAt
                tv_content.text = boardData.content
                tv_comment_count.text = "댓글 " + boardData.commentCount
                tv_heart_count.text = boardData.likeCount.toString()

                layout_board.addView(itemView)
            }
            2 ->{

                val itemView = LayoutInflater.from(this).inflate(R.layout.item_ulink_board_class_data, layout_board, false)

                val boardData = BoardSubjectData(
                        boardSubjectIdx = 0,
                        title = "님들 점심 추천",
                        initial = "",
                        nickname = "유링크좋아요",
                        profileImage = null,
                        content = "111김찌랑 된찌랑 둘중에 고민이에",
                        likeCount = 0,
                        commentCount = 0,
                        userIdx = 0,
                        createdAt = "",
                        isLike = false,
                        subjectIdx = 0,
                        isNotice = 0,
                        isMine = false,
                        noticeIdx = 0,
                        category = 0
                )


                val tv_nickname : TextView = itemView.findViewById(R.id.tv_nickname)
                val tv_time : TextView = itemView.findViewById(R.id.tv_time)
                val tv_content : TextView = itemView.findViewById(R.id.tv_content)
                val tv_comment_count : TextView = itemView.findViewById(R.id.tv_comment_count)
                val tv_heart_count : TextView = itemView.findViewById(R.id.tv_heart_count)
                //val img_tag : ImageView = itemView.findViewById(R.id.img_uni_tag)
                //val btn_heart : ImageButton = itemView.findViewById(R.id.btn_heart)


                tv_nickname.text = boardData.nickname
                tv_time.text = boardData.createdAt
                tv_content.text = boardData.content
                tv_comment_count.text = "댓글 "+boardData.commentCount
                tv_heart_count.text = boardData.likeCount.toString()


                layout_board.addView(itemView)
            }
        }
    }


    override fun onClick() {
        //사용자가 나일때
        val builder = AlertDialog.Builder(this)
//             val layout_mine = LayoutInflater.from(this).inflate(R.layout.dialog_board_reply_mine, null)
//             builder.setView(layout_mine)
//            layout_mine.findViewById<TextView>(R.id.btn_add_reply).setOnClickListener{
//                 //TODO 답글달기
//                 Toast.makeText(this,"답글달기  ",Toast.LENGTH_SHORT).show()
//             }
//            layout_mine.findViewById<TextView>(R.id.btn_delete).setOnClickListener{
//                //TODO 삭제
//                Toast.makeText(this,"삭제 ",Toast.LENGTH_SHORT).show()
//
//            }


        //사용자 내가 아닐때
        val layout_notmine = LayoutInflater.from(this).inflate(R.layout.dialog_board_reply_notmine, null)
        builder.setView(layout_notmine)

        dialog = builder.create()
        dialog.window?.setBackgroundDrawable(InsetDrawable(ColorDrawable(Color.TRANSPARENT), 100))
        dialog.show()
        layout_notmine.findViewById<TextView>(R.id.btn_send_message).setOnClickListener {
            //TODO 쪽지보내기
            Toast.makeText(this, " 쪽지보내기 ", Toast.LENGTH_SHORT).show()

        }
        layout_notmine.findViewById<TextView>(R.id.btn_add_reply).setOnClickListener {
            //TODO 답글달기
            Toast.makeText(this, "답글달기 ", Toast.LENGTH_SHORT).show()

        }
        layout_notmine.findViewById<TextView>(R.id.btn_accuse).setOnClickListener {
            //TODO 신고
            val accuse_dialog = DialogBuilder()
            val choice_dialog = DialogBuilder()


            choice_dialog.layout = LayoutInflater.from(this).inflate(R.layout.dialog_choice1, null)
            choice_dialog.build(this@BoardDetailActivity)

            choice_dialog.layout.findViewById<TextView>(R.id.tv_cancel).setOnClickListener {
                choice_dialog.dismiss()
            }
            choice_dialog.layout.findViewById<TextView>(R.id.tv_accuse).setOnClickListener {
                //TODO 신고 기능
                choice_dialog.dismiss()
            }

            accuse_dialog.layout = LayoutInflater.from(this@BoardDetailActivity).inflate(R.layout.dialog_board_accuse, null)
            accuse_dialog.build(this@BoardDetailActivity)
            accuse_dialog.show()

            accuse_dialog.layout.findViewById<TextView>(R.id.tv_choice1).setOnClickListener {
                //욕설
                choice_dialog.show()

            }
            accuse_dialog.layout.findViewById<TextView>(R.id.tv_choice2).setOnClickListener {
                //욕설
                choice_dialog.layout.findViewById<TextView>(R.id.tv_insertname).setText("음란물 또는 불건전한 대화")
                choice_dialog.setContent("청소년유해매체물 혹은 음란물, 음담패설 등\n타 사용자들에게 불쾌감을 주는 게시물로\n신고하시겠습니까?\n\n신고 접수까지 일정 시간이 소요됩니다.")
                choice_dialog.show()


            }
            accuse_dialog.layout.findViewById<TextView>(R.id.tv_choice3).setOnClickListener {
                //욕설
                choice_dialog.layout.findViewById<TextView>(R.id.tv_insertname).setText("상업적인 광고 및 판매글")
                choice_dialog.setContent("허가받지 않은 타 서비스 홍보, 이벤트 등의\n" + "광고/홍보 게시물로 신고하시겠습니까?\n" + "\n" + "신고 접수까지 일정 시간이 소요됩니다.")
                choice_dialog.show()

            }
            accuse_dialog.layout.findViewById<TextView>(R.id.tv_choice4).setOnClickListener {
                //욕설
                choice_dialog.layout.findViewById<TextView>(R.id.tv_insertname).setText("특정 정당, 정치인 비하 혹은\n" + "선거운동")
                choice_dialog.setContent("청소년유해매체물 혹은 음란물, 음담패설 등\n타 사용자들에게 불쾌감을 주는 게시물로\n신고하시겠습니까?\n\n신고 접수까지 일정 시간이 소요됩니다.")
                choice_dialog.show()

            }
            accuse_dialog.layout.findViewById<TextView>(R.id.tv_choice5).setOnClickListener {
                //욕설
                choice_dialog.layout.findViewById<TextView>(R.id.tv_insertname).setText("사칭 혹은 사기가 의심되는 글")
                choice_dialog.setContent("개인 혹은 관리자를 사칭하여 타인의 권리를\n" + "침해하고 사용자들에게 혼란을 야기하는\n" + "게시물로 신고하시겠습니까?\n" + "\n" + "신고 접수까지 일정 시간이 소요됩니다.")
                choice_dialog.show()


            }
            accuse_dialog.layout.findViewById<TextView>(R.id.tv_choice6).setOnClickListener {
                //욕설
//                        choice_dialog.layout.findViewById<TextView>(R.id.tv_insertname).setText("기타 사유")
//                        choice_dialog.setContent("사용자들의 권리를 침해하는 기타 사유로\n" +"이 게시물을 신고하시겠습니까?\n" + "\n" + "신고 접수까지 일정 시간이 소요됩니다.")
//                        choice_dialog.show()

                val layout = LayoutInflater.from(this).inflate(R.layout.dialog_other_reasons, null)
                builder.setView(layout)
                dialog = builder.create()
                dialog.window?.setBackgroundDrawable(InsetDrawable(ColorDrawable(Color.TRANSPARENT), 100))
                dialog.show()

                layout.findViewById<TextView>(R.id.tv_cancel).setOnClickListener {
                    dialog.dismiss()
                }
                layout.findViewById<TextView>(R.id.tv_accuse).setOnClickListener {
                    //TODO 신고
                    dialog.dismiss()

                }

            }

        }

    }


}

interface onClickMore {
    fun onClick(

    )
}

