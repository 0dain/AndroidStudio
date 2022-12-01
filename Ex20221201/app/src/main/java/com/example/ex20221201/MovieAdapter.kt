package com.example.ex20221201

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter(val context: Context, val movieList: ArrayList<MovieVO>): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvRank: TextView
        val tvOld: TextView
        val tvTitle: TextView
        val tvAcc: TextView
        val tvOpen: TextView

        init {
            tvRank = itemView.findViewById(R.id.tvRank)
            tvOld = itemView.findViewById(R.id.tvOld)
            tvTitle = itemView.findViewById(R.id.tvTitle)
            tvAcc = itemView.findViewById(R.id.tvAcc)
            tvOpen = itemView.findViewById(R.id.tvOpen)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.movie_list, null)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvRank.setText(movieList.get(position).rank)
        holder.tvOld.setText(movieList.get(position).rankOldAndNew)
        holder.tvTitle.setText(movieList.get(position).movieNm)
        holder.tvAcc.setText(movieList.get(position).audiAcc)
        holder.tvOpen.setText(movieList.get(position).openDt)

    }

    override fun getItemCount(): Int {
        return movieList.size
    }

}