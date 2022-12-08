package com.example.fullstackapplication.tip

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fullstackapplication.R

class BookMarkAdapter(val context: Context, val data: ArrayList<ListVO>, val keyData: ArrayList<String>, val bookmarkList: ArrayList<String>):RecyclerView.Adapter<BookMarkAdapter.ViewHolder>() {
    //Fragment4번과 연결
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val imgMark: ImageView
        val imgThum: ImageView
        val tvTitle: TextView

        init {
            imgMark = itemView.findViewById(R.id.imgMark)
            imgThum = itemView.findViewById(R.id.imgThum)
            tvTitle = itemView.findViewById(R.id.tvTitle)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //list_template.xml을 눈에 보이는 View객체로 바꾼다
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.list_template, null)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //북마크 데이터에 포함되어있는지 판단해서 view + data랑 합쳐주는 작업을 진행

            holder.tvTitle.text = data[position].title
            //이미지는 Glide를 활용중 => 웹에 있는 이미지를 가져와서 세팅, imgId가 웹페이지에 있는 이미지 주소값임!
            Glide.with(context).load(data[position].imgId).into(holder.imgThum)


        //가져올 때 북마크 이미지 검정색으로 변경

            holder.imgMark.setImageResource(R.drawable.bookmark_color)

    }

    override fun getItemCount(): Int {

        return data.size //null값 없음~
    }


}