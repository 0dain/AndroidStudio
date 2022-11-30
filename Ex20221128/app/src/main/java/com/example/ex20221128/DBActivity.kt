package com.example.ex20221128

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class DBActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dbactivity)

        val tvResult=findViewById<TextView>(R.id.tvResult)

        //넘어오는 값 확인하기
        val loginId:String=intent.getStringExtra("loginId")!!
        val loginPw:String=intent.getStringExtra("loginPw")!!

        Log.d("로그인정보", loginId+"/"+loginPw)

        //아이디: dain
        //비밀번호: 1234

        if(loginId=="dain"&&loginPw=="1234"){
            tvResult.text="로그인 성공"
        }else{
            tvResult.text="로그인 실패"
        }


    }
}