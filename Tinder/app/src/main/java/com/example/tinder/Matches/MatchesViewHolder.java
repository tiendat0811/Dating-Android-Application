package com.example.tinder.Matches;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tinder.Chat.ChatActivity;
import com.example.tinder.R;

public class MatchesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView matchesId, matchesName, lastestChat;
    public ImageView matchesImage;
    public MatchesViewHolder(View itemView){
        super(itemView);
        itemView.setOnClickListener(this);
        lastestChat = itemView.findViewById(R.id.lastestChat);
        matchesId = itemView.findViewById(R.id.matchesId);
        matchesName = itemView.findViewById(R.id.matchesName);
        matchesImage = itemView.findViewById(R.id.machesImage);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), ChatActivity.class);
        Bundle bundle  = new Bundle();
        bundle.putString("matchId", matchesId.getText().toString());
        intent.putExtras(bundle);
        v.getContext().startActivity(intent);
    }
}
