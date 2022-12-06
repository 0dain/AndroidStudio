package com.example.fullstackapplication.tip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fullstackapplication.R

class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        //Fragment2에서 ImageView를 클릭했을 때 넘어와서 구현되어야 할 View들

        //TextView
        //RecyclerView --> Adapter, data(VO), template(xml)

        //Adapter -> ListAdapter
        //data -> ListVO
        //template -> list_template.xml


    }
}