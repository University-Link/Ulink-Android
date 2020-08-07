package com.ulink.ulink.Activity

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.ulink.ulink.R
import com.ulink.ulink.repository.DataRepository
import com.ulink.ulink.utils.DialogBuilder
import kotlinx.android.synthetic.main.activity_change_major.btn_change
import kotlinx.android.synthetic.main.activity_change_nick_name.*

class ChangeNickNameActivity : AppCompatActivity() {

    var validate = false

    var majorIdx = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_nick_name)

        tv_nickname.text = intent.getStringExtra("nickName")
        majorIdx = intent.getIntExtra("majorIdx", 0)

        //        FIXME 대학 서버에 물어보기
        Glide.with(this).load(R.drawable.ulinkboard_ic_unih).into(img_univ)

        et_nickname.setOnFocusChangeListener { v, hasFocus ->
            btn_check.setBackgroundColor(resources.getColor(R.color.mainButton))
            btn_check.setTextColor(resources.getColor(R.color.white))
        }

        et_nickname.addTextChangedListener( object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validate = false
                btn_change.setBackgroundResource(R.drawable.signup_btn_next_unactivated)
                btn_change.setTextColor(resources.getColor(R.color.btnIcon3))
            }
        })

        setOnClick()

    }

    private fun setOnClick(){

        btn_check.setOnClickListener {
//            TODO 여기서 서버랑 통신해서 중복 확인하기 이밑에는 임시!
//            FIXME 만약 중복확인후에 새로 중복확인 햇는데 실패한 경우 변경버튼 비활성화

            DataRepository.getNicknameSameCheck(et_nickname.text.toString(), majorIdx,
            onSuccess = {
                validate = true
                DialogBuilder().apply {
                    build(this@ChangeNickNameActivity)
                    setContent(getString(R.string.change_nickname_available))
                    setClickListener {
                        dismiss()
                    }
                    show()
                }

                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(et_nickname.windowToken, 0)

                btn_change.setBackgroundColor(resources.getColor(R.color.mainButton))
                btn_change.setTextColor(resources.getColor(R.color.white))
            },
            onFailure = {
                validate = false
                DialogBuilder().apply {
                    build(this@ChangeNickNameActivity)
                    setContent(getString(R.string.change_nickname_using))
                    setClickListener {
                        dismiss()
                    }
                    show()
                }
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(et_nickname.windowToken, 0)

                btn_change.setBackgroundResource(R.drawable.signup_btn_next_unactivated)
                btn_change.setTextColor(resources.getColor(R.color.btnIcon3))
            })
        }

        btn_change.setOnClickListener {
            if (validate){
                DataRepository.updateNickname(et_nickname.text.toString(),
                onSuccess = {
                    finish()
                },
                onFailure = {
                    DialogBuilder().apply {
                        build(this@ChangeNickNameActivity)
                        setContent("닉네임 변경에 실패하였습니다.")
                        setClickListener {
                            dismiss()
                        }
                        show()
                    }
                })
            }
        }
        btn_back.setOnClickListener {
            finish()
        }



    }
}