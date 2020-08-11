package com.ulink.ulink.idPw

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ulink.ulink.R
import kotlinx.android.synthetic.main.fragment_authentication.*
import kotlinx.android.synthetic.main.fragment_id_success.*

class IdSuccessFragment(name : String, id : String) : Fragment() {

    val name = name
    val id = id

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_id_success, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_id_success.text = id
        tv_id_result.text = name+view.resources.getString(R.string.find_id_success)

        val builder = StringBuilder(tv_id_success.text.toString())

        for(i in tv_id_success.length()-4 until tv_id_success.length())
            builder.setCharAt(i, '*')

        tv_id_success.text = builder.toString()
    }
}