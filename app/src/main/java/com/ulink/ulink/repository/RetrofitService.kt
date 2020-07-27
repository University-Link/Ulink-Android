package com.ulink.ulink.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService  {

    private val baseURL = "http://52.78.27.117:3000/"

    var retrofit = Retrofit.Builder().baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    var service : RequestInterface = retrofit.create(
            RequestInterface::class.java)

}