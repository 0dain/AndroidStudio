package com.example.ex221205

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var tvTimer: TextView
    lateinit var tvTimer2: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvTimer = findViewById(R.id.tvTimer)
        tvTimer2 = findViewById(R.id.tvTimer2)

        val thread = TimerThread(tvTimer)//밑에 있는 TimerThread 사용할 수 있도록 생성
        thread.start()//스레드 클래스 안의 run() 실행

        val thread2 = TimerThread(tvTimer2)
        thread2.start()

        //여기에 Thread.sleep()이 있으면 화면이 동작하지 않음! 얘가 끝나야 화면이 보임
//        for(i in 10 downTo 0){
//            Log.d("타이머2", i.toString())
//            //sleep(밀리초): 코드를 지연시킴
//            Thread.sleep(1000)//1초 만큼 코드를 지연시킴
//        }




    }

    //Main Thread Queue(작업 영역)에 작업을 추가해주는 Handler 만들기
    val handler = object : Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val time = msg.arg1
            val tv = msg.obj as TextView//Any 타입이기 때문에 TextView로 다운캐스팅 해주기!
            //메인 UI를 건드는 작업!
            //직접적으로 view 정보를 수정하는게 아니고 메인 작업 Queue에 '이 작업 해주세요~'라고 작업 추가!
//            tvTimer.text = time.toString()
//            tvTimer2.text = time.toString()
            tv.text = time.toString()
        }
    }

    //Thread
    //시간 관련된 Thread 클래스 만들기
    inner class TimerThread(val tv: TextView) : Thread(){
        //run() 메서드가 존재!
            //스레드를 동작시키면 실행되는 메서드

        //오버라이딩 Ctrl + O
        override fun run() {
            super.run()

            //10 -> 0(1초마다 1씩 감소)
            for(i in 10 downTo 0){
                Log.d("타이머", i.toString())
//                tvTimer.text = i.toString() 오류남! MainThread가 운영할 때 동시에 다른 작업 불가능이라는 뜻
                //핸들러를 통해서 MainThread에 넣어 줘야 함
                val message = Message()//핸들러에게 정보를 전달해주는 객체
                //member 변수(field)가 3개 존재
                //arg1(전달 인자):Int, arg2(전달 인자):Int, obj(객체):Any
                message.arg1 = i
                message.obj = tv

                handler.sendMessage(message)
                //sleep(밀리초): 코드를 지연시킴
//                Thread.sleep(1000)//1초 만큼 코드를 지연시킴
                val rdValue = Random.nextInt(1000)

                Thread.sleep(rdValue.toLong())
            }


        }
    }


}