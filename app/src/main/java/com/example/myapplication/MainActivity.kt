package com.example.myapplication

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var txtDatePicker: TextView? = null
    private var txtMinutes: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val datePickerBtn: Button = findViewById(R.id.btnDatePicker)
        txtDatePicker = findViewById(R.id.txtDatePicker)
        txtMinutes = findViewById(R.id.txtMinutes)
        datePickerBtn.setOnClickListener {
            clickDatePicker()
        }

    }

    private fun clickDatePicker() {
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH) //month start from zero so you need to plus one to correct
        val day = myCalender.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, {view, year, month, dayOfMonth ->
            val seletedDate = "${dayOfMonth}/${month + 1}/${year}"
            txtDatePicker?.text = seletedDate
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate = sdf.parse(seletedDate)
            val selectedDateInMinutes = theDate.time / 60000
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateInMinutes = currentDate.time / 60000

            val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
            txtMinutes?.text = differenceInMinutes.toString()
        }, year, month, day)
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }
}