package com.ulink.ulink.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.ulink.ulink.R
import com.ulink.ulink.textChangedListener
import com.ulink.ulink.textResetButton
import kotlinx.android.synthetic.main.fragment_major.*
import kotlinx.android.synthetic.main.fragment_major.btn_back
import kotlinx.android.synthetic.main.fragment_major.btn_next
import kotlinx.android.synthetic.main.fragment_major.btn_reset
import kotlinx.android.synthetic.main.fragment_major.btn_search

private const val ARG_PARAM1 = "param1"

class MajorFragment : Fragment() {
    private var university: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            university = it.getString(ARG_PARAM1).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity!!.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        return inflater.inflate(R.layout.fragment_major, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_next.setOnClickListener(){
            (activity as RegisterActivity?)!!.replaceFragment(YearFragment.newInstance(university, et_major_search.text.toString()))
        }

        btn_back.setOnClickListener(){
            (activity as RegisterActivity?)!!.finishFragment(this)
        }

        et_major_search.textChangedListener {
            buttonSelector(btn_search, et_major_search)
        }

        btn_reset.textResetButton(et_major_search)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            MajorFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}