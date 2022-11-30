package com.example.ex20221130

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    var phoneList = ArrayList<PhoneVO>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lv = findViewById<ListView>(R.id.lv)

        //1. 화면에서 ListView의 위치 정해주기(xml 파일)
        //2. ListView 한 칸에 들어갈 디자인 정해주기(xml 파일)


        //3. ListView에 들어갈 데이터 만들기 -> 하나의 자료형(PhoneVO)
            //이미지뷰에 들어갈 Image의 ID값(Int)
            //이름, 전화번호(String)
        val p1 = PhoneVO(R.drawable.img, "조자연", "010-1234-5678")
        val p2 = PhoneVO(R.drawable.img2, "채수민", "010-1111-5678")
        val p3 = PhoneVO(R.drawable.img3, "자연", "010-1111-5555")
        val p4 = PhoneVO(R.drawable.img4, "선영표", "010-3333-5555")
        val p5 = PhoneVO(R.drawable.img5, "강예진", "010-1212-7777")

        //Adapter로 보낼 땐 하나로 묶여있어야 함
            //배열로 넘겨줄 건데 데이터 타입은 PhoneVO
        phoneList.add(p1)
        phoneList.add(p2)
//        phoneList.add(PhoneVO(R.drawable.img2, "채수민", "010-1111-5678"))//이렇게 넣는 걸 더 많이 씀
        phoneList.add(p3)
        phoneList.add(p4)
        phoneList.add(p5)


        //4. Adapter 만들기 🌟🌟🌟🌟🌟🌟🌟🌟
            //데이터의 자료형이 내가 만든 자료형(VO)이기 때문에 안드로이드에서 기본적으로 제공하는 ArrayAdapter는 사용 불가
        //Adapter: 데이터랑 템플릿을 합쳐서 ListView에 적재시켜주는 역할
        val adapter = PhoneAdapter(applicationContext, R.layout.phone_list, phoneList)

        lv.adapter = adapter


        //5. ListView에 Adapter 적용

        //6. 이벤트 달아주기

    }
}