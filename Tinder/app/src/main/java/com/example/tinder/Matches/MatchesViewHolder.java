package com.example.tinder.Matches;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tinder.R;

public class MatchesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView matchesId;
    public MatchesViewHolder(View itemView){
        super(itemView);
        itemView.setOnClickListener(this);
        matchesId = itemView.findViewById(R.id.matchesId);
    }

    @Override
    public void onClick(View v) {

    }
}
