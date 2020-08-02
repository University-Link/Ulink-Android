package com.ulink.ulink.register

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.ulink.ulink.Activity.LoginActivity
import com.ulink.ulink.R
import com.ulink.ulink.textChangedListener
import com.ulink.ulink.textResetButton
import kotlinx.android.synthetic.main.fragment_university.*
import kotlinx.android.synthetic.main.fragment_university.btn_back
import kotlinx.android.synthetic.main.fragment_university.btn_next

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

        val imm = view.context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

        var searchCheck = false

        var searchResult = mutableListOf<String>()
        var searchAdapter = SearchResultAdapter(view.context)
        searchAdapter.datas = searchResult
        rv_university_search.adapter = searchAdapter
        searchResult.add("해당 검색어 입력")
        searchAdapter.notifyDataSetChanged()

        searchAdapter.setResultClickListener(
            object : SearchResultAdapter.ResultClickListener {
                override fun onClick(view: View, position: Int) {
                    searchCheck = true
                    et_university_search.setText(searchAdapter.datas[position])
                    rv_university_search.visibility=View.INVISIBLE
                    btn_next.btnNextSelector()
                }
            })

        btn_search.setOnClickListener {
            imm.hideSoftInputFromWindow(et_university_search.windowToken, 0)
        }

        btn_next.setOnClickListener {
            (activity as RegisterActivity?)!!.replaceFragment(MajorFragment.newInstance(et_university_search.text.toString()))
        }

        btn_back.setOnClickListener {
            val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
        }

        et_university_search.textChangedListener {
            btnCheckSelector(btn_search, et_university_search)
        }

        btn_reset.textResetButton(et_university_search)
    }
}