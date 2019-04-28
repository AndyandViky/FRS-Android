package com.example.yanglin.arcface.controllers;

import com.example.yanglin.arcface.utils.OkhttpService;

import okhttp3.OkHttpClient;

/**
 * Created by yanglin on 18-4-10.
 */

public class NoticeCtrl extends OkhttpService{
    private String baseUrl = "/resident";

    public void getNotice(OkHttpClient okHttpClient, int pageNo, int pageSize, int status, OnResponseListener listener) {
        String url = baseUrl + "/notices?pageNo="+pageNo+"&pageSize="+pageSize;
        if (status != -1) {
            url = url + "&status=" + status;
        }
        this.doGet(okHttpClient, url, listener);
    }

    public void setReaded(OkHttpClient okHttpClient, String data, OnResponseListener listener) {
        String url = baseUrl + "/notice";
        this.doPut(okHttpClient, url, data, listener);
    }

    public void getUnReadCount(OkHttpClient okHttpClient, OnResponseListener listener) {
        String url = baseUrl + "/notice/unread";
        this.doGet(okHttpClient, url, listener);
    }
}
