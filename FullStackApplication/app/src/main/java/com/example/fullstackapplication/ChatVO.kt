package com.example.fullstackapplication

data class ChatVO(val name: String, val msg: String) {

    //반드시 FireBase RealTime DataBase를 사용하려면 기본생성자를 만들어야 함
    constructor(): this("","")

}