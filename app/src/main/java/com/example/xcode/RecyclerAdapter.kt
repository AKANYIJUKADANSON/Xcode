package com.example.xcode

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.xcode.models.menu_model

class RecyclerAdapter(
    val menuList:ArrayList<menu_model>,
    val context:Context
    ): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        // get the custom layout to inflate
        val customLayout = LayoutInflater.from(parent.context).inflate(R.layout.custom_menu, parent, false)
        return ViewHolder(customLayout)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        val current_menu = menuList[position]

        // Binding the data to the views
        holder.menu_image.setImageResource(current_menu.image)
        holder.menu_name.text = current_menu.menu_name
        holder.menu_type.text = "UGX "+ current_menu.menu_price
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    class  ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val menu_image:ImageView = itemView.findViewById(R.id.menu_image)
        val menu_name:TextView = itemView.findViewById(R.id.menu_name)
        val menu_type:TextView = itemView.findViewById(R.id.menu_price)

    }
}