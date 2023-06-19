package com.example.xcode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.xcode.models.TelegramModel

class ListView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        val list = arrayListOf<TelegramModel>()
        list.add(TelegramModel(R.drawable.comcartlogo, "Jackson", "Hello Bright", "2:00 am"))
        list.add(TelegramModel(R.drawable.comcartlogo, "Rebecca", "Hey hpoe your fine?", "3:00 pm"))
        list.add(TelegramModel(R.drawable.comcartlogo, "Hannah", "Lets meet at next week", "10:00 pm"))

        val myAdapter = ListAdapter(this, list)
        val listView:android.widget.ListView = findViewById(R.id.account_list_view)
        listView.adapter = myAdapter
    }
}