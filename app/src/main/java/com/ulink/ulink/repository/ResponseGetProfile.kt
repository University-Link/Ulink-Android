package com.ulink.ulink.repository


data class ResponseGetProfile(
        val status : Int,
        val success : Boolean,
        val message : String,
        val data : Data
){
    data class Data(
            val username : String,
            val name : String,
            val email : String?,
            val nickname : String,
            val majorIdx : Int,
            val universityIdx : Int,
            val major : String,
            val university : String,
            val emailCheck : Int,
            val profileImage : String
    )

}