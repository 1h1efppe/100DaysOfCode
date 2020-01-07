package com.example.loginlogout

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.os.postDelayed

class SplashActivity : AppCompatActivity() {

    var handler = Handler()
    val sharedPrefFile = "login"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor =  sharedPreferences.edit()

        fun setCourseToSharedPref() {
            editor.putString("username", "betaraybill")
            editor.putString("password", "123")
            editor.apply()
            editor.commit()
        }

        setCourseToSharedPref()

        handler.postDelayed(500) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onDestroy() {
        handler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }
}
