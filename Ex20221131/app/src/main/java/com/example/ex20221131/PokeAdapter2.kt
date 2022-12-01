package com.example.ex20221131

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

//outer class
class PokeAdapter2(val context: Context, val pokeList: ArrayList<PokeVO>) : RecyclerView.Adapter<PokeAdapter2.ViewHolder>() {

    //Java에서는 OnClickEvent를 구현함(Interface 형태로)

    //inner Class 명시를 해야 outer Class의 member들에 접근할 수 있음
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val imgPoke: ImageButton
        val tvPokeLv: TextView
        val tvPokeName: TextView
        val tvPokeType: TextView

        init { // 각각의 뷰가 초기화 되는 곳
            imgPoke = itemView.findViewById(R.id.imgPoke)
            tvPokeLv = itemView.findViewById(R.id.tvPokeLv)
            tvPokeName = itemView.findViewById(R.id.tvPokeName)
            tvPokeType = itemView.findViewById(R.id.tvPokeType)

            //1. ListView의 setOnItemClickListener
            itemView.setOnClickListener {
                //해당 아이템의 position 정보가 필요함
                val position:Int = adapterPosition//현재 이 상태의 position을 나타냄
                pokeList.removeAt(position)
                notifyDataSetChanged()//새로고침 -> Adapter 안에 있는 게 아니기 때문에 Adapter 안 써줘도 됨
            }

            imgPoke.setOnClickListener {
                //피카츄 클릭 하면 -> 피카츄입니다 출력
                val position: Int = adapterPosition//전역변수로 선언하면 안 됨 => 지금 클릭 할 때 작동해야 하기 때문!
//                Toast.makeText(context, pokeList.get(position).name+"입니다", Toast.LENGTH_SHORT).show()

                //레벨: 1 / 피카츄 / 타입: 전기로 출력해보기!
                val level: String = pokeList.get(position).level
                val name: String = pokeList.get(position).name
                val type: String = pokeList.get(position).type

                val result: String = "레벨: $level / $name / 타입: $type"
                Toast.makeText(context, result, Toast.LENGTH_SHORT).show()

            }

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