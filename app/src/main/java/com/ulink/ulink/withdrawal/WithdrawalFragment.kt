package com.ulink.ulink.withdrawal

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import com.ulink.ulink.Activity.LoginActivity
import com.ulink.ulink.R
import com.ulink.ulink.utils.DialogBuilder
import kotlinx.android.synthetic.main.activity_withdrawal.*
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


        et_etc.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                tv_etc_count.setText("(${et_etc.text.toString().length}/100)")
            }
        })

        btn_1.setOnClickListener {
            it.isSelected = !it.isSelected
        }
        btn_2.setOnClickListener {
            it.isSelected = !it.isSelected
        }
        btn_3.setOnClickListener {
            it.isSelected = !it.isSelected
        }
        btn_4.setOnClickListener {
            it.isSelected = !it.isSelected
        }
        btn_5.setOnClickListener {
            it.isSelected = !it.isSelected
        }
        btn_6.setOnClickListener {
            it.isSelected = !it.isSelected
            if (it.isSelected){
                et_etc.visibility = View.VISIBLE
            } else{
                et_etc.visibility = View.INVISIBLE
            }
        }

        btn_ok2.setOnClickListener {

            DialogBuilder().apply {
                build(requireContext())
                setContent("회원탈퇴가 완료되었습니다.")
                setClickListener {
                    dismiss()
                    activity?.let { it1 ->
//                    TODO 여기서 서버랑 통신해서 값 넘겨주기!

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

        }

    }
}