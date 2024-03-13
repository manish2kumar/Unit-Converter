package com.example.unitconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner

class MainActivity : AppCompatActivity() {

    private lateinit var fromSpinner: Spinner
    private lateinit var toSpinner: Spinner
    private lateinit var fromEditText: EditText
    private lateinit var toEditText: EditText
    private lateinit var btn_Convert: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_UnitConverter)
        setContentView(R.layout.activity_main)

        fromSpinner= findViewById(R.id.fromSpinner)
        toSpinner= findViewById(R.id.toSpinner)
        fromEditText= findViewById(R.id.fromEditText)
        toEditText= findViewById(R.id.toEditText)
        btn_Convert= findViewById(R.id.btn_Convert)


        val units = resources.getStringArray(R.array.units_array)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, units)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        fromSpinner.adapter = adapter
        toSpinner.adapter = adapter


        fromSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                convert()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }

        toSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                convert()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }

        btn_Convert.setOnClickListener {
            convert()
        }

    }

    private fun convert() {
        val fromUnit = fromSpinner.selectedItem.toString()
        val toUnit = toSpinner.selectedItem.toString()

        val inputValue = fromEditText.text.toString().toDoubleOrNull()
        if (inputValue != null) {
            val result = performConversion(fromUnit, toUnit, inputValue)
            toEditText.setText(result.toString())
        } else {
            toEditText.setText("")
        }
    }

    private fun performConversion(fromUnit: String, toUnit: String, value: Double): Double {

        when (fromUnit) {
            "Celsius" -> return when (toUnit) {
                "Celsius" -> value
                "Fahrenheit" -> value * 9 / 5 + 32
                "Kelvin" -> value + 273.15
                else -> value
            }
            "Fahrenheit" -> return when (toUnit) {
                "Celsius" -> (value - 32) * 5 / 9
                "Fahrenheit" -> value
                "Kelvin" -> (value - 32) * 5 / 9 + 273.15
                else -> value
            }
            "Kelvin" -> return when (toUnit) {
                "Celsius" -> value - 273.15
                "Fahrenheit" -> (value - 273.15) * 9 / 5 + 32
                "Kelvin" -> value
                else -> value
            }
            "Meters" -> return when (toUnit) {
                "Meters" -> value
                "Kilometers" -> value / 1000
                "Miles" -> value / 1609.34
                else -> value
            }
            "Kilometers" -> return when (toUnit) {
                "Meters" -> value * 1000
                "Kilometers" -> value
                "Miles" -> value / 1.60934
                else -> value
            }
            "Miles" -> return when (toUnit) {
                "Meters" -> value * 1609.34
                "Kilometers" -> value * 1.60934
                "Miles" -> value
                else -> value
            }
            "Seconds" -> return when (toUnit) {
                "Seconds" -> value
                "Minutes" -> value / 60
                "Hours" -> value / 3600
                else -> value
            }
            "Minutes" -> return when (toUnit) {
                "Seconds" -> value * 60
                "Minutes" -> value
                "Hours" -> value / 60
                else -> value
            }
            "Hours" -> return when (toUnit) {
                "Seconds" -> value * 3600
                "Minutes" -> value * 60
                "Hours" -> value
                else -> value
            }
            else -> return value
        }
    }
}