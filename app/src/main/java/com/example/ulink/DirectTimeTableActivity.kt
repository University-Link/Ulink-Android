package com.example.ulink

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_direct_type_time_table.*
import kotlinx.android.synthetic.main.activity_filter_normal.*
import kotlinx.android.synthetic.main.activity_notice_add.*
import kotlinx.android.synthetic.main.activity_notice_add.et_title
import kotlinx.android.synthetic.main.toolbar_direct_time_table.*

class DirectTimeTableActivity : AppCompatActivity() {
    lateinit var title : String
    var clubCheck : Boolean = false
    var partTimeCheck : Boolean = false
    var studyCheck : Boolean = false
    var studentCouncilCheck : Boolean = false
    var lessonCheck : Boolean = false
    var academyCheck : Boolean = false
    var workCheck : Boolean = false
    var serviceCheck : Boolean = false

    var textCheck = "#ffffff"
    var textUncheck = "#727272"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_direct_type_time_table)

        btn_check.setOnClickListener() {
            if (et_title.text.toString() == "") showToast("제목을 설정해주세요.")
        }

        btn_club.setOnClickListener(){
            clubCheck = true
            partTimeCheck = false
            studyCheck = false
            studentCouncilCheck = false
            lessonCheck = false
            academyCheck = false
            workCheck = false
            serviceCheck = false

            btn_club.setBackgroundResource(R.drawable.textfield_btn_box_bg_selected)
            btn_part_time.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_study.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_student_council.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_lesson.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_academy.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_work.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_service.setBackgroundResource(R.drawable.textfield_btn_box_bg)

            btn_club.setTextColor(Color.parseColor(textCheck))
            btn_part_time.setTextColor(Color.parseColor(textUncheck))
            btn_study.setTextColor(Color.parseColor(textUncheck))
            btn_student_council.setTextColor(Color.parseColor(textUncheck))
            btn_lesson.setTextColor(Color.parseColor(textUncheck))
            btn_academy.setTextColor(Color.parseColor(textUncheck))
            btn_work.setTextColor(Color.parseColor(textUncheck))
            btn_service.setTextColor(Color.parseColor(textUncheck))
        }
        btn_part_time.setOnClickListener(){
            clubCheck = false
            partTimeCheck = true
            studyCheck = false
            studentCouncilCheck = false
            lessonCheck = false
            academyCheck = false
            workCheck = false
            serviceCheck = false

            btn_club.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_part_time.setBackgroundResource(R.drawable.textfield_btn_box_bg_selected)
            btn_study.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_student_council.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_lesson.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_academy.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_work.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_service.setBackgroundResource(R.drawable.textfield_btn_box_bg)

            btn_club.setTextColor(Color.parseColor(textUncheck))
            btn_part_time.setTextColor(Color.parseColor(textCheck))
            btn_study.setTextColor(Color.parseColor(textUncheck))
            btn_student_council.setTextColor(Color.parseColor(textUncheck))
            btn_lesson.setTextColor(Color.parseColor(textUncheck))
            btn_academy.setTextColor(Color.parseColor(textUncheck))
            btn_work.setTextColor(Color.parseColor(textUncheck))
            btn_service.setTextColor(Color.parseColor(textUncheck))
        }
        btn_study.setOnClickListener(){
            clubCheck = false
            partTimeCheck = false
            studyCheck = true
            studentCouncilCheck = false
            lessonCheck = false
            academyCheck = false
            workCheck = false
            serviceCheck = false

            btn_club.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_part_time.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_study.setBackgroundResource(R.drawable.textfield_btn_box_bg_selected)
            btn_student_council.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_lesson.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_academy.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_work.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_service.setBackgroundResource(R.drawable.textfield_btn_box_bg)

            btn_club.setTextColor(Color.parseColor(textUncheck))
            btn_part_time.setTextColor(Color.parseColor(textUncheck))
            btn_study.setTextColor(Color.parseColor(textCheck))
            btn_student_council.setTextColor(Color.parseColor(textUncheck))
            btn_lesson.setTextColor(Color.parseColor(textUncheck))
            btn_academy.setTextColor(Color.parseColor(textUncheck))
            btn_work.setTextColor(Color.parseColor(textUncheck))
            btn_service.setTextColor(Color.parseColor(textUncheck))
        }
        btn_student_council.setOnClickListener(){
            clubCheck = false
            partTimeCheck = false
            studyCheck = false
            studentCouncilCheck = true
            lessonCheck = false
            academyCheck = false
            workCheck = false
            serviceCheck = false

            btn_club.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_part_time.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_study.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_student_council.setBackgroundResource(R.drawable.textfield_btn_box_bg_selected)
            btn_lesson.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_academy.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_work.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_service.setBackgroundResource(R.drawable.textfield_btn_box_bg)

            btn_club.setTextColor(Color.parseColor(textUncheck))
            btn_part_time.setTextColor(Color.parseColor(textUncheck))
            btn_study.setTextColor(Color.parseColor(textUncheck))
            btn_student_council.setTextColor(Color.parseColor(textCheck))
            btn_lesson.setTextColor(Color.parseColor(textUncheck))
            btn_academy.setTextColor(Color.parseColor(textUncheck))
            btn_work.setTextColor(Color.parseColor(textUncheck))
            btn_service.setTextColor(Color.parseColor(textUncheck))
        }
        btn_lesson.setOnClickListener(){
            clubCheck = false
            partTimeCheck = false
            studyCheck = false
            studentCouncilCheck = false
            lessonCheck = true
            academyCheck = false
            workCheck = false
            serviceCheck = false

            btn_club.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_part_time.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_study.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_student_council.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_lesson.setBackgroundResource(R.drawable.textfield_btn_box_bg_selected)
            btn_academy.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_work.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_service.setBackgroundResource(R.drawable.textfield_btn_box_bg)

            btn_club.setTextColor(Color.parseColor(textUncheck))
            btn_part_time.setTextColor(Color.parseColor(textUncheck))
            btn_study.setTextColor(Color.parseColor(textUncheck))
            btn_student_council.setTextColor(Color.parseColor(textUncheck))
            btn_lesson.setTextColor(Color.parseColor(textCheck))
            btn_academy.setTextColor(Color.parseColor(textUncheck))
            btn_work.setTextColor(Color.parseColor(textUncheck))
            btn_service.setTextColor(Color.parseColor(textUncheck))
        }
        btn_academy.setOnClickListener(){
            clubCheck = false
            partTimeCheck = false
            studyCheck = false
            studentCouncilCheck = false
            lessonCheck = false
            academyCheck = true
            workCheck = false
            serviceCheck = false

            btn_club.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_part_time.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_study.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_student_council.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_lesson.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_academy.setBackgroundResource(R.drawable.textfield_btn_box_bg_selected)
            btn_work.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_service.setBackgroundResource(R.drawable.textfield_btn_box_bg)


            btn_club.setTextColor(Color.parseColor(textUncheck))
            btn_part_time.setTextColor(Color.parseColor(textUncheck))
            btn_study.setTextColor(Color.parseColor(textUncheck))
            btn_student_council.setTextColor(Color.parseColor(textUncheck))
            btn_lesson.setTextColor(Color.parseColor(textUncheck))
            btn_academy.setTextColor(Color.parseColor(textCheck))
            btn_work.setTextColor(Color.parseColor(textUncheck))
            btn_service.setTextColor(Color.parseColor(textUncheck))
        }
        btn_work.setOnClickListener(){
            clubCheck = false
            partTimeCheck = false
            studyCheck = false
            studentCouncilCheck = false
            lessonCheck = false
            academyCheck = false
            workCheck = true
            serviceCheck = false

            btn_club.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_part_time.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_study.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_student_council.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_lesson.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_academy.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_work.setBackgroundResource(R.drawable.textfield_btn_box_bg_selected)
            btn_service.setBackgroundResource(R.drawable.textfield_btn_box_bg)

            btn_club.setTextColor(Color.parseColor(textUncheck))
            btn_part_time.setTextColor(Color.parseColor(textUncheck))
            btn_study.setTextColor(Color.parseColor(textUncheck))
            btn_student_council.setTextColor(Color.parseColor(textUncheck))
            btn_lesson.setTextColor(Color.parseColor(textUncheck))
            btn_academy.setTextColor(Color.parseColor(textUncheck))
            btn_work.setTextColor(Color.parseColor(textCheck))
            btn_service.setTextColor(Color.parseColor(textUncheck))
        }
        btn_service.setOnClickListener(){
            clubCheck = false
            partTimeCheck = false
            studyCheck = false
            studentCouncilCheck = false
            lessonCheck = false
            academyCheck = false
            workCheck = false
            serviceCheck = true

            btn_club.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_part_time.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_study.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_student_council.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_lesson.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_academy.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_work.setBackgroundResource(R.drawable.textfield_btn_box_bg)
            btn_service.setBackgroundResource(R.drawable.textfield_btn_box_bg_selected)

            btn_club.setTextColor(Color.parseColor(textUncheck))
            btn_part_time.setTextColor(Color.parseColor(textUncheck))
            btn_study.setTextColor(Color.parseColor(textUncheck))
            btn_student_council.setTextColor(Color.parseColor(textUncheck))
            btn_lesson.setTextColor(Color.parseColor(textUncheck))
            btn_academy.setTextColor(Color.parseColor(textUncheck))
            btn_work.setTextColor(Color.parseColor(textUncheck))
            btn_service.setTextColor(Color.parseColor(textCheck))
        }
    }
}