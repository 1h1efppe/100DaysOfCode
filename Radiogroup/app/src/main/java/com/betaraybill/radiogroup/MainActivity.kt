package com.betaraybill.radiogroup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textField =findViewById<TextView>(R.id.textView)
        fun message(value: String) : String = "You have selected $value RadioButton"

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            when (radio) {
                radioOne -> textField.text = message("first")
                radioTwo -> textField.text = message("second")
                radioThree -> textField.text = message("third")
                radioFour -> textField.text = message("fourth")
            }
        }
    }
}
