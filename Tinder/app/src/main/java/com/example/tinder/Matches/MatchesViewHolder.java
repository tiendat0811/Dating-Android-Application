package com.example.tinder.Matches;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tinder.R;

public class MatchesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView matchesId, matchesName;
    public ImageView matchesImage;
    public MatchesViewHolder(View itemView){
        super(itemView);
        itemView.setOnClickListener(this);
        matchesId = itemView.findViewById(R.id.matchesId);
        matchesName = itemView.findViewById(R.id.matchesName);
        matchesImage = itemView.findViewById(R.id.machesImage);
    }

    @Override
    public void onClick(View v) {

    }
}
