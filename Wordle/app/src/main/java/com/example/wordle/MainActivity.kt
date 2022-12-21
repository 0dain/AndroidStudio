package com.example.wordle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //정답 단어
        val answer = "apple"


        //RecyclerView 사용 6단계
        //1. Container 결정
        val rvGame = findViewById<RecyclerView>(R.id.rvGame)


        //2. Template 결정
        //game_list.xml

        //3. Item 결정
        //GameVO
        val gameList = ArrayList<GameVO>()
        //임시로 데이터 넣기
//        gameList.add(GameVO("h", "a", "p", "p", "y"))
//        gameList.add(GameVO("s", "m", "i", "l", "e"))
//        gameList.add(GameVO("l", "u", "n", "c", "h"))

        //빈칸 6개
        gameList.add(GameVO("", "", "", "", ""))
        gameList.add(GameVO("", "", "", "", ""))
        gameList.add(GameVO("", "", "", "", ""))
        gameList.add(GameVO("", "", "", "", ""))
        gameList.add(GameVO("", "", "", "", ""))
        gameList.add(GameVO("", "", "", "", ""))

        //4. Adapter 결정
        //GameAdapter
        //이벤트를 처리하는 곳에서 정답을 알고 있어야 뭔가 처리를 할 수 있기 때문에 answer를 같이 넘긴다
        val adapter = GameAdapter(this@MainActivity, gameList, answer)

        //5. Container에 Adapter 부착
        rvGame.adapter = adapter

        //5-1. 레이아웃 매니저 설정
        rvGame.layoutManager = LinearLayoutManager(this)


    }



}