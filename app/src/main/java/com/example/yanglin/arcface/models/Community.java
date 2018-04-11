package com.example.yanglin.arcface.models;

/**
 * Created by yanglin on 18-4-2.
 */

public class Community {
    int image;
    String title;
    String content;
    String time;
    String tag;
    String category;

    public Community(int image, String title, String content, String time, String tag, String category) {
        this.image = image;
        this.title = title;
        this.time = time;
        this.content = content;
        this.tag = tag;
        this.category = category;
    }
    public int getImage() {
        return this.image;
    }
    public void setImage(int image) {
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

    public String getTag() {
        return this.tag;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getCategory() {
        return this.category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
}
