package com.example.retrofitapi;

import com.google.gson.annotations.SerializedName;

public class Post {

    @SerializedName("UserId")
    private int userId;
    @SerializedName("Id")
    private int id;
    @SerializedName("Title")
    private String title;
    @SerializedName("Body")
    private String text;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
