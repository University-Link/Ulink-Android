package com.example.ulink
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.ulink.ChattingRecycler.ChattingAdapter
import com.example.ulink.ChattingRecycler.ChattingData
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_chatting.*
import kotlinx.android.synthetic.main.activity_main_content.*
import kotlinx.android.synthetic.main.toolbar_chatting.*
class ChattingActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    lateinit var className : String
    lateinit var ChattingAdapter : ChattingAdapter
    val datas : MutableList<ChattingData> = mutableListOf<ChattingData>()
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatting)
        val intent = intent
        className = intent.getStringExtra("className")

        ChattingAdapter = ChattingAdapter(this)
        rv_chatting.adapter = ChattingAdapter
        loadDatas()
        img_hamburger.setOnClickListener {
            setDrawerLayout(drawer_layout_main, nav_view as NavigationView)
        }
        nav_view.setNavigationItemSelectedListener(this) //navigation 리스너
        tv_back.setOnClickListener{
            CloseDrawerLayout(drawer_layout_main,nav_view as NavigationView)
        }
        btn_back.setOnClickListener {
            finish()
        }
    }
    private fun loadDatas() {
        datas.apply {
            add(
                ChattingData(
                    token = "user",
                    profile = "",
                    username = "익명의 가지",
                    message = "안녕하세요",
                    count = 11,
                    time = "오후 6:30"
                )
            )
            add(
                ChattingData(
                    token = "user",
                    profile = "",
                    username = "익명의 가지",
                    message = "안녕하세요 채팅테스트 하기 ㅎㅎㅎㅎㅎㅎㅎㅎ",
                    count = 11,
                    time = "오후 6:30"
                )
            )
            add(
                ChattingData(
                    token = "user",
                    profile = "",
                    username = "익명의 가지",
                    message = "어렵다",
                    count = 11,
                    time = "오후 6:30"
                )
            )
            add(
                ChattingData(
                    token = "user",
                    profile = "",
                    username = "익명의 가지",
                    message = "안녕하세요",
                    count = 11,
                    time = "오후 6:30"
                )
            )
            add(
                ChattingData(
                    token = "user",
                    profile = "",
                    username = "익명의 가지",
                    message = "안녕하세요",
                    count = 11,
                    time = "오후 6:30"
                )
            )
            add(
                ChattingData(
                    token = "user",
                    profile = "",
                    username = "익명의 가지",
                    message = "안녕하세요",
                    count = 11,
                    time = "오후 6:30"
                )
            )
        }
        ChattingAdapter.datas = datas
        ChattingAdapter.notifyDataSetChanged()
    }
    private fun setDrawerLayout(drawerLayout: DrawerLayout, navigationView: NavigationView){
        drawerLayout.openDrawer(Gravity.RIGHT)
        drawerLayout.let {
            //왼쪽
            if (it.isDrawerOpen(GravityCompat.END)) {
                it.closeDrawer(GravityCompat.END)
            }
        }
    }
    private fun CloseDrawerLayout(drawerLayout: DrawerLayout, navigationView: NavigationView){
        drawerLayout.closeDrawer(Gravity.RIGHT)
        drawerLayout.let {
            //왼쪽
            if (it.isDrawerOpen(GravityCompat.END)) {
                it.closeDrawer(GravityCompat.END)
            }
        }
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.notice->{
                val intent = Intent(this, NoticeActivity::class.java)
                intent.putExtra("class", className)
                startActivity(intent)
            }
//            R.id.QnA-> Toast.makeText(this,"qna공간",Toast.LENGTH_SHORT).show()
//            R.id.picture-> Toast.makeText(this,"사진,동영상",Toast.LENGTH_SHORT).show()
//            R.id.logout-> Toast.makeText(this,"파",Toast.LENGTH_SHORT).show()
        }
        return false
    }
}