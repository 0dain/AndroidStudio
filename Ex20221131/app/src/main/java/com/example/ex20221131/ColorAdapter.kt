package com.example.ex20221131

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

//RecyclerView.Adapter<어디에 있는 ViewHolder를 상속 받을 건지 써주기>()
class ColorAdapter(val context: Context, val colorList:ArrayList<ColorVO>): RecyclerView.Adapter<ColorAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val clColor: ConstraintLayout

        init {
            clColor = itemView.findViewById<ConstraintLayout>(R.id.clColor)

            itemView.setOnClickListener {
                val position = adapterPosition
                val color = colorList.get(position).color
                val sharedPreferences = context.getSharedPreferences("sp1", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()//컬러 정보 저장하기 위한 editor
                editor.putString("bgColor", color)
                editor.commit()
                (context as Activity).finish()

//                System.exit(0)//지금 보여지고 있는 Activity를 종료
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(context)//xml 파일을 View 객체로 전환
        val view = layoutInflater.inflate(R.layout.color_list, null)

        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val color: String = colorList.get(position).color
        holder.clColor.setBackgroundColor(Color.parseColor(color))

    }

    override fun getItemCount(): Int {
        return colorList.size
    }

}