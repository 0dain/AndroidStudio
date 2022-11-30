package com.example.directapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    lateinit var lv : ListView
    var directList = ArrayList<DirectVO>()
    lateinit var adapter : DirectAdapter//새로고침을 위해 만든 adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAdd = findViewById<Button>(R.id.btnAdd)
        lv = findViewById(R.id.lv)

        //1. btnAdd(추가)를 눌렀을 경우 AddActivity(Sub)이동
        btnAdd.setOnClickListener {
            //Intent생성
            val intent = Intent(this@MainActivity, AddActivity::class.java)

        //2. AddActivity에서 받아올 데이터가 있음(양방향 인텐트)
        //4. AddActivity에서 보낸 값(Intent 안에 들어있는)을 받아주자
            //런처 사용(콜백함수)
            launcher.launch(intent)
        }

        //3. AddActivity에 적음

        //5. ListView 만들기
            //5-1. ListView 위치 정해주기
            //4-2. ListView 한 칸에 들어갈 디자인(템플릿 -> xml)
            //4-3. AddActivity에서 받아온 결과값으로 ListView에 들어갈 데이터 만들기
                //(title, url --> 하나의 자료형으로(DirectVO)
            //4-4. Adapter 만들기
        val adapter = DirectAdapter(applicationContext, R.layout.url_list, directList)
            //4-5. Adapter ListView에 적용!

        lv.adapter = adapter


    }

    //AddActivity에서 받아온 데이터 꺼내주기
    val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        //it: resultCode, intent(title, url)

        if(it.resultCode == RESULT_OK){
            var title = it.data?.getStringExtra("title")
            var url = it.data?.getStringExtra("url")

            adapter.notifyDataSetChanged()//새로고침
            directList.add(DirectVO(title!!, url!!))

        }

    }






}