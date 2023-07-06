package com.example.xcode.utilities

import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.xcode.R
import com.google.android.material.snackbar.Snackbar

class BaseActivity:AppCompatActivity() {

    fun showErrorSnackBar(message: String, errorMessage: Boolean){
        val snackBar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
        val snackBarView = snackBar.view

        //Setting the background if there is an error
        if (errorMessage){
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(
                    this, R.color.errorColor
                )
            )
        }

        //Setting the background color if there is no error massage
        else{
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(
                    this, R.color.errorFreeColor
                )
            )
        }
        snackBar.show()
    }
}