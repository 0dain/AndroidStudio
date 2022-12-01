package com.example.ex20221131

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ColorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color)

        //1. Container 설정
        val rvColor = findViewById<RecyclerView>(R.id.rvColor)

        //2. Template 결정
        //color_list.xml

        //3. Item 결정
        val colorList = ArrayList<ColorVO>()
        var color: String

        //# + red(16)+green(16)+blue(16)
        for(i in 0 .. 255 step 32){
            var red: String = Integer.toHexString(i)//16진수로 변환

            for(j in 0 .. 255 step 32){
                var green: String = Integer.toHexString(j)//16진수로 변환

                for(k in 0 .. 255 step 32){
                    var blue: String = Integer.toHexString(k)//16진수로 변환

                    if(red.length == 1) red += 0
                    if(green.length == 1) green += 0
                    if(blue.length == 1) blue += 0

                    color = "#$red$green$blue"
                    colorList.add(ColorVO(color))
                }

            }
        }


        //4. Adapter 설정
        val adapter = ColorAdapter(this, colorList)

        //5. Container에 Adapter 부착
        rvColor.adapter = adapter
//        rvColor.layoutManager = GridLayoutManager(this, 10)
        rvColor.layoutManager = GridLayoutManager(this,40)
//        rvColor.layoutManager = LinearLayoutManager(this)
//        rvColor.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)



    }
}