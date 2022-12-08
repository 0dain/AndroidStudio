package com.example.fullstackapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatAdapter(val context: Context, val chatList: ArrayList<ChatVO>, val loginId: String): RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val imgChat: ImageView
        val tvChatOtherName: TextView
        val tvChatOtherMsg: TextView
        val tvChatMyMsg: TextView

        init {
            imgChat = itemView.findViewById(R.id.imgChat)
            tvChatOtherName = itemView.findViewById(R.id.tvChatOtherName)
            tvChatOtherMsg = itemView.findViewById(R.id.tvChatOtherMsg)
            tvChatMyMsg = itemView.findViewById(R.id.tvChatMyMsg)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.chat_list, null)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val name = chatList[position].name

        if(loginId == name){
            //로그인 한 아이디가 나라면 상대방 프로필, 이름, 메세지 내용 안 보이게
            holder.imgChat.visibility = View.INVISIBLE
            holder.tvChatOtherName.visibility = View.INVISIBLE
            holder.tvChatOtherMsg.visibility = View.INVISIBLE

            //내 메세지는 보이게
            holder.tvChatMyMsg.visibility = View.VISIBLE

            holder.tvChatMyMsg.text = chatList[position].msg

        }else{
            //로그인 한 아이디가 내가 아니라면 상대방 프로필, 이름, 메세지 내용 보이게
            holder.imgChat.visibility = View.VISIBLE
            holder.tvChatOtherName.visibility = View.VISIBLE
            holder.tvChatOtherMsg.visibility = View.VISIBLE

            holder.imgChat.setImageResource(R.drawable.kim_contact)
            holder.tvChatOtherName.text = chatList[position].name
            holder.tvChatOtherMsg.text = chatList[position].msg

            //내 메세지 안보이게
            holder.tvChatMyMsg.visibility = View.INVISIBLE
        }

    }

    override fun getItemCount(): Int {
        return chatList.size
    }


}