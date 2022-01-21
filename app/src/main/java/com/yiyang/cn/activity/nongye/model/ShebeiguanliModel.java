package com.yiyang.cn.activity.nongye.model;

public class ShebeiguanliModel {


    private String name;
    private String time;
    private int srcId;
    private boolean isKai;

    public ShebeiguanliModel(String name, String time, int srcId) {
        this.name = name;
        this.time = time;
        this.srcId = srcId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getSrcId() {
        return srcId;
    }

    public void setSrcId(int srcId) {
        this.srcId = srcId;
    }

    public boolean isKai() {
        return isKai;
    }

    public void setKai(boolean kai) {
        isKai = kai;
    }
}
