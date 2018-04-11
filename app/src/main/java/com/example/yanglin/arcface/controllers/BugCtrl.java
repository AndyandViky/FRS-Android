package com.example.yanglin.arcface.controllers;

import com.example.yanglin.arcface.utils.OkhttpService;

import okhttp3.OkHttpClient;

/**
 * Created by yanglin on 18-4-11.
 */

public class BugCtrl extends OkhttpService {
    public void applyBug(OkHttpClient okHttpClient, String data, OnResponseListener listener) {
        String url = "/bug";
        this.doPost(okHttpClient, url, data, listener);
    }
}
