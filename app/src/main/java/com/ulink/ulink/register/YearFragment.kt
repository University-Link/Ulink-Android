package com.ulink.ulink.register

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ulink.ulink.R
import kotlinx.android.synthetic.main.fragment_year.*
import kotlinx.android.synthetic.main.fragment_year.btn_back
import kotlinx.android.synthetic.main.fragment_year.btn_next
import java.text.SimpleDateFormat
import java.util.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
var cal = Calendar.getInstance()

class YearFragment : Fragment() {
    private var university: String = ""
    private var major: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            university = it.getString(ARG_PARAM1).toString()
            major = it.getString(ARG_PARAM2).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_year, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_back.setOnClickListener(){
            (activity as RegisterActivity?)!!.finishFragment(this)
        }

        btn_next.setOnClickListener(){
            (activity as RegisterActivity?)!!.replaceFragment(AgreeFragment.newInstance(university, major, tv_year.text.toString()))
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            YearFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}