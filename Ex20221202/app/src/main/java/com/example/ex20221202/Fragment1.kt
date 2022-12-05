package com.example.ex20221202

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient

class Fragment1 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_1, container, false)

        //webview찾아오기
        val wv = view.findViewById<WebView>(R.id.wv)
        //WebView에 원하는 웹페이지 띄우기

        val spf = requireActivity().getSharedPreferences(
            "mySPF",
            Context.MODE_PRIVATE
        )

        //1. 주소 준비
//        val url = "https://www.smhrd.or.kr/"
//        val url = spf.getString("url", "http://www.google.co.kr(기본값 -> null일 때 이걸 실행시켜라)")
        val url = spf.getString("url", "http://www.google.co.kr")
        //2. 설정 변경(JavaScript 사용 가능하다록 '허용')
        val ws = wv.settings
        ws.javaScriptEnabled = true

        //3. WebView에 Client 적용
        wv.webViewClient = WebViewClient()

        //4. 웹 뷰에 주소 적용
        //default Value가 있음
        wv.loadUrl(url!!) //내가 준비한 url 넣어주기

        return view
    }

}