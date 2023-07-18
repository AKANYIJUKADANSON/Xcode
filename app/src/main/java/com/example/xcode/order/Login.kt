package com.example.xcode.order

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.xcode.R
import com.example.xcode.utilities.BaseActivity
import com.google.firebase.auth.FirebaseAuth

class Login : BaseActivity() {
    private var mFirebaseAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButton:Button = findViewById(R.id.loginButton)
        loginButton.setOnClickListener {
            val intent = Intent(this, Welcome::class.java)
            startActivity(intent)

//            validateUserLoginDetails()
        }
    }

    private fun validateUserLoginDetails():Boolean {
        val loginEmail = findViewById<EditText>(R.id.loginEmail).text.toString()
        val loginPassword = findViewById<EditText>(R.id.loginPassword).text.toString()

        return when {
            TextUtils.isEmpty(loginEmail.trim { it <= ' ' }) ->{
                showErrorSnackBar("Email ID field must be filled", true)
                false
            }

            TextUtils.isEmpty(loginPassword.trim { it <= ' ' }) ->{
                showErrorSnackBar("Password field must be filled", true)
                false
            }

            else -> {
                // signin
                mFirebaseAuth.signInWithEmailAndPassword(loginEmail, loginPassword)
                    .addOnSuccessListener {
                        val intent = Intent(this, Welcome::class.java)
                        startActivity(intent)
                        finish()

                        Toast.makeText(this, "You have been logged in successfully", Toast.LENGTH_LONG).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Invalid email or password, Please try again", Toast.LENGTH_LONG).show()
                    }
                return true
            }
        }

    }

}