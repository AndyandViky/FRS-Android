package com.example.yanglin.arcface.models;

/**
 * Created by yanglin on 18-3-23.
 */

public class Visitor {
    String time;
    String name;
    String reason;
    String phone;
    int type;

    public Visitor(String name, String time, String reason, String phone, int type) {
        this.name = name;
        this.time = time;
        this.reason = reason;
        this.phone = phone;
        this.type = type;
    }

    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getReason() {
        return this.reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
}
