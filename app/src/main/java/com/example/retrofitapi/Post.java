package com.example.retrofitapi;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Post {

    @SerializedName("userId")
    private int userId;
    @SerializedName("id")
    private Integer id;
    @SerializedName("title")
    private String title;
    @SerializedName("body")
    private String text;

    public Post(int userId, String title, String text) {
        this.userId = userId;
        this.title = title;
        this.text = text;
    }

    @NonNull
    @Override
    public String toString() {
        return "\nuserId:"+userId+"\nid:"+id+"\ntitle:"+title+"\ntext:"+text;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Integer getId() {
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
