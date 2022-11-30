package com.example.ex20221124

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //1. view들의 id값 찾아오기
        //findViewById
        val etEmail=findViewById<TextInputEditText>(R.id.etEmail)
        val etPw=findViewById<TextInputEditText>(R.id.etPw)
        val btnLogin=findViewById<Button>(R.id.btnLogin)

        //2. 버튼에 이벤트 달아주기(setOnClickListener)
        //2-1. EditText에 적혀있는 email, password 값을 가져오기
        //email, pw에 저장(.toString)
        //2-2. 가져온 email, pw가 smhrd@smhrd.or.kr / qwer1234가 맞는지 판단(조건식)
        //맞다면 Toast로 "로그인 성공"
        //틀리면 "로그인 실패" 띄우기
        btnLogin.setOnClickListener {
            var email=etEmail.text.toString()
            var pw=etPw.text.toString()

            if(email=="smhrd@smhrd.or.kr"&&pw=="qwer123"){
                Toast.makeText(this, "로그인 성공🎉", Toast.LENGTH_SHORT).show()
            } else{
                Toast.makeText(this, "로그인 실패😥", Toast.LENGTH_SHORT).show()
            }

        }

    }
}