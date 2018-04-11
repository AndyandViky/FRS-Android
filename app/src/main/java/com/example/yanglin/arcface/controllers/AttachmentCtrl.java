package com.example.yanglin.arcface.controllers;

import com.example.yanglin.arcface.utils.OkhttpService;

/**
 * Created by yanglin on 18-4-11.
 */

public class AttachmentCtrl extends OkhttpService{
    public String uploadImage(String path, OnResponseListener listener) {
        String url = "/image";
        String result = this.upLoad(url, path, listener);
        return result;
    }
}
