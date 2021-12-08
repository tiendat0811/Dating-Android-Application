package com.example.tinder.Setup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tinder.R;

import java.util.List;

public class arrayAdapter extends ArrayAdapter<Cards> {
    Context context;

    public arrayAdapter (Context context, int resourceId, List<Cards> items){
        super(context, resourceId, items);
    }

    public View getView(int position, View convertView, ViewGroup parent) { ;
        Cards card_item = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }

        TextView userName = (TextView) convertView.findViewById(R.id.userName);
        ImageView userImage = (ImageView) convertView.findViewById(R.id.userImage);

        userName.setText(card_item.getName());
        Glide.with(getContext()).load(card_item.getProfileImageUrl()).into(userImage);

        return convertView;
    }
}
