package com.example.ex20221129

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import java.util.Random

class LunchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lunch)

        //Adapter View 만드는 6단계

        //1. Container 결정
        //위치 결정
        val lvLunch = findViewById<ListView>(R.id.lvLunch)
        val etAdd = findViewById<EditText>(R.id.etAdd)
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnResult = findViewById<Button>(R.id.btnResult)
        val tvLunch = findViewById<TextView>(R.id.tvLunch)
        //viewBinding 기법을 통해서 초기화 해결!

        //버튼 내용과 사이즈 추가
        btnAdd.text = "추가"
        btnResult.text = "뽑기"
//        btnAdd.setTextSize(18f)

        //2. Template 결정
        //아이템 디자인
        //lunch_list.xml

        //3. Item 결정
        //만약, 자료형이 여러 개라면 VO Class 생성!
        val data = ArrayList<String>()
        data.add("돈까스")
        data.add("제육볶음")
        data.add("초밥")

        //4. Adapter 결정
        //만약, TextView와 ArrayList<String>을 사용한다면 ArrayAdapter 사용 가능
        //1) 페이지 정보
        //2) 항목 뷰 디자인
        //3) TextView ID
        //4) data
        val adapter = ArrayAdapter<String>(this, R.layout.lunch_list, R.id.tvMenu, data)

        //5. Container에 만든 Adapter를 부착
        lvLunch.adapter = adapter

        //6. Event 처리
        //1) btnAdd를 눌렀을 때 etAdd에 값을 가져와서
        //2) data에 추가!
        //3) adapter 새로고침
        btnAdd.setOnClickListener {
            var input = etAdd.text.toString()
            data.add(input)
            adapter.notifyDataSetChanged()//새로고침
            etAdd.text = null//입력 후 내용 초기화
        }

        btnResult.setOnClickListener {
            val rd = Random()
            val menu = rd.nextInt(data.size)

            tvLunch.text = data.get(menu)

        }

    }
}