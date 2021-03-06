package com.example.tinder.Matches;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.tinder.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MatchesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter matchesAdapter;
    private RecyclerView.LayoutManager matchesLayoutManager;

    private String currentUserId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        matchesLayoutManager = new LinearLayoutManager(MatchesActivity.this);
        recyclerView.setLayoutManager(matchesLayoutManager);
        matchesAdapter = new MatchesAdapter(getDataSetMatches(), MatchesActivity.this);
        recyclerView.setAdapter(matchesAdapter);

        getUserMatchId();

    }

    private void getUserMatchId() {
        DatabaseReference matchDb = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserId).child("connections").child("matches");
        matchDb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot match: snapshot.getChildren()){
                        FetchMatchesInfo(match.getKey());
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void FetchMatchesInfo(String key) {
        DatabaseReference myDb = FirebaseDatabase.getInstance().getReference();
        myDb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child("Users").child(key).exists()){
                    String userId = snapshot.child("Users").child(key).getKey();
                    String name = "";
                    String profileImageUrl = "";
                    String lastestChat = "...";
                    String chatId = "";

                    if(snapshot.child("Users").child(key).child("connections").child("matches").child(currentUserId).child("chatId").exists()){
                        chatId = snapshot.child("Users").child(key).child("connections").child("matches").child(currentUserId).child("chatId").getValue().toString();
                    }
                    if(snapshot.child("Chat").child(chatId).exists()){
                        DataSnapshot temp = null;
                        for (DataSnapshot userSnapshot: snapshot.child("Chat").child(chatId).getChildren()) {
                            temp = userSnapshot;
                        }
                        lastestChat = temp.child("message").getValue().toString();
                    }

                    if(snapshot.child("Users").child(key).child("name").getValue()!=null){
                        name = snapshot.child("Users").child(key).child("name").getValue().toString();
                    }
                    if(snapshot.child("Users").child(key).child("profileImageUrl").getValue()!=null){
                        profileImageUrl = snapshot.child("Users").child(key).child("profileImageUrl").getValue().toString();
                    }

                    MatchesObject mo = new MatchesObject(userId, name, profileImageUrl, lastestChat);
                    resultMatches.add(mo);
                    matchesAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
//        DatabaseReference userDb = FirebaseDatabase.getInstance().getReference().child("Users").child(key);
//        DatabaseReference chatDb = FirebaseDatabase.getInstance().getReference().child("Chat");
//        userDb.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(snapshot.exists()){
//                    String userId = snapshot.getKey();
//                    String name = "";
//                    String profileImageUrl = "";
//                    String lastestChats = "";
//                    //get lastest chat
//                    userDb.child("connections").child("matches").child(currentUserId).child("chatId").addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            if(snapshot.exists()){
//                                chatId = snapshot.getValue().toString();
//                                Log.i("chat1", chatId);
//                            }
//                        }
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//                        }
//                    });
//
//                    chatDb = chatDb.child(chatId);
    //                    chatDb.orderByKey().limitToLast(1).addChildEventListener(new ChildEventListener() {
//                        @Override
//                        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                            if(snapshot.child("message").getValue()!= null){
//                                lastestChat = snapshot.child("message").getValue().toString();
//                                Log.i("chat2", lastestChat);
//                            }
//                        }
//                        @Override
//                        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//                        }
//                        @Override
//                        public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//
//                        }
//                        @Override
//                        public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//                        }
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//
//                        }
//                    });
//                    //end get lastest chat
//
//                    if(snapshot.child("name").getValue()!=null){
//                        name = snapshot.child("name").getValue().toString();
//                    }
//                    if(snapshot.child("profileImageUrl").getValue()!=null){
//                        profileImageUrl = snapshot.child("profileImageUrl").getValue().toString();
//                    }
//
//                    MatchesObject mo = new MatchesObject(userId, name, profileImageUrl, lastestChat);
//                    resultMatches.add(mo);
//                    matchesAdapter.notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        startActivity(getIntent());
    }

    private ArrayList<MatchesObject> resultMatches = new ArrayList<MatchesObject>();
    private List<MatchesObject> getDataSetMatches() {
        return  resultMatches;
    }
}