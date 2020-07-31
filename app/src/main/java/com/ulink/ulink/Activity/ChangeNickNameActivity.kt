package com.ulink.ulink.Activity

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.ulink.ulink.R
import com.ulink.ulink.utils.DialogBuilder
import kotlinx.android.synthetic.main.activity_change_major.btn_change
import kotlinx.android.synthetic.main.activity_change_nick_name.*

class ChangeNickNameActivity : AppCompatActivity() {

    var validate = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_nick_name)

        //        FIXME 이거 임시 지우기
        tv_nickname.text = "영희조아"
        Glide.with(this).load(R.drawable.ulinkboard_ic_unih).into(img_univ)


        et_nickname.setOnFocusChangeListener { v, hasFocus ->
            btn_check.setBackgroundColor(resources.getColor(R.color.mainButton))
            btn_check.setTextColor(resources.getColor(R.color.white))
        }

        setOnClick()

    }

    private fun setOnClick(){

        btn_check.setOnClickListener {
//            TODO 여기서 서버랑 통신해서 중복 확인하기 이밑에는 임시!
//            FIXME 만약 중복확인후에 새로 중복확인 햇는데 실패한 경우 변경버튼 비활성화
            var nameAvailable = true

            if(et_nickname.text.toString() == "a"){
                nameAvailable = false
            }

            if (nameAvailable){
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
            } else{
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
            }
        }

        btn_change.setOnClickListener {
            if (validate){
//                TODO 서버에 닉네임 변경 요청 보내기
                finish()
            }
        }
        btn_back.setOnClickListener {
            finish()
        }



    }
}