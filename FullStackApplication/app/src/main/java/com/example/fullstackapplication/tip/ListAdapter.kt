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

class ListAdapter(val context: Context, val tipList: ArrayList<ListVO>): RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    //BaseAdapter: 일반 ListView
    //RecyclerView: RecyclerViewAdapter 상속


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

    }

    override fun getItemCount(): Int {
        return tipList.size
    }


}