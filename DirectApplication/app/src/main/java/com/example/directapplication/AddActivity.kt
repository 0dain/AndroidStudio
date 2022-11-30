package com.example.directapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val etTitle = findViewById<EditText>(R.id.etTitle)
        val etUrl = findViewById<EditText>(R.id.etUrl)
        val btnSend = findViewById<Button>(R.id.btnSend)

        //3. AddActivity에서 btnSend(Button)을 누르면
        //EditText에 적힌 title, url 값을 가지고 MainActivity로 이동
        //(finish())
        btnSend.setOnClickListener {

            //EditText에 있는 내용 가져오기
            var title = etTitle.text.toString()
            var url = etUrl.text.toString()

            //Intent 생성
            val intent = Intent()//데이터 전달 객체용으로 생성

            intent.putExtra("title", title)
            intent.putExtra("url", url)

            //Intent에 내용 달아주기(key, value)

            //실행(setResult(ResultCode, intent))
            setResult(RESULT_OK, intent)
//            if(title != null && url != null){
//                setResult(RESULT_OK, intent)
//            }else{
//                setResult(RESULT_CANCELED)
//            }

            finish()

        }


    }
}