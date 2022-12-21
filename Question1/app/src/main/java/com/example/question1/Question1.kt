package com.example.question1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Question1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question1)

        val btn1 = findViewById<Button>(R.id.btn1)
        val btn2 = findViewById<Button>(R.id.btn2)
        val btn3 = findViewById<Button>(R.id.btn3)

        btn1.setOnClickListener {
            btn1.text = "1ST BUTTON!"
        }

        btn2.setOnClickListener {
            btn2.text = "2ND BUTTON!"
        }

        btn3.setOnClickListener {
            btn3.text = "3RD BUTTON!"
        }

    }
}