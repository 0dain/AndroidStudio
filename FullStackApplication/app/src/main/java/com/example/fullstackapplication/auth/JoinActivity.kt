package com.example.fullstackapplication.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.fullstackapplication.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class JoinActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        val btnJoinJoin =  findViewById<Button>(R.id.btnJoinJoin)
        val etJoinEmail = findViewById<EditText>(R.id.etJoinEmail)
        val etJoinPw =  findViewById<EditText>(R.id.etJoinPw)
        val etJoinCheck =  findViewById<EditText>(R.id.etJoinCheck)

        //auth초기화
        auth = Firebase.auth
        //Firebase.auth: 로그인, 회원가입, 인증 시스템에 대한 모든 기능이 담겨있음

        //btnJoinJoin 눌렀을 때
        btnJoinJoin.setOnClickListener {

            val email = etJoinEmail.text.toString()
            val pw = etJoinPw.text.toString()
            val pwCheck = etJoinCheck.text.toString()

            //create가 보내고 있는 전달 인자 2개(email, pw)는 실제로 회원가입 정보를 전달(Firebase)
            auth.createUserWithEmailAndPassword("ekdls@daum.net", "12345678")
                .addOnCompleteListener(this) { task ->
                    //task: 성공, 실패 여부를 가지고 있음
                    //task가 보낸 결과
                    if (task.isSuccessful) {
                        //성공했을 때 실행시킬 코드
                        Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()
                    } else {
                        //실패했을 때 실행시킬 코드
                        Toast.makeText(this, "회원가입 실패", Toast.LENGTH_SHORT).show()

                    }
                }

//            if(pw == pwCheck){
//                Toast.makeText(this, "$email, $pw", Toast.LENGTH_SHORT).show()
//            }else{
//                Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
//            }


        }



    }
}