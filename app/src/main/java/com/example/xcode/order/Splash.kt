package com.example.xcode.order

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.example.xcode.MainActivity
import com.example.xcode.R

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

//        val logoToAnimate = findViewById<ImageView>(R.id.list_image)
//        val animation = AnimationUtils.loadAnimation(this, androidx.appcompat.R.anim.abc_slide_in_bottom)
//        logoToAnimate.startAnimation(animation)

        Handler().postDelayed(
            {startActivity(Intent(this, Login::class.java))},
            3000
        )
    }
}