package com.example.spinnerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val arrayForSpinner = arrayOf("Select an option", "First", "Second", "Third")
        val mySpinner = findViewById<Spinner>(R.id.spinner)
        val showSelectedValueButton = findViewById<Button>(R.id.showSelectedValueButton)
        val selectedOptionText = findViewById<TextView>(R.id.selectedOptionText)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayForSpinner)
        var spinnerText = ""


        mySpinner.adapter = adapter
        mySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                if (arrayForSpinner[i] == "Select an option") {
                    spinnerText = ""
                }
                else {
                    spinnerText = arrayForSpinner[i]
                }
            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {

            }
        }
        showSelectedValueButton.setOnClickListener {
            if (spinnerText == "") {
                Toast.makeText(this@MainActivity, "Nothing selected", Toast.LENGTH_SHORT).show()
            } else {
                selectedOptionText.text = "Last item: $spinnerText"
            }
        }
    }
}
