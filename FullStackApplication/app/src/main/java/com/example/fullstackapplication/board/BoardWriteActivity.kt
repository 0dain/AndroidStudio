package com.example.fullstackapplication.board

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import com.example.fullstackapplication.R
import com.example.fullstackapplication.utils.FBdatabase

class BoardWriteActivity : AppCompatActivity() {

    //나중에 함수에서 사용해야 하기 때문에 전역변수로 빼줌
    lateinit var imgLoad: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_write)

        //id값 다 찾아오기
        imgLoad = findViewById(R.id.imgLoad)
        val etTitle = findViewById<EditText>(R.id.etTitle)
        val etContent = findViewById<EditText>(R.id.etContent)
        val imgWrite = findViewById<ImageView>(R.id.imgWrite)

        //db랑 auth를 관리하는 패키지안에 관련 내용 클래스들을 만들어서 호출해서 사용하자!

        imgWrite.setOnClickListener {
            val title = etTitle.text.toString()
            val content = etContent.text.toString()

            //board
                //-key(게시글의 고유한 uid: push())
                    //push(): 고유한 uid 부여하는 애
                    //- boardVO(title, content, 사용자 uid, time)
            FBdatabase.getBoardRef().push().setValue(BoardVO("1", "1","1","1"))

        }



    }


}