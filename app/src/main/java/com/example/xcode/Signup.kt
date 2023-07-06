package com.example.xcode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.xcode.models.User
import com.google.firebase.auth.FirebaseAuth

class Signup : AppCompatActivity() {

    private var mFirebaseAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)


        val signUpBtn = findViewById<Button>(R.id.signup_button)
        signUpBtn.setOnClickListener {
            val email = findViewById<EditText>(R.id.ed_signup_email).text.toString()
            val password = findViewById<EditText>(R.id.ed_signup_password).text.toString()

            if (validateRegDetails()) {

                mFirebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnSuccessListener {
                        // store user details
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Failure", Toast.LENGTH_LONG).show()
                    }

            }
        }

    }

    private fun saveUserDataToCloud(){
        val username = findViewById<EditText>(R.id.ed_signup_username).text.toString()
        val email = findViewById<EditText>(R.id.ed_signup_email).text.toString()
        val password = findViewById<EditText>(R.id.ed_signup_password).text.toString()
        val confirmPassword = findViewById<EditText>(R.id.ed_signup_confirm_password).text.toString()

        val userDetails = User("1", username, email, password, confirmPassword)


    }


    private fun validateRegDetails(): Boolean {
        //capturing what the user enters using ids and changing them into text format
        val email = findViewById<EditText>(R.id.ed_signup_email).text.toString()
        val password = findViewById<EditText>(R.id.ed_signup_password).text.toString()

        return when {

            TextUtils.isEmpty(email.trim { it <= ' ' }) -> {
                Toast.makeText(this, "Please enter email address", Toast.LENGTH_LONG).show()
                false
            }

            TextUtils.isEmpty(password.trim { it <= ' ' }) -> {
                Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show()
                false
            }

            // What to do if everything is valid
            else -> {
                true
            }


        }
    }
}