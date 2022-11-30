package com.example.ex20221130

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class PhoneAdapter(val context: Context, val layout: Int, val data: ArrayList<PhoneVO>) : BaseAdapter() {

    //위에서 받아오는 context는 화면정보를 가져오는 거고 해당 View(화면정보)는 MainActivity임

    //Activity의 힘(context)을 빌려서 Inflater를 할 수 있는 Inflater를 가져오자
    var inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?
        //getSystemService()는 하드웨어(핸드폰)에 담겨있는 센서들이나 Inflater를 추출할 수 있는 메서드
        //많은 센서들이 담겨있고 각각의 리턴값을 설정해주지 힘드니까 Any타입으로 리턴하고 있음
        //내가 Inflater를 빼면 Inflater로 형변환 해줘야 함

    //프로퍼티: 필드
    //멤버: 메서드
    override fun getCount(): Int {
        //ListView의 항목의 개수
        //ArrayList의 size를 통해서 알 수 있음
        return data.size
    }

    override fun getItem(p0: Int): Any {
        //p0: position
        //position의 위치한 data를 반환
        return data[p0]
    }

    override fun getItemId(p0: Int): Long {
        //position번째 id 값을 반환
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        //제일 중요!!! 🌟🌟🌟🌟🌟🌟
        //데이터+템플릿을 합친 뷰를 return 해줌
        //findViewById, setContentView 사용할 수 없음! 왜?! => Activity만 할 수 있는 일!
            //여기 Class 위에 뷰를 찾아오는 메서드 사용 불가능
            //대신 Activity의 힘을 빌릴 수 있음! -> inflate
                //inflate: 코드로 있는 xml파일을 눈에 보이는 View로 바꿔주는 작업
                    //이걸 사용하려면 inflater를 가져와야 함!

        //p0: 항목의 번호(position)
        //p1: 이전에 만들어진 View(xml(코드)을 눈에 보이는 형태로 바꾼 거)가 있는지 확인
        //p2: 모든 View(항목)을 담고 있는 ListView

        var view = p1
        //p1: value(상수)라서 다른 값을 넣을 수 없음! 그래서 var view에다가 p1을 넣어버림!

        //코드로 존재하는 layout을 눈에 보이는 View객체로 바꿔주자~
            //이렇게 하고 나서 Id값을 찾아 올 수 있음
//        inflater.inflate(layout(어떤 layout인지), 누가 이 템플릿을 포함할 건지, false(기존에 있는 속성과 일치시킬 건지에 대한 여부))
        //p1을 가지고 만들어진 view가 없으면 inflate를 하고 view가 있으면 inflate를 하지 않게! => 계속 inflate를 하게 되면 성능이 떨어짐

        if(view == null){
            view = inflater?.inflate(layout, p2, false)
        }

//        var view = inflater?.inflate(layout, p2, false) //사용하고 싶으면 변수에 담아줘야 함

        //위에 만든 view를 통해서 id값을 가져올 수 있음!
        val tvName = view?.findViewById<TextView>(R.id.tvName)
        val tvTel = view?.findViewById<TextView>(R.id.tvTel)
        val img = view?.findViewById<ImageView>(R.id.img)
        val btnCall = view?.findViewById<Button>(R.id.btnCall)

        //ArrayList --> data --->(id, name, tel)
        tvName?.text = data[p0].name
        tvTel?.text = data[p0].tel
        img?.setImageResource(data[p0].imgId)
        //여기서 버튼 이벤트 달아줄거임~
            //activity_main.xml는 ListView를 적제하는 곳임, 버튼이 있는 phone_list.xml에서 이벤트를 부여할 수 있음
        btnCall?.setOnClickListener {
            //전화 번호를 가져와서 ACTION_DIAL이 만들자!
            //묵시적 intent
            //액션
            //액션, 데이터(URi => tel:)
            var tel = Uri.parse("tel:${tvTel?.text.toString()}")
            val intent = Intent(Intent.ACTION_DIAL, tel)

            //startActivity 불가능
                //새로운 Task(Stack 통)을 만들어서 실행
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                //Activity의 힘을 빌려서 Start할 예정
                    //Activity의 힘 => context
            context.startActivity(intent)

        }

        //inflate가 된 view를 리턴(데이터 + 템플릿)
        return view!!

    }
}