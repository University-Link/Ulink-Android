package com.ulink.ulink.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ulink.ulink.Activity.ChangeMajorActivity
import com.ulink.ulink.Activity.ChangeNickNameActivity
import com.ulink.ulink.Activity.ChangePasswordActivity
import com.ulink.ulink.Activity.SchoolCertificateActivity
import com.ulink.ulink.R
import com.ulink.ulink.myActivity.MyActivityActivity
import kotlinx.android.synthetic.main.fragment_my.*

class MyFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnClick()

    }

    fun setOnClick(){

        btn_school_certification.setOnClickListener {
            val intent = Intent(context, SchoolCertificateActivity::class.java)
            startActivity(intent)
        }

        layout_writingmine.setOnClickListener {
            val intent = Intent(context, MyActivityActivity::class.java)
            intent.putExtra("type",0)
            startActivity(intent)
        }
        tv_writingmine.setOnClickListener{
            val intent = Intent(context, MyActivityActivity::class.java)
            intent.putExtra("type",0)
            startActivity(intent)
        }

        layout_commentmine.setOnClickListener {
            val intent = Intent(context, MyActivityActivity::class.java)
            intent.putExtra("type",1)
            startActivity(intent)
        }
        tv_commentmine.setOnClickListener {
            val intent = Intent(context, MyActivityActivity::class.java)
            intent.putExtra("type",1)
            startActivity(intent)
        }

        layout_writinglike.setOnClickListener {
            val intent = Intent(context, MyActivityActivity::class.java)
            intent.putExtra("type",2)
            startActivity(intent)
        }

        tv_writinglike.setOnClickListener {
            val intent = Intent(context, MyActivityActivity::class.java)
            intent.putExtra("type",2)
            startActivity(intent)
        }

        btn_changemajor.setOnClickListener {
            val intent = Intent(context, ChangeMajorActivity::class.java)
            startActivity(intent)
        }

        btn_changenickname.setOnClickListener {
            val intent = Intent(context, ChangeNickNameActivity::class.java)
            startActivity(intent)
        }

        btn_changepassword.setOnClickListener {
            val intent = Intent(context, ChangePasswordActivity::class.java)
            startActivity(intent)
        }
    }
}