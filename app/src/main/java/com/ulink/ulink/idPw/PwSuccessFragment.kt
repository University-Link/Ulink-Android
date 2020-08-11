package com.ulink.ulink.idPw

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ulink.ulink.Activity.LoginActivity
import com.ulink.ulink.R
import com.ulink.ulink.register.*
import com.ulink.ulink.textChangedListener
import kotlinx.android.synthetic.main.fragment_pw_success.*

class PwSuccessFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pw_success, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var pwWarningCheck = false

        et_password.textChangedListener {
            pwWarningCheck = et_password.warningPasswordCheck("^[a-zA-Z0-9!@#\$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?~`]+$", tv_password_warning)
            passwordCheck(et_password, et_password_check, tv_password_different)

            if(et_password.passwordNextCheck(8) && pwWarningCheck && tv_password_different.visibility==View.GONE)
                btn_confirm.btnNextSelector()
            else
                btn_confirm.btnNextReset()
        }

        et_password_check.textChangedListener {
            passwordCheck(et_password, et_password_check, tv_password_different)

            if(et_password.passwordNextCheck(8) && pwWarningCheck && tv_password_different.visibility==View.GONE)
                btn_confirm.btnNextSelector()
            else
                btn_confirm.btnNextReset()
        }

        btn_confirm.setOnClickListener{
            if(et_password.passwordNextCheck(8) && pwWarningCheck && tv_password_different.visibility==View.GONE) {
                val intent = Intent(view.context, LoginActivity::class.java)
                startActivity(intent)
            }
        }

    }
}