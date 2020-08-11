package com.ulink.ulink.Ulink

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.ulink.ulink.R
import com.ulink.ulink.Ulink.ulinknotice.RequestNoticeReport
import com.ulink.ulink.Ulink.ulinknotice.ResponseNotice
import com.ulink.ulink.repository.DataRepository
import com.ulink.ulink.repository.RequestBoardReport
import com.ulink.ulink.repository.ResponseBoardReport
import com.ulink.ulink.repository.RetrofitService
import com.ulink.ulink.textChangedListener
import com.ulink.ulink.utils.DialogBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


fun reportBoardDialog(view : View, idx : Int, boardType : Int){
    lateinit var dialog: AlertDialog

    val builder = AlertDialog.Builder(view.context)

    val accuseDialog = DialogBuilder2()
    val choiceDialog = DialogBuilder2()

    var reason = 0
    val idx = idx
    val boardType = boardType
    var content = ""

    choiceDialog.layout = LayoutInflater.from(view.context).inflate(R.layout.dialog_choice1, null)
    choiceDialog.build(view.context)

    choiceDialog.layout.findViewById<TextView>(R.id.tv_cancel).setOnClickListener {
        choiceDialog.dismiss()
    }
    choiceDialog.layout.findViewById<TextView>(R.id.tv_accuse).setOnClickListener {
        val body = RequestBoardReport(boardIdx = idx, reason = reason, content = content)
        when (boardType) {
            0 -> {
                RetrofitService.service.reportUlinkBoard(DataRepository.token, body).enqueue(object :
                    Callback<ResponseBoardReport> {
                    override fun onFailure(call: Call<ResponseBoardReport>, t: Throwable) {
                    }

                    override fun onResponse(
                        call: Call<ResponseBoardReport>,
                        response: Response<ResponseBoardReport>
                    ) {
                        Log.d("body", body.toString())
                        Log.d("body", response.body().toString())
                        response.body()?.let {
                            if (it.status == 201) {
                                accuseDialog.dismiss()
                                choiceDialog.dismiss()
                                DialogBuilder().apply {
                                    build(view.context)
                                    setContent(view.resources.getString(R.string.report_success))
                                    setClickListener {
                                        dismiss()
                                    }
                                    show()
                                }
                            }
                        } ?: let {
                            accuseDialog.dismiss()
                            choiceDialog.dismiss()
                            DialogBuilder().apply {
                                build(view.context)
                                setContent(view.resources.getString(R.string.report_fail))
                                setClickListener {
                                    dismiss()
                                }
                                show()
                            }
                        }
                    }
                })
            }
            1 -> {
                RetrofitService.service.reportSchoolBoard(DataRepository.token, body).enqueue(object :
                    Callback<ResponseBoardReport> {
                    override fun onFailure(call: Call<ResponseBoardReport>, t: Throwable) {
                    }

                    override fun onResponse(
                        call: Call<ResponseBoardReport>,
                        response: Response<ResponseBoardReport>
                    ) {
                        Log.d("body", body.toString())
                        Log.d("body", response.body().toString())
                        response.body()?.let {
                            if (it.status == 201) {
                                accuseDialog.dismiss()
                                choiceDialog.dismiss()
                                DialogBuilder().apply {
                                    build(view.context)
                                    setContent(view.resources.getString(R.string.report_success))
                                    setClickListener {
                                        dismiss()
                                    }
                                    show()
                                }
                            }
                        } ?: let {
                            accuseDialog.dismiss()
                            choiceDialog.dismiss()
                            DialogBuilder().apply {
                                build(view.context)
                                setContent(view.resources.getString(R.string.report_fail))
                                setClickListener {
                                    dismiss()
                                }
                                show()
                            }
                        }
                    }
                })
            }
            else -> {
                RetrofitService.service.reportSubjectBoard(DataRepository.token, body).enqueue(object :
                    Callback<ResponseBoardReport> {
                    override fun onFailure(call: Call<ResponseBoardReport>, t: Throwable) {
                    }

                    override fun onResponse(
                        call: Call<ResponseBoardReport>,
                        response: Response<ResponseBoardReport>
                    ) {
                        Log.d("body", body.toString())
                        Log.d("body", response.body().toString())
                        response.body()?.let {
                            if (it.status == 201) {
                                accuseDialog.dismiss()
                                choiceDialog.dismiss()
                                DialogBuilder().apply {
                                    build(view.context)
                                    setContent(view.resources.getString(R.string.report_success))
                                    setClickListener {
                                        dismiss()
                                    }
                                    show()
                                }
                            }
                        } ?: let {
                            accuseDialog.dismiss()
                            choiceDialog.dismiss()
                            DialogBuilder().apply {
                                build(view.context)
                                setContent(view.resources.getString(R.string.report_fail))
                                setClickListener {
                                    dismiss()
                                }
                                show()
                            }
                        }
                    }
                })
            }
        }
    }

    accuseDialog.layout = LayoutInflater.from(view.context).inflate(R.layout.dialog_board_accuse, null)
    accuseDialog.build(view.context)
    accuseDialog.show()

    accuseDialog.layout.findViewById<TextView>(R.id.tv_choice1).setOnClickListener {
        accuseDialog.dismiss()
        reason = 1
        choiceDialog.layout.findViewById<TextView>(R.id.tv_insertname).text = "욕설 또는 비하하는 글"
        choiceDialog.setContent("특정 개인, 혹은 불특정 다수에 대한 비하/ \n모욕을 하는 게시물로 신고하시겠습니까?\n\n신고 접수까지 일정 시간이 소요됩니다. ")
        choiceDialog.show()
    }

    accuseDialog.layout.findViewById<TextView>(R.id.tv_choice2).setOnClickListener {
        accuseDialog.dismiss()
        reason = 2
        choiceDialog.layout.findViewById<TextView>(R.id.tv_insertname).text = "음란물 또는 불건전한 대화"
        choiceDialog.setContent("청소년유해매체물 혹은 음란물, 음담패설 등\n타 사용자들에게 불쾌감을 주는 게시물로\n신고하시겠습니까?\n\n신고 접수까지 일정 시간이 소요됩니다.")
        choiceDialog.show()
    }

    accuseDialog.layout.findViewById<TextView>(R.id.tv_choice3).setOnClickListener {
        accuseDialog.dismiss()
        reason = 3
        choiceDialog.layout.findViewById<TextView>(R.id.tv_insertname).text = "상업적인 광고 및 판매글"
        choiceDialog.setContent("허가받지 않은 타 서비스 홍보, 이벤트 등의\n" + "광고/홍보 게시물로 신고하시겠습니까?\n" + "\n" + "신고 접수까지 일정 시간이 소요됩니다.")
        choiceDialog.show()
    }

    accuseDialog.layout.findViewById<TextView>(R.id.tv_choice4).setOnClickListener {
        accuseDialog.dismiss()
        reason = 4
        choiceDialog.layout.findViewById<TextView>(R.id.tv_insertname).text = "특정 정당, 정치인 비하 혹은\n" + "선거운동"
        choiceDialog.setContent("특정 정당이나 정치인에 대한 비난/비하 혹은\n홍보/선거운동을 하는 게시물로 신고하시겠\n습니까?\n\n신고 접수까지 일정 시간이 소요됩니다.")
        choiceDialog.show()
    }

    accuseDialog.layout.findViewById<TextView>(R.id.tv_choice5).setOnClickListener {
        accuseDialog.dismiss()
        reason = 5
        choiceDialog.layout.findViewById<TextView>(R.id.tv_insertname).text = "사칭 혹은 사기가 의심되는 글"
        choiceDialog.setContent("개인 혹은 관리자를 사칭하여 타인의 권리를\n" + "침해하고 사용자들에게 혼란을 야기하는\n" + "게시물로 신고하시겠습니까?\n" + "\n" + "신고 접수까지 일정 시간이 소요됩니다.")
        choiceDialog.show()
    }

    accuseDialog.layout.findViewById<TextView>(R.id.tv_choice6).setOnClickListener {
        accuseDialog.dismiss()
        reason = 6
        val layout = LayoutInflater.from(view.context).inflate(R.layout.dialog_other_reasons, null)
        builder.setView(layout)
        dialog = builder.create()
        dialog.window?.setBackgroundDrawable(InsetDrawable(ColorDrawable(Color.TRANSPARENT), 100))
        dialog.show()

        layout.findViewById<TextView>(R.id.tv_cancel).setOnClickListener {
            dialog.dismiss()
        }

        layout.findViewById<TextView>(R.id.tv_accuse).setOnClickListener {
            val body = RequestBoardReport(boardIdx = idx, reason = reason, content = content)
            when (boardType) {
                0 -> {
                    RetrofitService.service.reportUlinkBoard(DataRepository.token, body).enqueue(object :
                        Callback<ResponseBoardReport> {
                        override fun onFailure(call: Call<ResponseBoardReport>, t: Throwable) {
                        }

                        override fun onResponse(
                            call: Call<ResponseBoardReport>,
                            response: Response<ResponseBoardReport>
                        ) {
                            Log.d("body", body.toString())
                            Log.d("body", response.body().toString())
                            response.body()?.let {
                                if (it.status == 201) {
                                    accuseDialog.dismiss()
                                    choiceDialog.dismiss()
                                    DialogBuilder().apply {
                                        build(view.context)
                                        setContent(view.resources.getString(R.string.report_success))
                                        setClickListener {
                                            dismiss()
                                        }
                                        show()
                                    }
                                }
                            } ?: let {
                                accuseDialog.dismiss()
                                choiceDialog.dismiss()
                                DialogBuilder().apply {
                                    build(view.context)
                                    setContent(view.resources.getString(R.string.report_fail))
                                    setClickListener {
                                        dismiss()
                                    }
                                    show()
                                }
                            }
                        }
                    })
                }
                1 -> {
                    RetrofitService.service.reportSchoolBoard(DataRepository.token, body).enqueue(object :
                        Callback<ResponseBoardReport> {
                        override fun onFailure(call: Call<ResponseBoardReport>, t: Throwable) {
                        }

                        override fun onResponse(
                            call: Call<ResponseBoardReport>,
                            response: Response<ResponseBoardReport>
                        ) {
                            Log.d("body", body.toString())
                            Log.d("body", response.body().toString())
                            response.body()?.let {
                                if (it.status == 201) {
                                    accuseDialog.dismiss()
                                    choiceDialog.dismiss()
                                    DialogBuilder().apply {
                                        build(view.context)
                                        setContent(view.resources.getString(R.string.report_success))
                                        setClickListener {
                                            dismiss()
                                        }
                                        show()
                                    }
                                }
                            } ?: let {
                                accuseDialog.dismiss()
                                choiceDialog.dismiss()
                                DialogBuilder().apply {
                                    build(view.context)
                                    setContent(view.resources.getString(R.string.report_fail))
                                    setClickListener {
                                        dismiss()
                                    }
                                    show()
                                }
                            }
                        }
                    })
                }
                else -> {
                    RetrofitService.service.reportSubjectBoard(DataRepository.token, body).enqueue(object :
                        Callback<ResponseBoardReport> {
                        override fun onFailure(call: Call<ResponseBoardReport>, t: Throwable) {
                        }

                        override fun onResponse(
                            call: Call<ResponseBoardReport>,
                            response: Response<ResponseBoardReport>
                        ) {
                            Log.d("body", body.toString())
                            Log.d("body", response.body().toString())
                            response.body()?.let {
                                if (it.status == 201) {
                                    accuseDialog.dismiss()
                                    choiceDialog.dismiss()
                                    DialogBuilder().apply {
                                        build(view.context)
                                        setContent(view.resources.getString(R.string.report_success))
                                        setClickListener {
                                            dismiss()
                                        }
                                        show()
                                    }
                                }
                            } ?: let {
                                accuseDialog.dismiss()
                                choiceDialog.dismiss()
                                DialogBuilder().apply {
                                    build(view.context)
                                    setContent(view.resources.getString(R.string.report_fail))
                                    setClickListener {
                                        dismiss()
                                    }
                                    show()
                                }
                            }
                        }
                    })
                }
            }
        }

        var etContent = layout.findViewById<EditText>(R.id.et_content)
        etContent.textChangedListener{
            layout.findViewById<TextView>(R.id.tv_word_count).text = etContent.text.length.toString()+"/200"
            content = etContent.text.toString()
        }
    }
}