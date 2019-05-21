package com.example.yanglin.arcface.models;

public class Password {
    String oldPwd;
    String newPwd;
    String confirmPwd;

    public String getOldP() {return this.oldPwd;}

    public void setOldP(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getNewP() {
        return newPwd;
    }

    public void setNewP(String newPwd) {
        this.newPwd = newPwd;
    }

    public String getConP() {
        return confirmPwd;
    }

    public void setConP(String confirmPwd) {
        this.confirmPwd = confirmPwd;
    }

}
