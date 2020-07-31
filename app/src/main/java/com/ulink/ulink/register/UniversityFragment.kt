package com.ulink.ulink.register

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.ulink.ulink.Activity.LoginActivity
import com.ulink.ulink.R
import com.ulink.ulink.textChangedListener
import com.ulink.ulink.textResetButton
import kotlinx.android.synthetic.main.fragment_university.*

class UniversityFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity!!.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        return inflater.inflate(R.layout.fragment_university, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_next.setOnClickListener(){
            (activity as RegisterActivity?)!!.replaceFragment(MajorFragment.newInstance(et_university_search.text.toString()))
        }

        btn_back.setOnClickListener(){
            val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
        }

        et_university_search.textChangedListener {
            buttonSelector(btn_search, et_university_search)
        }

        btn_reset.textResetButton(et_university_search)
    }
}