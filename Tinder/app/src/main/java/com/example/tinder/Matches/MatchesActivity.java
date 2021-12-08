package com.example.tinder.Matches;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.tinder.R;

import java.util.ArrayList;
import java.util.List;

public class MatchesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter matchesAdapter;
    private RecyclerView.LayoutManager matchesLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        matchesLayoutManager = new LinearLayoutManager(MatchesActivity.this);
        recyclerView.setLayoutManager(matchesLayoutManager);
        matchesAdapter = new MatchesAdapter(getDataSetMatches(), MatchesActivity.this);
        recyclerView.setAdapter(matchesAdapter);

        MatchesObject mob = new MatchesObject("asd");
        resultMatches.add(mob);
        matchesAdapter.notifyDataSetChanged();
    }

    private ArrayList<MatchesObject> resultMatches = new ArrayList<MatchesObject>();
    private List<MatchesObject> getDataSetMatches() {
        return  resultMatches;
    }
}