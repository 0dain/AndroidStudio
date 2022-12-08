package com.example.fullstackapplication.tip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.fullstackapplication.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ListActivity : AppCompatActivity() {

    lateinit var adapter: ListAdapter

    //게시물의 uid값이 들어갈 가변배열
    var keyData = ArrayList<String>()

    //북마크 경로 설정을 위한 선언
    lateinit var bookmarkRef: DatabaseReference

    //북마크된 게시물의 정보 저장할 배열
    var bookmarkList = ArrayList<String>()

    //2번 방법
    var auth: FirebaseAuth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val rvList = findViewById<RecyclerView>(R.id.rvList)
        val tvCategory = findViewById<TextView>(R.id.tvCategory)

        //RealTime Database에 필요한 객체 선언
        val db = Firebase.database

        //database에 어떤 것을 참조할 건지 설정
        //Fragment2에서 전체보기를 눌렀을 때 가져올 데이터가 저장됨
        val allContent = db.getReference("content")

        //push(): key값으로 타임스템프를 찍어서 고유한 값으로 만들어줌, 게시물을 구분할 수 있는 고유한 값을 만드는 것
//        allContent.push().setValue(
//            ListVO("https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FOtaMq%2Fbtq67OMpk4W%2FH1cd0mda3n2wNWgVL9Dqy0%2Fimg.png", "요리하기1", "https://philosopher-chan.tistory.com/1249")
//        )

        //북마크 경로
        bookmarkRef = db.getReference("bookmarklist")


        //Fragment2에서 intent를 통해 보낸 데이터 꺼내주기(tag값)
        val category = intent.getStringExtra("category")

        //꺼낸 tag값 tvCategory에 넣어줌!
        tvCategory.text = category


        //Fragment2에서 ImageView를 클릭했을 때 넘어와서 구현되어야 할 View들

        //TextView
        //RecyclerView --> Adapter, data(VO), template(xml)

        //Adapter -> ListAdapter
        //data -> ListVO
        //template -> list_template.xml

        //이미지의 id(Int), title(String) --> VO로 묶어야 할 데이터
        val tipList = ArrayList<ListVO>()
        //3~4개 임의 데이터 넣기
//        tipList.add(ListVO("https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FOtaMq%2Fbtq67OMpk4W%2FH1cd0mda3n2wNWgVL9Dqy0%2Fimg.png", "요리하기1", "https://philosopher-chan.tistory.com/1249"))
//        tipList.add(ListVO("https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FmBh5u%2Fbtq651yYxop%2FX3idRXeJ0VQEoT1d6Hln30%2Fimg.png", "요리하기2", "https://philosopher-chan.tistory.com/1242"))
//        tipList.add(ListVO("https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcH5pus%2Fbtq66jlPrFq%2FQKIxxS85V7CBr3fBc1nZCk%2Fimg.png", "요리하기3", "https://philosopher-chan.tistory.com/1247"))
//        tipList.add(ListVO("https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FFtY3t%2Fbtq65q6P4Zr%2FWe64GM8KzHAlGE3xQ2nDjk%2Fimg.png", "요리하기4", "https://philosopher-chan.tistory.com/1248"))

        //template.xml -> imageView 하나, TextView 하나, 북마크 Image View

        //임의 데이터를 Firebase에 넣자!
        //content(하나의 카테고리)
        //uid(게시물을 구분할 수 있는 uid)
        //imgId, title, url 값을 가지게 만들어 줄 거임
//        for (i in 0 until tipList.size){
//            allContent.push().setValue(
//                tipList[i]
//            )
//        }

        //RealTime Database에 넣은 값 가져오기
        //Firebase에서 데이터를 받아오는 Listener
        val postListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("스냅샷", snapshot.toString())
                //value: key값의 value 값을 가져옴
                Log.d("스냅샷", snapshot.value.toString())

                //model에 snapshot.children을 담겠다
                for (model in snapshot.children){
                    val item = model.getValue(ListVO::class.java)
                    //model에는 여러가지 게시물이 담겨있고 1개에 대한 게시물에 접근하기 위해 value를 ListVO 타입으로 받아옴
                    if (item != null) {
                        tipList.add(item)
                    }

                    //북마크 관련
                    keyData.add(model.key.toString())


                }
                //데이터를 받아 온 직후에 Adapter 새로고침!
                //왜 새로고침?! => 데이터를 받아오는 속도가 Adapter가 실행되는 속도보다 느리기 때문에!
                adapter.notifyDataSetChanged()

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }
        allContent.addValueEventListener(postListener)

        //북마크 경로
        getBookMarkData()

        //Adapter: 리사이클러뷰를 상속받게!
            //ViewHolder, OnCreateView, OnbindingView, getItemCount
        //Adapter 생성(위에서 전역 변수로 만들었음)
        adapter = ListAdapter(this@ListActivity, tipList, keyData, bookmarkList)
        //Adapter 부착
        rvList.adapter = adapter

        //ListActivity에서 내가 만든 ListAdapter를 rv에 적용하기!
            //단, GridLayoutManager 사용해서 두 줄로 쌓이게!
//        rvList.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
//        rvList.layoutManager = GridLayoutManager(this(화면정보), 2(몇 칸씩 할 건지))
        rvList.layoutManager = GridLayoutManager(this, 2)

    }

    //bookmarklist에 저장되어 있는 데이터 가져오는 함수
    fun getBookMarkData(){
        val postListener2 = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                //ListActivity를 들릴 때 마다 데이터가 누적됨 그래서 clear 해주는 거임!
                bookmarkList.clear()

                for (model in snapshot.children){
                    Log.d("북마크", model.toString())
                    Log.d("북마크", model.key.toString())
                    //북마크 게시물의 uid값을 bookmarkList에 저장
                    //1번 방법
//                    bookmarkList.add(model.value.toString())
                    //2번 방법
                    //아래에서 bookmarkRef.child(auth.currentUser!!.uid).addValueEventListener(postListener2)를 쓰면
                    //이렇게 받아옴
                    bookmarkList.add(model.key.toString())

                    Log.d("사이즈", bookmarkList.size.toString())

                }
                //Adapter 새로고침
                //데이터 받아 오는 속도가 느리기 때문!
                adapter.notifyDataSetChanged()

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }
        //bookmarklist경로에 있는 데이터를 snapshot으로 받아옴
        //1번 방법
//        bookmarkRef.addValueEventListener(postListener2)
        //2번 방법
        bookmarkRef.child(auth.currentUser!!.uid).addValueEventListener(postListener2)
    }




}