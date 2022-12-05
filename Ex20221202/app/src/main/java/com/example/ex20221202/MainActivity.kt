package com.example.ex20221202

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // View들의 Id값 찾아오기
        val btnLog = findViewById<Button>(R.id.btnLog)
        val bnv = findViewById<BottomNavigationView>(R.id.bnv)
        val fl = findViewById<FrameLayout>(R.id.fl)

        //Fragment 구현
        //bnv에서 선택한 메뉴에 따라 fl에 표시할 Fragment를 갈아 끼워준다

        supportFragmentManager.beginTransaction().replace(
            //매개변수 2개
            //1. fragment가 들어갈 위치
            //fl안에 들어갈 거임
            R.id.fl,
            //2. 내가 갈아끼울 Fragment 객채
            Fragment1()
        ).commit()//커밋 꼭 해주기!

        bnv.setOnItemSelectedListener {
            //item(it) -> 내가 선택한 메뉴의 정보
            Log.d("id", it.itemId.toString())

            when(it.itemId){
                R.id.tab1 -> {
                    Toast.makeText(this, "첫 번째 탭입니다", Toast.LENGTH_SHORT).show()

                    //beginTransaction(): 프래그먼트에 추가/변경/삭제
                    supportFragmentManager.beginTransaction().replace(
                        //매개변수 2개
                    //1. fragment가 들어갈 위치
                        //fl안에 들어갈 거임
                        R.id.fl,
                    //2. 내가 갈아끼울 Fragment 객채
                        Fragment1()
                    ).commit()//커밋 꼭 해주기!
                }
                R.id.tab2 -> {
                    Toast.makeText(applicationContext, "두 번째 탭입니다", Toast.LENGTH_SHORT).show()

                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl,
                        Fragment2()
                    ).commit()

                }
                R.id.tab3 -> {
                    Toast.makeText(this, "세 번째 탭입니다", Toast.LENGTH_SHORT).show()

                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl,
                        Fragment3()
                    ).commit()

                }
                R.id.tab4 -> {
                    Toast.makeText(this, "네 번째 탭입니다", Toast.LENGTH_SHORT).show()

                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl,
                        Fragment4()
                    ).commit()
                }
            }//when 끝



            //리턴 값 false -> 이벤트가 끝나지 않았다고 판단하여 다른 탭으로 이동이 되지 않음(클릭 안 됨)
            //true를 통해서 이벤트 종료를 감지해줘야 함
            true
        }


    }
}