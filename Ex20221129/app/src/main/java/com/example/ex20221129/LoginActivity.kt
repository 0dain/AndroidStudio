package com.example.ex20221129

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etId = findViewById<EditText>(R.id.etId)
        val etPw = findViewById<EditText>(R.id.etPw)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        //setResult(resultCode, intent)
        btnLogin.setOnClickListener {
            var id = etId.text.toString()
            var pw = etPw.text.toString()

            val intent = Intent()

            intent.putExtra("id", id)
            intent.putExtra("pw", pw)

            if (id == "ekdls" && pw == "1234") {
                intent.putExtra("content", "로그인 성공")
                setResult(RESULT_OK, intent)
            } else {
                intent.putExtra("content", "로그인 실패")
                setResult(RESULT_CANCELED, intent)
            }

            finish()

        }


    }
}