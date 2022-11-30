package com.example.directapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView

class DirectAdapter(val context: Context, val layout: Int, val data: ArrayList<DirectVO>) : BaseAdapter() {

    var inflater =context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?

    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(p0: Int): Any {
        return data[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        var view=p1

        if(view == null){
            view = inflater?.inflate(layout, p2, false)
        }

        val tvTitle = view?.findViewById<TextView>(R.id.tvTitle)
        val tvUrl = view?.findViewById<TextView>(R.id.tvUrl)
        val btnSend = view?.findViewById<Button>(R.id.btnSend)

        tvTitle?.text = data[p0].title
        tvUrl?.text = data[p0].url

        btnSend?.setOnClickListener {

        }

        return view!!

    }


}
