package com.ulink.ulink.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ulink.ulink.R
import kotlinx.android.synthetic.main.activity_filter_major.*
import kotlinx.android.synthetic.main.toolbar_filter_normal.*

class FilterMajorActivity : AppCompatActivity() {
    var myMajorCheck = false
    var essentialCultureCheck = false
    var selectCultureCheck = false
    var generalCultureCheck = false
    var teacherCourseCheck = false
    var otherMajorCheck = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter_major)

        btn_back.setOnClickListener() {
            finish()
        }

        btn_check.setOnClickListener(){
            //TODO 확인 누를 때 보낼 정보
            finish()
        }

        btn_reset.setOnClickListener(){
            btn_my_major.setBackgroundResource(R.drawable.main_major_setting_btn_round_unselected)
            btn_essential_culture.setBackgroundResource(R.drawable.main_major_setting_btn_round_unselected)
            btn_select_culture.setBackgroundResource(R.drawable.main_major_setting_btn_round_unselected)
            btn_general_culture.setBackgroundResource(R.drawable.main_major_setting_btn_round_unselected)
            btn_teacher_course.setBackgroundResource(R.drawable.main_major_setting_btn_round_unselected)
            btn_other_major.setBackgroundResource(R.drawable.main_major_setting_btn_round_unselected)

            myMajorCheck = false
            essentialCultureCheck = false
            selectCultureCheck = false
            generalCultureCheck = false
            teacherCourseCheck = false
            otherMajorCheck = false
        }
        btn_my_major.setOnClickListener(){
            btn_my_major.setBackgroundResource(R.drawable.main_major_setting_btn_round_selected)
            btn_essential_culture.setBackgroundResource(R.drawable.main_major_setting_btn_round_unselected)
            btn_select_culture.setBackgroundResource(R.drawable.main_major_setting_btn_round_unselected)
            btn_general_culture.setBackgroundResource(R.drawable.main_major_setting_btn_round_unselected)
            btn_teacher_course.setBackgroundResource(R.drawable.main_major_setting_btn_round_unselected)
            btn_other_major.setBackgroundResource(R.drawable.main_major_setting_btn_round_unselected)

            myMajorCheck = true
            essentialCultureCheck = false
            selectCultureCheck = false
            generalCultureCheck = false
            teacherCourseCheck = false
            otherMajorCheck = false
        }
        btn_essential_culture.setOnClickListener(){
            btn_my_major.setBackgroundResource(R.drawable.main_major_setting_btn_round_unselected)
            btn_essential_culture.setBackgroundResource(R.drawable.main_major_setting_btn_round_selected)
            btn_select_culture.setBackgroundResource(R.drawable.main_major_setting_btn_round_unselected)
            btn_general_culture.setBackgroundResource(R.drawable.main_major_setting_btn_round_unselected)
            btn_teacher_course.setBackgroundResource(R.drawable.main_major_setting_btn_round_unselected)
            btn_other_major.setBackgroundResource(R.drawable.main_major_setting_btn_round_unselected)

            myMajorCheck = false
            essentialCultureCheck = true
            selectCultureCheck = false
            generalCultureCheck = false
            teacherCourseCheck = false
            otherMajorCheck = false
        }
        btn_select_culture.setOnClickListener(){
            btn_my_major.setBackgroundResource(R.drawable.main_major_setting_btn_round_unselected)
            btn_essential_culture.setBackgroundResource(R.drawable.main_major_setting_btn_round_unselected)
            btn_select_culture.setBackgroundResource(R.drawable.main_major_setting_btn_round_selected)
            btn_general_culture.setBackgroundResource(R.drawable.main_major_setting_btn_round_unselected)
            btn_teacher_course.setBackgroundResource(R.drawable.main_major_setting_btn_round_unselected)
            btn_other_major.setBackgroundResource(R.drawable.main_major_setting_btn_round_unselected)

            myMajorCheck = false
            essentialCultureCheck = false
            selectCultureCheck = true
            generalCultureCheck = false
            teacherCourseCheck = false
            otherMajorCheck = false
        }
        btn_general_culture.setOnClickListener(){
            btn_my_major.setBackgroundResource(R.drawable.main_major_setting_btn_round_unselected)
            btn_essential_culture.setBackgroundResource(R.drawable.main_major_setting_btn_round_unselected)
            btn_select_culture.setBackgroundResource(R.drawable.main_major_setting_btn_round_unselected)
            btn_general_culture.setBackgroundResource(R.drawable.main_major_setting_btn_round_selected)
            btn_teacher_course.setBackgroundResource(R.drawable.main_major_setting_btn_round_unselected)
            btn_other_major.setBackgroundResource(R.drawable.main_major_setting_btn_round_unselected)

            myMajorCheck = false
            essentialCultureCheck = false
            selectCultureCheck = false
            generalCultureCheck = true
            teacherCourseCheck = false
            otherMajorCheck = false
        }
        btn_teacher_course.setOnClickListener(){
            btn_my_major.setBackgroundResource(R.drawable.main_major_setting_btn_round_unselected)
            btn_essential_culture.setBackgroundResource(R.drawable.main_major_setting_btn_round_unselected)
            btn_select_culture.setBackgroundResource(R.drawable.main_major_setting_btn_round_unselected)
            btn_general_culture.setBackgroundResource(R.drawable.main_major_setting_btn_round_unselected)
            btn_teacher_course.setBackgroundResource(R.drawable.main_major_setting_btn_round_selected)
            btn_other_major.setBackgroundResource(R.drawable.main_major_setting_btn_round_unselected)

            myMajorCheck = false
            essentialCultureCheck = false
            selectCultureCheck = false
            generalCultureCheck = false
            teacherCourseCheck = true
            otherMajorCheck = false
        }
        btn_other_major.setOnClickListener(){
            btn_my_major.setBackgroundResource(R.drawable.main_major_setting_btn_round_unselected)
            btn_essential_culture.setBackgroundResource(R.drawable.main_major_setting_btn_round_unselected)
            btn_select_culture.setBackgroundResource(R.drawable.main_major_setting_btn_round_unselected)
            btn_general_culture.setBackgroundResource(R.drawable.main_major_setting_btn_round_unselected)
            btn_teacher_course.setBackgroundResource(R.drawable.main_major_setting_btn_round_unselected)
            btn_other_major.setBackgroundResource(R.drawable.main_major_setting_btn_round_selected)

            myMajorCheck = true
            essentialCultureCheck = false
            selectCultureCheck = false
            generalCultureCheck = false
            teacherCourseCheck = false
            otherMajorCheck = true
        }
    }
}