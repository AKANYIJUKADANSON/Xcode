package com.example.xcode.order

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.xcode.R

class Tables : AppCompatActivity() {
    private lateinit var mSelectedTable:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tables)

        // setting up the action bar
        setUpActionBar()

        val tables = arrayOf("T01", "T02", "T03", "T04", "T05", "T06", "T07")
        val tablesArrayAdapter = ArrayAdapter(this, R.layout.custom_table, tables)

        val tableField:AutoCompleteTextView = findViewById(R.id.ac_tv_tables)

        tableField.setAdapter(tablesArrayAdapter)

        // get the selected value
        tableField.setOnItemClickListener { adapterView, view, i, l ->
            val selectedTable = adapterView.getItemAtPosition(i)
            mSelectedTable = selectedTable.toString()
            Toast.makeText(this, "Selected Table: $mSelectedTable", Toast.LENGTH_LONG).show()
        }
    }

    private fun setUpActionBar(){
        val myToolBar = findViewById<Toolbar>(R.id.tables_toolbar)
        setSupportActionBar(myToolBar)
        val actionBar = supportActionBar
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_back_arrow)
        }

        myToolBar.setNavigationOnClickListener { onBackPressed() }
    }
}