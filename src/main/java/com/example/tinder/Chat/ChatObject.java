package com.example.tinder.Chat;

public class ChatObject {
    private String message;
    private Boolean myChat;

    public ChatObject(String message, Boolean myChat) {
        this.message = message;
        this.myChat = myChat;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getMyChat() {
        return myChat;
    }

    public void setMyChat(Boolean myChat) {
        this.myChat = myChat;
    }
}
