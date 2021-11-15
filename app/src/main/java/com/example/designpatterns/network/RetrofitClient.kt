package com.example.designpatterns.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {


       private const val BASE_URL = "https://api.github.com/"

       val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'z'").create()

       val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiInterface::class.java)

}

