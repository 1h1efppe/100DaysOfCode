package com.betaraybill.loginform

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val email = findViewById<EditText>(R.id.emailField).text
        val password = findViewById<EditText>(R.id.passwordField).text
        val buttonA = findViewById<Button>(R.id.loginButton)
        buttonA.setOnClickListener {
            if (emailIsValid(email.toString())) {
                val intent = Intent(this, LoggedScreen::class.java)
                intent.putExtra("email", email.toString())
                intent.putExtra("password", password.toString())
                startActivity(intent)
            } else {
                Toast.makeText(this@MainActivity, "Invalid email", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun emailIsValid(email: String) : Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
