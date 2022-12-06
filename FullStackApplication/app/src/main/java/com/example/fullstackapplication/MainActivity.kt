package com.example.fullstackapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import com.example.fullstackapplication.auth.IntroActivity
import com.example.fullstackapplication.fragment.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bnv = findViewById<BottomNavigationView>(R.id.bnv)
        val fl = findViewById<FrameLayout>(R.id.fl)
        val imgLogout = findViewById<ImageView>(R.id.imgLogout)//임시 로그아웃

        //auth의 로그아웃 기능 사용을 위해
        auth = Firebase.auth

        //Fragment 구현!
        //기본 화면
        supportFragmentManager.beginTransaction().replace(
            //매개변수 2개
            //1. Fragment가 들어갈 위치
            R.id.fl,
            //2. 내가 갈아끼울 Fragment 객체
            Fragment1()
        ).commit()

        //하단 메뉴바 클릭 시 화면 바뀌기
        bnv.setOnItemSelectedListener {
            //it: 내가 클릭한 item 정보
            when(it.itemId){

                R.id.tab1 ->{
                    //Fragment1 부분으로 fl을 갈아끼워준다
                    //beginTransaction(): 프레그먼트 삭제, 수정에 관한 메서드
                    //replace(): 대체 하는 내용
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl,
                        Fragment1()
                    ).commit()
                }

                R.id.tab2 ->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl,
                        Fragment2()
                    ).commit()

                }

                R.id.tab3 ->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl,
                        Fragment3()
                    ).commit()
                }

                R.id.tab4 ->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl,
                        Fragment4()
                    ).commit()
                }

                R.id.tab5 ->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl,
                        Fragment5()
                    ).commit()

                }
            }//when 닫힘

            true

        }//bnv 닫힘

        //로그아웃
        imgLogout.setOnClickListener {
            auth.signOut()//현재 유저의 값을 지워줌
            //로그아웃 후 IntroActivity로 돌아가자!
            val intent = Intent(this@MainActivity, IntroActivity::class.java)
                //이전에 쌓여있는 Activity를 모두 지우자!
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        //auth에 담겨있는 기능
            //createUsersWithEmailandPassword: 회원가입(email, pw)
            //singInWithEmailandPassword: 로그인(email, pw)
            //singInAnonymous: 익명로그인()
            //singOut(): 로그아웃(페이지 이동 기능 X)


    }
}