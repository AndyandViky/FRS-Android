package com.example.yanglin.arcface.controllers;

import com.example.yanglin.arcface.utils.OkhttpService;

import okhttp3.OkHttpClient;

/**
 * Created by yanglin on 18-4-10.
 */

public class UserCtrl extends OkhttpService{
    private String baseUrl = "/resident";
    public void applyVisitor(OkHttpClient okHttpClient, String data, OnResponseListener listener) {
        String url = baseUrl + "/visitor";
        this.doPost(okHttpClient, url, data, listener);
    }

    public void approveVisite(OkHttpClient okHttpClient, String data, OnResponseListener listener) {
        String url = baseUrl + "/visite";
        this.doPut(okHttpClient, url, data, listener);
    }

    public void openDoor(OkHttpClient okHttpClient, String data, OnResponseListener listener) {
        String url = baseUrl + "/open/door";
        this.doPost(okHttpClient, url, data, listener);
    }

    public void getGateRecord(OkHttpClient okHttpClient, int pageNo, int pageSize, OnResponseListener listener) {
        String url = baseUrl + "/records?pageNo="+pageNo+"&pageSize="+pageSize;
        this.doGet(okHttpClient, url, listener);
    }

    public void getVisitors(OkHttpClient okHttpClient, int pageNo, int pageSize, int status, OnResponseListener listener) {
        String url = baseUrl + "/visitors?pageNo="+pageNo+"&pageSize="+pageSize;
        if (status != -1) {
            url = url + "&status=" + status;
        }
        this.doGet(okHttpClient, url, listener);
    }

    public void getArticles(OkHttpClient okHttpClient, int pageNo, int pageSize, String category, OnResponseListener listener) {
        String url = baseUrl + "/articles?pageNo=" + pageNo + "&pageSize=" + pageSize + "&category=" + category;
        this.doGet(okHttpClient, url, listener);
    }

    public void getFaceImages(OkHttpClient okHttpClient, int status, OnResponseListener listener) {
        String url = "/face";
        if(status != -1) {
            url = url + "?isActive=" + status;
        }
        this.doGet(okHttpClient, url, listener);
    }

    public void deleteFaceImage(OkHttpClient okHttpClient, String data, OnResponseListener listener) {
        String url = "/face";
        this.doDelete(okHttpClient, url, data, listener);
    }

    public void activeFaceImage(OkHttpClient okHttpClient, String data, OnResponseListener listener) {
        String url = "/face/active";
        this.doPut(okHttpClient, url, data, listener);
    }

    public void addNewBehavior(OkHttpClient okHttpClient, String data, OnResponseListener listener) {
        String url = baseUrl + "/behavior";
        this.doPost(okHttpClient, url, data, listener);
    }
}
