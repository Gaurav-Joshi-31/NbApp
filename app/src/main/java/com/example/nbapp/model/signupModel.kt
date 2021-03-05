package com.example.nbapp.model

import com.google.gson.annotations.SerializedName

data class signupModel (@SerializedName("name") val userName: String?,
                        @SerializedName("phone") val userPhone: String?,
                        @SerializedName("email") val userEmail: String?,
                        @SerializedName("password") val userPassword: String?,
                        @SerializedName("country") val userCountry: String?,
                        @SerializedName("city") val userCity: String?)