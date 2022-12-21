package com.example.ex20221201

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class WeatherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        val rvWeather = findViewById<RecyclerView>(R.id.rvWeather)
        val btnWeather = findViewById<Button>(R.id.btnWeather)

        val weatherList = ArrayList<WeatherVO>()

        //Volley 네트워크 통신 4단계
        //1. Volley 라이브러리 설정
        //1-1. 라이브러리 추가, 인터넷 권한, 요청 방식이 http 인지 https인지 !
        //1-2. http면 manifests에 android:usesCleartextTraffic="true" 추가 하기!
        //2. RequestQueue 생성
        val requestQueue = Volley.newRequestQueue(this)

        val adapter = WeatherAdapter(this, weatherList)
        rvWeather.adapter = adapter
        rvWeather.layoutManager = LinearLayoutManager(this)

        //3. Request 생성
        btnWeather.setOnClickListener {

            val cityList = ArrayList<String>()
            cityList.add("Gwangju")
            cityList.add("Seoul")
            cityList.add("Jeju-do")
            cityList.add("New York")
            cityList.add("Katarzyna")
            cityList.add("Madrid")
            cityList.add("Beijing")
            cityList.add("London")

            val requestList = ArrayList<StringRequest>()

            for(i in 0 until cityList.size){
                val url = "https://api.openweathermap.org/data/2.5/weather?q=${cityList[i]}&appid=0191cb6e487f18ad2edd5dcf1dc5e49a&units=metric"

                val request = StringRequest(
                    Request.Method.GET,
                    url,
                    {response ->
                        Log.d("날씨 $i", response)
                        //weather[0] → main
                        val result = JSONObject(response)
                        val weathers = result.getJSONArray("weather")
                        val weather = weathers[0] as JSONObject //as JSONObject JSONObject로 다운캐스팅한다는 뜻
                        val state = weather.getString("main")

                        //main → temp, humidity
                        val main = result.getJSONObject("main")
                        val temp = main.getString("temp")
                        val humidity = main.getString("humidity")

                        //wind → speed
                        val wind = result.getJSONObject("wind")
                        val speed = wind.getString("speed")

                        Log.d("현재날씨$i", "상태: $state, 온도: $temp, 습도: $humidity, 풍속: $speed")
                        weatherList.add(WeatherVO(cityList[i], state, temp, humidity, speed))
                        adapter.notifyDataSetChanged()//새로고침
                    },
                    {}
                )
                requestList.add(request)
            }

            for(i in 0 until requestList.size){
                requestQueue.add(requestList[i])
            }

//            Thread.sleep(10000)//1초 지연




       }



    }
}