package com.example.ex20221201

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MovieActivity : AppCompatActivity() {

    //Volley에 필요한 객체 두 개
    var queue: RequestQueue? = null//요청을 가지고 서버로 이동
    lateinit var request: StringRequest//요청/응답이 들어가는 객체

    var movies = ArrayList<MovieVO>()//영화 정보들이 담길 배열

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        //Volley를 통한 네트워크 통신 4단계
        //1. Volley 설정
        //1-1.Volley 라이브러리 추가(지금 사용하는 버전 - implementation 'com.android.volley:volley:1.2.1')
        //1-2. Manifest에 Permission 추가! -> Internet
        //2. RequestQueue 생성
            //위에 전역변수로 선언함
        //3. Request 생성
            //버튼을 눌렀을 때 생성!
        //4. RequestQueue에 Request 추가
            //아래 쪽에서 queue?.add(request) 써주면 됨~


        //1) Container 결정
        val rc = findViewById<RecyclerView>(R.id.rc)
        val btnMovie = findViewById<Button>(R.id.btnMovie)
        val etInput = findViewById<EditText>(R.id.etInput)//원하는 날짜로 조회하기 위해

        //2) 템플릿
            //movie_list

        //3)아이템
            //movies: ArrayList<MovieVo>, 위에 전역변수로 선언됨

        //4) Adapter
            //MovieAdapter
        val adapter = MovieAdapter(this, movies)

        //5) Container에 Adapter 부착
        rc.adapter = adapter

        //리사이클러뷰 한정 레이아웃 붙여줘야 함!
        rc.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


        //버튼을 클릭하거나 가상핸드폰(에뮬레이터)을 작동, OnCreate가 실행될 때마다 request가 들어갈 장소를 만들어줌
        if(queue==null) queue = Volley.newRequestQueue(this@MovieActivity)

        //btnMovie를 클릭했을 때 영화 정보를(response) Log로 확인해보자!
        btnMovie.setOnClickListener {
            movies.clear()//누적된 정보 삭제하기 위해
            val date = etInput.text.toString()//원하는 날짜 입력한 값 가져오기
            val url = "https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=f5eef3421c602c6cb7ea224104795888&targetDt=$date" //원하는 날짜로 입력한 date 추가

            //StringRequest는 4개의 매개변수를 가짐
//            request = StringRequest(
//                1,
//                2,
//                3,
//                4)
            //이렇게 적으면 자동태그로 각각의 요소에 뭐가 들어가는지 알려주기 때문에 헷갈리면 이렇게 해라~

            request = StringRequest(
                //1. get/post
                Request.Method.GET,
                //2. URL
                url,
                //3. 응답 성공 시 Listener
                {response ->
                    //응답 받아온 response : String
                    //실제 데이터 타입은 JSONObject
                        //파싱해야 함
                    val json1 = JSONObject(response)
                    val json2 = json1.getJSONObject("boxOfficeResult")
                    val json3 = json2.getJSONArray("dailyBoxOfficeList")
                    Log.d("json2", json2.toString())//jsonobject타입이기 때문에 toString해야 함
                    Log.d("json3", json3.toString())//jsonobject타입이기 때문에 toString해야 함

//                    val movie = json3.getJSONObject(0)
//                    val rank = movie.getString("rank")
//                    Log.d("rank", rank)

                    //JsonArray에 순차적으로 접근해서 영화정보 꺼내오기
                        //until: 오른쪽 등호 없애기
                    for (i in 0 until json3.length()){
                        val movie = json3.getJSONObject(i)
                        Log.d("for", "돌아가는중")
                        //rank
                        var rank = movie.getString("rank")
                        //rankOldAndNew
                        var rankOldAndNew = movie.getString("rankOldAndNew")
                        //movieNm
                        var movieNm = movie.getString("movieNm")
                        Log.d("영화", movieNm)
                        //openDt
                        var openDt = movie.getString("openDt")
                        //audiAcc
                        var audiAcc =movie.getString("audiAcc")

                        //하나의 자료형 MovieVO
                        //MovieVO를 ArrayList에 저장
                        movies.add(MovieVO(rank, rankOldAndNew, movieNm, audiAcc, openDt))

                    }
                    adapter.notifyDataSetChanged()
                },
                //4. 응답 실패 시 Listener
                {error ->

                }

            )//request 생성

            queue?.add(request)//queue한테 request 추가!

        }


    }
}