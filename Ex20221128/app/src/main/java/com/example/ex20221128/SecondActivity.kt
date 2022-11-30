package com.example.ex20221128

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val btnPre = findViewById<Button>(R.id.btnPre)
        val cl=findViewById<ConstraintLayout>(R.id.cl)
        //제너릭<>: 타입을 강하게 체크하는 기능
//        val list1= arrayListOf("1", "2", true, 3.5) => 가능
//        val list1= arrayListOf<String>("1", "2", true, 3.5) => 불가능

        //1. intent 안에 color 키 값을 통해 색깔을 꺼내자!
        val color:String=intent.getStringExtra("color")!!
//        val color:String?=intent.getStringExtra("color")
//        val color=intent.getStringExtra("color")
        Log.d("넘어오는색상", color)

        //Java
        //문자열 -> 정수로 바꿀 때
        //Integer.parseInt()

        //문자열 -> Color 바꿀 때
        //Color.parseColor()

        cl.setBackgroundColor(Color.parseColor(color))

        //2. 배경 색상을 color로 변경하자!



        //btnPre를 눌렀을 때 이전 페이지(FirstActivity)로 돌아간다
        btnPre.setOnClickListener {
//            var intent = Intent(this, FirstActivity::class.java)
//            startActivity(intent)
            finish()//stack 구조에서 나가기!
        }

    }




}