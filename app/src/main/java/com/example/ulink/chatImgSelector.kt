package com.example.ulink

import com.example.ulink.R

fun chatImgSelector(index : Int) : Int {
    return when (index){
        0 -> R.drawable.class_img_subject_0
        1 -> R.drawable.class_img_subject_1
        2 -> R.drawable.class_img_subject_2
        3 -> R.drawable.class_img_subject_3
        4 -> R.drawable.class_img_subject_4
        5 -> R.drawable.class_img_subject_5
        6 -> R.drawable.class_img_subject_6
        7 -> R.drawable.class_img_subject_7
        8 -> R.drawable.class_img_subject_8
        9 -> R.drawable.class_img_subject_9
        else ->  R.drawable.class_img_subject_0
    }
}