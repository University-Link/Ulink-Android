package com.example.ulink.repository

import retrofit2.Call
import retrofit2.http.*

interface RequestInterface {


    @GET("/schedule")
    fun getMainTimeTable(
            @Header("token") token : String
    ) : Call<ResponseMainTimeTable>

    @GET("/schedule/{idx}")
    fun getTimeTableWithId(
            @Header("token") token: String,
            @Path("idx") idx : String,
            @Query("isSubject") isSubject : Boolean
    ) : Call<ResponseTimeTable>
}