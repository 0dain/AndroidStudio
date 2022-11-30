package com.example.ex20221124

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class ImageActivity : AppCompatActivity() {

    //배열 선언
    //intArrayOf => int형만 들어가는 ArrayList
    val imgArray= intArrayOf(R.drawable.pink, R.drawable.black, R.drawable.blue, R.drawable.yellow, R.drawable.red)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        //View의 id 값 다 찾아오기
        val img=findViewById<ImageView>(R.id.img)
        val btnPre=findViewById<Button>(R.id.btnPre)
        val btnNext=findViewById<Button>(R.id.btnNext)

        //이미지 바꿔보기
//        img.setImageResource(R.drawable.pink) 리소스 파일에 있는 파일의 경로를 찾는 거이기 때문에 R.사진이 들어있는 폴더명.파일명 해주면 됨
//        img.setImageResource(R.drawable.pink)

        var index=0//버튼 두 개가 인덱스를 같이 사용할 수 있도록 !
        img.setImageResource(imgArray[index])

        //클릭할 때만 사진 변경
        btnPre.setOnClickListener {
            //1. Pre 버튼을 눌렀을 때(setOnClickListener)
            //1-1. index -1 감소
            //해당 index에 있는 img의 id를 가져와서 ImageView에 set!
            //index 조건 => 0보다 작으면 다시 마지막 이미지로 돌아가야 함 => index 값을 size-1로
            if(index>0){
                index--
            }else{
                index=imgArray.size-1
            }
            img.setImageResource(imgArray[index])

        }

        btnNext.setOnClickListener {
            //2. Next 버튼을 눌렀을 때
            //2-1. index+1
            //해당 index에 있는 img의 id를 가져와서 ImageView에 set!
            //index 조건 => size-1보다 크면 index 값을 0으로!
            if(index<imgArray.size-1){
                index++
            }else{
                index=0
            }
            img.setImageResource(imgArray[index])
        }

    }
}