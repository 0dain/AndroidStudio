package com.example.ex20221128

import android.app.SearchManager
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCall=findViewById<Button>(R.id.btnCall)
        val btnWeb=findViewById<Button>(R.id.btnWeb)
        val btnGoogle=findViewById<Button>(R.id.btnGoogle)
        val btnSearch=findViewById<Button>(R.id.btnSearch)
        val btnSms=findViewById<Button>(R.id.btnSms)
        val btnPhoto=findViewById<Button>(R.id.btnPhoto)

        //암묵적 intient
        //안드로이드 내부에 있는 어플리케이션을 실행
        //Chrom, Camera, Message, Call

        //Intent 사용 용도
            //1. 액션, 데이터
            //1-2. 액션 -> 카메라
            //2. Android 4대 구성요소간의 데이터 주고 받을 때

        //btnCall 누르면 전화가 가게 만들어보자!
        btnCall.setOnClickListener {
            //데이터: 전화번호
            //intent 생성하기
//            var intent=Intent(액션(actionDial, 데이터(전화번호))
            //URi: key, value
            //이거는 전화번호야~ 하고 알려주는 거 => key
            //"tel:010-1234-5678"
            var uri= Uri.parse("tel:010-1234-5678")//uri데이터 파싱해주기
            var intent=Intent(Intent.ACTION_CALL, uri)

            //permission: 권한
            //AndroidMainfest.xml에서 권한을 부여한 후에 사용자에게 권한을 허용할 건지 물어봐야함
            //ActivityCompat 에 들어있음
            //checkSelfPermission(현재 페이지 정보(this), 어떤 권한인지): 지금 현재 권한이 부여되어 있는지
                //결과 값으로 승인이 되어있는지 아닌지를 반환
            if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE)
            !=PackageManager.PERMISSION_GRANTED){
                //승인이 안 되어 있는 상태라면 알림창을 띄워서 승인할 수 있도록 해야 함!

                //ActivityCompat는 확인하는 기능, 요청하는 기능 둘 다 있음
                    //arrayOf(요청을 하나만 하는 게 아니니까~, 한 번에 여러 개를 허용 요청을 할 수 있음)
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CALL_PHONE),
                0)//requestCode => 내가 어떤 걸 요청했는지 구분하기 위한 코드(16진수 id값은 들어가면 안 됨)
                return@setOnClickListener//네이블 => 확인이 끝나고 요청을 했으면 다시 이벤트함수를 실행해라!

            }
            //intent 실행시키기
            startActivity(intent)
        }

        //btnWeb을 클릭하면 구글 홈페이지가 보이게
        btnWeb.setOnClickListener {
            //데이터: 구글 주소(http://www.google.co.kr)
            var uri=Uri.parse("http://www.google.co.kr")
            var intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        //btnGoogle 클릭하면 구글 맵 보이게
        btnGoogle.setOnClickListener {
            //액션, 데이터
            //데이터: 구글 맵은 get방식 => 구글 맵 주소/경도, 위도
            var uri=Uri.parse("https://google.com/maps?q=35.14670147841655,126.92215633785938")
            var intent=Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        //btnSearch 클릭하면 해당 키워드로 구글 검색
        btnSearch.setOnClickListener {
            //intent에 키 값만 들어가고 데이터는 안 들어감
            //1. 검색하는 intent를 하나 생성
            var intent=Intent(Intent.ACTION_WEB_SEARCH)

            //2. 검색하고자 하는 키워드를 intent에 넣어준다
//            intent.putExtra(SearchManager.QUERY(키 값),"안드로이드"(검색하고자 하는 키워드))
            intent.putExtra(SearchManager.QUERY,"안드로이드")

            //3. intent 실행
            startActivity(intent)
        }

        //사진 찍기
        //MediaStore: Emulator(가상 핸드폰)에서 동작할 수 있는 카메라, 저장소 제공
        btnPhoto.setOnClickListener {
            //intent에 키 값만 들어가고 데이터는 안 들어감
            var intent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivity(intent)
        }

        //메세지 보내기
        //btnSms를 클릭하면 문자를 보내는 페이지로 이동한 다음 내용을 꺼내올 예정
        btnSms.setOnClickListener {
            var intent=Intent(Intent.ACTION_SENDTO)
            //문자내용
            //"sms_body" 라는 key값이 value가 문자 내용임을 구분할 수 있음
            intent.putExtra("sms_body", "안녕하세요~~")
            //누구한테 보낼건지에 대한 데이터 : tel: --> Uri 파싱 필요
            //smsto: => 누구한테 보낼건지
            //얘는 전화번호를 한 번 더 파싱해줘야 함
            intent.data=Uri.parse("smsto:"+Uri.encode("010-1234-5678"))
            startActivity(intent)
        }


    }
}