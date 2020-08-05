package com.ulink.ulink.myActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import com.google.android.material.tabs.TabLayoutMediator
import com.ulink.ulink.R
import com.ulink.ulink.Ulink.UlinkBoardData
import kotlinx.android.synthetic.main.activity_my_activity.*

class MyActivityActivity : AppCompatActivity() {

    val mAdapter = MyActivityAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_activity)


        val type = intent.getIntExtra("type", 0)
        setViewpager(type)
        setTab()

    }


    fun setTab(){
        TabLayoutMediator(tl_myactivity, vp_myactivity, TabLayoutMediator.TabConfigurationStrategy { tab, position ->
            val tablayout = LayoutInflater.from(applicationContext).inflate(R.layout.tab_myactivity, null)
            var text = tablayout.findViewById<TextView>(R.id.tv_tab)

            when (position) {
                0 -> {
                    text.text = "내가 쓴 글"
                }
                1 -> {
                    text.text = "작성한 댓글"
                }
                2 -> {
                    text.text = "좋아요한 글"
                }
            }
            tab.customView = tablayout
        }).attach()
    }

    fun setViewpager(type : Int){
        vp_myactivity.adapter = mAdapter
        vp_myactivity.setCurrentItem(type, false)
        vp_myactivity.offscreenPageLimit = 2

//      TODO 여기서 서버랑 통신해서 각 탭에 데이터 넣기!
        for (a in 0 until 3){
            (mAdapter.fragmentList[a] as MyActivityFragment).setData(
                UlinkBoardData(
                    img_profile = "",
                    nickname = "조개탕수만",
                    time = "방금",
                    content = "ㅁㅇㄴㄹㅁㄴㅇㄹ",
                    like = true,
                    comment_count = "2",
                    heart_count = "999+",
                    category = "유링크 게시판"
                )
            )
        }

    }
}