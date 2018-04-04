package com.example.yanglin.arcface.models;

/**
 * Created by yanglin on 18-4-2.
 */

public class Info {
    String image;
    String title;
    String content;
    String time;

    public Info(String image, String title, String content, String time) {
        this.image = image;
        this.title = title;
        this.time = time;
        this.content = content;
    }
    public String getImage() {
        return this.image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
}
