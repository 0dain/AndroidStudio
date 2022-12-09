package com.example.fullstackapplication.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.fullstackapplication.R
import com.example.fullstackapplication.board.BoardWriteActivity

class Fragment3 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_3, container, false)

        val rvBoard = view.findViewById<RecyclerView>(R.id.rvBoard)
        val btnWrite = view.findViewById<Button>(R.id.btnWrite)

        //btnWrite를 클릭하면 BoardWriteActivity로 이동
        btnWrite.setOnClickListener {
            val intent = Intent(requireActivity(), BoardWriteActivity::class.java)//manifests에 경로 확인해서 오류나면 수정해주기~
            startActivity(intent)
        }


        return view
    }


}