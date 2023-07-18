package com.example.xcode

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.xcode.models.Meal
import com.example.xcode.models.Menu

class RecyclerAdapter(
    val mealList:ArrayList<Meal>,
    val context:Context
    )
    : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        // get the custom layout to inflate
        val customLayout = LayoutInflater.from(parent.context).inflate(R.layout.custom_food, parent, false)
        return ViewHolder(customLayout)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        val currentMeal = mealList[position]

        // Binding the data to the views
        holder.mealImage.setImageResource(currentMeal.image)
        holder.mealTitle.text = currentMeal.meal_title
        holder.mealType.text = currentMeal.meal_category
        holder.mealPrice.text = "UGX "+ currentMeal.meal_price
    }

    override fun getItemCount(): Int {
        return mealList.size
    }

    class  ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val mealImage:ImageView = itemView.findViewById(R.id.iv_meal_image)
        val mealTitle:TextView = itemView.findViewById(R.id.tv_meal_name)
        val mealType:TextView = itemView.findViewById(R.id.tv_meal_type)
        val mealPrice:TextView = itemView.findViewById(R.id.tv_meal_price)

    }
}