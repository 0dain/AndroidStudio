package com.example.fullstackapplication.tip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.fullstackapplication.R

class WebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        //받아온 url값을 꺼내서 해당 웹페이지가 WebView에 뜨게 만들자!

        val wv = findViewById<WebView>(R.id.wv)

        //ListAdapter에서 보내주는 url 값 받아주기 => 1. 주소 준비
        //받아 주는 값이 String? 타입이다! url을 사용할 때 null처리 해줘야 함
        val url = intent.getStringExtra("url")

        //2. 설정 변경(JavaScript 사용 가능하도록 '허용')
            //안드로이드 스튜디오에서는 JS가 지원되지 않기 때문!
        val ws = wv.settings
        ws.javaScriptEnabled = true

        //3. WebView에 Client 설정
        wv.webViewClient = WebViewClient()

        //4. 웹 뷰에 주소 적용
        //intent로 부터 값을 무조건 받아오는 이유: 값이 없으면 RecyclerView에서 안 보임(실행 안 됨)
        wv.loadUrl(url!!)


    }
}