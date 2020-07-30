package com.ulink.ulink.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ulink.ulink.R
import com.ulink.ulink.adapter.ChangeMajorRecyclerAdapter
import com.ulink.ulink.utils.DialogBuilder
import kotlinx.android.synthetic.main.activity_change_major.*

class ChangeMajorActivity : AppCompatActivity() {


    var major = ""
    var validate = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_major)


        val mAdapter = ChangeMajorRecyclerAdapter(this, onClick = {
            btn_change.setBackgroundColor(resources.getColor(R.color.mainButton))
            btn_change.setTextColor(resources.getColor(R.color.white))
//            TODO 클릭된 학과 임시로 담기 -> 변경누르면 그걸 전달!
            major = it
            validate = true
        })
        rv_changemajor.adapter = mAdapter

//        TODO 여기서 editext 기반으로 서버에 검색 요청 없으면 해당 검색어 입력 하나 띄우고 et에 있는걸로 변경
        mAdapter.addData("컴퓨터공학과")
        mAdapter.notifyItemInserted(0)

        et_major_search.setOnFocusChangeListener { v, hasFocus ->
            btn_search.setBackgroundResource(R.drawable.btn_request_active)
            btn_search.setTextColor(resources.getColor(R.color.white))
        }

        btn_change.setOnClickListener {
            if (validate){
                DialogBuilder().apply {
                    build(this@ChangeMajorActivity)
                    setContent(getString(R.string.major_change_changed))
                    setClickListener {
                        dismiss()
                        finish()
                    }
                    show()
                }
            }
        }
    }

}