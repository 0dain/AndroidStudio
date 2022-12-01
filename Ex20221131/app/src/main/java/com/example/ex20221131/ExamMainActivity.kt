package com.example.ex20221131

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout

class ExamMainActivity : AppCompatActivity() {

    lateinit var clExam: ConstraintLayout
    override fun onRestart() {
        super.onRestart()
        val sharedPreferences = getSharedPreferences("sp1", Context.MODE_PRIVATE)
        val color = sharedPreferences.getString("bgColor", "white")//bgColor에 값이 없으면 화이트 넣어라!
        clExam.setBackgroundColor(Color.parseColor(color))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exam_main)

        //로그인 기능
        //자동 로그인
        //Application을 종료해도 정보가 저장될 필요성이 있음!
            //DB(DataBase)
                //RDB(Relational DataBase) - 관계형 데이터 베이스
                    //RDBMS
                        //OracleDB
                        //MySQL
                        //MariaDB
                //SQL: 쿼리문 작성
                //NoSQL: Key, Value)

                //SQLite -> 실제 데이터 베이스를 안드로이드에 내장시키는 API
                //SharedPreference -> 환경설정 정보들을 공유하기 위해 사용
                    // 버튼 -> 그리드 -> 리니어 -> 그리드
                    //NoSQL형태 -> Key, Value

                //FireBase
                //카톡 채팅 만들기 나중에 해 볼 예정

        clExam = findViewById(R.id.clExam)
        val btnRed = findViewById<Button>(R.id.btnRed)
        val btnPink = findViewById<Button>(R.id.btnPink)
        val btnBlack = findViewById<Button>(R.id.btnBlack)
        val btnOther = findViewById<Button>(R.id.btnOther)

        //tvResul: 변수, 소문자로 시작
        //PI = 3.141592 : 상수!, 전부 다 대문자
        //Context: 클래스, 첫 문자만 대문자

        //Context.MODE_PRIVATE: Context안에 있는 MODE_PRIVATE 이라는 뜻
        //MODE_PRIVATE: 생성한 application 내에서만 공유 가능
        //MODE_WORLD_READABLE: 다른 application에서 읽을 수 있음
        //MODE_WORLD_WRITEABLE: 다른 application에서 읽고 쓸 수 있음

        //색상 정보
        val sharedPreferences = getSharedPreferences("sp1", Context.MODE_PRIVATE)

        //정보 가져오기
        val bgColor:String? = sharedPreferences.getString("bgColor", "white")

        //세팅된 배경 색 가져오기
        clExam.setBackgroundColor(Color.parseColor(bgColor))

        btnRed.setOnClickListener {

            //editor를 통해서 정보를 저장, 삭제, 수정 가능
            val editor = sharedPreferences.edit()
            val color: String = "#FF0000"

//            editor.putString("bgColor", "red")
            editor.putString("bgColor", color)
            editor.commit()//꼭 해줘야 함!

            clExam.setBackgroundColor(Color.parseColor(color))

        }

        btnPink.setOnClickListener {

            val editor = sharedPreferences.edit()
            val color: String = "#E91E63"

            editor.putString("bgColor", color)
            editor.commit()

            clExam.setBackgroundColor(Color.parseColor(color))

        }

        btnBlack.setOnClickListener {

            val editor = sharedPreferences.edit()
            val color: String = "#000000"

            editor.putString("bgColor", color)
            editor.commit()

            clExam.setBackgroundColor(Color.parseColor(color))

        }

        btnOther.setOnClickListener {

            //ColorActivity랑 연결

            //인텐트 생성
            val intent = Intent(this@ExamMainActivity, ColorActivity::class.java)

            //실행
            startActivity(intent)

        }

    }

}