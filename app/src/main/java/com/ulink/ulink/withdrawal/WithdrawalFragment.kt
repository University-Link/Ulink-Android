package com.ulink.ulink.withdrawal

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.ulink.ulink.Activity.LoginActivity
import com.ulink.ulink.R
import com.ulink.ulink.repository.DataRepository
import com.ulink.ulink.utils.DialogBuilder
import kotlinx.android.synthetic.main.fragment_withdrawal.*


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

        layout_withdrawalFragment.setOnTouchListener { v, event ->
            return@setOnTouchListener true
        }

        btn_back.setOnClickListener {
            fragmentManager?.beginTransaction()?.remove(this)?.commit()
        }

        btn_ok2.setOnClickListener {
            Log.d("tag", "22")
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