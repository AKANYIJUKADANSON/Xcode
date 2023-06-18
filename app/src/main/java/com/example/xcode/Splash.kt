package com.example.xcode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val logoToAnimate = findViewById<ImageView>(R.id.splash_image)
        val animation = AnimationUtils.loadAnimation(this, androidx.appcompat.R.anim.abc_slide_in_bottom)
        logoToAnimate.startAnimation(animation)

        Handler().postDelayed(
            {startActivity(Intent(this, MainActivity::class.java))},
            3000
        )
    }
}