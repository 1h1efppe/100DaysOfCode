package com.example.androidimageview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val image = findViewById<ImageView>(R.id.imageView)
        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            if (image.visibility == View.INVISIBLE) {
                image.visibility = View.VISIBLE
            } else {
                image.visibility = View.INVISIBLE
            }
        }
    }
}
