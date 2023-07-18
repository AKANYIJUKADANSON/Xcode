package com.example.xcode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView

import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import com.example.xcode.models.Meal
import com.example.xcode.models.Menu
import java.util.*
import kotlin.collections.ArrayList

class RecyclerView : AppCompatActivity() {
    lateinit var mealList:ArrayList<Meal>
    lateinit var searchView: SearchView
    lateinit var searchList:ArrayList<Meal>
    lateinit var mealRecyclerView:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        // Setting up the action bar
        setUpActionBar()

//        searchView = findViewById(R.id.meal_search_view)
        //  get the recyclerview
        mealRecyclerView = findViewById(R.id.menu_recyc_view)

        // Create a list of items and add them to the list

        searchList = ArrayList()

        mealList =  ArrayList()
        mealList.add(Meal(R.drawable.kfc, "KFC", "Chicken", 30000))
        mealList.add(Meal(R.drawable.pizza, "Chicken Pizza", "Pizza", 45000))
        mealList.add(Meal(R.drawable.coffee, "Coffee", "Coffee", 20000))
        mealList.add(Meal(R.drawable.juice, "Juice", "Drinks",10000))
        mealList.add(Meal(R.drawable.vegetables, "Vegetable Mix", "Meal",20000))
        mealList.add(Meal(R.drawable.humbgr, "Burger", "Burger",35000))
        mealList.add(Meal(R.drawable.rolex, "Rolex", "Chapatti", 8000))
        mealList.add(Meal(R.drawable.fish, "Tangi Fish", "Fish" , 50000))
        mealList.add(Meal(R.drawable.snacks, "Daddies", "Snacks", 6000))
        mealList.add(Meal(R.drawable.pilau, "Beef Pilau", "Rice", 30000))




        // get the adapter
        val mealAdapter = RecyclerAdapter(mealList, this)

        // Design layout type for the layout
        mealRecyclerView.layoutManager = GridLayoutManager(this, 2)

        // setting the adapter
        mealRecyclerView.adapter = mealAdapter

    }


    override fun onCreateOptionsMenu(menu: android.view.Menu?): Boolean {

        // Get menu inflater
        menuInflater.inflate(R.menu.custom_menu, menu)

        val menuSearchItem = menu!!.findItem(R.id.app_bar_search)

        // Get the SearchView and set the searchable configuration
        searchView = menuSearchItem!!.actionView as SearchView
        searchView.maxWidth = Int.MAX_VALUE

        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchList.clear()
                val searchText = newText!!.toLowerCase(Locale.getDefault())
                if (searchText.isNotEmpty()){
                    mealList.forEach {
                        if (it.meal_title.toLowerCase(Locale.getDefault()).contains(searchText)){
                            searchList.add(it)
                        }
                    }
                    mealRecyclerView.adapter!!.notifyDataSetChanged()
                }else{
                    searchList.clear()
                    searchList.addAll(mealList)
                    mealRecyclerView.adapter!!.notifyDataSetChanged()
                }
                return true
            }

        })

        return super.onCreateOptionsMenu(menu)
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





