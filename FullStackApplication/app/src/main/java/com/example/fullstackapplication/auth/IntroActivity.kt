package com.example.fullstackapplication.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.fullstackapplication.R

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        val btnIntroLogin = findViewById<Button>(R.id.btnIntroLogin)
        val btnIntroJoin = findViewById<Button>(R.id.btnIntroJoin)
        val btnIntroNo = findViewById<Button>(R.id.btnIntroNo)

        //btnIntroLogin 누르면
        btnIntroLogin.setOnClickListener {
            //LoginActivity로 이동

            //Intent생성
            val intent = Intent(this@IntroActivity, LoginActivity::class.java)

            //Intent 실행
            startActivity(intent)

        }

        //btnIntroJoin 누르면
        btnIntroJoin.setOnClickListener {
            //JoinActivity로 이동

            val intent = Intent(this@IntroActivity, JoinActivity::class.java)

            startActivity(intent)
        }

        //btnIntroNo 누르면
        btnIntroNo.setOnClickListener {

        }


    }
}