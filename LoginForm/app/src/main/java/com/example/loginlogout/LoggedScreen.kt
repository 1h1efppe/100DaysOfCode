package com.example.loginlogout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class LoggedScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_screen)

        val logoutButton = findViewById<Button>(R.id.logoutButton)
        val usernameTextView = findViewById<TextView>(R.id.nameTextView)
        val bundle :Bundle? = intent.extras
        if (bundle!=null){
            val message = bundle.getString("username")
            usernameTextView.text = message

        }

        logoutButton.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
