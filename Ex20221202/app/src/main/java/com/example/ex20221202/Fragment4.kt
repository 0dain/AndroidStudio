package com.example.ex20221202

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

class Fragment4 : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var view = inflater.inflate(R.layout.fragment_4, container, false)
        //id값 찾아오기
        val etUrl = view.findViewById<EditText>(R.id.etUrl)
        val btnSend = view.findViewById<Button>(R.id.btnSend)

        //버튼 클릭 시
        btnSend.setOnClickListener {

            //ShardPreference에 Url값 저장
            val url = etUrl.text.toString()

            //1. SharedPreference 가져오기
            //1-1. 이름 지정
            //1-2. 모드 설정
            //requireActivity.getSharedPreference()해줘야 함
            val spf = requireActivity().getSharedPreferences(
                "mySPF",
                Context.MODE_PRIVATE
            )
            //2. ShardPreference에 데이터를 저장할 수 있는 editor 가져오기
            val editor = spf.edit()

            //3. editor를 통해서 데이터 저장하기(commit)
            editor.putString("url", url).commit()

            // + Fragment1에서 꺼내서 url에 설정

        }


        return view
    }



}