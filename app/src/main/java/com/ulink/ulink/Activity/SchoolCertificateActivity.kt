package com.ulink.ulink.Activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import androidx.annotation.RequiresApi
import com.ulink.ulink.R
import com.ulink.ulink.utils.DialogBuilder
import kotlinx.android.synthetic.main.activity_school_certificate.*

class SchoolCertificateActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_school_certificate)


        tv_emailcertificateinfo.text = Html.fromHtml(getString(R.string.school_certificate_info), Html.FROM_HTML_MODE_LEGACY)

        btn_back.setOnClickListener { finish() }

//      기본 다이얼로그

        btn_request.setOnClickListener {
            DialogBuilder().apply {
                build(this@SchoolCertificateActivity)
                setContent(getString(R.string.school_certificate_codesent))
                setClickListener {
                    dismiss()
                }
                show()
            }
        }

    }


}