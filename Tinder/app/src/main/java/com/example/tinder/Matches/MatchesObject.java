package com.example.tinder.Matches;

public class MatchesObject {
    private String userId, name, profileImageUrl, lastestChat;

    public MatchesObject(String userId, String name, String profileImageUrl, String lastestChat) {
        this.userId = userId;
        this.name = name;
        this.profileImageUrl = profileImageUrl;
        this.lastestChat = lastestChat;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getLastestChat() {
        return lastestChat;
    }

    public void setLastestChat(String lastestChat) {
        this.lastestChat = lastestChat;
    }
}
