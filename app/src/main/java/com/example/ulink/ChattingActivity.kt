package com.example.ulink

import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_chatting.*
import kotlinx.android.synthetic.main.toolbar.*

class ChattingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatting)

        img_hamburger.setOnClickListener {
            setDrawerLayout(drawer_layout_main, nav_view as NavigationView)
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

}
