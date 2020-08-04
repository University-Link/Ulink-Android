package com.ulink.ulink.register

import android.content.Context.INPUT_METHOD_SERVICE
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.ulink.ulink.Activity.LoginActivity
import com.ulink.ulink.R
import com.ulink.ulink.repository.*
import com.ulink.ulink.textChangedListener
import com.ulink.ulink.textResetButton
import kotlinx.android.synthetic.main.fragment_university.*
import kotlinx.android.synthetic.main.fragment_university.btn_back
import kotlinx.android.synthetic.main.fragment_university.btn_next
import kotlinx.android.synthetic.main.fragment_university.btn_reset
import kotlinx.android.synthetic.main.fragment_university.btn_search
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UniversityFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity!!.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        return inflater.inflate(R.layout.fragment_university, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imm = view.context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

        var searchCheck = false

        var searchResult = mutableListOf<University>()
        var searchAdapter = UniversitySearchAdapter(view.context)
        searchAdapter.datas = searchResult
        rv_university_search.adapter = searchAdapter
        searchResult.add(University(universityIdx = 1, name = "해당 검색어 입력"))
        searchAdapter.notifyDataSetChanged()

        searchAdapter.setResultClickListener(
            object : UniversitySearchAdapter.ResultClickListener {
                override fun onClick(view: View, position: Int) {
                    searchCheck = true
                    et_university_search.setText(searchAdapter.datas[position].name)
                    et_university_search.clearFocus()
                    rv_university_search.visibility=View.INVISIBLE
                    btn_next.btnNextSelector()
                }
            })

        btn_search.setOnClickListener {
            imm.hideSoftInputFromWindow(et_university_search.windowToken, 0)
            RetrofitService.service.getUniversity(et_university_search.text.toString()).enqueue(object : Callback<ResponseUniversity> {
                override fun onFailure(call: Call<ResponseUniversity>, t: Throwable) {
                }
                override fun onResponse(
                    call: Call<ResponseUniversity>,
                    response: Response<ResponseUniversity>
                ) {
                    response.body()?.let {
                        if (it.status % 20 == 0) {
                            searchResult.apply{
                            }
                        }
                    }
                }
            })
            rv_university_search.visibility=View.VISIBLE
            searchCheck = false
        }

        btn_next.setOnClickListener {
            if(searchCheck)
                (activity as RegisterActivity?)!!.replaceFragment(MajorFragment.newInstance(et_university_search.text.toString()))
        }

        btn_back.setOnClickListener {
            val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
        }

        et_university_search.textChangedListener {
            btnCheckSelector(btn_search, et_university_search)
        }

        et_university_search.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL && searchCheck) {
                btn_next.btnNextReset()
                searchCheck = false
            }
            false
        }

        btn_reset.textResetButton(et_university_search)

        btn_reset.setOnClickListener{
            et_university_search.setText("")
            btn_next.btnNextReset()
            searchCheck = false
        }
    }
}