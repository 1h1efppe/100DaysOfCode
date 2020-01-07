package com.example.loginlogout

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val usernameField = findViewById<EditText>(R.id.usernameField)
        val passwordField = findViewById<EditText>(R.id.passwordField)
        val loginButton = findViewById<Button>(R.id.loginButton)

        val sharedPreferences: SharedPreferences = this.getSharedPreferences(SplashActivity().sharedPrefFile, Context.MODE_PRIVATE)
        val defaultUsername = "test"
        val defaultPassword = "123"
        val username = sharedPreferences.getString("username", defaultUsername)
        val password = sharedPreferences.getString("password", defaultPassword)

        Log.d("username", username)
        Log.d("password", password)

        loginButton.setOnClickListener {
            if (usernameField.text.isNullOrEmpty() || passwordField.text.isNullOrEmpty()) {
                 Toast.makeText(this@MainActivity, "Credentials should not be empty", Toast.LENGTH_SHORT).show()
            } else if (usernameField.text.toString() == username && passwordField.text.toString() == password) {
                val intent = Intent(this,LoggedScreen::class.java)
                intent.putExtra("username", username)
                startActivity(intent)
                finish()
            }
            else {
                Toast.makeText(this@MainActivity, "Wrong credentials", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
