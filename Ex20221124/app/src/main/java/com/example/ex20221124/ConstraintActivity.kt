package com.example.ex20221124

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

// : => Kotlin에서의 상속 기호!
class ConstraintActivity : AppCompatActivity() {

    //전역변수(뷰) 만들기
    //setContentView이거 아래에 find해야 하니까 변수만 선언해주기
//    val tvResult: TextView => 뷰 타입은 이렇게만 선언하는게 불가능! 나중에 초기화를 꼭 하겠다는 약속을 해줘야 함
    lateinit var tvResult: TextView
    //lateinit이라는 키워드로 나중에 꼭 초기화를 하겠다는 약속!
    //이렇게 할 땐 val을 쓸 수 없음!
    lateinit var etNum1: TextView
    lateinit var etNum2: TextView

    //OnCreate()는 Activity가 실행될 때 최초 딱 한 번(가장 먼저) 호출되는 메서드
    //Activity 생명주기
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint)//🌟xml 파일과 kt 파일을 연결해주는 코드!, 없으면 화면 안 뜸🌟
        //setContentView 위에 find 할 수 없음! => xml 파일이랑 kt 파일이 연결되기 전이기 때문에!

        //1. xml의 view에 id 지정
        //2. id값을 이용해서 view를 찾아온다( => findViewById)
//        findViewById<내가 찾아올 뷰타입>()
        //R => 리소스 폴더에서
        //id => id값을 참조할 거다
        //tvResult => tvResult라는 id 값을
        //tvResult가 저장되어 있는 주소값을 Int형으로 가져옴
        tvResult =findViewById(R.id.tvResult)//뒤에서 자료형을 추론할 수 있게 하니까 앞에 변수에 지정해주는 자료형은 생략할 수 있음
//        val tvResult:TextView=findViewById<TextView>(R.id.tvResult) //사용하기 위해서는 변수에 담아줘야 하는데 변수의 자료형은 TextView타입으로 지정해줘야 함
        //id값은 문자열로 정해줬는데 받아오는 값이 Int임
        //R 폴더에 실제로 모든 뷰(리소스)들의 id값이 저장이 되는데 이때 주소값이 저장이 됨
        //이 주소값은 16진수 상수 형태로 저장이 되어 있음 => Int형!
        //변수명이랑 id값 통일하기!! => 헷갈리지 않게~

        //버튼 id값 가져오기
        val btnMinus=findViewById<Button>(R.id.btnMinus)

        //btnMul, btnDiv, etNum1, etNum2 가져오기
        val btnMul=findViewById<Button>(R.id.btnMul)
        val btnDiv=findViewById<Button>(R.id.btnDiv)
        etNum1=findViewById(R.id.etNum1)
        etNum2=findViewById(R.id.etNum2)


        //폰트 색상 바꾸는 방법 두 가지
        tvResult.setTextColor(Color.BLUE) //1번
        tvResult.setTextColor(Color.parseColor("#ff9999")) //2번, 이렇게 할 땐 # 필수!

        //폰트 크기 조절
        //textSize에는 float자료형이 들어가야 함!
        tvResult.textSize = 40.0f

        //글씨 내용 바꾸기
        //charSequence는 인터페이스 - String은 charSequence 인터페이스를 상속받는 중
        tvResult.text = "연산결과: 0"

        //버튼을 눌렀을 때 이벤트 생성
        //더하기 => "더하기 버튼이 눌렸습니다" 라는 토스트(Toast)를 띄워주자!
        //이벤트 주는 방법
        //1) 이벤트 메소드 설계 후 뷰에 연결하기
        //onCreate 바깥에다가 만들기!

        //2) innerClass(Listener OnClick 구현)
        btnMinus.setOnClickListener {
            //{}안에다가 기능 구현만 하면 됨!
            //Minus 버튼을 눌렀을 때 "빼기 버튼이 눌렸습니다" Toast로 띄우기
            Toast.makeText(this, "빼기 버튼이 눌렸습니다", Toast.LENGTH_SHORT).show()

            //findViewById하고 나서!
            //1. EditText에 적혀있는 내용 가져오기
            //코드 간결화 var num1=etNum1.text.toString().toInt()
//            var num1=etNum1.text.toString()
            var num1=etNum1.text.toString().toInt()
            var num2=etNum2.text.toString().toInt()
            //etNum1, etNum2에 있는 내용 변수 num1, num2에 저장
            //실제로 getText --> Editable ---> 문자열(.toString)로 형변환 해줘야 함 ---> 정수형(.toInt)으로 형변환
            //2. num1, num2 연산 결과를 tvResult에 set 하기!
            //2-1. num1, num2 연산 결과를 문자열로 바꿔서 set 해주기!
            var minus: Int
            if(num1>num2){
                minus=num1-num2
            }else{
                minus=num2-num1
            }
            tvResult.text="연산결과: "+minus.toString()
        }

        btnMul.setOnClickListener {
            Toast.makeText(this, "곱하기 버튼이 눌렸습니다", Toast.LENGTH_SHORT).show()

            var num1=etNum1.text.toString().toInt()
            var num2=etNum2.text.toString().toInt()

            var mul=num1*num2
            tvResult.text="연산결과: "+mul.toString()
        }

        btnDiv.setOnClickListener {
            Toast.makeText(this, "나누기 버튼이 눌렸습니다", Toast.LENGTH_SHORT).show()

            var num1=etNum1.text.toString().toInt()
            var num2=etNum2.text.toString().toInt()

            var div: Int

            if(num1>num2) div=num1/num2
            else div=num2/num1

//            var div=num1/num2
            tvResult.text="연산결과: "+div.toString()
        }

        //3) 인터페이스(interface) 상속받아서 OnClick 구현


    }//onCreate 밖

    //1번 방법
    //이벤트 함수가 달리는 뷰에 대한 정보가 매개변수로 사용되어야 함
    //이벤트 리스너는 무조건 View 매개변수 가질 수 있도록!
    fun myClick(view: View) {
        //Toast 띄우기
//        Toast.makeText(화면정보(어디에 띄워줄 건지), 문구(문자열 또는 id값), 지속시간)
        //1) this대신 사용할 수 있는 거: ConstraintActivity.this => Toast를 띄울 화면 정보
        //2) 문구(무조건 String, Int가 허용되는 경우는 id 값만) => 숫자를 띄우고 싶으면 형변환 하면 됨(.toString)
        //3) Toast에 Short(3초), Long(5초) 속성 사용: 지속시간 => 두 개만 있음
        Toast.makeText(this, "더하기 버튼이 눌렸습니다", Toast.LENGTH_SHORT).show()

        //전역변수로 선언해서 여기서 불러오지 않아도 됨!
//        val tvResult=findViewById<TextView>(R.id.tvResult)
//        val etNum1=findViewById<TextView>(R.id.etNum1)
//        val etNum2=findViewById<TextView>(R.id.etNum2)

        var num1=etNum1.text.toString().toInt()
        var num2=etNum2.text.toString().toInt()
        //Emulator를 처음 실행시키면 EditText에는 아무 값도 없음 ""
        //"".toInt() NumberFormatException
        //버튼을 눌렀을 때 젹혀 있는 값을 가지고 와줘야 함!

        var plus=num1+num2
        //문자열 변환 2가지 방법
        //1번
        tvResult.text="연산결과: "+plus.toString()
        //2번
//      tvResult.text="연산결과 : $plus"
        //3번 => 변수 선언 안 하고 넘기는 거임
//        tvResult.text="연산결과: ${num1+num2}"
    }

}