package com.example.yanglin.arcface.utils;

import android.support.v7.app.AppCompatActivity;

import com.example.yanglin.arcface.controllers.UserCtrl;
import com.example.yanglin.arcface.models.Base2Response;
import com.example.yanglin.arcface.models.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import okhttp3.OkHttpClient;

public class Cache extends AppCompatActivity {

    String filename = "user.txt";
    String baseDir = "/data/user/0/com.example.yanglin.arcface/files";

    public void setUser(String text, File file) {
        createPrivateFile(filename, text, file);
    }

    public void deleteUser() {
        deletePrivateFile(filename);
    }

    public User getUser(File parentFile) {
        File file = new File(parentFile, filename);
        //如果文件存在，则读取
        if(file.exists()) {
            try {

                FileInputStream fin = new FileInputStream(file);
                //把字节流转化为字符流
                BufferedReader buffer = new BufferedReader(new InputStreamReader(fin));
                //读取文件中的用户名和密码
                String text = buffer.readLine();

                Gson gson = new Gson();
                java.lang.reflect.Type type = new TypeToken<User>() {}.getType();
                User user = gson.fromJson(text, type);
                return user;

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }

    public String getToken() {
        User user = getUser();
        if(user == null) {
            return "";
        } else {
            String auth = "Bearer " + user.getData().getToken();
            return auth;
        }
    }

    public void refreshToken(final User user) {
        UserCtrl userCtrl = new UserCtrl();
        final OkHttpClient okHttpClient = new OkHttpClient();
        userCtrl.getToken(okHttpClient, user.getData().getUser().getId(), new OkhttpService.OnResponseListener() {
            @Override
            public void onSuccess(String result) {
                java.lang.reflect.Type type = new TypeToken<Base2Response>() {}.getType();
                final Base2Response base2Response = new Gson().fromJson(result, type);
                if (base2Response.getCode() != 1) {
                    return;
                }
                user.getData().setToken(base2Response.getData());
                File file = new File(baseDir);
                setUser(new Gson().toJson(user), file);
            }

            @Override
            public void onFailure(IOException error) {
            }
        });
    }

    public User getUser() {
        File parentFile = new File(baseDir);
        File file = new File(parentFile, filename);
        //如果文件存在，则读取
        if(file.exists()) {
            try {

                FileInputStream fin = new FileInputStream(file);
                //把字节流转化为字符流
                BufferedReader buffer = new BufferedReader(new InputStreamReader(fin));
                //读取文件中的用户名和密码
                String text = buffer.readLine();

                Gson gson = new Gson();
                java.lang.reflect.Type type = new TypeToken<User>() {}.getType();
                User user = gson.fromJson(text, type);
                return user;

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }



    private void deletePrivateFile(String filename) {
        try {
            File parentFile = new File(baseDir);
            // 找到文件所在的路径并删除该文件
            File file = new File(parentFile, filename);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createPrivateFile(String name, String text, File parentFile) //向内部存储写文件
    {
        File file = new File(parentFile, name);
        try {

            FileOutputStream fos = new FileOutputStream(file);
            //写入用户名和密码，以name##passwd的格式写入
            fos.write(text.getBytes());
            //关闭输出流
            fos.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
