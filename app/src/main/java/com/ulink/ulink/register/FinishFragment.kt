package com.ulink.ulink.register

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ulink.ulink.Activity.LoginActivity
import com.ulink.ulink.R
import com.ulink.ulink.textChangedListener
import kotlinx.android.synthetic.main.fragment_finish.*
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.btn_back
import kotlinx.android.synthetic.main.fragment_register.btn_next

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FinishFragment : Fragment() {
    private var id: String = ""
    private var password: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getString(ARG_PARAM1).toString()
            password = it.getString(ARG_PARAM2).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_finish, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_next.setOnClickListener{
            val intent = Intent(context, LoginActivity::class.java)
            intent.putExtra("id", id)
            intent.putExtra("password", password)
            startActivity(intent)
        }

        btn_back.setOnClickListener{
            (activity as RegisterActivity?)!!.finishFragment(this)
        }
    }
    companion object {
        @JvmStatic
        fun newInstance(id: String, password: String) =
            FinishFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, id)
                    putString(ARG_PARAM2, password)
                }
            }
    }
}