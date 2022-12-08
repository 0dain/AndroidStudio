package com.example.fullstackapplication.tip

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fullstackapplication.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ListAdapter(val context: Context, val tipList: ArrayList<ListVO>, val keyData: ArrayList<String>, val bookmarkList: ArrayList<String>): RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    //BaseAdapter: 일반 ListView
    //RecyclerView: RecyclerViewAdapter 상속

    //북마크 관련
    val db = Firebase.database
    //북마크를 한 회원 구분을 위해
    val auth: FirebaseAuth = Firebase.auth

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        //list_template.xml에 있는 View들을 사용하기 위해 View 선언
        val clTipList: ConstraintLayout
        val imgThum: ImageView
        val tvTitle: TextView
        val imgMark: ImageView

        init {
            //View 초기화
            clTipList = itemView.findViewById(R.id.clTipList)
            imgThum = itemView.findViewById(R.id.imgThum)
            tvTitle = itemView.findViewById(R.id.tvTitle)
            imgMark = itemView.findViewById(R.id.imgMark)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //만들어진 ItemView가 없을 때 ViewHolder을 만듦
        //xml코드를 눈에 보이는 View 객체로 바꿔서 ViewHolder로 보내주는 역할
        val layoutInflater = LayoutInflater.from(context)//context를 통해서 inflater 추출
        //getSystemService: 하드웨어가 가지고 있는 많은 센서 서비스들이 담겨있음(진동, 수평, 온도 등)

        //xml코드를 눈에 보이는 객체로 바꿔주기
            //list_template에 있는 걸 눈에 보이는 객체로 바꿔주라는 뜻!
        val view = layoutInflater.inflate(R.layout.list_template, null)

        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //만들어진 ViewHolder가 있다면 여기서 꺼내서 써라!
//        holder.imgThum.setImageResource(tipList[position].imgId)
//        Glide.with(context).load(tipList[position].imgId).into(holder.imgThum)
        Glide.with(context).load(tipList[position].imgId).into(holder.imgThum)
        holder.tvTitle.text = (tipList[position].title)

        //imgView를 클릭했을 때 url 값을 가지고 WebViewActivity로 넘어가기
        holder.imgThum.setOnClickListener {

            //여기는 activity가 아니라서 this를 쓸 수 없음 화면정보를 넘겨주는 context를 써줘야 함
            val intent = Intent(context, WebViewActivity::class.java)
            intent.putExtra("url", tipList[position].url)

            //여기는 activity가 아니라서 startActivity를 할 수 없음 context의 힘을 빌려서 startActivity해주자!
            context.startActivity(intent)

        }

        //이미 북마크 체크가 되어 있는 경우 처음부터 까만 북마크가 보여야 함! => Adapter가 실행되는 순간 판단해서 색칠할 수 있게 해야 함
            //아래에 있는 클릭을 했을 때 색깔을 바꾸면 기존에 있는 북마크 색이 바뀌지 않음
        if(bookmarkList.contains(keyData[position])){
            holder.imgMark.setImageResource(R.drawable.bookmark_color)
        }else{
            holder.imgMark.setImageResource(R.drawable.bookmark_white)
        }

        //북마크 모양을 클릭했을 때 해당 게시물의 uid값이 bookmarklist 경로로 들어가야 함
        holder.imgMark.setOnClickListener {

            //Firebase에 있는 bookmarklist경로로 접근
            val bookmarkRef = db.getReference("bookmarklist")
//            bookmarkRef.child(keyData[position]).setValue("good")
            //북마크한 회원 구분을 위해 .child(auth.currentUser!!.uid) 추가
            //단정기호 쓴 이유: 로그인을 하지 않으면 어플 접속 불가능
            bookmarkRef.child(auth.currentUser!!.uid).child(keyData[position]).setValue("good")

            //이미 저장이 되어 있는 게시물인지 아닌지 판단
            //bookmarkList에 해당 게시물이 포함되어 있는지 확인하면 됨
            //contains: 포함되어 있는지 확인하는 것
            if (bookmarkList.contains(keyData[position])){
                //북마크를 취소
                //database에서 해당 keyData 삭제
                bookmarkRef.child(auth.currentUser!!.uid).child(keyData[position]).removeValue()

                //imgMark 사진 변경 => 흰색
                holder.imgMark.setImageResource(R.drawable.bookmark_white)
            }else{
                //북마크 추가가
                //database에 해당 keyData 추가
                bookmarkRef.child(auth.currentUser!!.uid).child(keyData[position]).setValue("good")

                //imgMark 사진 변경 => 검정
                holder.imgMark.setImageResource(R.drawable.bookmark_color)
           }

        }

    }

    override fun getItemCount(): Int {
        return tipList.size
    }


}