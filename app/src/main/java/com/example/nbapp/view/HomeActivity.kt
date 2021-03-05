package com.example.nbapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nbapp.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        nameText.text="Name- "+intent.getStringExtra("name")
        phoneText.text="Phone- "+intent.getStringExtra("phone")
        emailText.text="Email- "+intent.getStringExtra("email")
        countryText.text="Country- "+intent.getStringExtra("country")
        cityText.text="City- "+intent.getStringExtra("city")

    }
}