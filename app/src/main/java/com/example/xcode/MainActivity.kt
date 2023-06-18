package com.example.xcode

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var selectedDate:TextView
    private lateinit var showAge:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        selectedDate = findViewById(R.id.tv_dobSelected)
        selectedDate.text = selectedDate.toString()

        showAge = findViewById<TextView>(R.id.tv_age_display)
        showAge.text = "Your Age: $showAge"



    }

    fun selectDate(view: View) {
        var calender = Calendar.getInstance()
        var cday = calender.get(Calendar.DAY_OF_MONTH)
        var cmonth = calender.get(Calendar.MONTH)
        var cyear = calender.get(Calendar.YEAR)

        // setting the calender dialog
        val dateDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
                view, year, month, dayOfMonth ->
            cday = dayOfMonth
            cmonth = month
            cyear = year
            val currentYear = Calendar.getInstance().get(Calendar.YEAR)

            showAge.text = (currentYear - cyear).toString()

            selectedDate.text =  "You selected: $cday $cmonth, $cyear"
        }, cday, cmonth, cyear)

        dateDialog.show()
    }
}