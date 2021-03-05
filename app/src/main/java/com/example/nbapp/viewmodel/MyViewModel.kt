package com.example.nbapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nbapp.model.signupModel
import com.example.nbapp.retrofit.RetroInstance
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewModel: ViewModel() {

    fun makeSignup(user: signupModel):LiveData<String>{
        val resultString=MutableLiveData<String>()

      RetroInstance.instance.signup(user).enqueue(object :Callback<ResponseBody>{
          override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            resultString.postValue(null)
          }

          override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                 if(response.code()==201){
                     resultString.postValue("Success")
                 }
              else if(response.code()==409){
                     resultString.postValue("Login")
                 }

              else{
                     resultString.postValue(null)
                 }


          }

      })
        return resultString

    }

    fun makeLogin(email:String, password:String):LiveData<signupModel>{
        val userDataa=MutableLiveData<signupModel>()
      RetroInstance.instance.login(email,password).enqueue(object: Callback<signupModel>{
          override fun onFailure(call: Call<signupModel>, t: Throwable) {
              userDataa.value = null
          }

          override fun onResponse(call: Call<signupModel>, response: Response<signupModel>) {
              if(response.isSuccessful){
                 userDataa.value=response.body()
              }
              else{
                  userDataa.value=null
              }
          }

      })
        return userDataa
    }
}