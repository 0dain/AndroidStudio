package com.example.question1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class Question2 : AppCompatActivity() {

    val imgArray = intArrayOf(R.drawable.ha2, R.drawable.ha3, R.drawable.ha1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question2)

        val imgHa = findViewById<ImageView>(R.id.imgHa)
        val btnPre = findViewById<Button>(R.id.btnPre)
        val btnNext = findViewById<Button>(R.id.btnNext)

        var index = 0
        imgHa.setImageResource(imgArray[index])

        btnPre.setOnClickListener {
            if (index > 0) {
                index--
            } else {
                index = imgArray.size - 1
            }
            imgHa.setImageResource(imgArray[index])
        }

        btnNext.setOnClickListener {
            if (index < imgArray.size - 1) {
                index++
            } else {
                index = 0
            }
            imgHa.setImageResource(imgArray[index])
        }
    }
}