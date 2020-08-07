package com.ulink.ulink.Activity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.ulink.ulink.R
import com.ulink.ulink.register.filterCheck
import com.ulink.ulink.repository.DataRepository
import com.ulink.ulink.textChangedListener
import com.ulink.ulink.utils.DialogBuilder
import kotlinx.android.synthetic.main.activity_change_password.*
import kotlinx.android.synthetic.main.activity_change_password.btn_back
import kotlinx.android.synthetic.main.activity_change_password.btn_ok
import kotlinx.android.synthetic.main.activity_change_password.et_password
import kotlinx.android.synthetic.main.activity_change_password.et_password_check

class ChangePasswordActivity : AppCompatActivity() {

    var validate = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        val sharedPref: SharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE)
        val id = sharedPref.getString("id", "")
        findViewById<EditText>(R.id.et_id).setText(id)
        findViewById<EditText>(R.id.et_id).isEnabled = false

        setFilter()
        setOnClick()
    }

    private fun setFilter() {

        et_password.textChangedListener {
            validate = et_password.filterCheck("^[a-zA-Z0-9!@#\$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?~`]+$")
        }

        et_password_check.textChangedListener {
            if (et_password.text.toString() != et_password_check.text.toString()) {
                tv_wrongpassword.visibility = View.VISIBLE
                btn_ok.setBackgroundResource(R.drawable.mypage_withdrawal_unactivated_img_textbox)
                btn_ok.setTextColor(resources.getColor(R.color.btnIcon3))
            } else {
                tv_wrongpassword.visibility = View.GONE
                btn_ok.setBackgroundColor(resources.getColor(R.color.mainButton))
                btn_ok.setTextColor(resources.getColor(R.color.white))
            }
        }
    }


    private fun setOnClick() {

        btn_ok.setOnClickListener {
            if (et_currentpassword.text.isNullOrBlank()) {
                DialogBuilder().apply {
                    build(this@ChangePasswordActivity)
                    setContent("현재 비밀번호를 입력하세요")
                    setClickListener {
                        dismiss()
                    }
                    show()
                }
                return@setOnClickListener
            }
            if (!validate) {
                DialogBuilder().apply {
                    build(this@ChangePasswordActivity)
                    setContent("새 비밀번호를 확인해 주세요.")
                    setClickListener {
                        dismiss()
                    }
                    show()
                }
                return@setOnClickListener
            }

            //          TODO 서버랑 통신하여 현재 비밀번호가 맞는지 확인 맞다면 비밀번호 변경 시도
//                           success는 현재 비밀번호가 맞을 경우
            var success = true

            if (et_currentpassword.text.toString() == "0420"){
                success = true
            }

            if (success) {
                if (validate && et_password.text.toString() == et_password_check.text.toString()) {
                    DataRepository.updatePassword(et_currentpassword.text.toString(), et_password.text.toString(),
                            onSuccess = {
                                DialogBuilder().apply {
                                    build(this@ChangePasswordActivity)
                                    setContent("변경되었습니다.")
                                    setClickListener {
                                        dismiss()
                                        finish()
                                    }
                                    show()
                                }
                            },
                            onFailure = {
                                DialogBuilder().apply {
                                    build(this@ChangePasswordActivity)
                                    setContent("비밀번호 변경에 실패했습니다.")
                                    setClickListener {
                                        dismiss()
                                    }
                                    show()
                                }
                            })
                } else{
                    DialogBuilder().apply {
                        build(this@ChangePasswordActivity)
                        setContent("비밀번호를 확인해 주세요.")
                        setClickListener {
                            dismiss()
                            finish()
                        }
                        show()
                    }
                }
            } else {
                DialogBuilder().apply {
                    build(this@ChangePasswordActivity)
                    setContent("현재 비밀번호를 확인해주세요")
                    setClickListener {
                        dismiss()
                    }
                    show()
                }

            }

            btn_back.setOnClickListener {
                finish()
            }
        }

    }
}