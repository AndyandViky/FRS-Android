package com.example.yanglin.arcface.models;

/**
 * Created by yanglin on 18-3-20.
 */

public class MyList {

    String title;
    String content;

    public MyList(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitlt() {
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
}
