package com.example.question1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Question3 : AppCompatActivity() {

    var musicList = ArrayList<MusicVO>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question3)

        var rvHot = findViewById<RecyclerView>(R.id.rvHot)

        musicList.add(MusicVO(R.drawable.nxde, "Nxde", "아이들"))
        musicList.add(MusicVO(R.drawable.nxde, "LOVE", "아이들"))
        musicList.add(MusicVO(R.drawable.nxde, "조각품", "아이들"))
        musicList.add(MusicVO(R.drawable.nxde, "Reset", "아이들"))
        musicList.add(MusicVO(R.drawable.girls, "forever1", "소녀시대"))
        musicList.add(MusicVO(R.drawable.girls, "Lucky Like That", "소녀시대"))
        musicList.add(MusicVO(R.drawable.girls, "Closer", "소녀시대"))
        musicList.add(MusicVO(R.drawable.lee, "어차피 잊을거면서", "이준호"))
        musicList.add(MusicVO(R.drawable.lee, "canvas", "이준호"))
        musicList.add(MusicVO(R.drawable.lee, "Fine", "이준호"))

        val adapter = MusicAdapter(this, musicList)

        rvHot.adapter = adapter

        rvHot.layoutManager = LinearLayoutManager(this)

    }
}