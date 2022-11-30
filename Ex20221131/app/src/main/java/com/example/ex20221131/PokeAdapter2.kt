package com.example.ex20221131

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PokeAdapter2(val context: Context, val pokeList: ArrayList<PokeVO>) : RecyclerView.Adapter<PokeAdapter2.ViewHolder>() {

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val imgPoke: ImageView
        val tvPokeLv: TextView
        val tvPokeName: TextView
        val tvPokeType: TextView

        init {
            imgPoke = itemView.findViewById(R.id.imgPoke)
            tvPokeLv = itemView.findViewById(R.id.tvPokeLv)
            tvPokeName = itemView.findViewById(R.id.tvPokeName)
            tvPokeType = itemView.findViewById(R.id.tvPokeType)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //itemView가 없을 때 ViewHolder를 만듦
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.poke_list, null)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //만들어진 ViewHolder가 있다면 여기서 꺼내서 씀!
        holder.imgPoke.setImageResource(pokeList.get(position).img)
        holder.tvPokeLv.setText(pokeList.get(position).level)
        holder.tvPokeName.setText(pokeList.get(position).name)
        holder.tvPokeType.setText(pokeList.get(position).type)
    }

    override fun getItemCount(): Int {
        //몇 번 만들어지는지
        return pokeList.size

    }

}