package com.example.nbapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.nbapp.R
import com.example.nbapp.model.signupModel
import com.example.nbapp.viewmodel.MyViewModel
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity() {
    lateinit var viewmodel: MyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewmodel = ViewModelProviders.of(this).get(MyViewModel::class.java)
        signupButton.setOnClickListener { view ->

            sendData()
        }
        loginGo.setOnClickListener { view ->
            if (signupView.visibility == View.VISIBLE) signupView.visibility =
                View.GONE else signupView.visibility = View.VISIBLE
            if (loginview.visibility == View.VISIBLE) loginview.visibility =
                View.GONE else loginview.visibility = View.VISIBLE
            if (loginview.visibility == View.VISIBLE) loginGo.text =
                "New User? Signup" else loginGo.text = "Already a member? Login"
        }
        loginButton.setOnClickListener { view ->
            loginUser()
        }


    }

 fun loginUser() {
        if (loginemailEditText.text.isNullOrEmpty()
            || loginpassEditText.text.isNullOrEmpty()
        ) {
            Toast.makeText(this, "Please enter details correctly", Toast.LENGTH_SHORT).show()
        } else {
            progress.visibility=View.VISIBLE
            viewmodel.makeLogin(loginemailEditText.text.toString().trim(),loginpassEditText.text.toString().trim())
                .observe(this,Observer{
                    if(it==null){
                        Toast.makeText(this, "Details dosen't match", Toast.LENGTH_SHORT).show()
                        progress.visibility=View.GONE
                    }
                    else{
                        val intent = Intent(this, HomeActivity::class.java).apply {
                            putExtra("name",it.userName)
                            putExtra("phone",it.userPhone)
                            putExtra("email",it.userEmail)
                            putExtra("country",it.userCountry)
                            putExtra("city",it.userCity)
                        }
                        progress.visibility=View.GONE
                        startActivity(intent)
                        finish()
                    }
                })
        }

    }

    fun sendData() {
        if (nameEditText.text.isNullOrEmpty()
            || phoneEditText.text.isNullOrEmpty()
            || emailEditText.text.isNullOrEmpty()
            || phoneEditText.text.length!=10
            || passwordEditText.text.length < 6
            || countryEditText.text.isNullOrEmpty()
            || cityEditText.text.isNullOrEmpty()
        ) {

            Toast.makeText(this, "Please enter details correctly", Toast.LENGTH_SHORT).show()
        } else {
            progress.visibility=View.VISIBLE
            val userInfo = signupModel(
                userName = nameEditText.text.toString().trim(),
                userPhone = phoneEditText.text.toString().trim(),
                userEmail = emailEditText.text.toString().trim(),
                userPassword = passwordEditText.text.toString().trim(),
                userCountry = countryEditText.text.toString().trim(),
                userCity = cityEditText.text.toString().trim()
            )
            viewmodel.makeSignup(userInfo).observe(this,Observer{
                if (it == "Success") {
                    Toast.makeText(
                        this,
                        "User created successfully. Please login",
                        Toast.LENGTH_SHORT
                    ).show()
                    progress.visibility=View.GONE
                } else if (it == "Login") {
                    Toast.makeText(this, "User Already exist. Please login", Toast.LENGTH_SHORT)
                        .show()
                    progress.visibility=View.GONE
                } else {
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
                    progress.visibility=View.GONE
                }
            })
        }


    }


}
