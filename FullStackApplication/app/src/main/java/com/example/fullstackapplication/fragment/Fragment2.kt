package com.example.fullstackapplication.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.fullstackapplication.R
import com.example.fullstackapplication.tip.ListActivity

class Fragment2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_2, container, false)//Fragment_2 코드를 눈에 보이는 객체로 바꿔서 view에 담음

        val imgAll = view.findViewById<ImageView>(R.id.imgAll)

        imgAll.setOnClickListener {
            //ListActivity로 넘어가자!
//            val intent = Intent(activity(Fragment는 Activity가 아니기 때문에 화면 정보를 받아 와야 함 => activity 또는 context를 써주면 됨), ListActivity::class.java)
            val intent = Intent(context, ListActivity::class.java)
            //클릭한 imgView에 해당하는 태그(tag)를 가지고 넘어갈 거임
            intent.putExtra("category", imgAll.tag.toString())

            startActivity(intent)
        }

        val imgCook = view.findViewById<ImageView>(R.id.imgCook)
        imgCook.setOnClickListener {
            val intent = Intent(context, ListActivity::class.java)
            intent.putExtra("category", imgCook.tag.toString())
            startActivity(intent)
        }

        val imgLife = view.findViewById<ImageView>(R.id.imgLife)
        imgLife.setOnClickListener {
            val intent = Intent(context, ListActivity::class.java)
            intent.putExtra("category", imgLife.tag.toString())
            startActivity(intent)
        }

        return view


    }


}