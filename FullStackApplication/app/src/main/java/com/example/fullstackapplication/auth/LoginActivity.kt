package com.example.fullstackapplication.auth

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.fullstackapplication.MainActivity
import com.example.fullstackapplication.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    //FirebaseAuth 선언
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //자동 로그인을 위한 sharedPreferences
        val sharedPreferences = getSharedPreferences("autoLogin", Context.MODE_PRIVATE)
        //로그인 정보 가져오기
        //정보 있으면 키 값을 가져오고 없으면 빈 값을 가져옴
        val loginId = sharedPreferences.getString("loginId", "")
        val loginPw = sharedPreferences.getString("loginPw", "")

        //FirebaseAuth 초기화
        auth = Firebase.auth

        val btnLoginLogin = findViewById<Button>(R.id.btnLoginLogin)
        val etLoginEmail = findViewById<EditText>(R.id.etLoginEmail)
        val etLoginPw = findViewById<EditText>(R.id.etLoginPw)

        //자동 로그인 관련
        etLoginEmail.setText(loginId)
        etLoginPw.setText(loginPw)

        //btnLoginLogin 누르면
        btnLoginLogin.setOnClickListener {

            val email = etLoginEmail.text.toString()
            val pw = etLoginPw.text.toString()

            //auth 기능을 활용하여 로그인 하기: signInWithEmailAndPassword
            auth.signInWithEmailAndPassword(email, pw).addOnCompleteListener(this){ task ->
                if(task.isSuccessful){
                    //로그인 성공
                    Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()

                    //자동로그인
                    //editor: 데이터를 삽입, 수정, 삭제 할 때 필요
                    val editor = sharedPreferences.edit()
                    editor.putString("loginId", email)
                    editor.putString("loginPw", pw)
                    editor.commit()//꼭 저장해주기!


                    //로그인 성공했으면 MainActivity로 이동
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()//메인에서 뒤로가기 누르면 어플 종료되게 함
                }else{
                    //로그인 실패
                    Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
                }
            }

//            Toast.makeText(this, "$email, $pw", Toast.LENGTH_SHORT).show()

        }



    }
}