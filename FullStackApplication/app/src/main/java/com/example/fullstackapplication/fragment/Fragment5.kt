package com.example.fullstackapplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fullstackapplication.ContactAdapter
import com.example.fullstackapplication.ContactVO
import com.example.fullstackapplication.R
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase


class Fragment5 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_5, container, false)

        //연락처 관련
        //AdapterView 6단계
        //1. Container 결정
        val rvContact = view.findViewById<RecyclerView>(R.id.rvContact)

        //2. Template 결정
        //contact_list.xml

        //3. item 결정
        //ContactVO
        val contactList = ArrayList<ContactVO>()
        val db = Firebase.database//지금 연동된 내 DB랑 연결됨
        val contact2 = db.getReference("contact2")//연락처 등록할 때 쓰던 키 값



        //4.Adapter 결정
        //requireContext(): Fragment에서 페이지정보(화면정보) 호출할 때 사용
        val adapter = ContactAdapter(requireContext(), contactList)

        //5. Container에 Adapter 부착
        rvContact.adapter = adapter

        //5-1. 레이아웃 메니저 세팅
        rvContact.layoutManager = LinearLayoutManager(requireContext())

        //하위값이 변화하는 걸 감지
        contact2.addChildEventListener(object : ChildEventListener{
            //object에서 Alt + O 해서 5개 멤버(메서드) 오버라이드 해주기
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val contact = snapshot.getValue<ContactVO>() as ContactVO
                contactList.add(contact)
                //추가가 완료된 이후 Adapter 새로고침하기
                adapter.notifyDataSetChanged()
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
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