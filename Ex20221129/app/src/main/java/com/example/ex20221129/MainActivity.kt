package com.example.ex20221129

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //View id값 찾아오기
        tvResult = findViewById<TextView>(R.id.tvResult)
        val btnNext = findViewById<Button>(R.id.btnNext)

        //btnNext를 눌렀을 때
        btnNext.setOnClickListener {
            //Intent 생성
            val intent = Intent(this@MainActivity, SubActivity::class.java)

            //Intent 실행(Sub로 이동 및 요청(받아오는 결과값이 있음))
//            startActivityForResult(intent, 1004)
            //requestCode : 결과값을 보내 줘야 하는 Activity를 구분하기 위해서

            //startActivityForResult() -> 사라질 코드이기 때문에 다른 방법으로 구현할 거임
            launcher.launch(intent)//밑에서 만든 launcher 불러오는 중임
            //실행하려면 launch() 키워드가 붙어줘야함
                //전달인자로 내가 생성한 intent 넣어주기!

        }


    }//onCreate 밖

    //onActivityResult() -> 곧 사라질 코드
    //Intent를 통해서 받아온 결과값을 처리할 수 있도록 도와주는 함수
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        //1)repuestCode : (1004) 내가 보낸 요청코드를 받아오는 매개변수
//        //2) resultCode : (RESULT_OK) 받아온 결과값의 상태
//        //3) Intent? data : str(문구)가 붙어있는 Intent를 받아오는 매개변수
//
//        //내가 보낸 요청이 맞는지 확인(요청코드가 1004가 맞는지)
//        if (requestCode == 1004) {
//            //resultCode도 내가 원하는 결과값이 맞는지(RESULT_OK가 맞는지)
//            if (resultCode == RESULT_OK) {
//                //두 가지 다 만족하면 받아온 결과값을 처리
//                //tvResult의 text를 받아온 str로 바꾸자!
//                //1. str 꺼내기
//                val str = data?.getStringExtra("content")
//
//                //2. TextView 내용을 str로 바꾸기
//                tvResult.text=str
//            }
//        }
//    }

    //곧 사라질 코드를 대신해서 쓸 수 있는 코드!
    //callback 함수
    //1. 다른 함수의 인자로 사용되는 함수
    //2. 어떤 이벤트에 의해 호출되어 지는 함수(ex. 버튼 클릭 시 Intent 실행시키면서 호출)

    //ActivityResultLauncher 사용
        //Activity에서 데이터를 받아오기 위해 사용하는 함수
        //Fragment(부분화면)에서도 데이터를 주고받을 때 사용할 수 있음
    //기존에 ActivityForResult는 메모리가 너무 작음! -> 프로세스, 액티비티가 소멸될 수 있음
    //런처는 메모리부족으로 소멸되었다가 재생성해도 결과값을 받아올 수 있음

    //매개변수 2가지 : Contract 자료형, 콜백메서드
    val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        //실제로 ActivityResultContracts 를 타고 들어가면
        //1) createIntent --> StartActivityForResult 대체
        //2) parseResult --> onActivityResult 대체 (resultCode랑 Intent만 받아옴)
        //런처를 사용하게 되면 요청을 보낸 Activity를 구분할 필요 없음
            //그래서 requestCode가 필요 없음

        //it: ActivityResult 가 resultCode랑 Intent를 매개변수로 받아옴(SubActivity에서)
        //it에서 resultCode랑 data를 추출
//        Log.d("data", it.toString())
        Log.d("data", it.data.toString())//Intent{(has extras)}
        Log.d("data",it.resultCode.toString())//RESULT_OK

        //ResultCode가 RESULT_OK 인지 확인
        if(it.resultCode== RESULT_OK){
            tvResult.text=it.data?.getStringExtra("content")
            //it.data ---> Intent? 의 리턴값을 가지고 있음
        }

    }



}