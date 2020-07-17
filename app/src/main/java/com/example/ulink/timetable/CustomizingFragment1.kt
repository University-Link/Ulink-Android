package com.example.ulink.timetable

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.ulink.R
import com.example.ulink.repository.*
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.dialog_timetable_name.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CustomizingFragment1() :Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_timetable_bottomsheet_customize1, container, false)
    }

}

