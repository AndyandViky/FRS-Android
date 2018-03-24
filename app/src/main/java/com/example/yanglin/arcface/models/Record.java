package com.example.yanglin.arcface.models;

/**
 * Created by yanglin on 18-3-21.
 */

public class Record {
    int type;
    int peopleCount;
    String time;

    public Record(int type, int peopleCount, String time) {
        this.type = type;
        this.peopleCount = peopleCount;
        this.time = time;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }

    public int getPeopleCount() {
        return this.peopleCount;
    }
    public void setPeopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
    }

    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }


}
