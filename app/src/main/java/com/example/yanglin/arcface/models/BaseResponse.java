package com.example.yanglin.arcface.models;

/**
 * Created by yanglin on 18-4-11.
 */

public class BaseResponse {

    /**
     * code : 1
     * msg : 成功
     */

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
