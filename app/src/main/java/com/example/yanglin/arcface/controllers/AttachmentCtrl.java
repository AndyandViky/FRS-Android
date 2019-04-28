package com.example.yanglin.arcface.controllers;

import com.example.yanglin.arcface.utils.OkhttpService;

import java.io.File;

/**
 * Created by yanglin on 18-4-11.
 */

public class AttachmentCtrl extends OkhttpService{
    public String uploadImage(File file, OnResponseListener listener) {
        String url = "/image";
        String result = this.upLoad(url, file, listener);
        return result;
    }
}
