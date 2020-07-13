package com.example.ulink

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_filter_normal.*
import kotlinx.android.synthetic.main.toolbar_filter_normal.*

class FilterNormalActivity : AppCompatActivity() {
    var mondayCheck = false
    var tuesdayCheck = false
    var wednesdayCheck = false
    var thursdayCheck = false
    var fridayCheck = false

    var mondayEmptyCheck = false
    var tuesdayEmptyCheck = false
    var wednesdayEmptyCheck = false
    var thursdayEmptyCheck = false
    var fridayEmptyCheck = false

    var credit1Check = false
    var credit2Check = false
    var credit3Check = false
    var credit4Check = false

    var oneClassExclude = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter_normal)

        one_class_exclude.setOnClickListener() {
            if (oneClassExclude == false)
                oneClassExclude = true
            else
                oneClassExclude = false
        }

        btn_back.setOnClickListener() {
            finish()
        }

        btn_reset.setOnClickListener() {
            mondayCheck = false
            tuesdayCheck = false
            wednesdayCheck = false
            thursdayCheck = false
            fridayCheck = false

            mondayEmptyCheck = false
            tuesdayEmptyCheck = false
            wednesdayEmptyCheck = false
            tuesdayEmptyCheck = false
            fridayEmptyCheck = false

            oneClassExclude = false

            credit1Check = false
            credit2Check = false
            credit3Check = false
            credit4Check = false

            btn_monday.setBackgroundResource(R.drawable.io_main_filtersetting_btn_mon_selected)
            btn_tuesday.setBackgroundResource(R.drawable.io_main_filtersetting_btn_tue_selected)
            btn_wednesday.setBackgroundResource(R.drawable.io_main_filtersetting_btn_wed_selected)
            btn_thursday.setBackgroundResource(R.drawable.io_main_filtersetting_btn_thu_selected)
            btn_friday.setBackgroundResource(R.drawable.io_main_filtersetting_btn_fri_selected)


            one_class_exclude.setBackgroundResource(R.drawable.main_filtersetting_toggle01)

            btn_monday_empty.setBackgroundResource(R.drawable.io_main_filtersetting_btn_monempty_unselected)
            btn_tuesday_empty.setBackgroundResource(R.drawable.io_main_filtersetting_btn_tueempty_unselected)
            btn_wednesday_empty.setBackgroundResource(R.drawable.io_main_filtersetting_btn_wedempty_unselected)
            btn_thursday_empty.setBackgroundResource(R.drawable.io_main_filtersetting_btn_thuempty_unselected)
            btn_friday_empty.setBackgroundResource(R.drawable.io_main_filtersetting_btn_friempty_unselected)

            btn_credit_1.setBackgroundResource(R.drawable.credit_btn_1)
            btn_credit_2.setBackgroundResource(R.drawable.credit_btn_2)
            btn_credit_3.setBackgroundResource(R.drawable.credit_btn_3)
            btn_credit_4.setBackgroundResource(R.drawable.credit_btn_4)

        }

        date_all_clear.setOnClickListener(){
            mondayCheck = false
            tuesdayCheck = false
            wednesdayCheck = false
            thursdayCheck = false
            fridayCheck = false

            btn_monday.setBackgroundResource(R.drawable.io_main_filtersetting_btn_mon_selected)
            btn_tuesday.setBackgroundResource(R.drawable.io_main_filtersetting_btn_tue_selected)
            btn_wednesday.setBackgroundResource(R.drawable.io_main_filtersetting_btn_wed_selected)
            btn_thursday.setBackgroundResource(R.drawable.io_main_filtersetting_btn_thu_selected)
            btn_friday.setBackgroundResource(R.drawable.io_main_filtersetting_btn_fri_selected)
        }

        credit_all_clear.setOnClickListener() {
            credit1Check = false
            credit2Check = false
            credit3Check = false
            credit4Check = false

            btn_credit_1.setBackgroundResource(R.drawable.credit_btn_1)
            btn_credit_2.setBackgroundResource(R.drawable.credit_btn_2)
            btn_credit_3.setBackgroundResource(R.drawable.credit_btn_3)
            btn_credit_4.setBackgroundResource(R.drawable.credit_btn_4)
        }

        btn_monday.setOnClickListener() {
            if(mondayCheck == false) {
                btn_monday.setBackgroundResource(R.drawable.main_filtersetting_btn_mon_selected)
                mondayCheck = true
            }
            else {
            btn_monday.setBackgroundResource(R.drawable.io_main_filtersetting_btn_mon_selected)
            mondayCheck = false
            }
        }

        btn_tuesday.setOnClickListener() {
            if(tuesdayCheck == false) {
                btn_tuesday.setBackgroundResource(R.drawable.main_filtersetting_btn_tue_selected)
                tuesdayCheck = true
            }
            else {
                btn_tuesday.setBackgroundResource(R.drawable.io_main_filtersetting_btn_tue_selected)
                tuesdayCheck = false
            }
        }

        btn_wednesday.setOnClickListener() {
            if(wednesdayCheck == false) {
                btn_wednesday.setBackgroundResource(R.drawable.main_filtersetting_btn_wed_selected)
                wednesdayCheck = true
            }
            else {
                btn_wednesday.setBackgroundResource(R.drawable.io_main_filtersetting_btn_wed_selected)
                wednesdayCheck = false
            }
        }

        btn_thursday.setOnClickListener() {
            if(thursdayCheck == false) {
                btn_thursday.setBackgroundResource(R.drawable.main_filtersetting_btn_thu_selected)
                thursdayCheck = true
            }
            else {
                btn_thursday.setBackgroundResource(R.drawable.io_main_filtersetting_btn_thu_selected)
                thursdayCheck = false
            }
        }

        btn_friday.setOnClickListener() {
            if(fridayCheck == false) {
                btn_friday.setBackgroundResource(R.drawable.main_filtersetting_btn_fri_selected)
                fridayCheck = true
            }
            else {
                btn_friday.setBackgroundResource(R.drawable.io_main_filtersetting_btn_fri_selected)
                fridayCheck = false
            }
        }

        btn_monday_empty.setOnClickListener() {
            if(mondayEmptyCheck == false) {
                btn_monday_empty.setBackgroundResource(R.drawable.main_filtersetting_btn_monempty_unselected)
                mondayEmptyCheck = true
            }
            else {
                btn_monday_empty.setBackgroundResource(R.drawable.io_main_filtersetting_btn_monempty_unselected)
                mondayEmptyCheck = false
            }
        }

        btn_tuesday_empty.setOnClickListener() {
            if(tuesdayEmptyCheck == false) {
                btn_tuesday_empty.setBackgroundResource(R.drawable.main_filtersetting_btn_tueempty_unselected)
                tuesdayEmptyCheck = true
            }
            else {
                btn_tuesday_empty.setBackgroundResource(R.drawable.io_main_filtersetting_btn_tueempty_unselected)
                tuesdayEmptyCheck = false
            }
        }

        btn_wednesday_empty.setOnClickListener() {
            if(wednesdayEmptyCheck == false) {
                btn_wednesday_empty.setBackgroundResource(R.drawable.main_filtersetting_btn_wedempty_unselected)
                wednesdayEmptyCheck = true
            }
            else {
                btn_wednesday_empty.setBackgroundResource(R.drawable.io_main_filtersetting_btn_wedempty_unselected)
                wednesdayCheck = false
            }
        }

        btn_thursday_empty.setOnClickListener() {
            if(thursdayEmptyCheck == false) {
                btn_thursday_empty.setBackgroundResource(R.drawable.main_filtersetting_btn_thuempty_unselected)
                thursdayEmptyCheck = true
            }
            else {
                btn_thursday_empty.setBackgroundResource(R.drawable.io_main_filtersetting_btn_thuempty_unselected)
                thursdayEmptyCheck = false
            }
        }

        btn_friday_empty.setOnClickListener() {
            if(fridayEmptyCheck == false) {
                btn_friday_empty.setBackgroundResource(R.drawable.main_filtersetting_btn_friempty_unselected)
                fridayEmptyCheck = true
            }
            else {
                btn_friday_empty.setBackgroundResource(R.drawable.io_main_filtersetting_btn_friempty_unselected)
                fridayEmptyCheck = false
            }
        }

        btn_credit_1.setOnClickListener() {
            if(credit1Check == false) {
                btn_credit_1.setBackgroundResource(R.drawable.credit_btn_1_selected)
                credit1Check = true
            }
            else {
                btn_credit_1.setBackgroundResource(R.drawable.credit_btn_1)
                credit1Check = false
            }
        }

        btn_credit_2.setOnClickListener() {
            if(credit2Check == false) {
                btn_credit_2.setBackgroundResource(R.drawable.credit_btn_2_selected)
                credit2Check = true
            }
            else {
                btn_credit_2.setBackgroundResource(R.drawable.credit_btn_2)
                credit2Check = false
            }
        }
        btn_credit_3.setOnClickListener() {
            if(credit3Check == false) {
                btn_credit_3.setBackgroundResource(R.drawable.credit_btn_3_selected)
                credit3Check = true
            }
            else {
                btn_credit_3.setBackgroundResource(R.drawable.credit_btn_3)
                credit3Check = false
            }
        }
        btn_credit_4.setOnClickListener() {
            if(credit4Check == false) {
                btn_credit_4.setBackgroundResource(R.drawable.credit_btn_4_selected)
                credit4Check = true
            }
            else {
                btn_credit_4.setBackgroundResource(R.drawable.credit_btn_4)
                credit4Check = false
            }
        }
    }
}