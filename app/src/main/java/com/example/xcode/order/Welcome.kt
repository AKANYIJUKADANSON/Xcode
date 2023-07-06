package com.example.xcode.order

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.example.xcode.R
import com.example.xcode.RecyclerView

class Welcome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        setUpActionBar()
        // Make order
        val makeOrderBtn = findViewById<Button>(R.id.make_order_btn)
        makeOrder(makeOrderBtn)

    }

    fun makeOrder(view: View) {
        view.setOnClickListener {
            val intent = Intent(this, Tables::class.java)
            startActivity(intent)
        }
    }

    private fun setUpActionBar(){
        val myToolBar = findViewById<Toolbar>(R.id.welcome_toolbar)
        setSupportActionBar(myToolBar)
        val actionBar = supportActionBar
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_back_arrow)
        }

        myToolBar.setNavigationOnClickListener { onBackPressed() }
    }
}