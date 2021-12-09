package com.example.tinder.Chat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tinder.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter chatAdapter;
    private RecyclerView.LayoutManager chatLayoutManager;

    private EditText edtMessage;
    private Button btnSend;
    private String currentUserId, matchId, chatId;

    DatabaseReference userDb, chatDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        matchId = getIntent().getExtras().getString("matchId");
        currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        userDb = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserId).child("connections").child("matches").child(matchId).child("chatId");
        chatDb = FirebaseDatabase.getInstance().getReference().child("Chat");
        getChatId();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(false);
        chatLayoutManager = new LinearLayoutManager(ChatActivity.this);
        recyclerView.setLayoutManager(chatLayoutManager);
        chatAdapter = new ChatAdapter(getDataSetChat(), ChatActivity.this);
        recyclerView.setAdapter(chatAdapter);

        edtMessage = findViewById(R.id.edtMessage);
//        btnSend = findViewById(R.id.btnSend);
//
//        btnSend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String message = edtMessage.getText().toString();
//
//                if(!message.isEmpty()){
//                    DatabaseReference newMessDb = chatDb.push();
//
//                    Map newMesssage = new HashMap();
//                    newMesssage.put("sender", currentUserId);
//                    newMesssage.put("message", message);
//
//                    newMessDb.setValue(newMesssage);
//                }
//                edtMessage.setText("");
//            }
//        });
    }
    public void sendMessage(View view) {
        String message = edtMessage.getText().toString();

        if(!message.isEmpty()){
            DatabaseReference newMessDb = chatDb.push();

            Map newMesssage = new HashMap();
            newMesssage.put("sender", currentUserId);
            newMesssage.put("message", message);

            newMessDb.setValue(newMesssage);
        }
        edtMessage.setText("");
    }

    private void getChatId(){
        userDb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    chatId = snapshot.getValue().toString();
                    chatDb = chatDb.child(chatId);

                    getChatMessages();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getChatMessages() {
        chatDb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if(snapshot.exists()){
                    String message = null;
                    String sender = null;

                    if(snapshot.child("message").getValue()!=null){
                        message = snapshot.child("message").getValue().toString();
                    }
                    if(snapshot.child("sender").getValue()!=null){
                        sender = snapshot.child("sender").getValue().toString();
                    }

                    if(message!=null && sender!=null){
                        Boolean myChat = false;
                        if(sender.equals(currentUserId)){
                            myChat = true;
                        }

                        ChatObject newMessage = new ChatObject(message, myChat);
                        resultChat.add(newMessage);
                        chatAdapter.notifyDataSetChanged();
                    }
                }
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
            }
            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private ArrayList<ChatObject> resultChat = new ArrayList<ChatObject>();
    private List<ChatObject> getDataSetChat() {
        return  resultChat;
    }
}