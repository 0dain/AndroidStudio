package com.example.fullstackapplication.fragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.fullstackapplication.ContactVO
import com.example.fullstackapplication.R
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase


class Fragment1 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_1, container, false)
        val tvMsg = view.findViewById<TextView>(R.id.tvMsg)
        val clHome = view.findViewById<ConstraintLayout>(R.id.clHome)

        //예호선생님 Firebase랑 연결되는지 확인하기 위한 것
        val etName = view.findViewById<EditText>(R.id.etName)
        val btnSend = view.findViewById<Button>(R.id.btnSend)

        //연락처 관련
        val etContactName = view.findViewById<EditText>(R.id.etContactName)
        val etContactAge = view.findViewById<EditText>(R.id.etContactAge)
        val etContactTel = view.findViewById<EditText>(R.id.etContactTel)
        val btnContact = view.findViewById<Button>(R.id.btnContact)

        //Firebase에서 데이터를 실시간으로 읽고 쓸 수 있는 RealTime Database
            //NoSQL형식 -> key:value
        // Write a message to the database
        val database = Firebase.database//연결된 Firebase계정의 RealTime DB를 연결 한 것
        //다른 계정을 사용하고 싶을 때
//        val url = "https://fir-demo-project.firebaseio.com/"
//        val db = Firebase.database(url)

        //예호쌤 DB 연결
        val yhurl = "https://android-project-yeho-default-rtdb.firebaseio.com/"
        val yhdb = Firebase.database(yhurl)
        val dain = yhdb.getReference("임다인")

        //전송 버튼을 눌렀을 때
        btnSend.setOnClickListener {
            val input = etName.text.toString()
            //예호쌤 DB랑 연결된 곳으로 내용을 담아서 보낸다!
            dain.setValue(input)
        }

        //연락처 추가를 위해 내 DB랑 연결
        //contact라는 키 값으로
//        val contact = database.getReference("contact")
        val contact = database.getReference("contact2")//새로운 키 값

        //연락처 추가하기 버튼을 눌렀을 때
        btnContact.setOnClickListener {
            val name = etContactName.text.toString()
            val age = etContactAge.text.toString().toInt()
            val tel = etContactTel.text.toString()

            //contact 라는 키 값에 데이터 보내기
//            contact.setValue(ContactVO(name, age, tel))
            contact.push().setValue(ContactVO(name, age, tel))//새로운 키 값에 데이터 넣기 => push() 추가!
            //실행할 때마다 앞에 특정문자들이 붙어서 값이 들어감

        }

        val myRef = database.getReference("message")//key
        myRef.setValue("Hello, World!")//value

        //배경색 바꿀 변수
        val color = database.getReference("color")

        color.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val bgColor = snapshot.getValue<String>()
                //bgColor가 null이 아닐 때, bgColor가 바꿀 수 있는 색일 때 변경하게 해주자!
                if (bgColor != null){
                    //예외처리 하기
                        //코드를 처리할 때 어떤 오류가 뜨는지 인지하고 사용해야 함
                    try {
                        clHome.setBackgroundColor(Color.parseColor(bgColor))
                    }catch (e : IllegalArgumentException){//색을 찾을 수 없을 때 뜨는 오류
                        clHome.setBackgroundColor(Color.parseColor("yellow"))
                    }catch (e: StringIndexOutOfBoundsException){
                        clHome.setBackgroundColor(Color.parseColor("blue"))
                    }
                }else{
                    //배경색 변경
                    clHome.setBackgroundColor(Color.parseColor("white"))
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })


        //데이터 가져오기
            //myRef에 값이 추가되는 이벤트를 감지
        myRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                //데이터가 변하면 동작
                val message = snapshot.getValue<String>()
                tvMsg.setText(message)
            }

            override fun onCancelled(error: DatabaseError) {
                //데이터가 삭제되면 동작
            }
        })

        //저장된 연락처 가져오기
        contact.addChildEventListener(object : ChildEventListener{

            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                //추가되면
                val webContact = snapshot.getValue<ContactVO>()
                Log.d("연락처", webContact.toString())
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                //수정되면
                TODO("Not yet implemented")
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                //삭제되면
                TODO("Not yet implemented")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })



        return view
    }

}