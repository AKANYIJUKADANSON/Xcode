package com.example.xcode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.xcode.databinding.ActivityMealsMenuBinding
import com.example.xcode.models.Menu

class MealsMenu : AppCompatActivity() {
    private lateinit var binding:ActivityMealsMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_meals_menu)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_meals_menu)

    }

    fun menuDownloadSuccess(menuList:ArrayList<Menu>){
        if (menuList.size > 0){
            // get the recycler view
            val menuRecyclerView = binding.menuRecyclerView

            // Get the adapter



        }
    }
}