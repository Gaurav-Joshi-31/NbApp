package com.example.nbapp.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroInstance {
    val baseUrl="https://itihas.herokuapp.com/api/"

        val instance: ApiService by lazy{
            val retrofit= Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(ApiService::class.java)
        }

}