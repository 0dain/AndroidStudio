package com.example.fullstackapplication.tip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.fullstackapplication.R

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val rvList = findViewById<RecyclerView>(R.id.rvList)
        val tvCategory = findViewById<TextView>(R.id.tvCategory)

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
            //이미지 3~4개 임의 데이터 넣기
        val tipList = ArrayList<ListVO>()
        tipList.add(ListVO("https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FOtaMq%2Fbtq67OMpk4W%2FH1cd0mda3n2wNWgVL9Dqy0%2Fimg.png", "요리하기1", "https://philosopher-chan.tistory.com/1249"))
        tipList.add(ListVO("https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FmBh5u%2Fbtq651yYxop%2FX3idRXeJ0VQEoT1d6Hln30%2Fimg.png", "요리하기2", "https://philosopher-chan.tistory.com/1242"))
        tipList.add(ListVO("https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcH5pus%2Fbtq66jlPrFq%2FQKIxxS85V7CBr3fBc1nZCk%2Fimg.png", "요리하기3", "https://philosopher-chan.tistory.com/1247"))
        tipList.add(ListVO("https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FFtY3t%2Fbtq65q6P4Zr%2FWe64GM8KzHAlGE3xQ2nDjk%2Fimg.png", "요리하기4", "https://philosopher-chan.tistory.com/1248"))

        //template.xml -> imageView 하나, TextView 하나, 북마크 Image View

        //Adapter: 리사이클러뷰를 상속받게!
            //ViewHolder, OnCreateView, OnbindingView, getItemCount
        //Adapter 생성
        val adapter = ListAdapter(this@ListActivity, tipList)
        //Adapter 부착
        rvList.adapter = adapter

        //ListActivity에서 내가 만든 ListAdapter를 rv에 적용하기!
            //단, GridLayoutManager 사용해서 두 줄로 쌓이게!
//        rvList.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
//        rvList.layoutManager = GridLayoutManager(this(화면정보), 2(몇 칸씩 할 건지))
        rvList.layoutManager = GridLayoutManager(this, 2)

    }
}