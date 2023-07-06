package com.example.xcode.order

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.xcode.R

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButton:Button = findViewById(R.id.loginButton)
        loginButton.setOnClickListener {
            val intent = Intent(this, Welcome::class.java)
            startActivity(intent)
        }
    }


    private fun validateUserLoginDetails(){

    }

}