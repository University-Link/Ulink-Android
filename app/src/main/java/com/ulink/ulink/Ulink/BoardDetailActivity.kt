package com.ulink.ulink.Ulink

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import com.ulink.ulink.R
import com.ulink.ulink.Ulink.BoardCommentRecycler.UlinkBoardCommentAdapter
import com.ulink.ulink.Ulink.UlinkBoardRecycler.UlinkBoardData
import com.ulink.ulink.utils.DialogBuilder
import kotlinx.android.synthetic.main.activity_board_comment.*
import kotlinx.android.synthetic.main.toolbar_board_comment.*
import kotlinx.android.synthetic.main.toolbar_ulink_inside.btn_back

class BoardDetailActivity : AppCompatActivity() {
    lateinit var board_adapter : UlinkBoardCommentAdapter
    val datas : MutableList<UlinkBoardData> = mutableListOf<UlinkBoardData>()
    val accuse_dialog = DialogBuilder()
    val choice_dialog = DialogBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_comment)
        btn_back.setOnClickListener {
            finish()
        }
        choice_dialog.layout = LayoutInflater.from(this@BoardDetailActivity).inflate(R.layout.dialog_choice1, null)
        choice_dialog.build(this@BoardDetailActivity)

        choice_dialog.layout.findViewById<TextView>(R.id.tv_cancel).setOnClickListener {
            choice_dialog.dismiss()
        }
        choice_dialog.layout.findViewById<TextView>(R.id.tv_accuse).setOnClickListener {
            //TODO 신고 기능
            choice_dialog.dismiss()
        }

        btn_siren.setOnClickListener {
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
                choice_dialog.layout.findViewById<TextView>(R.id.tv_insertname).setText("특정 정당, 정치인 비하 혹은\n" +"선거운동")
                choice_dialog.setContent("청소년유해매체물 혹은 음란물, 음담패설 등\n타 사용자들에게 불쾌감을 주는 게시물로\n신고하시겠습니까?\n\n신고 접수까지 일정 시간이 소요됩니다.")
                choice_dialog.show()

            }
            accuse_dialog.layout.findViewById<TextView>(R.id.tv_choice5).setOnClickListener {
                //욕설
                choice_dialog.layout.findViewById<TextView>(R.id.tv_insertname).setText("사칭 혹은 사기가 의심되는 글")
                choice_dialog.setContent("개인 혹은 관리자를 사칭하여 타인의 권리를\n" + "침해하고 사용자들에게 혼란을 야기하는\n" + "게시물로 신고하시겠습니까?\n" + "\n" +"신고 접수까지 일정 시간이 소요됩니다.")
                choice_dialog.show()

            }
            accuse_dialog.layout.findViewById<TextView>(R.id.tv_choice6).setOnClickListener {
                //욕설
                choice_dialog.layout.findViewById<TextView>(R.id.tv_insertname).setText("기타 사유")
                choice_dialog.setContent("사용자들의 권리를 침해하는 기타 사유로\n" +"이 게시물을 신고하시겠습니까?\n" + "\n" + "신고 접수까지 일정 시간이 소요됩니다.")
                choice_dialog.show()

            }

        }


        board_adapter = UlinkBoardCommentAdapter(this)

        rv_board_comment.adapter = board_adapter

        datas.apply {
            add(
                UlinkBoardData(
                    img_profile = "",
                    nickname = "홍주니",
                    time = "10분전",
                    content = "공감합니다:)",
                    like = false,
                    comment_count = "",
                    heart_count = "1"

                )
            )
            board_adapter.datas = datas
            board_adapter.notifyDataSetChanged()
        }
    }

}