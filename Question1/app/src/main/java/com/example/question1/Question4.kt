package com.example.question1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.random.Random

class Question4 : AppCompatActivity() {

    lateinit var etTime: EditText
    lateinit var btnStart: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question4)

        etTime = findViewById(R.id.etTime)
        btnStart = findViewById(R.id.btnStart)

        btnStart.setOnClickListener {
            val thread = Timer(etTime)
            thread.start()
        }

    }

    val handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val time = msg.arg1
            val tv = msg.obj as EditText

            tv.setText(time.toString())

        }
    }

    inner class Timer(var time: EditText) : Thread() {

        override fun run() {
            super.run()

            //10 -> 0(1초마다 1씩 감소)
            for (i in time.text.toString().toInt() downTo 0) {
                Log.d("타이머", i.toString())
                val message = Message()
                message.arg1 = i
                message.obj = time

                handler.sendMessage(message)
                //sleep(밀리초): 코드를 지연시킴
//                Thread.sleep(1000)//1초 만큼 코드를 지연시킴
                val rdValue = Random.nextInt(1000)

                Thread.sleep(rdValue.toLong())
            }


        }

    }
}