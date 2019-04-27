package com.example.yanglin.arcface.controllers;

import com.example.yanglin.arcface.utils.OkhttpService;

import okhttp3.OkHttpClient;

/**
 * Created by yanglin on 18-4-11.
 */

public class BugCtrl extends OkhttpService {
    private String baseUrl = "/resident";
    public void applyBug(OkHttpClient okHttpClient, String data, OnResponseListener listener) {
        String url = baseUrl + "/bug";
        this.doPost(okHttpClient, url, data, listener);
    }
}
