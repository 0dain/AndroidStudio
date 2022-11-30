package com.example.ex20221131

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class PokeAdapter(val context: Context, val pokeList: ArrayList<PokeVO>) : BaseAdapter() {

    override fun getCount(): Int {
        //item(항목 뷰)가 몇 번 만들어져야 하는 지
        return pokeList.size
    }

    override fun getItem(p0: Int): Any {
        //p0: position
        return pokeList[p0]
        //또는 pokeList.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        //항목에 대한 id값
        return p0.toLong()

    }

    //🌟🌟🌟🌟🌟
    //p0: position
    //p1: itemView
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        //item View 하나하나를 생성

        //0. xml -> kotlin: Inflater
        val layoutInflater = LayoutInflater.from(context)

        //1. poke_list.xml -> 코드로 접근할 수 있게
        var view = layoutInflater.inflate(R.layout.poke_list, null)
        var holder = ViewHolder()//아래에서 만든 ViewHolder Class

        if(p1 == null){
            Log.d("호출", "1")
            //항목 뷰(itemView)가 없을 때
            //각 component들을 초기화 시켜주자
            holder.imgPoke = view.findViewById<ImageView>(R.id.imgPoke)
            holder.tvPokeLv = view.findViewById<TextView>(R.id.tvPokeLv)
            holder.tvPokeName = view.findViewById<TextView>(R.id.tvPokeName)
            holder.tvPokeType = view.findViewById<TextView>(R.id.tvPokeType)

            view.tag=holder
        }else{
            Log.d("호출", "2")
            //항목 뷰(itemView)가 있을 때
            holder = p1.tag as ViewHolder
            view = p1
        }

//        val imgPoke = view.findViewById<ImageView>(R.id.imgPoke)
//        val tvPokeLv = view.findViewById<TextView>(R.id.tvPokeLv)
//        val tvPokeName = view.findViewById<TextView>(R.id.tvPokeName)
//        val tvPokeType = view.findViewById<TextView>(R.id.tvPokeType)

        holder.imgPoke?.setImageResource(pokeList.get(p0).img)
        holder.tvPokeLv?.setText("레벨: "+pokeList.get(p0).level)
        holder.tvPokeName?.setText(pokeList.get(p0).name)
        holder.tvPokeType?.setText("타입: "+pokeList.get(p0).type)

        return view

    }

    //innerClass 사용하는 이유
        //1. 부모 클래스의 변수들을 다 사용할 수 있음
        //2. 외부에서 이 inner Class를 사용할 이유가 없을 때

    //Design Pattern
    //MVC

    //ViewHolder Pattern
    //getView의 findViewById로 접근한 정보들을 저장하고 있는 Class ViewHolder를 만들어서 메모리의 성능 향상!

    class ViewHolder(){
        var imgPoke: ImageView? = null
        var tvPokeLv: TextView? = null
        var tvPokeName: TextView? = null
        var tvPokeType: TextView? = null

    }


}