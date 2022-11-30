package com.example.ex20221129

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class SubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        //MainActivity에서 requestCode와 함께 Intent로 이동한 상태

        val etResult = findViewById<EditText>(R.id.etResult)
        val btnSend = findViewById<Button>(R.id.btnSend)

        //btnSend를 눌렀을 때
        btnSend.setOnClickListener {
            //1. EditText에 적혀있는 문구 가져오기
            //2. 문구를 변수에 저장
            var str = etResult.text.toString()

            //3. Intent 생성
            val intent = Intent()

            //4. Intent에 변수에 담긴 값을 붙여준다 => putExtra(key, value)
            intent.putExtra("content", str)

            //5. setResult(resultCode, intent)
            setResult(RESULT_OK, intent)
            //resultCode: 결과값이 잘 반환이 되었는지에 대한 판단을 위한 코드(결과값의 상태를 메인에서 판단하기 위한 코드)

            //6. finish()
            finish()

        }


    }
}