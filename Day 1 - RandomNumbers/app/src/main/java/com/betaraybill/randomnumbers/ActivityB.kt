package com.betaraybill.randomnumbers

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ActivityB : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b)
        var textViewB = findViewById<TextView>(R.id.textViewB)
        textViewB.text = Randomizer().number.toString()
        val buttonB = findViewById<Button>(R.id.buttonB)
        buttonB.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            Randomizer().number = Randomizer().getRandomNumber()
            startActivity(intent)
        }
    }
}
