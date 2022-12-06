package com.example.fullstackapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.fullstackapplication.auth.IntroActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //OnCreate가 실행되면 3초 뒤에 IntroActivity로 넘어가는 코드 작성
        //Thread, Intent 필요
        //프로그램에는 MainTread는 무조건 한 개
        //내가 만든 작업처리는 SubThread
        //SubThread가 동작하고 있는 MainThread에 끼어들려면
        //Handler가 필요함(메세지 객체, Thread를 실행시킬 수 있음)

        //Handler를 가져와주자
//        Handler().postDelayed({실행시킬 코드}, 딜레이 시킬 시간)
        Handler().postDelayed({
            //Intent 생성
            val intent = Intent(this@SplashActivity, IntroActivity::class.java)
            //Intent 실행
            startActivity(intent)
        }, 3000)




   }
}