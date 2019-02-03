package com.example.yanglin.arcface.controllers;

import com.example.yanglin.arcface.utils.OkhttpService;

import okhttp3.OkHttpClient;

/**
 * Created by yanglin on 18-4-10.
 */

public class UserCtrl extends OkhttpService{
    public void applyVisitor(OkHttpClient okHttpClient, String data, OnResponseListener listener) {
        String url = "/visitor";
        this.doPost(okHttpClient, url, data, listener);
    }

    public void openDoor(OkHttpClient okHttpClient, String data, OnResponseListener listener) {
        String url = "/open/door";
        this.doPost(okHttpClient, url, data, listener);
    }
}
