package com.betaraybill.checkboxapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var checkOne = false
    var checkTwo = false
    var checkThree = false
    var checkFour = false


    var message = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkBoxOne.setOnClickListener {
            message = ""
            checkOne = checkBoxOne.isChecked


        }

        checkBoxTwo.setOnClickListener {
            message = ""
            checkTwo = checkBoxTwo.isChecked

        }
        checkBoxThree.setOnClickListener {
            message = ""
            checkThree = checkBoxThree.isChecked

        }
        checkBoxFour.setOnClickListener {
            message = ""
            checkFour = checkBoxFour.isChecked

        }


        val resultTextField = findViewById<TextView>(R.id.result)

        button.setOnClickListener {
            message = "${if (checkOne) {checkBoxOne.text } else {""}}" +
                    (if (checkTwo) { " and " + checkBoxTwo.text} else {""}) +
                    (if (checkThree) {" and " + checkBoxThree.text} else {""}) +
                    (if (checkFour) {" and " + checkBoxFour.text} else {""})

            if (message == "") {
                message = "Nothing is checked"
            }
            else {
                if (message.first() == ' ') {
                    message = message.substring(5)
                }
                message += " is checked"
            }

            resultTextField.text = message
        }
    }
}
