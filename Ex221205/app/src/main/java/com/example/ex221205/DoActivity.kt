package com.example.ex221205

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlin.random.Random

class DoActivity : AppCompatActivity() {

    var isPlaying: Boolean = true//제한시간 관련 조건
    var score = 0 //점수를 저장하는 변수

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_do)

        val tvScore = findViewById<TextView>(R.id.tvScore)
        val btnStart = findViewById<Button>(R.id.btnStart)
        val tvTime = findViewById<TextView>(R.id.tvTime)

        val imgViews = ArrayList<ImageView>()

//        val resId = R.id.imageView1
        for(i in 1 .. 9){
            //resources.getIdentifier(): 리소스 아이디를 가져 오는 메서드
                //두 번째 매개변수는 가져오는 데이터가 있는 파일 위치를 적어주면 됨, 레이아웃이면 레이아웃, drawable이면 drawable을 써주면 됨
            val resId = resources.getIdentifier("imageView$i", "id", packageName)
            val imgView = findViewById<ImageView>(resId)
            imgViews.add(imgView)
            //visibility : View 상태를 보여주게 할 건지 안 보이게 할 건지 설정할 수 있음
            imgView.visibility = View.INVISIBLE //안 보이게 하겠다는 설정!(공간은 그대로)
        }

        //게임시작
        btnStart.setOnClickListener {
            //버튼을 클릭하면 스레드가 시작하도록!
            isPlaying = true
            //버튼 누르면 현재 점수 초기화
            tvScore.text = "현재 점수 : 00"

            //제한 시간 관련 스레드
            val thread2 = TimeThread(tvTime)
            thread2.start()

            //두더지 on/off 관련 스레드
            for(i in 0 until imgViews.size){
                val imgView = imgViews.get(i)
                imgView.visibility = View.VISIBLE//보여주겠다!

                //클릭 했을 때 어떤 상태인지 조건문을 활용해서 판단하기 위해 태그를 다는 거임
                //핸들러에서 on을 Int 타입으로 값을 넘겨주기 때문에 tag를 Int형으로 받는 거임
                //각 이미지 뷰의 tag의 최초 값은 0 => 여기서 0은 두더지가 앉아 있음을 의미!
                imgView.tag = 0

                val thread = DoThread(imgView)
                thread.start()

                //사진 클릭 시 점수 카운트 하기 위해
                imgView.setOnClickListener {
                  if(imgView.tag == 1){
                      score++
                  }else{
                    score--
                      if(score < 0){
                          score = 0
                      }
                  }
                    tvScore.text = "현재 점수 : $score"
                }
            }//for문 닫힘

        }//btnStart 닫힘

    } //onCreate() 닫힘

    //MainThread에 SubThread 작업을 추가하기 위해 handler 생성
    //제한시간 handler
    val handler2 = object : Handler(Looper.getMainLooper()){
        //Ctrl + O : handleMessage
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val time = msg.arg1
            val tv = msg.obj as TextView

            tv.text = time.toString()

        }
    }


    //사진 on/off 변경 handler
    val handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)

            val imgView = msg.obj as ImageView //어떤 이미지뷰에 적용될 건지
            val img = msg.arg1 //on 이미지인지 off 이미지인지(이미지의 정보를 담고 있음)
            val tag = msg.arg2 //현재 이미지의 상태, 1이라면 일어나있음 0이라면 앉아 있음을 의미
            imgView.setImageResource(img)
            imgView.tag = tag //이미지 뷰 태그에 핸들러에서 받아온 태그 값으로 설정해주기

        }
    }

    //두더지 on/off 스레드
    inner class DoThread(val imgView: ImageView) : Thread(){
        override fun run() {
            super.run()

            //무한 반복을 하고 싶다면!? while문으로 감싸기 => 조건은 true
            //제한 시간을 넣을 땐 조건을 true말고 isPlaying 주기~
            while (isPlaying){
                // 랜덤하게 0 ~ 5초 후에 일어나게!
//                val onTime = Random.nextInt(5000)

                //난이도 조정
                var level = score * 20
                if(score >= 40){
                    level = 800
                }

                val onTime = Random.nextInt(5 * (1000-level))
//            Log.d("테스트", onTime.toString())
                Thread.sleep(onTime.toLong())

                val message = Message()
                message.arg1 = R.drawable.on
                message.arg2 = 1 //두더지가 일어나있음을 의미
                message.obj = imgView
                handler.sendMessage(message)

                // 랜덤하게 0 ~ 5초 후에 앉기!
                val offTime = Random.nextInt(3 * (1000-level))
//            Log.d("테스트", offTime.toString())
                Thread.sleep(offTime.toLong())

                val messageOff = Message()
                messageOff.arg1 = R.drawable.off
                messageOff.arg2 = 0 //두더지가 앉아 있음을 의미
                messageOff.obj = imgView
                handler.sendMessage(messageOff)

            }

        }
    }

    //제한시간 Thread
    inner class TimeThread(val tv: TextView):Thread(){
        //Ctrl + O : run
        override fun run() {
            super.run()

            for(i in 30 downTo 0){
                val message = Message()
                message.arg1 = i
                message.obj = tv

                handler2.sendMessage(message)

                Thread.sleep(1000)

            }
            //제한시간이 끝나면 스레드가 멈추게 한다
            isPlaying = false
            //페이지 이동을 하고 싶으면 여기서 Intent로 이동하면 됨~

        }
    }


}