package com.example.xcode

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.xcode.models.TelegramModel

class ListAdapter(val context: Context, val myList:ArrayList<TelegramModel>):BaseAdapter() {
    override fun getCount(): Int {
        return  myList.size
    }

    override fun getItem(p0: Int): Any {
        return myList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val customView = LayoutInflater.from(context).inflate(R.layout.custom_item, null, false)
        val image = customView.findViewById<ImageView>(R.id.list_image)
        val name:TextView = customView.findViewById(R.id.tv_name)
        val message:TextView = customView.findViewById(R.id.tv_message)
        val time:TextView = customView.findViewById(R.id.tv_time)

        image.setImageResource(R.drawable.comcartlogo)
        name.text = myList[p0].name
        message.text = myList[p0].message
        time.text = myList[p0].time

        return  customView
    }
}