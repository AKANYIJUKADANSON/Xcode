package com.example.xcode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import com.example.xcode.models.menu_model

class RecyclerView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        // Setting up the action bar
        setUpActionBar()

        //  get the recyclerview
        val menuRecyclerView = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.menu_recyc_view)

        // Create a list of items and add them to the list
        val menuList:ArrayList<menu_model> = ArrayList()
        menuList.add(menu_model(R.drawable.kfc, "KFC", "30000"))
        menuList.add(menu_model(R.drawable.pizza, "Chicken Pizza", "45000"))
        menuList.add(menu_model(R.drawable.coffee, "Coffee", "20000"))
        menuList.add(menu_model(R.drawable.juice, "Juice", "10000"))
        menuList.add(menu_model(R.drawable.vegetables, "Vegetable Mix", "20000"))
        menuList.add(menu_model(R.drawable.humbgr, "Burger", "35000"))
        menuList.add(menu_model(R.drawable.rolex, "Rolex", "8000"))
        menuList.add(menu_model(R.drawable.fish, "Fish", "50000"))
        menuList.add(menu_model(R.drawable.snacks, "snacks", "6000"))
        menuList.add(menu_model(R.drawable.pilau, "Beef Pilau", "30000"))




        // get the adapter
        val menuAdapter = RecyclerAdapter(menuList, this)

        // Design layout type for the layout
        menuRecyclerView.layoutManager = GridLayoutManager(this, 2)


        // setting the adapter
        menuRecyclerView.adapter = menuAdapter
    }

    private fun setUpActionBar(){
        val myToolBar = findViewById<Toolbar>(R.id.menu_toolbar)
        setSupportActionBar(myToolBar)
        val actionBar = supportActionBar
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_back_arrow)
        }

        myToolBar.setNavigationOnClickListener { onBackPressed() }
    }
}