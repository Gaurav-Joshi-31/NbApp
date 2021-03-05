package com.example.nbapp.retrofit

import com.example.nbapp.model.signupModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @POST("signup")
    fun signup(@Body userData:signupModel): Call<ResponseBody>

    @FormUrlEncoded
    @POST("login")
    fun login(@Field("email") email: String,
              @Field("password") password: String): Call<signupModel>

}