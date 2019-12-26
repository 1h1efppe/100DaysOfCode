package com.betaraybill.randomnumbers

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var textViewA = findViewById<TextView>(R.id.randomTextA)
        textViewA.text = Randomizer().number.toString()
        val buttonA = findViewById<Button>(R.id.buttonA)
        buttonA.setOnClickListener {
            val intent = Intent(this, ActivityB::class.java)
            Randomizer().number = Randomizer().getRandomNumber()
            startActivity(intent)
        }
    }
}
