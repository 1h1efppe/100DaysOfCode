package com.homelander.bmicalculator

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weight = findViewById<TextView>(R.id.weight)
        val height = findViewById<TextView>(R.id.height)
        val button = findViewById<Button>(R.id.calculate)
        val result = findViewById<TextView>(R.id.result)
        val image = findViewById<ImageView>(R.id.resultImage)



        fun calculateBmi(): String {
            var text = ""
            if (weight.text.isEmpty() || height.text.isEmpty()) {
                Toast.makeText(this@MainActivity, "Please fill Weight and Height", Toast.LENGTH_LONG).show()
            } else {
                val weightAmount = weight.text.toString().toFloat()
                val heightAmount = height.text.toString().toFloat()
                text = "%.1f".format((weightAmount / (heightAmount * heightAmount)) * 10000)
            }
            return text
        }

        fun detectBodyType(bmi : Int): String {
            return when (bmi) {
                in 0..19 -> "skinny"
                in 20..30 -> "normal"
                else -> "fat"
            }
        }

        button.setOnClickListener {
            if (detectBodyType(calculateBmi().toFloat().toInt()) == "skinny") {
                image.setImageResource(R.drawable.loltwo)
                result.text = "Your BMI is ${calculateBmi()}. You are skinny."
            }
            else if (detectBodyType(calculateBmi().toFloat().toInt()) == "normal") {
                image.setImageResource(R.drawable.lolthree)
                result.text = "Your BMI is ${calculateBmi()}. You have normal weight."
            }
            else {
                image.setImageResource(R.drawable.lol)
                result.text = "Your BMI is ${calculateBmi()}. You have some extra weight."
            }
        }
    }
}
