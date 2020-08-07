package com.ulink.ulink.withdrawal

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.ulink.ulink.Activity.LoginActivity
import com.ulink.ulink.R
import com.ulink.ulink.repository.DataRepository
import com.ulink.ulink.utils.DialogBuilder
import kotlinx.android.synthetic.main.activity_withdrawal.*
import kotlinx.android.synthetic.main.fragment_withdrawal.*
import kotlinx.android.synthetic.main.fragment_withdrawal.btn_back


class WithdrawalFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_withdrawal, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref: SharedPreferences = requireContext().getSharedPreferences("pref", Context.MODE_PRIVATE)
        val id = sharedPref.getString("id", "")
        et_id.setText(id)
        et_id.isEnabled = false
        setOnClick()
    }



    fun setOnClick() {

        et_password.setOnFocusChangeListener { v, hasFocus ->
            btn_ok2.setBackgroundColor(resources.getColor(R.color.mainButton))
            btn_ok2.setTextColor(resources.getColor(R.color.white))
        }

        layout_withdrawalFragment.setOnTouchListener { v, event -> true }

        btn_back.setOnClickListener {
            (context as WithdrawalActivity).removeFragment(this)
        }

        btn_ok2.setOnClickListener {
            if (!et_password.text.isNullOrBlank()) {
                DataRepository.requestWithdraw(et_password.text.toString(),
                        onSuccess = {
                            DialogBuilder().apply {
                                build(requireContext())
                                setContent("회원탈퇴가 완료되었습니다.")
                                setClickListener {
                                    dismiss()
                                    activity?.let { it1 ->
                                        ActivityCompat.finishAffinity(it1)
                                        val sharedPref: SharedPreferences = requireContext().getSharedPreferences("pref", Context.MODE_PRIVATE)
                                        val sharedEdit = sharedPref.edit()
                                        sharedEdit.putBoolean("autoLogin", false)
                                        sharedEdit.commit()

                                        val intent = Intent(activity, LoginActivity::class.java)
                                        startActivity(intent)
                                    }
                                }
                                show()
                            }


                        },
                        onFailure = {
                            Log.d("tag", it)
                            DialogBuilder().apply {
                                build(requireContext())
                                setContent("비밀번호를 확인해 주세요.")
                                setClickListener {
                                    dismiss()
                                }
                                show()
                            }
                        })

            } else {
                Toast.makeText(context, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
            }
        }
    }
}