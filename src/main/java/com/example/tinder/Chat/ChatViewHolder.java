package com.example.tinder.Chat;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.example.tinder.R;

public class ChatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView tvMessage;
    public LinearLayout container;
    public ChatViewHolder(View itemView){
        super(itemView);

        tvMessage = itemView.findViewById(R.id.tvMessage);
        container = itemView.findViewById(R.id.container);
    }

    @Override
    public void onClick(View v) {
    }
}
