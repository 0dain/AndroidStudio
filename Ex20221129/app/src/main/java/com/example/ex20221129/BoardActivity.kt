package com.example.ex20221129

import android.content.DialogInterface
import android.content.DialogInterface.OnClickListener
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog

class BoardActivity : AppCompatActivity() {

    // 초기화는 나중에 할게~ : lateinit
    //전역 view로 사용 가능
    lateinit var tvContent: TextView
    lateinit var btnWrite: Button
    lateinit var btnBoard: Button//나예호

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board)

        tvContent = findViewById(R.id.tvContent)
        btnWrite = findViewById(R.id.btnWrite)
        btnBoard = findViewById(R.id.btnBoard)
        btnBoard.isEnabled = false//나예호
        val btnLogin1 = findViewById<Button>(R.id.btnLogin1)
        val lv = findViewById<ListView>(R.id.lv)
        val etBoard = findViewById<EditText>(R.id.etBoard)

        //로그인 버튼을 누르면 LoginActivity로 이동(양방향임)
        btnLogin1.setOnClickListener {
            val intent = Intent(this@BoardActivity, LoginActivity::class.java)
            launcher.launch(intent)//양방향 통신 .launch()
        }


        //람다식
        lv.setOnItemClickListener { adapterView, view, i, l ->

            //adapterView: ListView에 대한 정보
            //view: ListView가 있는 현재 페이지에 대한 정보
            //i(position): 내가 클릭한 item의 위치(index 0부터 시작)
            //l(id): Long -> 내가 클릭한 item의 고유한 값(16진수의 id값이 들어감)

            //i(position)을 통해서 내가 뭘 클릭 했는지 알아보자

//            Log.d("view", view.toString())
//            Log.d("i", i.toString())
//            Log.d("l", l.toString())

            if (i == 0) {
                Toast.makeText(this, adapterView.adapter.getItem(i).toString(), Toast.LENGTH_SHORT)
                    .show()
            }

//            Toast.makeText(this, "더하기 버튼이 눌렸습니다", Toast.LENGTH_SHORT).show()

        }

        //Adapter View를 사용하기 위한 6단계
        //1. Container 결정
        // ListView의 위치 결정

        //2. Template 결정
        //각 항목(Item)에 적용될 디자인 결정
        //layout 패키지에 xml 형태로 생성해야 함
        //board_list.xml 최상위 레이아웃: TextView
        //id는 tvBoard

        //3. Item 결정
        //VO(value object)
        //ArrayList
        val data = ArrayList<String>()
        data.add("1. 연말파티 기대된다😍")
        data.add("2. 빨리 팀 발표 했으면 좋겠다😫")
        data.add("3. 까꿍밤 보고 싶다 😭")

        //4. Adapter 결정
        //디자인(항목 뷰, 템플릿) + Item(VO Class) 를 Adapter View에 동적으로 뿌려주는 역할

        //ArrayAdapter를 사용하자
        //사용 조건 : 템플릿이 TextView, 아이템 요소가 String
        //생성자 요소 4개
        //1) 페이지 정보
        //2) 템플릿(항목 뷰)
        //3) TextView의 id
        //4) Item
        val adapter =
            ArrayAdapter<String>(this@BoardActivity, R.layout.board_list, R.id.tvBoard, data)

        //5. Container에 Adapter 부착
        //여기서 Container는 ListView임
        lv.adapter = adapter
        //안쪽에서 만든 필드를 위에서 만든 adapter로

        //6. Event 처리
        //1)btnBoard를 클릭했을 때
        btnBoard.setOnClickListener {
            //2)etBoard에 값을 가져와서
            //3) val input에 저장
            val input = etBoard.text.toString()

            //4) data에 input을 추가
            data.add(input)

            //adapter를 새로고침하자! => 아이템이 변했을 때 adapter 새로고침하기!(또는 그럴 일은 없겠지만 템플릿이 수정되었을 때)
            adapter.notifyDataSetChanged()
            etBoard.text = null//글 등록 후 안에 내용 비우기

        }

        //게시글을 클릭 했을 때 해당 게시글 삭제
//        lv.setOnItemClickListener { adapterView, view, i, l ->
//            data.removeAt(i)
//            adapter.notifyDataSetChanged()
//        }

        //게시글 클릭 했을 때 사용자에게 의사 물어보고 해당 글 삭제
        lv.setOnItemClickListener { adapterView, view, i, l ->

            //AlertDialog 옵션 정보를 담는 builder 생성
            val builder = AlertDialog.Builder(this)
            builder.setTitle("게시글 삭제")
            builder.setMessage("해당 게시글을 삭제하시겠습니까?")
            builder.setPositiveButton("삭제", OnClickListener { dialogInterface, id ->
                data.removeAt(i)
                adapter.notifyDataSetChanged()
            })
//            builder.setPositiveButton("삭제", DialogInterface.OnClickListener{ p0, p1 ->
//
//            })

            builder.setNegativeButton("취소", null)

            //주의
            //builder를 통해 옵션을 단 이후 맨 마지막에는 무조건!!! show() 함수를 달아야 함!!!!
            builder.show()
        }

    }//onCreate 밖

    //양방향 통신을 위한 launcher(intent 데이터 받아주는 콜백함수 생성)
    val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        Log.d("데이터", it.data.toString())
        Log.d("데이터", it.resultCode.toString())

        if (it.resultCode == RESULT_OK) {
            tvContent.text = it.data?.getStringExtra("content")
            btnWrite.isEnabled = true//is라는 키워드가 붙은 함수들은 다 boolean타입을 리턴함!
            btnBoard.isEnabled = true//나예호
        } else if (it.resultCode == RESULT_CANCELED) {
            tvContent.text = it.data?.getStringExtra("content")
        }

    }


}