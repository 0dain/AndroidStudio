package com.example.fullstackapplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fullstackapplication.R
import com.example.fullstackapplication.tip.BookMarkAdapter
import com.example.fullstackapplication.tip.ListVO
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Fragment4 : Fragment() {

    //Firebase
    var auth: FirebaseAuth = Firebase.auth
    var db = Firebase.database
    val contentRef = db.getReference("content")
    val bookmarkRef = db.getReference("bookmarklist")

    //ArrayList
    var data = ArrayList<ListVO>()
    var keyData = ArrayList<String>()//ListVO를 포함하는 게시물의 uid
    var bookmarkList = ArrayList<String>()//내가 선택한 게시물 uid

    //Adapter 선언
    lateinit var adapter: BookMarkAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_4, container, false)

        val rvBookMark = view.findViewById<RecyclerView>(R.id.rvBookMark)

        //1. 전체 컨텐츠 데이터들을 다 가져오자
//        getContentData()
        //여기서 호출하면 북마크 체크가 안 되어 있는 데이터까지 전부 다 가져오게 되므로
        //getBookMarkData 함수 안에서 호출할 수 있도록 하자~

        //2. 사용자가 북마크한 정보를 다 가져오자
        getBookMarkData()//bookmakrList를 다 가지고온 뒤쪽에 getContent가 실행되고 있음
        //getContent가 하고 있는 일: 전체 데이터를 가져오는 것이 아니라 bookmarkList에 있는 것만 가져오는 역할을 함

        //3. 전체 컨텐츠 중에서 사용자가 북마크한 정보만 화면에 출력! => RecyclerView에 반영
        adapter = BookMarkAdapter(requireActivity(), data, keyData, bookmarkList)

        //4. Adapter 적용
        rvBookMark.adapter = adapter

        //4-1. 레이아웃 매니저 적용
        rvBookMark.layoutManager = GridLayoutManager(requireContext(), 2)

        return view
    }

    //전체 보기에 있는 게시물 중에서 북마크가 찍힌 데이터(VO)만 가져와야 함
    //
    fun getContentData(){
        //content 경로에 있는 데이터를 다 가지고 오자
        //uid --> keyData
            //ListVO --> data
        val posterListener = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for(model in snapshot.children){
                    val item = model.getValue(ListVO::class.java)

                    //북마크 리스트에 있는 데이터만 가져오게 수정하자!
//                    if (item != null) { //이건 전부다 가져오게 함 ㅠ.ㅠ
//                        data.add(item)
//                    }
                    //bookmarkList에 값이 있어야 함
                    if(bookmarkList.contains(model.key.toString())){
                        if(item != null){
                            data.add(item)
                            //data에 내가 북마크한 게시물만 담기게 됨
                        }
                        keyData.add(model.key.toString())
                    }

                }
                //Adapter 새로고침 하기
                adapter.notifyDataSetChanged()

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }
        contentRef.addValueEventListener(posterListener)
        //snapshot : Content 경로에 있는 데이터 전부를 가져옴


    }

    fun getBookMarkData(){
        //bookmarklist경로에 있는 데이터를 다 가지고 오자
        //게시물의 uid값 -> bookmarkList에 담아줄거임
        val postListener2 = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                //같은 데이터가 누적이 되기 때문에 clear해줘야 함
                bookmarkList.clear()
                for(model in snapshot.children){
                    bookmarkList.add(model.key.toString())
                }
                //Adapter 새로고침 하기
                adapter.notifyDataSetChanged()
                //북마크된 자료만 가져올 수 있도록!(bookmarklist에 있는 데이터만 가져와서 data(ArrayList<Vo>)에 담는다!
                getContentData()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }
        bookmarkRef.child(auth.currentUser!!.uid).addValueEventListener(postListener2)


    }

    //반복되는 코드들 --> Class 함수로 사용


}