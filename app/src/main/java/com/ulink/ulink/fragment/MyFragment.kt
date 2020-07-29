package com.ulink.ulink.fragment

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ulink.ulink.Activity.SchoolCertificateActivity
import com.ulink.ulink.R
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

        btn_school_certification.setOnClickListener {
            val intent = Intent(context, SchoolCertificateActivity::class.java)
            startActivity(intent)
        }


    }
}