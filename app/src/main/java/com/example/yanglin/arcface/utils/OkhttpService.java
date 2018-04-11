package com.example.yanglin.arcface.utils;

import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by yanglin on 18-4-10.
 */

public class OkhttpService {

    public interface OnResponseListener{
        void onSuccess(String result);
        void onFailure(IOException error);
    }



    MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final String TAG = "1";
    private String basePath = "http://192.168.1.115:8000";
    private String auth = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzZWxmSWQiOjIsImlhdCI6MTUyMzM3NDU3MSwiZXhwIjoxNTI0MjM4NTcxfQ.XaM3w7L-iaAxBrZwDl8t5i_YM1DBs8RiIar-QIWZkKk";
    protected String doGet(OkHttpClient okHttpClient, String url, final OnResponseListener listener) {
        url = basePath+url;
        Request request = new Request.Builder()
                .header("authorization", auth)
                .url(url)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                listener.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                listener.onSuccess(response.body().string());
            }
        });
        return null;
    }

    protected String doPost(OkHttpClient okHttpClient, String url, String data, final OnResponseListener listener) {
        url = basePath+url;
        RequestBody body = RequestBody.create(JSON, data);

        Request request = new Request.Builder()
                .header("authorization", auth)
                .url(url)
                .post(body)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                listener.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                listener.onSuccess(response.body().string());
            }
        });
        return null;
    }

    protected String doPut(OkHttpClient okHttpClient, String url, String data, final OnResponseListener listener) {
        url = basePath+url;
        RequestBody body = RequestBody.create(JSON, data);

        Request request = new Request.Builder()
                .header("authorization", auth)
                .url(url)
                .put(body)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                listener.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                listener.onSuccess(response.body().string());
            }
        });
        return null;
    }

    protected String doDelete(OkHttpClient okHttpClient, String url, String data, final OnResponseListener listener) {
        url = basePath+url;
        RequestBody body = RequestBody.create(JSON, data);

        Request request = new Request.Builder()
                .header("authorization", auth)
                .url(url)
                .delete(body)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                listener.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                listener.onSuccess(response.body().string());
            }
        });
        return null;
    }

    protected String upLoad(String url, String path, final OnResponseListener listener) {
        url = basePath+url;
        File file = new File("", path);
        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", path, fileBody)
                .build();
        Request request = new Request.Builder()
                .header("authorization", auth)
                .url(url)
                .post(requestBody)
                .build();


        final okhttp3.OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        OkHttpClient okHttpClient  = httpBuilder
                //设置超时
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                listener.onFailure(e);
            }


            @Override
            public void onResponse(Call call, Response response) throws IOException {
                listener.onSuccess(response.body().string());
            }
        });
        return null;
    }
}
