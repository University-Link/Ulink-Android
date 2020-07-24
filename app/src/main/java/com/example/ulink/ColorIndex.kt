package com.example.ulink

fun chatImgSelector(index : Int) : Int {
    return when (index){
        0 -> R.drawable.io_class_img01
        1 -> R.drawable.io_class_img02
        2 -> R.drawable.io_class_img03
        3 -> R.drawable.io_class_img04
        4 -> R.drawable.io_class_img05
        5 -> R.drawable.io_class_img06
        6 -> R.drawable.io_class_img07
        7 -> R.drawable.io_class_img08
        8 -> R.drawable.io_class_img09
        9 -> R.drawable.io_class_img10
        10 -> R.drawable.io_class_img11
        11 -> R.drawable.io_class_img12
        12 -> R.drawable.io_class_img13
        13 -> R.drawable.io_class_img14
        14 -> R.drawable.io_class_img15
        15 -> R.drawable.io_class_img16
        16 -> R.drawable.io_class_img17
        17 -> R.drawable.io_class_img18
        18 -> R.drawable.io_class_img19
        else -> R.drawable.io_class_img20
    }
}

fun getColors(type: Int): Int {
    return when (type) {
        0 -> R.drawable.bg_round_border_subject_color_1
        1 -> R.drawable.bg_round_border_subject_color_2
        2 -> R.drawable.bg_round_border_subject_color_3
        3 -> R.drawable.bg_round_border_subject_color_4
        4 -> R.drawable.bg_round_border_subject_color_5
        5 -> R.drawable.bg_round_border_subject_color_6
        6 -> R.drawable.bg_round_border_subject_color_7
        7 -> R.drawable.bg_round_border_subject_color_8
        8 -> R.drawable.bg_round_border_subject_color_9
        9 -> R.drawable.bg_round_border_subject_color_10
        10 -> R.drawable.bg_round_border_subject_color_11
        11 -> R.drawable.bg_round_border_subject_color_12
        12 -> R.drawable.bg_round_border_subject_color_13
        13 -> R.drawable.bg_round_border_subject_color_14
        14 -> R.drawable.bg_round_border_subject_color_15
        15 -> R.drawable.bg_round_border_subject_color_16
        16 -> R.drawable.bg_round_border_subject_color_17
        17 -> R.drawable.bg_round_border_subject_color_18
        18 -> R.drawable.bg_round_border_subject_color_19
        else -> R.drawable.bg_round_border_subject_color_20
    }
}

fun scheduleColorSelector(type: Int): Int {
    return when (type) {
        0 -> R.drawable.calendar_schedule_color1
        1 -> R.drawable.calendar_schedule_color2
        2 -> R.drawable.calendar_schedule_color3
        3 -> R.drawable.calendar_schedule_color4
        4 -> R.drawable.calendar_schedule_color5
        5 -> R.drawable.calendar_schedule_color6
        6 -> R.drawable.calendar_schedule_color7
        7 -> R.drawable.calendar_schedule_color8
        8 -> R.drawable.calendar_schedule_color9
        9 -> R.drawable.calendar_schedule_color10
        10 -> R.drawable.calendar_schedule_color11
        11 -> R.drawable.calendar_schedule_color12
        12 -> R.drawable.calendar_schedule_color13
        13 -> R.drawable.calendar_schedule_color14
        14 -> R.drawable.calendar_schedule_color15
        15 -> R.drawable.calendar_schedule_color16
        16 -> R.drawable.calendar_schedule_color17
        17 -> R.drawable.calendar_schedule_color18
        18 -> R.drawable.calendar_schedule_color19
        else -> R.drawable.calendar_schedule_color20
    }
}