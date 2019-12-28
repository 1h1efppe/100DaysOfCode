package com.betaraybill.seekbar

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView

class MainActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {

    private var secretText: TextView? = null
    private var seekbar: SeekBar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.secretText = findViewById(R.id.textView)
        this.seekbar = findViewById(R.id.seekBar)
        seekbar!!.setOnSeekBarChangeListener(this)
    }

    @SuppressLint("SetTextI18n")
    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        if (p1 in 40..45) {
            secretText!!.text = "Seekbar position is $p1"
        }
        else {
            secretText!!.text = ""
        }
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {
    }

    override fun onStopTrackingTouch(p0: SeekBar?) {
    }
}
