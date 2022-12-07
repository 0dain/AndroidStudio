package com.example.ex221205

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import kotlin.random.Random

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val btnGame = findViewById<Button>(R.id.btnGame)
        val numbers = ArrayList<Int>()//1~25랜덤 숫자 담을 배열
        var cnt = 1//누른 버튼의 숫자를 기억하기 위한 변수(현재 눌러야 하는 숫자)

       rdSet(numbers, cnt)

        //버튼 여러개 가져오기
        val btns = ArrayList<Button>()
        for(i in 1 .. 25){
            //리소스 id 가져오기
            val resId = resources.getIdentifier("btn$i", "id", packageName)
            val btn = findViewById<Button>(resId)
            btns.add(btn)//찾아온 아이디들 ArrayList에 넣어보기
            //잘 들어왔는지 확인 하는 방법(처음에 버튼들이 안 보이게 하면 잘 가져오는지 알 수 있음)
            btn.visibility = View.INVISIBLE//안 보이게!
        }

        //버튼을 누르면
        btnGame.setOnClickListener {

            for(i in 0 until btns.size){
                val btn = btns[i]
                btnSet(btn, numbers[i])
//                //버튼에 숫자 부여하고
//                btn.text = numbers[i].toString()
//                //버튼 보이게 하기
//                btn.visibility = View.VISIBLE

                //보여진 버튼들에게 클릭 이벤트 달아주기
                btn.setOnClickListener {
                    if(btn.text.toString().toInt() == cnt){
                        btn.visibility = View.INVISIBLE
                        cnt++

                        if(cnt % 25 == 1){
                            rdSet(numbers, cnt)
                            for(j in 0 until btns.size) {
                                val btn = btns[j]
                                btnSet(btn, numbers[j])
                            }
                        }
                    }
                }
            }
        }//btnGame 닫힘


    }//onCreate 닫힘

    fun btnSet(btn: Button, value: Int){
        btn.text = value.toString()
        btn.visibility = View.VISIBLE
    }

    fun rdSet(numbers: ArrayList<Int>, cnt: Int){
        numbers.clear()

        //1~25까지 배열에 넣기
        for(i in cnt .. cnt+24){
            numbers.add(i)
        }

        //배열 안에 있는 숫자들 섞어버리기~
        for (i in 0 until 100){
            //섞어야 할 배열(numbers)의 인덱스 번호이기 때문에 25를 구하는 거임!
            val rdNum1 = Random.nextInt(25) //0~24 랜덤 수 뽑기
            val rdNum2 = Random.nextInt(25) //0~24 랜덤 수 뽑기

            //ex) rdNum1 = 3, rdNum2 = 6 => 치환하기
            val temp = numbers[rdNum1]
            numbers[rdNum1] = numbers[rdNum2]
            numbers[rdNum2] = temp
        }

        Log.d("랜덤", numbers.toString())
    }

}