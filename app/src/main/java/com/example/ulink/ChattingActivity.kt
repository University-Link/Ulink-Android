package com.example.ulink

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_chatting.*
import kotlinx.android.synthetic.main.toolbar.*

class ChattingActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatting)

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
                startActivity(intent)
            }
            R.id.QnA-> Toast.makeText(this,"qna공간",Toast.LENGTH_SHORT).show()
            R.id.picture-> Toast.makeText(this,"사진,동영상",Toast.LENGTH_SHORT).show()
            R.id.logout-> Toast.makeText(this,"파",Toast.LENGTH_SHORT).show()

        }
        return false
    }





}
