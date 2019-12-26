package com.betaraybill.loginform

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView


class LoggedScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_screen)
        val email = findViewById<TextView>(R.id.emailTextView)
        val password = findViewById<TextView>(R.id.passwordTextView)
        email.text = "Email: ${intent.getStringExtra("email")}"
        password.text = "Password: ${intent.getStringExtra("password")}"
    }
}
