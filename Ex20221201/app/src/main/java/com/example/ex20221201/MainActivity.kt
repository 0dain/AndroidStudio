package com.example.ex20221201

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {

    lateinit var queue: RequestQueue
    lateinit var request: StringRequest//받아오는 응답이 문자열


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Volley: 서버 통신을 위한 라이브러리
            //Request: 내가 하고 싶은 요청이 담기는 곳 / 응답이 들어 오는 곳
            //RequestQueue: Request에 담긴 요청을 가지고 서버로 이동하는 객체


        val btnReq = findViewById<Button>(R.id.btnReq)
        val etUrl = findViewById<EditText>(R.id.etUrl)
        val tvResult = findViewById<TextView>(R.id.tvResult)
        //request에 요청 등록 -> 요청을 들고 queue에 적용 -> queue가 들고 서버로 이동 -> 응답을 가지고 queue가 돌아옴 -> 그 응답을 request한테 전달 -> 처리(request를 가지고)

        //btnReq를 클릭했을 때
            //queue 초기화 진행
        queue = Volley.newRequestQueue(this@MainActivity)//현재 페이지에 대한 정보를 소괄호 안에 적음
        btnReq.setOnClickListener {
            // etUrl에 적힌 값을 가져와서 request를 생성
            val url = etUrl.text.toString()

            //해당 request를 queue에 적용

            //실제 요청을 할 수 있는 객체: Request 만들기
            //StringRequest의 4가지 매개변수
                //1. 데이터 전송 방식(GET/POST)
                //2. 요청할 서버 주소(URL)
                //3. 응답 성공했을 경우 실행시킬 코드
                //4. 응답 실패했을 경우 실행시킬 코드
                //3, 4는 Listener임
            request = StringRequest(
                //데이터 전송 방식
                Request.Method.GET,
                //요청할 서버 주소
                url,
                //응답에 성공했을 때 어떻게 할 건지 Listener
                {response ->
                    Log.d("res", response.toString())
                    tvResult.text = response
                },
                //응답에 오류가 발생할 때 어떻게 할 건지 Listener
                {error ->
                    Log.d("err", error.toString())
                    Toast.makeText(this, "에러발생!💥", Toast.LENGTH_SHORT).show()
                }
            //RequestQueue가 받아온 응답은 response랑 error 매개변수를 통해 확인 가능
            )

            //여러 번 요청을 하게 될 때 캐시가 누적된다
            request.setShouldCache(false)//캐시 저장소를 비워주는 기능

            queue.add(request)

        }

    }
}