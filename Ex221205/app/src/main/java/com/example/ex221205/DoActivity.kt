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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_do)

        val tvScore = findViewById<TextView>(R.id.tvScore)
        val btnStart = findViewById<Button>(R.id.btnStart)

        val imgViews = ArrayList<ImageView>()

        for(i in 1 .. 9){
            //        val resId = R.id.imageView1
            //리소스 아이디를 가져 오는 메서드
            //두 번째 매개변수는 가져오는 데이터가 있는 파일 위치를 적어주면 됨, 레이아웃이면 레이아웃, drawable이면 drawable을 써주면 됨
            val resId = resources.getIdentifier("imageView$i", "id", packageName)
            val imgView = findViewById<ImageView>(resId)
            imgViews.add(imgView)
            //visibility : View 상태를 보여주게 할 건지 안 보이게 할 건지 설정할 수 있음
            imgView.visibility = View.INVISIBLE //안 보이게 하겠다는 설정!(공간은 그대로)
        }

        btnStart.setOnClickListener {
            for(i in 0 until imgViews.size){
                val imgView = imgViews.get(i)
                imgView.visibility = View.VISIBLE//보여주겠다!

                val thread = DoThread(imgView)
                thread.start()

            }

        }

    } //onCreate()끝

    val handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)

           val imgView = msg.obj as ImageView
            imgView.setImageResource(R.drawable.on)

        }
    }

    inner class DoThread(val imgView: ImageView) : Thread(){
        override fun run() {
            super.run()

            // 랜덤하게 0 ~ 5초 후에 일어나게!
            val onTime = Random.nextInt(5000)
//            Log.d("테스트", onTime.toString())
            Thread.sleep(onTime.toLong())

            val message =Message()
            message.obj = imgView
            handler.sendMessage(message)
        }
    }




}