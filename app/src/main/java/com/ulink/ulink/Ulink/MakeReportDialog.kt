package com.ulink.ulink.Ulink

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.ulink.ulink.R


fun MakeReportDialog(view : View){
    lateinit var dialog: AlertDialog

    val builder = AlertDialog.Builder(view.context)

    val accuse_dialog = DialogBuilder2()
    val choice_dialog = DialogBuilder2()


    choice_dialog.layout =
        LayoutInflater.from(view.context).inflate(R.layout.dialog_choice1, null)
    choice_dialog.build(view.context)

    choice_dialog.layout.findViewById<TextView>(R.id.tv_cancel).setOnClickListener {
        choice_dialog.dismiss()
    }
    choice_dialog.layout.findViewById<TextView>(R.id.tv_accuse).setOnClickListener {
        //TODO 신고 기능
        choice_dialog.dismiss()
    }

    accuse_dialog.layout = LayoutInflater.from(view.context)
        .inflate(R.layout.dialog_board_accuse, null)
    accuse_dialog.build(view.context)
    accuse_dialog.show()

    accuse_dialog.layout.findViewById<TextView>(R.id.tv_choice1)
        .setOnClickListener {
            //욕설
            choice_dialog.show()

        }
    accuse_dialog.layout.findViewById<TextView>(R.id.tv_choice2)
        .setOnClickListener {
            //욕설
            choice_dialog.layout.findViewById<TextView>(R.id.tv_insertname)
                .setText("음란물 또는 불건전한 대화")
            choice_dialog.setContent("청소년유해매체물 혹은 음란물, 음담패설 등\n타 사용자들에게 불쾌감을 주는 게시물로\n신고하시겠습니까?\n\n신고 접수까지 일정 시간이 소요됩니다.")
            choice_dialog.show()


        }
    accuse_dialog.layout.findViewById<TextView>(R.id.tv_choice3)
        .setOnClickListener {
            //욕설
            choice_dialog.layout.findViewById<TextView>(R.id.tv_insertname)
                .setText("상업적인 광고 및 판매글")
            choice_dialog.setContent("허가받지 않은 타 서비스 홍보, 이벤트 등의\n" + "광고/홍보 게시물로 신고하시겠습니까?\n" + "\n" + "신고 접수까지 일정 시간이 소요됩니다.")
            choice_dialog.show()

        }
    accuse_dialog.layout.findViewById<TextView>(R.id.tv_choice4)
        .setOnClickListener {
            //욕설
            choice_dialog.layout.findViewById<TextView>(R.id.tv_insertname)
                .setText("특정 정당, 정치인 비하 혹은\n" + "선거운동")
            choice_dialog.setContent("청소년유해매체물 혹은 음란물, 음담패설 등\n타 사용자들에게 불쾌감을 주는 게시물로\n신고하시겠습니까?\n\n신고 접수까지 일정 시간이 소요됩니다.")
            choice_dialog.show()

        }
    accuse_dialog.layout.findViewById<TextView>(R.id.tv_choice5)
        .setOnClickListener {
            //욕설
            choice_dialog.layout.findViewById<TextView>(R.id.tv_insertname)
                .setText("사칭 혹은 사기가 의심되는 글")
            choice_dialog.setContent("개인 혹은 관리자를 사칭하여 타인의 권리를\n" + "침해하고 사용자들에게 혼란을 야기하는\n" + "게시물로 신고하시겠습니까?\n" + "\n" + "신고 접수까지 일정 시간이 소요됩니다.")
            choice_dialog.show()


        }
    accuse_dialog.layout.findViewById<TextView>(R.id.tv_choice6)
        .setOnClickListener {
            val layout = LayoutInflater.from(view.context)
                .inflate(R.layout.dialog_other_reasons, null)
            builder.setView(layout)
            dialog = builder.create()
            dialog.window?.setBackgroundDrawable(
                InsetDrawable(
                    ColorDrawable(Color.TRANSPARENT),
                    100
                )
            )
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