package com.example.tinder.Chat;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tinder.R;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatViewHolder> {
    private List<ChatObject> chatList;
    private Context context;

    public ChatAdapter(List<ChatObject> matchesList, Context context) {
        this.chatList = matchesList;
        this.context = context;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, null, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutView.setLayoutParams(lp);
        ChatViewHolder rcv = new ChatViewHolder((layoutView));

        return rcv;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        holder.tvMessage.setText(chatList.get(position).getMessage());
        Log.i("chat","rigt?");
        if(chatList.get(position).getMyChat()){
            holder.tvMessage.setGravity(Gravity.END);
            holder.tvMessage.setTextColor(Color.parseColor("#ffffff"));
            holder.container.setBackgroundColor(Color.parseColor("#358eb8"));
        }else {
            holder.tvMessage.setGravity(Gravity.START);
            holder.tvMessage.setTextColor(Color.parseColor("#ffffff"));
            holder.container.setBackgroundColor(Color.parseColor("#645a56"));
        }
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }
}