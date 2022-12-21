package com.example.wordle

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnKeyListener
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

//1. GameAdapter의 생성자(context, gameList) 구현
//3. GameAdapter가 RecyclerView.Adapter<ViewHolder>를 상속
class GameAdapter(val context: Context, val gameList: ArrayList<GameVO>, val answer: String):RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    //2. inner class ViewHolder 구현
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        //5. ViewHolder field 구성(etGame1~etGame5)
        val etGame1: EditText
        val etGame2: EditText
        val etGame3: EditText
        val etGame4: EditText
        val etGame5: EditText

        //6. init{}, findeViewById를 통해 필드 초기화
        init {
            etGame1 = itemView.findViewById(R.id.etGame1)
            etGame2 = itemView.findViewById(R.id.etGame2)
            etGame3 = itemView.findViewById(R.id.etGame3)
            etGame4 = itemView.findViewById(R.id.etGame4)
            etGame5 = itemView.findViewById(R.id.etGame5)

            val etList = ArrayList<EditText>()
            etList.add(etGame1)
            etList.add(etGame2)
            etList.add(etGame3)
            etList.add(etGame4)
            etList.add(etGame5)

            //현재 행의 포지션
//            val position = adapterPosition


            //자동으로 다음 칸으로 이동
            for (i in 0 until 4){
                etList.get(i).setOnKeyListener(
                    object : OnKeyListener {
                        override fun onKey(p0: View?, p1: Int, p2: KeyEvent?): Boolean {
                            if (p2?.action == KeyEvent.ACTION_DOWN) {
//                              etGame1.requestFocus()
                                etList.get(i+1).requestFocus()
                            }
                            return false
                        }
                    })
            }

            //key를 감지하는 리스너
            etGame5.setOnKeyListener(object : OnKeyListener{
                override fun onKey(p0: View?, p1: Int, p2: KeyEvent?): Boolean {
                    Log.d("테스트1", p1.toString())
                    Log.d("테스트2", p2.toString())

                    if(p1 != 66){
                        Toast.makeText(context, "마지막 칸에서 엔터를 눌러주세요", Toast.LENGTH_SHORT).show()
                    }else if (p1 == 66 && p2?.action == KeyEvent.ACTION_UP){
                        //p2?.action == KeyEvent.ACTION_UP => 엔터 한 번만 출력
//                      KeyEvent.KEYCODE_ENTER //이렇게 써도 되고 KEYCODE_66으로 써도 됨
                        Log.d("테스트", "엔터눌러짐")

//                        //정답 여부에 따라 색깔 변환!
//                        checkAnswer(answer, etList)
//
//                        //엔터치면 접근 못하게 하기
//                        disableEditText(etList)

                        //엔터치면 다음 칸 첫 번째로 커서 이동
//                       etList.get(0).requestFocus()
                    }



                    return false
                }
            })
        }

    }

    //정답 체크 관련 메서드
    fun checkAnswer(answer: String, etList:ArrayList<EditText>){
        //정답(answer)이 apple일 때
        //사용자가 작성한 답(etList)이 a l b u m
        //일치하면 초록색, 위치가 다르면 노랑색, 없으면 회색
        //a: 초, l: 노, b: 회, u: 회, m: 회 => 배경색 초기화

        for (i in 0 until etList.size){

            val answerChar : Char = answer.get(i)
            val etChar : Char = etList.get(i).text.toString().single()

            if(answerChar == etChar){
                etList.get(i).setBackgroundColor(Color.parseColor("green"))
            }else{
                var check = true

                for (j in 0 until etList.size){
                    if (etChar == answer.get(j)){
                        check = false
                        etList.get(i).setBackgroundColor(Color.parseColor("yellow"))
                    }
                }

                if(check == true){
                    etList.get(i).setBackgroundColor(Color.parseColor("gray"))
                }
            }

        }

    }//checkAnswer 닫힘

    //한 행 전체 활성화/비활성화
    fun enableEditText(etList: ArrayList<EditText>){
        for (i in 0 until etList.size){
            etList.get(i).isEnabled = true
        }
    }

    //엔터 치면 지나간 칸 비활성화
    fun disableEditText(etList: ArrayList<EditText>){
        for (i in 0 until etList.size){
            etList.get(i).isEnabled = false
        }
    }


    //4. 구현되지 않은 member 구현(메서드)
    //7. 오버라이딩된 메서드 3개 구현
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        //9. layoutInflater를 통해 xml을 View 객체로 변환
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.game_list, null)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //10. 만들어진 holder안에 etGame1~etGame5를
        //11. gameList[position].word1을 통해 값을 꺼내서 setText해주기

        //현재 행의 포지션을 가져오기 위한 리스트
        val etList = ArrayList<EditText>()
        etList.add(holder.etGame1)
        etList.add(holder.etGame2)
        etList.add(holder.etGame3)
        etList.add(holder.etGame4)
        etList.add(holder.etGame5)

//        holder.etGame1.text = gameList[position].word1 //이건 왜 안 됨?!...
            //위에 초기화되는 etGame1~etGame5는 EditText라서! TextView는 가능함 EditText는 setText로 해줘야 함
        holder.etGame1.setText(gameList[position].word1)
        holder.etGame2.setText(gameList[position].word2)
        holder.etGame3.setText(gameList[position].word3)
        holder.etGame4.setText(gameList[position].word4)
        holder.etGame5.setText(gameList[position].word5)

        //현재 행의 포지션을 가져와서 지금 포지션에서만 입력할 수 있게!
//        for (i in 0 until etList.size){
//            if(etList.get(i) == i){
//
//            }
//        }
//        if (position == 0){
//            enableEditText(etList)
//        }else{
//            disableEditText(etList)
//        }

        holder.etGame5.setOnKeyListener(object : OnKeyListener{
            override fun onKey(p0: View?, p1: Int, p2: KeyEvent?): Boolean {
                if(p1 == KeyEvent.KEYCODE_ENTER && p2?.action == KeyEvent.ACTION_UP){
                    if(position == gameList.size - 1){
                        Toast.makeText(context, answer, Toast.LENGTH_SHORT).show()
                    }else{
                        //정답 여부에 따라 색깔 변환!
                        checkAnswer(answer, etList)

                        //엔터치면 접근 못하게 하기
                        disableEditText(etList)
                    }
                }
                return false
            }

        })




    }

    override fun getItemCount(): Int {
        //8. gameList의 크기를 리턴
        return gameList.size
    }
}