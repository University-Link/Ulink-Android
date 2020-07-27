package com.ulink.ulink.repository

import com.google.gson.annotations.SerializedName

data class ResponseGetTimeTableWithId(
        val status : Int,
        val success : Boolean,
        val message : String,
        val data : Data
){

    data class Data(
            val timeTable : TimeTable,
            val minTime : String,
            val maxTime : String,
            val subjects : Subjects
    ){
        data class Subjects(
                @SerializedName("0")
                val mon : List<Subject>,
                @SerializedName("1")
                val tue : List<Subject>,
                @SerializedName("2")
                val wed : List<Subject>,
                @SerializedName("3")
                val thu : List<Subject>,
                @SerializedName("4")
                val fri : List<Subject>
        )
    }

}