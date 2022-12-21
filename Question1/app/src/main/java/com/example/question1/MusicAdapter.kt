package com.example.question1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MusicAdapter(val context: Context, val musicList: ArrayList<MusicVO>): RecyclerView.Adapter<MusicAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imgThum:ImageView
        val tvTitle: TextView
        val tvName:TextView
        val tvLike: TextView

        init {
            imgThum = itemView.findViewById(R.id.imgThum)
            tvTitle = itemView.findViewById(R.id.tvTitle)
            tvName = itemView.findViewById(R.id.tvName)
            tvLike = itemView.findViewById(R.id.tvLike)

            tvLike.setOnClickListener {
                var like = tvLike.text

                if (like == "🤍"){
                    tvLike.text = "❤"
                }else{
                    tvLike.text = "🤍"
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.music_list, null)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imgThum.setImageResource(musicList[position].img)
        holder.tvTitle.text=musicList[position].title
        holder.tvName.text=musicList[position].name
    }

    override fun getItemCount(): Int {
        return musicList.size
    }


}