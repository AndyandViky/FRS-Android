package com.example.yanglin.arcface.models;

/**
 * Created by yanglin on 18-4-11.
 */

public class RegisterVisitor {

    /**
     * name : 杨林
     * gender : 0
     * age : 20
     * phone : 17805850733
     * deadline : 1
     * belong : 2
     * reason : 123
     * facePath : 123
     * adress_id : 1
     */

    private String name;
    private int gender;
    private int age;
    private String phone;
    private int deadline;
    private int belong;
    private String reason;
    private String facePath;
    private int adress_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getDeadline() {
        return deadline;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    public int getBelong() {
        return belong;
    }

    public void setBelong(int belong) {
        this.belong = belong;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getFacePath() {
        return facePath;
    }

    public void setFacePath(String facePath) {
        this.facePath = facePath;
    }

    public int getAdress_id() {
        return adress_id;
    }

    public void setAdress_id(int adress_id) {
        this.adress_id = adress_id;
    }
}
