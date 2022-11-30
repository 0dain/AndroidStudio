package com.example.ex20221131

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    var pokeList = ArrayList<PokeVO>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Adapter View
            //ListView
                //동적으로 나열 가능
                //안쪽에 사용하고 있는 Adapter에서 findViewById()가 굉장히 많이 호출된다
                    //안드로이드에서 메모리 리소스를 많이 잡아 먹는 함수 중에 하나이다

            //RecyclerView

        //AdapterView 만드는 6단계
        //1. Container 결정
        val lvPoke = findViewById<ListView>(R.id.lvPoke)

        //2. Template 결정
            //poke_list.xml

        //3. Item 결정
        //PokeVO Class 생성
        pokeList.add(PokeVO(R.drawable.p1, "피카츄", "전기"))
        pokeList.add(PokeVO(R.drawable.p2, "꼬부기", "물"))
        pokeList.add(PokeVO(R.drawable.p3, "파이리", "불"))
        pokeList.add(PokeVO(R.drawable.p4, "이상해씨", "풀"))
        pokeList.add(PokeVO(R.drawable.p5, "버터플", "벌레"))
        pokeList.add(PokeVO(R.drawable.p6, "구구", "비행"))
        pokeList.add(PokeVO(R.drawable.p1, "피카츄", "전기"))
        pokeList.add(PokeVO(R.drawable.p2, "꼬부기", "물"))
        pokeList.add(PokeVO(R.drawable.p3, "파이리", "불"))
        pokeList.add(PokeVO(R.drawable.p4, "이상해씨", "풀"))
        pokeList.add(PokeVO(R.drawable.p5, "버터플", "벌레"))
        pokeList.add(PokeVO(R.drawable.p6, "구구", "비행"))

        //4. Adapter결정
        //PokeAdapter 생성
        //(페이지정보, 템플릿, Id, data)
        val adapter = PokeAdapter(this@MainActivity, pokeList)

        //5. Container에 Adapter 부착
        lvPoke.adapter=adapter

        //6. Event 처리




    }

}