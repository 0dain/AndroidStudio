package com.example.ex20221128

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.Button
import android.widget.ListView

class FirstActivity : AppCompatActivity() {

    var color:String="white" //배경 색상을 저장하여 Second에 보낼 변수!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        val btnNext=findViewById<Button>(R.id.btnNext)
        val lv=findViewById<ListView>(R.id.lv)

        //Android 4대 구성요소
        //Activity => 화면 구성
        //Service => Background에서 동작 => Activity에서 화면만 뺀 거
        //BR(BroadCast Receiver)
        //CP(Content Provider)
            //카카오톡에서 다른 사람에게 연락처를 전송하고자 할 때
            //연락처 어플리케이션에서 연락처 정보를 카톡으로 넘김
        //4대 구성요소 간 정보를 매개 => Intent

        //명시적, 묵시적
        //explicit, implicit


        //버튼 클릭을 감지하는 리스너를 장착
        //setOnClickListener => 이거는 전체를 감지
        //우리는 클릭하는 아이템을 구분해야 하기 때문에 저걸 쓰면 안 됨


        //btnNext를 누르면 SecondActivity로 color 코드를 가지고 넘어간다
        btnNext.setOnClickListener {
            var intent =Intent(this@FirstActivity, SecondActivity::class.java)

            intent.putExtra("color", color)

            //단방향 호출
            startActivity(intent) //stack에 Activity를 넣는 거임
        }

        lv.setOnItemClickListener(object : OnItemClickListener{
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                //AdapterView =>ListView
                //p2: position

                //p0가 null인지 아닌지 체크를 해야 함
                if(p0!=null){
                    color=p0.adapter.getItem(p2).toString()
                }

//                color=p0!!.adapter.getItem(p2).toString()


//                if(p2==0){
//                    color="purple"
//                }else if(p2==1){
//                    color="yellow"
//                }else if(p2==2){
//                    color="pink"
//                }

//                Log.d("찍어보자", p2.toString())
                Log.d("찍어보자", color)
            }
        })




        //inner class
        //익명 클래스 anonymous class
//        btnNext.setOnClickListener(object : View.OnClickListener{
//            override fun onClick(v: View?) {
//
//            }
//        })
//
//        btnNext.setOnClickListener { v ->
//
//        }

//        val btnNext=findViewById<Button>(R.id.btnNext)
//
//        //btnNext를 클릭했을 때, SecondActivity로 이동!
//        btnNext.setOnClickListener {
//            //Activivy로 이동하는 Intent 생성(명시적 인텐드)
//            // (시작 Activity, 도착 Activity) => (this, java class)
//                //도착은 현재 Kclass로 만들어진 Activity를 java class로 바꿔주기
//                    //액티비티명::class.java
//            var intent = Intent(this, SecondActivity::class.java)
//            //실행!
//            startActivity(intent)
//        }

    }

    //오버라이딩 단축키 Ctrl+O 혹은 우클릭 -> generate -> override methods
//    override fun onStart() {
//        super.onStart()
//        Log.d("생명주기", "onStart입니다")
//        //d => 디버그
//    }
//
//    override fun onStop() {
//        super.onStop()
//        Log.d("생명주기", "onStop입니다")
//    }
//
//    override fun onResume() {
//        super.onResume()
//        Log.d("생명주기", "onResume입니다")
//    }
//
//    override fun onPause() {
//        super.onPause()
//        Log.d("생명주기", "onPause입니다")
//    }
//
//    override fun onRestart() {
//        super.onRestart()
//        Log.d("생명주기", "onRestart입니다")
//    }



}